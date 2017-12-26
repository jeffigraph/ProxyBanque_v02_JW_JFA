package com.proxibanque.model;

public class CompteEpargne extends Compte {
	
	public static final String TYPE_COMPTE = "epargne";

	private Taux tauxRemuneration;

	/**
	 * @param numeroCompte
	 * @param dateOuverture
	 * @param type
	 */
	public CompteEpargne(String numeroCompte, String dateOuverture, String type) {
		super(numeroCompte, dateOuverture, type);
	}

	/**
	 * @param numeroCompte
	 * @param solde
	 * @param dateOuverture
	 * @param type
	 */
	public CompteEpargne(String numeroCompte, Double solde, String dateOuverture, String type) {
		super(numeroCompte, solde, dateOuverture, type);
	}

	/**
	 * @return the tauxRemuneration
	 */
	public Taux getTauxRemuneration() {
		return tauxRemuneration;
	}

	/**
	 * @param tauxRemuneration
	 *            the tauxRemuneration to set
	 */
	public void setTauxRemuneration(Taux tauxRemuneration) {
		this.tauxRemuneration = tauxRemuneration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.proxibanquev1.model.Compte#getTypeCompte()
	 */
	@Override
	public String getTypeCompte() {
		return TYPE_COMPTE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompteEpargne [tauxRemuneration=" + tauxRemuneration + "]";
	}

}