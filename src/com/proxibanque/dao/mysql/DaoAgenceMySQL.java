package com.proxibanque.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proxibanque.dao.DaoException;
import com.proxibanque.dao.IDaoAgence;
import com.proxibanque.dao.IDaoConseiller;
import com.proxibanque.dao.JdbcDaoException;
import com.proxibanque.model.Agence;
import com.proxibanque.model.Conseiller;
import com.proxibanque.util.BDD;

/**
 * Class DAO Agence qui n'est pas utilise dans ce projet
 * @author JL JFA
 *
 */
public class DaoAgenceMySQL implements IDaoAgence {

	private IDaoConseiller daoConseiller;

	public DaoAgenceMySQL(IDaoConseiller daoConseiller) throws JdbcDaoException {
		super();
		this.daoConseiller = new DaoConseillerMySQL();
	}

	public DaoAgenceMySQL() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insert(Agence agence) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Agence agence) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Agence agence) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.proxibanquev1.dao.IDaoAgence#selectAll()
	 */
	@Override
	public List<Agence> selectAll() throws JdbcDaoException {

		List<Agence> lstAg = new ArrayList<>();

		try {
			Connection cnx = BDD.getConnection();

			String selectAllAgenceSQL = "SELECT IDAGENCEBANCAIRE, DATECREATION FROM AGENCEBANCAIRE";
			selectAllAgenceSQL = selectAllAgenceSQL.toLowerCase();

			Statement stat = cnx.createStatement();

			ResultSet rslts = stat.executeQuery(selectAllAgenceSQL);

			while (rslts.next()) {
				lstAg.add(new Agence(rslts.getInt("IDAGENCEBANCAIRE"), rslts.getDate("DATECREATION"),
						daoConseiller.selectConseillerByType(Conseiller.TYPEGERANT), daoConseiller.getAllElement()));
			}

		} catch (DaoException | SQLException ex) {
			throw new JdbcDaoException("DaoAgenceMySQl : selectAll() : " + ex);
		}

		return lstAg;
	}

	/* (non-Javadoc)
	 * @see com.proxibanque.dao.IDaoAgence#selectAgenceById(int)
	 */
	@Override
	public Agence selectAgenceById(int idAgence) {

		Agence ag = null;

		try {
			Connection cnx = BDD.getConnection();

			String selectAllAgenceSQL = "SELECT IDAGENCEBANCAIRE, DATECREATION " + "FROM AGENCEBANCAIRE "
					+ "WHERE IDAGENCEBANCAIRE=" + idAgence;
			selectAllAgenceSQL = selectAllAgenceSQL.toLowerCase();

			Statement stat = cnx.createStatement();

			ResultSet rslts = stat.executeQuery(selectAllAgenceSQL);

			if (rslts.next()) {
				ag = new Agence(rslts.getInt("IDAGENCEBANCAIRE"), rslts.getDate("DATECREATION"),
						daoConseiller.selectConseillerByType(Conseiller.TYPEGERANT),
						daoConseiller.selectAllByAgenceId(idAgence));
			}

		} catch (SQLException ex) {
			System.out.println("DaoAgenceMySQl : selectAgenceById() : " + ex.getMessage());
		}
		return ag;
	}

	@Override
	public int selectIdByDate(Date dateCreation) {
		// TODO Auto-generated method stub
		return 0;
	}

}
