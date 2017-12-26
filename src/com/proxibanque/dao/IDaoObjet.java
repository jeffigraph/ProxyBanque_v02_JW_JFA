package com.proxibanque.dao;

import java.util.List;

/**
 * Fournis les methodes CRUD emplyees par les DAO qui l'implementent
 * 
 * @author JL JFA
 *
 */
public interface IDaoObjet<T> {

	/**
	 * ajouter un objet dans une persistence
	 * 
	 * @param t
	 *            : un objet g�n�rique
	 */
	public void addElement(T t) throws DaoException;

	/**
	 * ajouter un objet dans une persistence, un ID est genere par DB
	 * 
	 * @param t
	 *            : un objet g�n�rique
	 */
	public void addElementAndGetKey(T t) throws DaoException;

	/**
	 * r�cup�rer un objet depuis une persistence
	 * 
	 * @param k
	 *            le cl� d'�lement
	 * @return un objet g�n�rique
	 */
	public T getElementById(long k) throws DaoException;

	/**
	 * r�cup�rer tous objet depuis une persistence
	 * 
	 * @return un ArrayList d'objets
	 */
	public List<T> getAllElement() throws DaoException;

	/**
	 * modifier un objet dans une persistence
	 * 
	 * @param t
	 *            : l'objet modifi� et enresigtrer
	 */
	public void updateElement(T t) throws DaoException;

	/**
	 * supprimer un objet dans une persistence
	 * 
	 * @param key
	 *            : cle de l'objet � supprimer
	 */
	public void deleteElement(long k) throws DaoException;
}
