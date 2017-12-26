package com.proxibanque.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description d'une Agence - comporte un gerant - une liste de conseillers
 *
 * @author JL JFA
 *
 */
public class Agence {

    private int id;

    private Date dateCreation;

    /**
     * le Gerant de l'Agence
     */
    private Conseiller gerant;

    /**
     * liste des conseillers clients appartenant a l'agence
     */
    private List<Conseiller> conseillers;

    /**
     * @param id
     * @param dateCretion
     * @param gerant
     * @param conseillers
     */
    public Agence(int id, Date dateCretion, Conseiller gerant, List<Conseiller> conseillers) {
        super();
        this.id = id;
        this.dateCreation = dateCretion;
        this.gerant = gerant;
        this.conseillers = conseillers;
    }

    /**
     * @param id
     * @param dateCretion
     * @param gerant
     */
    public Agence(int id, Date dateCretion, Gerant gerant) {
        this(id, dateCretion, gerant, new ArrayList<Conseiller>());
    }

    /**
     * Ajoute un conseiller dans la liste de conseillers
     *
     * @param conseiller
     */
    public void addConseiller(Conseiller conseiller) {
        conseillers.add(conseiller);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the dateCretion
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * @return the gerant
     */
    public Conseiller getGerant() {
        return gerant;
    }

   
    /**
     * Rend une liste des Conseillers de l'Agence
     *
     * @return the conseillers
     */
    public List<Conseiller> getConseillers() {
        return conseillers;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param dateCretion the dateCretion to set
     */
    public void setDateCreation(Date dateCretion) {
        this.dateCreation = dateCretion;
    }

    /**
     * @param gerant the gerant to set
     */
    public void setGerant(Gerant gerant) {
        this.gerant = gerant;
    }

    /**
     * @param conseillers the conseillers to set
     */
    public void setConseillers(List<Conseiller> conseillers) {
        this.conseillers = conseillers;
    }

    /**
     * Supprimer un conseiller de l'Agence
     *
     * @param conseiller
     */
    public void removeConseiller(Conseiller conseiller) {
        // TODO supprimer un conseiller de la liste des conseillers de l'Agence
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Agence [id=" + id + ", dateCreation=" + dateCreation + ", gerant=" + gerant + ", conseillers="
				+ conseillers + "]";
	}

}
