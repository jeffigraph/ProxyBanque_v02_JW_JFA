package com.proxibanque.service;

import com.proxibanque.dao.DaoException;
import com.proxibanque.dao.IDaoConseiller;
import com.proxibanque.model.Conseiller;

/**
 * Gestion de l'authentification utilisateur
 * 
 * Cherche l'information par DAO - si on trouve un Conseiller par son ID
 * employée et ID agence, login l'autentification est réussie, et la session est
 * ouverte
 * 
 * @author JL JFA
 *
 */
public class ServiceLogin {

	private IDaoConseiller mDaoConseiller;

	private Conseiller mConseiller;

	public ServiceLogin(IDaoConseiller mDaoConseiller) {
		super();
		this.mDaoConseiller = mDaoConseiller;

		mConseiller = null;
	}

	public Conseiller getMyConseiller() {
		return mConseiller;
	}

	/**
	 * Retourne le premier conseiller de la base de donnee correspondant au login et
	 * au psw fournis en parametre renvoie null si aucun conseiller ne correspond
	 * 
	 * @param login
	 *            du conseiller
	 * @param psw
	 *            du conseiller
	 * @return Conseiller 
	 * 				le conseiller trouve ou null
	 * @throws DaoException
	 *             la requete a achouee
	 */
	public Conseiller login(String login, String psw) throws DaoException {
		mConseiller = mDaoConseiller.selectConseillerByLogin(login, psw);

		return mConseiller;
	}

}
