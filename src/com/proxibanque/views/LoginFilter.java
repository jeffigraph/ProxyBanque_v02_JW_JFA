package com.proxibanque.views;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filtre les acces des utilisateurs L'utilisateur doit avoir une session valide
 * pour acceder a toute page autre que: "Autentification" ou "index.jsp"
 * 
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * Filtre l'acces au page du ste Web selon l'exstance d'une session valide ou
	 * non
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		HttpSession session = httpRequest.getSession(false);
//		String loginURI = httpRequest.getContextPath() + "/Autentification";
//		String indexURI = httpRequest.getContextPath() + "/index.jsp";
//
//		boolean loggedIn = session != null && session.getAttribute("user") != null;
//		boolean loginRequest = httpRequest.getRequestURI().equals(loginURI)
//				|| httpRequest.getRequestURI().equals(indexURI);
//
//		if (loggedIn || loginRequest) {
//			chain.doFilter(request, response);
//		} else {
//			httpResponse.sendRedirect(loginURI);
//		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
