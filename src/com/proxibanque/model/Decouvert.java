package com.proxibanque.model;

/**
 * Decrit le montant decouvert d'un particulier et d'une entreprise
 * @author JL JFA
 *
 */
public class Decouvert {
	public final static Double DECOUVERT_PARTICULIER = 1000.0;
	public final static Double DECOUVERT_ENTREPRISE = 50000.0;

	private Double decouvertAutorise;

	/**
	 * Constructeur de la classe Decouvert
	 * @param decouvertAutorise
	 */
	public Decouvert(Double decouvertAutorise) {
		super();
		this.decouvertAutorise = decouvertAutorise;
	}

	/**
	 * @param decouvertAutorise
	 *            the decouvertAutorise to set
	 */
	public void setDecouvertAutorise(Double decouvertAutorise) {
		this.decouvertAutorise = decouvertAutorise;
	}

	/**
	 * @return the decouvertAutorise
	 */
	public Double getDecouvertAutorise() {
		return decouvertAutorise;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Decouvert [decouvertAutorise=" + decouvertAutorise + "]";
	}

}