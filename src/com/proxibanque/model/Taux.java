package com.proxibanque.model;

/**
 * D�crit le taux de r�un�ration
 * @author JL JFA
 *
 */
public class Taux {

	private Double taux;

	/**
	 * @param taux
	 */
	public Taux(Double taux) {
		super();
		this.taux = taux;
	}

	/**
	 * @return the taux
	 */
	public Double getTaux() {
		return taux;
	}

	/**
	 * @param taux
	 *            the taux to set
	 */
	public void setTaux(Double taux) {
		this.taux = taux;
	}

}