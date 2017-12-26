package com.proxibanque.views;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proxibanque.dao.JdbcDaoException;
import com.proxibanque.model.Client;
import com.proxibanque.service.ServiceEngine;

/**
 * Affichage de la liste des clients presents dans le portefeuille du conseiller 
 * actuellement identifie dans la session en cours
 * 
 * Servlet implementation class ViewClientsServlet
 */
@WebServlet("/ViewClients")
public class ViewClientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceEngine srvcEng;

	/**
	 * Chargement du gestionnaire de service de connexion a la base de donnees
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewClientsServlet() {
		super();
		try {
			srvcEng = new ServiceEngine();
		} catch (JdbcDaoException ex) {
			System.out.println("ViewClientsServlet : " + ex.getMessage());
		}
	}

	/**
	 * Verifie la validite de la session
	 * Recupere l'id du conseiller puis la liste de ses clients
	 * Renvoie la requete a "ViewClients.jsp", la page d'affichage des donnees
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
				List<Client> clientsList = srvcEng.getServiceGestionClient().getAllClientsByConseillerId(idConseiller);

				request.setAttribute("clientList", clientsList);
				request.setAttribute("msg",
						"Il y a " + clientsList.size() + " clients correspondants &agrave; votre demande");
				request.setAttribute("displayOK", true);
			} else {
				request.setAttribute("msg", "Aucun clients ne correspondent &agrave; votre demande");
				request.setAttribute("displayOK", false);
			}

			request.getRequestDispatcher("ViewClients.jsp").forward(request, response);
		} else {
			request.setAttribute("displayOK", false);
			request.setAttribute("msg", "Pas de session utilisateur, vous devez vous identifier");
			request.getRequestDispatcher("Autentification.jsp").forward(request, response);
		}
	}

	/**
	 * Rien a faire a ce niveau
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
