/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxibanque.dao;

import java.util.List;

import com.proxibanque.model.CarteBancaire;

/**
 * Gestion d'une CarteBanquaire
 *
 * @author JL JFA
 */

public interface IDaoCarteBancaire {
	public int insert(CarteBancaire cb);

	public void update(CarteBancaire cb);

	public void delete(CarteBancaire cb);

	/**
	 * Renvois la CarteBancoire dont le numero correspond a celui fournie en
	 * parametre
	 * 
	 * @param numeroCB
	 * @return CarteBancaire ou null
	 */
	public CarteBancaire selectCarteByNumero(String numeroCB);

	public List<CarteBancaire> selectAll();

}
