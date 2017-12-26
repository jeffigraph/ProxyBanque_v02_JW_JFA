package com.proxibanque.dao;

/**
 * Exception d'acces aux donnees
 * 
 * @author JL JFA
 */
public abstract class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5838005733846754538L;

	/**
	 * Constructeur par defaut
	 */
	public DaoException() {

	}

	/**
	 * Constructeur qui prend un message en paramètre
	 * 
	 * @param message  Description de l'Exception
	 */
	public DaoException(String message) {
		super(message);
	}

	/**
	 * Constructeur qui prend l'Exception d'origine en parametre
	 * 
	 * @param cause l'Exception d'origine
	 */
	public DaoException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructeur qui prend en parametre un message et l'Exception d'origine
	 * 
	 * @param message
	 * @param cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
