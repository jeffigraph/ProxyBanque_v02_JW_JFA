package com.proxibanque.dao;

import com.proxibanque.model.Virement;

/**
 * Gestion des Virements
 * 
 * @author JL JFA
 *
 */
public interface IDaoVirement {

	/**
	 * Insere le virement fourni en parametre dans la base
	 * 
	 * @param virement
	 * @throws DaoException
	 *             la requete a echouee
	 */
	public void insertVirement(Virement virement) throws DaoException;
}
