package com.proxibanque.model;

import java.util.List;

/**
 * La classe donne des renseignements sur le nom, le prenom, le numero
 * identifiant d'un conseiller qui a la responsabilite d'un groupe de MAX_CLIENT
 * clients
 *
 * @author JL JFA
 *
 */
public class Conseiller {
	
	// Attributs DB aide à preparation des requetes
	public static int TYPECONSEILLER = 1;
	public static int TYPEGERANT = 2;
	
	public static final String TABLENAME = "conseiller";
    public static final String KEYNAME = "idconseiller";
    public static final String TABLECOLUMNS = "idconseiller, idtypeconseiller, nom, prenom";
    public static final String ALLCOLUMNPARAMS = "?,?,?,?,?";
	
	private final int MAX_CLIENT = 10;

	private int id;

	private String nom;

	private String prenom;

	private String typeConseiller;

	private List<Client> portefeuilleClients;
	

	/**
	 * Constructeur de la classe Conseiller
	 *
	 * @param nom
	 * @param prenom
	 * @param id
	 */
	// public Conseiller(int id, String nom, String prenom) {
	// super();
	// this.nom = nom;
	// this.prenom = prenom;
	// this.id = id;
	// this.typeConseiller = null;
	// this.portefeuilleClients = new ArrayList<>();
	// }
	//
	// public Conseiller(int id, String nom, String prenom, List<Client>
	// portefeuilleClients) {
	// this.nom = nom;
	// this.prenom = prenom;
	// this.id = id;
	// this.typeConseiller = null;
	// this.portefeuilleClients = portefeuilleClients;
	// }

	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param typeConseiller
	 * @param portefeuilleClients
	 */
	public Conseiller(int id, String nom, String prenom, String typeConseiller, List<Client> portefeuilleClients) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.typeConseiller = typeConseiller;
		this.portefeuilleClients = portefeuilleClients;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the typeConseiller
	 */
	public String getTypeConseiller() {
		return typeConseiller;
	}

	/**
	 * @param typeConseiller
	 *            the typeConseiller to set
	 */
	public void setTypeConseiller(String typeConseiller) {
		this.typeConseiller = typeConseiller;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Methode permet de verifier la taille du groupe de client sous la
	 * responsabilite d'un conseiller Si la taille de la liste est inferieur e 10.
	 * On ajoute le nouveau client e cette liste
	 *
	 * @return true
	 * @param client
	 *            Si non
	 * @return false
	 */
	public boolean addClient(Client client) {
		if (portefeuilleClients.size() < this.MAX_CLIENT) {
			portefeuilleClients.add(client);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * methode permet de supprimer un client d'un groupe de client
	 *
	 * @param client
	 * @return
	 */
	public boolean removeClient(Client client) {
		return portefeuilleClients.remove(client);
	}

	/**
	 * Recuperer un client de type Client e partir d'une parametre entree
	 *
	 * @param idClient
	 * @return Client
	 */
	public Client getClient(int idClient) {
		return portefeuilleClients.get(idClient);
	}

	/**
	 * Recuperer la liste de clients gere par un conseiller
	 *
	 * @return the portefeuilleClients
	 */
	public List<Client> getPortefeuilleClients() {
		return portefeuilleClients;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Conseiller [MAX_CLIENT=" + MAX_CLIENT + ", id=" + id + ", nom=" + nom + ", prenom=" + prenom
				+ ", typeConseiller=" + typeConseiller + ", portefeuilleClients=" + portefeuilleClients + "]";
	}

}
