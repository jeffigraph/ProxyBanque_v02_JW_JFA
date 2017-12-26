package com.proxibanque.model;

import java.util.List;

/**
 * Decrit un gerant qui est aussi un conseiller
 * @author JL JFA
 *
 */
public class Gerant extends Conseiller {

	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param typeConseiller
	 * @param portefeuilleClients
	 */
	public Gerant(int id, String nom, String prenom, String typeConseiller, List<Client> portefeuilleClients) {
		super(id, nom, prenom, typeConseiller, portefeuilleClients);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nom
	 * @param prenom
	 * @param id
	 */
//	public Gerant(int id, String nom, String prenom) {
//		super(id ,nom, prenom);
//	}
	

}