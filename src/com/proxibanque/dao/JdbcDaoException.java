package com.proxibanque.dao;

/**
 * Gere les Exceptions d'acces a la base de donnees par JDBC
 * 
 * @author JL JFA
 */
public class JdbcDaoException extends DaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6814171226707488576L;

	/**
	 * Constructeur par defaut
	 */
	public JdbcDaoException() {
		super();
	}

	/**
	 * Constructeur qui prend un message en paramètre
	 * 
	 * @param message
	 *            Description de l'Exception
	 */
	public JdbcDaoException(String message) {
		super(message);
	}

	/**
	 * Constructeur qui prend l'Exception d'origine en parametre
	 * 
	 * @param cause
	 *            l'Exception d'origine
	 */
	public JdbcDaoException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructeur qui prend en parametre un message et l'Exception d'origine
	 * 
	 * @param message
	 *            Description de l'Exception
	 * @param cause
	 *            l'Exception d'origine
	 */
	public JdbcDaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
