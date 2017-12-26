package com.proxibanque.views;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proxibanque.dao.JdbcDaoException;
import com.proxibanque.model.Agence;
import com.proxibanque.model.Conseiller;
import com.proxibanque.service.ServiceAgence;
import com.proxibanque.service.ServiceEngine;

/**
 * TODO :
 * Gere affichage des information de ou des Agences Bancaires
 * de la base ProxyBanque
 * 
 * Servlet implementation class ViewAgenceServlet
 */
@WebServlet("/proxybanque")
public class ViewAgenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceAgence srvcAgence;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAgenceServlet() {
        super();
        try {
			srvcAgence = (new ServiceEngine()).getServiceAgence();
		} catch (JdbcDaoException e) {
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Agence ag = srvcAgence.getAgenceById(1);

		if (ag != null) {
			List<Conseiller> lstCons = ag.getConseillers();
			Map<Integer, Integer> mapNbrClientsConseiller = new HashMap<>();
			
			for(Conseiller cons : lstCons) {
				mapNbrClientsConseiller.put(cons.getId(), cons.getPortefeuilleClients().size());
//				System.out.println("id=" + cons.getId() + " val=" + cons.getPortefeuilleClients().size());
			}
			
			request.setAttribute("listConseillers", lstCons);
			request.setAttribute("mapNbrClientsConseiller", mapNbrClientsConseiller);
			
			request.setAttribute("msg", "L'agence comporte "+lstCons.size()+" conseillers");
			request.setAttribute("displayOK", true);
		} else {
			request.setAttribute("displayOK", false);
			request.setAttribute("msg", "Aucune agence n'a &eacute;t&eacute; trouv&eacute;e..");
		}
		request.getRequestDispatcher("ViewAgence.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
