package com.proxibanque.model;

/**
 * Definit les informations d'un client avec ses comptes.
 *
 * @author JL JFA
 * @version 0.1
 *
 */
public class Client {

    // Attributs DB aide à preparation des requetes
    public static final String TABLENAME = "client";
    public static final String KEYNAME = "idclient";
    public static final String TABLECOLUMNS = "idclient, nom, prenom, adresse, ville, codepostal, telephone";
    public static final String ALLCOLUMNPARAMS = "?,?,?,?,?,?,?";
	
	public static final int PARTICULIER = 1;
    public static final int ENTREPRISE = 2;

    public int idClient;

    private String nom;

    private String prenom;

    private String adresse;

    private String ville;

    private int codePostal;

    private String telephone;

    private CompteCourant compteCourant;

    private CompteEpargne compteEpargne;

    public Client(int idClient, String nom, String prenom, String adresse, String ville, int codePostal, String telephone, CompteCourant compteCourant, CompteEpargne compteEpargne) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.telephone = telephone;
        this.compteCourant = compteCourant;
        this.compteEpargne = compteEpargne;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @return the codePostal
     */
    public Integer getCodePostal() {
        return codePostal;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @return the compteCourant
     */
    public CompteCourant getCompteCourant() {
        return compteCourant;
    }

    /**
     * @return the compteEpargne
     */
    public CompteEpargne getCompteEpargne() {
        return compteEpargne;
    }

    /**
     * @return the idClient
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @param codePostal the codePostal to set
     */
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @param compteCourant the compteCourant to set
     */
    public void setCompteCourant(CompteCourant compteCourant) {
        this.compteCourant = compteCourant;
    }

    /**
     * @param compteEpargne the compteEpargne to set
     */
    public void setCompteEpargne(CompteEpargne compteEpargne) {
        this.compteEpargne = compteEpargne;
    }


    /**
     * @param codePostal the codePostal to set
     */
    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * @param idClient the idClient to set
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * @return true if there is a CompteCourant
     */
    public boolean hasCompteCourant() {
        return compteCourant != null;
    }

    /**
     * @return true if there is e CompteEpargne
     */
    public boolean hasCompteEpargne() {
        return compteEpargne != null;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Client [ id=" + idClient +", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", codePostal=" + codePostal
                + ", ville=" + ville + ", telephone=" + telephone + ", compteCourant=" + compteCourant
                + ", compteEpargne=" + compteEpargne + "]";
    }
	
	public Compte getCompteByNum(String numCompte) {
		if ( null != compteCourant && numCompte.equals(compteCourant.getNumeroCompte()) )
			return compteCourant;
		else if ( null != compteEpargne && numCompte.equals(compteEpargne.getNumeroCompte()) )
			return compteEpargne;
		else
			return null;
	}
	
}
