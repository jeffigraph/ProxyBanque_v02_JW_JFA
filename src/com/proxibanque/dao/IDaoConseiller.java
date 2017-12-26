package com.proxibanque.dao;

import java.util.List;

import com.proxibanque.model.Conseiller;

/**
 * Gestion des Conseillers
 * 
 * @author JL JFA
 *
 */
public interface IDaoConseiller extends IDaoObjet<Conseiller> {

	/**
	 * Renvoie le premier conseiller du type fourni en parametr
	 * 
	 * @param typeConseiller
	 * @return Conseiller ou null
	 */
	public Conseiller selectConseillerByType(int typeConseiller);

	/**
	 * Renvoi liste des conseillers de l'agence dont l'identifiant est passe en
	 * parametre
	 * 
	 * @param idAgence
	 * @return List<Conseiller> ou null
	 */
	public List<Conseiller> selectAllByAgenceId(int idAgence);

	/**
	 * Renvoie le conseiller dont le login et le mode de passe sont fourni en
	 * parametre, null sinon
	 * 
	 * @param login
	 * @param psw
	 * @return Conseiller ou null
	 * @throws DaoException
	 *             la requete a echouee
	 */
	public Conseiller selectConseillerByLogin(String login, String psw) throws DaoException;
}
