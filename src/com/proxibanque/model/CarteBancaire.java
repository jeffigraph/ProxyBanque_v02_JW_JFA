package com.proxibanque.model;

/**
 * @author JL JFA
 *
 */
public class CarteBancaire {
	public static final String VISA_ELECTRON = "visa electron";
	public static final String VISA_PREMIER = "visa premier";

	public final String NUMERO_CARTE;
	public final String TYPE_CARTE;

	/**
	 * @param numeroCarte
	 * @param tYPE_CARTE
	 */
	public CarteBancaire(String numeroCarte, String TYPE_CARTE) {
		super();
		this.NUMERO_CARTE = numeroCarte;
		this.TYPE_CARTE = TYPE_CARTE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CarteBancaire [NUMERO_CARTE=" + NUMERO_CARTE + ", TYPE_CARTE=" + TYPE_CARTE + "]";
	}

}