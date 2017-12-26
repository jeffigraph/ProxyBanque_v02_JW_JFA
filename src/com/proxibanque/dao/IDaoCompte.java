package com.proxibanque.dao;

import com.proxibanque.model.Client;
import com.proxibanque.model.Compte;
import com.proxibanque.model.CompteCourant;
import com.proxibanque.model.CompteEpargne;

/**
 * Gestion des Comptes
 * 
 * @author JL JFA
 *
 */
public interface IDaoCompte {
	
	public CompteCourant selectCompteCourantByIdClient(int idClient);
	public CompteEpargne selectCompteEpargneByIdClient(int idClient);
	
	public void updateCompteCourantForClient(Client c) throws DaoException ;
	public void updateCompteEpargneForClient(Client c) throws DaoException ;
	
	public Compte getCompteExist(String noCompte) throws DaoException ;
	
	public void updateCompteSolde(Compte c) throws DaoException ;
}
