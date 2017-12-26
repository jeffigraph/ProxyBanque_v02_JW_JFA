package com.proxibanque.service;

import java.util.List;

import com.proxibanque.dao.IDaoAgence;
import com.proxibanque.dao.JdbcDaoException;
import com.proxibanque.model.Agence;

/**
 * TODO :
 * Gestion des informations des agences
 * 
 * @author JL JFA
 *
 */
public class ServiceAgence {
	/**
	 * La liste des Agences
	 */
	private static List<Agence> Agenceslist;

	/**
	 * Le DAO permettant de consulter la table AgenceBancaire de de la Base de
	 * donnees
	 */
	private IDaoAgence daoAgence;

	/**
	 * Construit une liste des Agences presentes dans la Base de donnees
	 * 
	 * @throws JdbcDaoException
	 */
	public ServiceAgence(IDaoAgence daoAgence) throws JdbcDaoException {
		super();
		this.daoAgence = daoAgence;
		Agenceslist = daoAgence.selectAll();
	}

	/**
	 * @param idAgence  un identifiant d'Agence
	 * @return recherche une Agence dont l'id correspond a l'idAgence fourni en
	 *         parametre l'Agence doit se trouver dans l'AgencesList sinon null est
	 *         retourne
	 */
	public Agence getAgenceById(int idAgence) {
		if (Agenceslist != null) {
			for (Agence ag : Agenceslist) {
				if (ag.getId() == idAgence) {
					// return daoAgence.selectAgenceById(idAgence);
					return ag;
				}
			}
		}
		return null;
	}
}
