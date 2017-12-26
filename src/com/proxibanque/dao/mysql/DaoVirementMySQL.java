package com.proxibanque.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.proxibanque.dao.IDaoVirement;
import com.proxibanque.dao.JdbcDaoException;
import com.proxibanque.model.Virement;
import com.proxibanque.util.BDD;

/**
 * Class DAO qui faire que insertion 
 * 
 * @author JL JFA
 *
 */
public class DaoVirementMySQL implements IDaoVirement {

	
	private PreparedStatement pstmtInsertVirement;
	
	public DaoVirementMySQL() throws JdbcDaoException {
		super();
		
		prepareStatement();
	}
	
	
	private void prepareStatement() throws JdbcDaoException {
		try {
			Connection cnx = BDD.getConnection();

			pstmtInsertVirement = cnx.prepareStatement("insert into `virement` (`montant`, `numerocomptedebite`, `numerocomptecredite`, `dateoperation`) "
					+ "VALUES(?,?,?,now())");
			
			// deconnection
			
		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoConseillerMySQL.prepareStatement : preparation des requetes echoue " + ex);
		}
	}

	
	@Override
	public void insertVirement(Virement v) throws JdbcDaoException {
		try {
			
			pstmtInsertVirement.setDouble(1, v.getMontant());
			pstmtInsertVirement.setString(2, v.getIdCompteDepart());
			pstmtInsertVirement.setString(3, v.getIdCompteCible());
			
			if (0 == pstmtInsertVirement.executeUpdate()) {
				throw new JdbcDaoException("DaoVirement.insertVirement : ajout de conseiller echoue, " + v);
			}

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoVirement.insertVirement : Probleme de requete, " + ex);
		}

	}

}
