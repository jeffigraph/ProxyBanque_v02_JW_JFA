package com.proxibanque.model;

/**
 * Descrit un compte courant
 *
 * @author JL JFA
 * @version 0.1
 *
 */
public class CompteCourant extends Compte {


	/**
     * le type d'un compte courant
     */
    public static final String TYPE_COMPTE = "courant";

    /**
     * la carte Bancaire associee au compte, defini seuelment si le client en
     * possede une, sinon null.
     */
    private CarteBancaire carteBancaire;

    /**
     * le montant du decouvert autorise
     */
    //private Decouvert autorisationDecouvert;
    private int decouvertAutorise;
/*
    public CompteCourant(CarteBancaire carteBancaire, int decouvertAutorise, int numeroCompte, Date dateOuverture, String type) {
        super(numeroCompte, dateOuverture, type);
        this.carteBancaire = carteBancaire;
        this.decouvertAutorise = decouvertAutorise;
    }*/

    public CompteCourant(String numeroCompte, Double solde, String dateOuverture, String type, CarteBancaire carteBancaire, int decouvertAutorise) {
        super(numeroCompte, solde, dateOuverture, type);
        this.carteBancaire = carteBancaire;
        this.decouvertAutorise = decouvertAutorise;
    }

    /**
     * @return the carteBancaire
     */
    public CarteBancaire getCarteBancaire() {
        return carteBancaire;
    }

    /**
     * @param carteBancaire the carteBancaire to set
     */
    public void setCarteBancaire(CarteBancaire carteBancaire) {
        this.carteBancaire = carteBancaire;
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
        return "CompteCourant [carteBancaire=" + carteBancaire + ", autorisationDecouvert=" + decouvertAutorise
                + ", solde=" + super.getSolde() + "]";
    }

    public int getDecouvertAutorise() {
        return decouvertAutorise;
    }

    public void setDecouvertAutorise(int decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }

}
