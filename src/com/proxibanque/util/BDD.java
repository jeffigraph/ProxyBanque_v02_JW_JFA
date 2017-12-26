package com.proxibanque.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.proxibanque.dao.JdbcDaoException;

/**
 * Class singleton (mCnx) de connection SGBD, la meme connection est utilisee
 * 
 * TODO : implementer une methode type TIMER pour garder la Connection (eviter TimeOut MySQL)
 * 
 * @author JL JFA
 */
public class BDD {

	private static final String PILOTE = "com.mysql.jdbc.Driver";

	private static Connection mCnx;

	private BDD() {
	}

	/**
	 * Ajouter d'un connection a la BD, a l'initialisation de l'Appli
	 * 
	 * @param url
	 * @param user
	 * @param psw
	 * @throws JdbcDaoException
	 */
	public static void addDatabase(String url, String user, String psw) throws JdbcDaoException {

		try {
			Class.forName(PILOTE);
			BDD.mCnx = DriverManager.getConnection(url, user, psw);
		} catch (ClassNotFoundException e) {
			throw new JdbcDaoException("DatabaseManager.addDatabase : MySQL Driver non trouve " + e);
		} catch (SQLException e) {
			throw new JdbcDaoException("DatabaseManager.addDatabase : Connection non etabli " + e);
		}

	}

	/**
	 * Recupere l'instance de connection
	 * 
	 * @return l'instance connection
	 */
	public static synchronized Connection getConnection() {
		return BDD.mCnx;
	}

	/**
	 * Ferme la connection singleton a la fermture de l'Application
	 * 
	 * @throws SQLException
	 */
	public static synchronized void closeConnection() throws SQLException {
		BDD.mCnx.close();
	}

	/**
	 * Methode type TIMER pour garder la Connection (eviter TimeOut MySQL)
	 */
	private void tickerConnectionSDBD() {
		// TODO : un thread TIMER pour garder la connection MySQL
		try {
			mCnx.prepareStatement("commit").execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
