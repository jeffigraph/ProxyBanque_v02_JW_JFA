package com.proxibanque.dao;

import java.util.List;

import com.proxibanque.model.Client;

/**
 * Gestion des Clients
 * 
 * @author JL JFA
 *
 */
public interface IDaoClient extends IDaoObjet<Client> {

	/**
	 * Renvoie la liste des clients du conseiller dont l'identifiant est fourni en
	 * parametre
	 * 
	 * @param idConseiller
	 * @return List<Client>
	 */
	public List<Client> selectAllClientByConseillerId(int idConseiller);
}
