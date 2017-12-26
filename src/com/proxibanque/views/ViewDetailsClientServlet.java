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
import com.proxibanque.model.Client;
import com.proxibanque.service.ServiceEngine;

/**
 * Affichage du details des informations du client 
 * et assure la mise a jour de ses informations
 * 
 * Servlet implementation class ViewCompteClient
 */
@WebServlet("/ViewDetailsClient")
public class ViewDetailsClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceEngine srvcEng;

	/**
	 * Chargement du gestionnaire de service de connexion a la base de donnees
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewDetailsClientServlet() {
		super();
		try {
			srvcEng = new ServiceEngine();
		} catch (JdbcDaoException ex) {
			System.out.println("ViewDetailsClientServlet : " + ex.getMessage());
		}
	}

	/**
	 * Recupere les informations du client dont l'id a ete recu en parametre de la requete
	 * et renvoie le client en parametre de la requte de la page  d'affichage : "ViewDetailsClient.jsp" 
	 * 
	 * Si aucun client n'est trouve, renvoie a une page d'erreur
	 * Si la session n'est pas valide, renvoie a la page d'autentification
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("user") != null) {
			int idClient = 0;
			idClient = Integer.parseInt(request.getParameter("idClient"));
			if (idClient != 0) {
				try {
					Client client = srvcEng.getServiceGestionClient().getClient(idClient);
					session.setAttribute("client", client);

					request.setAttribute("displayOK", true);
					request.setAttribute("msg", "Votre client : " + client.getPrenom() + " " + client.getNom());
					request.getRequestDispatcher("ViewDetailsClient.jsp").forward(request, response);

				} catch (DaoException ex) {
					System.out.println("ViewDetailsClientServlet : doGet" + ex.getMessage());
					request.setAttribute("displayOK", false);
					request.setAttribute("msg", "Impossible de trouver le client dans la base de donn&eacute;e");
					request.getRequestDispatcher("ViewErreur.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("displayOK", false);
				request.setAttribute("msg", "Pas de session utilisateur, vous devez vous identifier");
				request.getRequestDispatcher("Autentification.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("displayOK", false);
			request.setAttribute("msg", "Pas de session utilisateur, vous devez vous identifier");
			request.getRequestDispatcher("Autentification.jsp").forward(request, response);
		}
	}

	/**
	 * Gere le mise a jour des informations d'un client (hors gestion de ses comptes)
	 * 
	 * Recupere les nouvelles valeurs frounies par la methode POST
	 * 
	 * Met a jour le client enregistre dans la session
	 * 
	 * Met a jour la base de donnees
	 * 
	 * Affiche le resultat de l'operation avec les valeurs clients mises a jour
	 * 
	 * 
	 * Si la session n'est pas valide, renvoie a la page d'autentification
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			Client clientOriginel = (Client) session.getAttribute("client");

			String prenom, nom, adresse, strCodePostal, ville, telephone = "";

			prenom = request.getParameter("prenom");
			nom = request.getParameter("nom");
			adresse = request.getParameter("adresse");
			strCodePostal = request.getParameter("codepostal");
			ville = request.getParameter("ville");
			telephone = request.getParameter("telephone");

			int codePostal = 0;
			try {
				codePostal = Integer.parseInt(strCodePostal);
			} catch (NumberFormatException ex) {
				System.out.println("ViewDetailsClientServlet : doPost() : " + ex.getMessage());
				codePostal = clientOriginel.getCodePostal();
			}
			if (prenom.equals(""))
				prenom = clientOriginel.getPrenom();
			if (nom.equals(""))
				nom = clientOriginel.getNom();
			if (adresse.equals(""))
				adresse = clientOriginel.getAdresse();
			if (ville.equals(""))
				ville = clientOriginel.getVille();
			if (telephone.equals(""))
				telephone = clientOriginel.getTelephone();

			Client clientModifie = new Client(clientOriginel.getIdClient(), nom, prenom, adresse, ville, codePostal,
					telephone, clientOriginel.getCompteCourant(), clientOriginel.getCompteEpargne());

			try {
				srvcEng.getServiceGestionClient().updateClient(clientModifie);
				session.setAttribute("client", clientModifie);
				
				request.setAttribute("displayOK", true);
				request.setAttribute("msg",	"Les donn&eacute;es de votre client ont bien &eacute;t&eacute; mises &agrave; jour");
				request.getRequestDispatcher("ViewDetailsClient.jsp").forward(request, response);
			} catch (DaoException ex) {
				System.out.println("ViewDetailsClientServlet : doPost() : " + ex.getMessage());
				request.setAttribute("displayOK", false);
				request.setAttribute("msg", "Les donn&eacute;es de votre client : " + clientModifie.getPrenom() + " "
						+ clientModifie.getNom() + " n'ont pas pu &ecirc;tre mises &agrave; jour");
				request.getRequestDispatcher("ViewDetailsClient.jsp").forward(request, response);
			}

		} else {
			request.setAttribute("displayOK", false);
			request.setAttribute("msg", "Pas de session utilisateur, vous devez vous identifier");
			request.getRequestDispatcher("Autentification.jsp").forward(request, response);
		}
	}

}
