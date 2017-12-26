package com.proxibanque.model;

/**
 * Description d'un Compte bancaire classe abstraite devant etre etendue
 *
 * @author JL JFA
 *
 */
public abstract class Compte {
	
	// Attributs DB aide à preparation des requetes
	public static final String TABLENAME = "compte";
    public static final String KEYNAME = "numerocompte";
    public static final String TABLECOLUMNS = "numerocompte, dateouverture, solde, idtypeclient";
    public static final String ALLCOLUMNPARAMS = "?,?,?,?";
	
	
	private String numeroCompte;

    private Double solde;

    private String dateOuverture;

    /**
     * le type du compte : Courant ou Epargne
     */
    private String type;

    /**
     * les types que peut prendre un compte
     */
    public static final String PARTICULIER = "particuler";
    public static final String ENTREPRISE = "entreprise";

    /**
     * @param numeroCompte
     * @param dateOuverture du compte
     * @param type le type du compte : Courant ou Epargne
     */
    Compte(String numeroCompte, String dateOuverture, String type) {
        this(numeroCompte, 0.0, dateOuverture, type);
    }

    /**
     * @param numeroCompte
     * @param solde
     * @param dateOuverture
     * @param type le type du compte : Courant ou Epargne
     */
    Compte(String numeroCompte, Double solde, String dateOuverture, String type) {
        super();
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.dateOuverture = dateOuverture;
        this.type = type;
    }

    /**
     * @return the numeroCompte
     */
    public String getNumeroCompte() {
        return numeroCompte;
    }

    /**
     * @return the solde
     */
    public Double getSolde() {
        return solde;
    }

    /**
     * @return the dateOuverture
     */
    public String getDateOuverture() {
        return dateOuverture;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param numeroCompte the numeroCompte to set
     */
    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    /**
     * @param solde the solde to set
     */
    public void setSolde(Double solde) {
        this.solde = solde;
    }

    /**
     * @param dateOuverture the dateOuverture to set
     */
    public void setDateOuverture(String dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Credite le compte du montant
     *
     * @param montant
     */
    public void addToSolde(int montant) {
        this.solde += montant;
    }

    /**
     * Debite le compte du montant
     *
     * @param montant
     */
    public void removeFromSolde(int montant) {
        this.solde -= montant;
    }

    /**
     * @return le type du compte
     */
    public abstract String getTypeCompte();

}
