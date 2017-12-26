package com.proxibanque.service;

import com.proxibanque.dao.DaoException;
import com.proxibanque.dao.IDaoCompte;
import com.proxibanque.dao.IDaoVirement;
import com.proxibanque.model.Client;
import com.proxibanque.model.Compte;
import com.proxibanque.model.CompteCourant;
import com.proxibanque.model.Virement;

/**
 * Service realisant des Virements d'un Compte a un autre
 * 
 * @author JL JFA
 *
 */
public class ServiceVirement {
	/**
	 * interface de gestion de la base de donnees
	 */
	private IDaoCompte daoCompte;
	private IDaoVirement daoVirement;

	/**
	 * montant maximal pour un virement
	 */
	final static double SEUILMAX = 9999;

	public ServiceVirement(IDaoCompte daoCompte, IDaoVirement daoVirement) {
		super();
		this.daoCompte = daoCompte;
		this.daoVirement = daoVirement;
	}

	/**
	 * Realise un virement ente les deux comptes fournis en parametre
	 * 
	 * @param depart le compte a debiter
	 * @param cible	 le compte a crediter
	 * @param montant   le montant a traiter
	 * @return boolean true si virement reussi
	 * @throws DaoException
	 */
	public boolean faireVirement(Compte depart, Compte cible, double montant) throws DaoException {
		if (checkMontantSolde(depart, montant)) {

			// Insertion de virement dans table
			daoVirement.insertVirement(new Virement(depart.getNumeroCompte(), cible.getNumeroCompte(), montant));

			depart.setSolde(depart.getSolde() - montant);
			cible.setSolde(cible.getSolde() + montant);

			// modification de table compte
			daoCompte.updateCompteSolde(depart);
			daoCompte.updateCompteSolde(cible);

			return true;
		} else
			return false;
	}
	
	
	public boolean faireVirement(Client debiteur, Compte depart, Client crediteur, Compte cible, double montant) throws DaoException {
		if (checkMontantSolde(depart, montant)) {

			// Insertion de virement dans table
			daoVirement.insertVirement(new Virement(depart.getNumeroCompte(), cible.getNumeroCompte(), montant));

			depart.setSolde(depart.getSolde() - montant);
			cible.setSolde(cible.getSolde() + montant);

			// modification de table compte
			daoCompte.updateCompteSolde(depart);
			daoCompte.updateCompteSolde(cible);

			return true;
		} else
			return false;
	}
	

	/**
	 * Rend le Compte dont le numero de compte est fourni en paramete, rend null
	 * s'il n'existe pas
	 * 
	 * @param numero  de comtpte du compte retrouver
	 * @return true si trouvé
	 * @throws DaoException
	 */
	public Compte getCompteExist(String noCompte) throws DaoException {
		return daoCompte.getCompteExist(noCompte);
	}

	/**
	 * Verifie que le montant ne depasse pas le solde du compte
	 * 
	 * @param depart    le compte a verifier
	 * @param montant	le montant à virer
	 * @return true si le solde est suffisant
	 */
	private boolean checkMontantSolde(Compte depart, double montant) {
		// TODO
		if ((depart.getSolde() >= montant)
			 || (depart instanceof CompteCourant && ((CompteCourant) depart).getDecouvertAutorise() + depart.getSolde() >= montant))
			return true;
		else
			return false;
	}

}