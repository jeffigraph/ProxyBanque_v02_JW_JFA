package com.proxibanque.views;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proxibanque.dao.DaoException;
import com.proxibanque.dao.JdbcDaoException;
import com.proxibanque.model.Compte;
import com.proxibanque.service.ServiceEngine;

/**
 * Gere la gestion d'un virement ente deux comptes 
 * fournis par l'utilisateur en parametres de la session
 * Affiche le resultats des operations
 * 
 * Servlet implementation class ViewGestionVirementClientServlet
 */
@WebServlet("/GestionVirement")
public class ViewGestionVirementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServiceEngine srvcEng;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewGestionVirementServlet() {
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

			request.setAttribute("virementOK", false);
			request.setAttribute("displayOK", true);
			request.setAttribute("msg", "S&eacute;lectionnez les comptes clients");
			request.getRequestDispatcher("ViewVirementChoixComptesClients.jsp").forward(request, response);
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

			String compteDebit = request.getParameter("numerocomptedebit");
			String compteCredit = request.getParameter("numerocomptecredit");
			double montant = Double.parseDouble(request.getParameter("montant"));

			try {
				Compte debiteur = srvcEng.getServiceVirement().getCompteExist(compteDebit);
				Compte crediteur = srvcEng.getServiceVirement().getCompteExist(compteCredit);

				if (null != debiteur && null != crediteur) {

					System.out.println("debiteur: " + debiteur.getSolde() + " crediteur:" + crediteur.getSolde());

					if (srvcEng.getServiceVirement().faireVirement(debiteur, crediteur, montant)) {

						debiteur = srvcEng.getServiceVirement().getCompteExist(compteDebit);
						crediteur = srvcEng.getServiceVirement().getCompteExist(compteCredit);

						request.setAttribute("debiteur", debiteur);
						request.setAttribute("crediteur", crediteur);

						request.setAttribute("virementOK", true);
						request.setAttribute("displayOK", true);
						request.setAttribute("msg", "Virement reussi");

					} else {
						request.setAttribute("displayOK", false);
						request.setAttribute("msg", "Montant insuffisant du debiteur, impossible d'effectuer virement");
					}

					request.getRequestDispatcher("ViewVirementChoixComptesClients.jsp").forward(request, response);

				} else {
					request.setAttribute("displayOK", false);
					request.setAttribute("msg", "Impossible de trouver les comptes dans la base");
					request.getRequestDispatcher("ViewVirementChoixComptesClients.jsp").forward(request, response);
				}

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
