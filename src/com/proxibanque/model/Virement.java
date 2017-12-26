package com.proxibanque.model;

/**
 * 
 * @author JL JFA
 *
 */
public class Virement {
	String date;
	String idCompteDepart;
	String idCompteCible;
	Double montant;

	/**
	 * @param date
	 * @param idCompteDepart
	 * @param idCompteCible
	 */
	public Virement(String idCompteDepart, String idCompteCible, double montant) {
		super();
		this.idCompteDepart = idCompteDepart;
		this.idCompteCible = idCompteCible;
		this.montant = montant;
	}

	/**
	 * methode pour recuperer la date a laquelle a ete effectuee la transaction
	 * 
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * methode recupere l'identifiant du compte depart
	 * 
	 * @return the idCompteDepart
	 */
	public String getIdCompteDepart() {
		return idCompteDepart;
	}

	/**
	 * methode pour recuperer l'identifiant du compte cible
	 * 
	 * @return the idCompteCible
	 */
	public String getIdCompteCible() {
		return idCompteCible;
	}

	/**
	 * methode pour recuperer le montant de transaction
	 * 
	 * @return the montant
	 */
	public Double getMontant() {
		return montant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Virement [date=" + date + ", idCompteDepart=" + idCompteDepart + ", idCompteCible="
				+ idCompteCible + ", montant=" + montant + "]";
	}

}
