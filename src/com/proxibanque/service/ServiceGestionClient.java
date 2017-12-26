package com.proxibanque.service;

import java.util.Date;
import java.util.List;

import com.proxibanque.dao.DaoException;
import com.proxibanque.dao.IDaoClient;
import com.proxibanque.model.CarteBancaire;
import com.proxibanque.model.Client;
import com.proxibanque.model.CompteCourant;
import com.proxibanque.model.CompteEpargne;
import com.proxibanque.model.Conseiller;

/**
 * Gestion des comptes clients en lien avec la Base de donnees: 
 * - creation De clients et ajout a la Base de donnees 
 * - suppressions de Clients de la Base 
 * - recuperation de Clients depuis la Base 
 * - gestion des comptes associes au Client 
 * - gestion des CarteBancaires associees au CompteCourant d'un Client
 *
 * @author JL JFA
 *
 */
public class ServiceGestionClient {
	// DaoClient daoClient = new DaoClientMem();

	private IDaoClient daoClient;

	public ServiceGestionClient(IDaoClient daoClient) {
		super();
		this.daoClient = daoClient;
	}

	/**
	 * Cree un nouveau Client et l'enregistre dans la base de donnees
	 *
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param codePostal
	 * @param ville
	 * @param telephone
	 * @param typeClient
	 *            le type de Client : Particulier ou Entreprise
	 * @return le Client que vient d'etre cree
	 */
	public Client createNewClient(String nom, String prenom, String adresse, Integer codePostal, String ville,
			String telephone, String typeClient) {
		// TODO : Dao addElementAndGetKey est implemente

		Client nvClient = null;

		return nvClient;
	}

	/**
	 * Retourne un Client
	 *
	 * @param idClient
	 *            l'identifiant du client dans la base de donnees
	 * @return le Client
	 * @throws DaoException
	 */
	public Client getClient(long idClient) throws DaoException {
		// return daoClient.getClient(idClient);
		return daoClient.getElementById(idClient);
	}

	public List<Client> getAllClientsByConseiller(Conseiller conseiller) {
		return daoClient.selectAllClientByConseillerId(conseiller.getId());
	}

	public List<Client> getAllClientsByConseillerId(int idConseiller) {
		return daoClient.selectAllClientByConseillerId(idConseiller);
	}
	
	public List<Client> getAllClients() throws DaoException {
		return daoClient.getAllElement();
	}

	/**
	 * Modifie les informations d'un client
	 *
	 * @param client
	 *            le Client a modifier
	 * @throws DaoException
	 */
	public void updateClient(Client client) throws DaoException {
		daoClient.updateElement(client);
	}

	/**
	 * Supprime un client de la base de donnee
	 *
	 * @param idClient
	 * @return true la suppression est un succes
	 * @throws DaoException
	 */
	public boolean removeClient(int idClient) throws DaoException {
		daoClient.deleteElement(idClient);
		return true;
	}

	public boolean removeclient(Client client) throws DaoException {
		this.removeClient(client.getIdClient());
		return true;
	}

	/**
	 * Ajoute un nouveau Compte a un Client
	 *
	 * @param client
	 * @param numeroCompte
	 * @param dateOuverture
	 * @param typeClient
	 *            le type de client : Particulier ou Entreprise
	 * @param typeCompte
	 *            le type de Compte : Courant ou Epargne
	 * @return
	 */
	public boolean addNewCompte(Client client, int numeroCompte, Date dateOuverture, String typeClient,
			String typeCompte) {
		// TODO Dao Compte methode addNewCompte à implementer

		return false;
	}

	/**
	 * Supprime un Compte appartenant a un client
	 *
	 * @param client
	 *            le client
	 * @param typeCompte
	 *            le type du compte a supprimer : CompteEpargne ou CompteCourant
	 * @return true si la suppression est un succes
	 */
	public boolean removeCompte(Client client, String typeCompte) {
		switch (typeCompte) {
		case CompteCourant.TYPE_COMPTE:
			if (client.getCompteCourant() != null) {
				// verifier solde superieur a 0
				// si oui supprmer le compte
				client.setCompteCourant(null);
				return true;
			}
			return false;
		case CompteEpargne.TYPE_COMPTE:
			if (client.getCompteEpargne() != null) {
				// verifier solde superieur a 0
				// si oui supprmer le compte
				client.setCompteEpargne(null);
				return true;
			}
			return false;
		default:
			return false;
		}

	}

	/**
	 * Ajoute une nouvelle CarteBancaire a un CompteCourant
	 *
	 * @param compteCourant
	 * @param carteBancaire
	 * @return true si l'ajout est un succes
	 */
	public boolean addCarteBancaire(CompteCourant compteCourant, CarteBancaire carteBancaire) {
		if (compteCourant.getCarteBancaire() == null) {
			compteCourant.setCarteBancaire(carteBancaire);

			// TODO: Methode DAO à implementer

			return true;
		}

		return false;
	}

	/**
	 * Supprime une CarteBancaire d'un CompteCourant
	 *
	 * @param compteCourant
	 * @param carteBancaire
	 * @return true si la suppression est un succes
	 */
	public boolean removeCarteBancaire(CompteCourant compteCourant, CarteBancaire carteBancaire) {
		if (compteCourant.getCarteBancaire() != null) {
			compteCourant.setCarteBancaire(null);

			// TODO: Methode DAO à implementer

			return true;
		}

		return false;
	}

}
