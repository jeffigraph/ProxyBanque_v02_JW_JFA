package com.proxibanque.views;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proxibanque.dao.DaoException;
import com.proxibanque.dao.JdbcDaoException;
import com.proxibanque.model.Client;
import com.proxibanque.model.Compte;
import com.proxibanque.service.ServiceEngine;

/**
 * Gere la gestion d'un virement ente deux comptes 
 * fournis par l'utilisateur en parametres de la session
 * Affiche le resultats des operations
 * 
 * Servlet implementation class ViewGestionVirementClientServlet
 */
@WebServlet("/GestionVirement2")
public class ViewGestionVirementServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServiceEngine srvcEng;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewGestionVirementServlet2() {
		super();
		try {
			srvcEng = new ServiceEngine();
		} catch (JdbcDaoException ex) {
			System.out.println("ViewGestionVirementServlet : " + ex.getMessage());
		}
	}

	/**
	 * Renvoie vers la page de saisie des paramtres du virement : "ViewVirementChoixComptesClients.jsp"
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			
			int idConseiller = (Integer) session.getAttribute("idCons");

			if (idConseiller != 0) {
				
				try {
				
				// Recupere deux lists de clients depuis la DB
				List<Client> clientsDebiteurList = srvcEng.getServiceGestionClient().getAllClientsByConseillerId(idConseiller);
				List<Client> clientsCrediteurList = srvcEng.getServiceGestionClient().getAllClients();
				
				request.setAttribute("clientsDebiteurList", clientsDebiteurList);				
				request.setAttribute("clientsCrediteurList", clientsCrediteurList);
				
//				System.out.println(clientsDebiteurList);
//				System.out.println(clientsCrediteurList);
//				if (clientsCrediteurList.get(1).hasCompteCourant()) {
//				System.out.println(clientsCrediteurList.get(1).getCompteCourant().getNumeroCompte());
////				} else System.out.println("pas de compte");
				
				
				request.setAttribute("virementOK", false);
				request.setAttribute("displayOK", true);
				request.setAttribute("msg", "S&eacute;lectionnez les comptes clients");
				request.getRequestDispatcher("ViewVirementListeComptes.jsp").forward(request, response);
				
				} catch (DaoException e) {
					request.setAttribute("displayOK", false);
					request.setAttribute("msg", "Probleme de requete SGBD, relogin");
					request.getRequestDispatcher("Autentification.jsp").forward(request, response);
				}
			}
			
		} else {
			request.setAttribute("displayOK", false);
			request.setAttribute("msg", "Pas de session utilisateur, vous devez vous identifier");
			request.getRequestDispatcher("Autentification.jsp").forward(request, response);
		}

	}

	/**
	 * Recupere les parametres d'execution du virement fournis en parametres de la requete
	 * Verifie la possibilite d'effectuer le virement (existance des comptes, montant de leurs soldes)
	 * et realise le virement
	 * 
	 * Renvoie le resultat vers la page d'affichage : "ViewVirementChoixComptesClients.jsp"
	 * 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		
		if (session != null && session.getAttribute("user") != null) {
			
			// recupere la selection de IHM
			String compteDebit = request.getParameter("numerocomptedebit");
			long idClientDebit = Long.parseLong(request.getParameter("idclientdebiteur"));
			String compteCredit = request.getParameter("numerocomptecredit");
			long idClientCredit = Long.parseLong(request.getParameter("idclientcrediteur"));
			double montant = Double.parseDouble(request.getParameter("montant"));
				

			try {
				
				// Recharger les clients depuis DB
				Client debiteur = srvcEng.getServiceGestionClient().getClient(idClientDebit);
				Client crediteur = srvcEng.getServiceGestionClient().getClient(idClientCredit);
				
				Compte compteDebiteur = debiteur.getCompteByNum(compteDebit);
				Compte compteCrediteur = crediteur.getCompteByNum(compteDebit);
				
				// System.out.println("debiteur: " + debiteur.getSolde() + " crediteur:" + crediteur.getSolde());

				if ( null != compteDebiteur && null != compteCrediteur && srvcEng.getServiceVirement().faireVirement(debiteur, compteDebiteur, crediteur, compteCrediteur, montant)) {

					compteDebiteur = srvcEng.getServiceVirement().getCompteExist(compteDebit);
					compteCrediteur = srvcEng.getServiceVirement().getCompteExist(compteCredit);

					request.setAttribute("debiteur", compteDebiteur);
					request.setAttribute("crediteur", compteCrediteur);

					request.setAttribute("virementOK", true);
					request.setAttribute("displayOK", true);
					request.setAttribute("msg", "Virement reussi");

				} else {
					request.setAttribute("displayOK", false);
					request.setAttribute("msg", "Montant insuffisant du debiteur, impossible d'effectuer virement");
				}

				request.getRequestDispatcher("ViewVirementListeComptes.jsp").forward(request, response);

				
			} catch (DaoException e) {
				System.out.println("ViewGestionVirementServlet : doGet" + e.getMessage());
				request.setAttribute("displayOK", false);
				request.setAttribute("msg", "Impossible de trouver les comptes dans la base");
				request.getRequestDispatcher("ViewErreur.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("displayOK", false);
			request.setAttribute("msg", "Pas de session utilisateur, vous devez vous identifier");
			request.getRequestDispatcher("Autentification.jsp").forward(request, response);
		}

	}

}
