/**
 * 
 */
package com.proxibanque.dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.proxibanque.dao.IDaoCarteBancaire;
import com.proxibanque.model.CarteBancaire;
import com.proxibanque.util.BDD;

/**
 * Class DAO carte bancaire, CRUD non implemente
 * 
 * @author JL JFA
 *
 */
public class DaoCarteBancaireMySQL implements IDaoCarteBancaire {

	/**
	 * 
	 */
	public DaoCarteBancaireMySQL() {
	}

	@Override
	public int insert(CarteBancaire cb) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(CarteBancaire cb) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(CarteBancaire cb) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.proxibanque.dao.IDaoCarteBancaire#selectCarteByNumero(java.lang.String)
	 */
	@Override
	public CarteBancaire selectCarteByNumero(String numeroCB) {
		CarteBancaire cb = null;

		try {
			Connection cnx = BDD.getConnection();
			String selectCarteByNumeroSQL = "SELECT CARTEBANCAIRE.NUMEROCARTEBANCAIRE NUMERO, TYPECARTEBANCAIRE.TYPECARTEBANCAIRE TYPE"
					+ "    FROM CARTEBANCAIRE, TYPECARTEBANCAIRE"
					+ "    WHERE CARTEBANCAIRE.IDTYPECARTEBANCAIRE = TYPECARTEBANCAIRE.IDTYPECARTEBANCAIRE"
					+ "    AND CARTEBANCAIRE.NUMEROCARTEBANCAIRE =" + numeroCB;
			selectCarteByNumeroSQL = selectCarteByNumeroSQL.toLowerCase();

			Statement stat = cnx.createStatement();

			ResultSet rslts = stat.executeQuery(selectCarteByNumeroSQL);

			if (rslts.next()) {
				cb = new CarteBancaire(rslts.getString("NUMERO"), rslts.getString("TYPE"));
			}

		} catch (SQLException ex) {
			System.out.println("DaoCarteBancaireMySQL : selectCarteByNumero() : " + ex.getMessage());
		}

		return cb;
	}

	@Override
	public List<CarteBancaire> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
