/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxibanque.dao;

import java.sql.Date;
import java.util.List;

import com.proxibanque.model.Agence;

/**
 * Interface de gestion d'une Agence
 *
 * @author JL JFA
 */

public interface IDaoAgence {

    /**
     * @param agence
     * @return
     */
	public boolean insert(Agence agence);
	public boolean delete(Agence agence);
    
	public boolean update(Agence agence);
    
	/**
	 * @return List des Agences de la base ProxyBanque
	 * @throws JdbcDaoException
	 */
	public List<Agence> selectAll() throws JdbcDaoException;
	public Agence selectAgenceById(int idAgence);
	public int selectIdByDate(Date dateCreation);
    
}
