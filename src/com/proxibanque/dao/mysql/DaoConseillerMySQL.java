package com.proxibanque.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proxibanque.dao.DaoException;
import com.proxibanque.dao.IDaoClient;
import com.proxibanque.dao.IDaoConseiller;
import com.proxibanque.dao.JdbcDaoException;
import com.proxibanque.model.Client;
import com.proxibanque.model.Conseiller;
import com.proxibanque.util.BDD;

/**
 * Class DAO Conseiller qui utilise les statement CRUD prepares et interface
 * generique
 * 
 * @author JL JFA
 *
 */
public class DaoConseillerMySQL extends AbstractJdbcDao implements IDaoConseiller {

	private IDaoClient mDaoClient;

	protected PreparedStatement pstmtLogin;

	public DaoConseillerMySQL() throws JdbcDaoException {
		mDaoClient = new DaoClientMySQL();

		prepareStatement();
	}

	private void prepareStatement() throws JdbcDaoException {
		try {
			Connection mCnx = BDD.getConnection();

			// login
			pstmtLogin = mCnx.prepareStatement("select " + getTableColumns() + " from " + getTableName()
					+ " where `login` = ? and `password` = md5(?)");

			// deconnection

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoConseillerMySQL.prepareStatement : preparation des requetes echoue " + ex);
		}
	}

	@Override
	public void addElement(Conseiller t) throws DaoException {
		try {
			pstmtInsert.setLong(1, t.getId());
			pstmtInsert.setString(2, t.getTypeConseiller());
			pstmtInsert.setString(3, t.getNom());
			pstmtInsert.setString(4, t.getPrenom());

			if (0 == pstmtInsert.executeUpdate()) {
				throw new JdbcDaoException("DaoConseillerMySQL.addElement : ajout de conseiller echoue, " + t);
			}

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoConseillerMySQL.addElement : Probleme de requete, " + ex);
		}
	}

	@Override
	public void addElementAndGetKey(Conseiller t) throws DaoException {
		try {
			pstmtInsertGetKey.setString(1, t.getTypeConseiller());
			pstmtInsertGetKey.setString(2, t.getNom());
			pstmtInsertGetKey.setString(3, t.getPrenom());

			if (0 == pstmtInsertGetKey.executeUpdate()) {
				throw new JdbcDaoException("DaoClientMySQL.addElementAndGetKey : ajout de conseiller echoue, " + t);
			} else {
				// recupre ID
				ResultSet rs = pstmtInsertGetKey.getGeneratedKeys();
				rs.first();
				t.setId(Integer.parseInt(rs.getString(1)));
			}

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoConseillerMySQL.addElementAndGetKey : Probleme de requete, " + ex);
		}
	}

	@Override
	public Conseiller getElementById(long k) throws DaoException {
		try {
			pstmtSelectByKey.setLong(1, k);

			ResultSet res = pstmtSelectByKey.executeQuery();

			if (res.next()) {
				int id = res.getInt(1);
				String type = res.getString(2);
				String nom = res.getString(3);
				String prenom = res.getString(4);

				List<Client> portefeuilleClients = mDaoClient.selectAllClientByConseillerId(id);

				return new Conseiller(id, nom, prenom, type, portefeuilleClients);
			} else {
				throw new JdbcDaoException("DaoConseillerMySQL.getElementById : Conseiller non trouve " + k);
			}
		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoConseillerMySQL.getElementById : Probleme de requete, " + ex);
		}
	}

	@Override
	public List<Conseiller> getAllElement() throws DaoException {
		try {
			ResultSet res = pstmtSelectAll.executeQuery();

			List<Conseiller> allConseiller = new ArrayList<>();
			while (res.next()) {
				int id = res.getInt(1);
				String type = res.getString(2);
				String nom = res.getString(3);
				String prenom = res.getString(4);
				List<Client> portefeuilleClients = mDaoClient.selectAllClientByConseillerId(id);

				allConseiller.add(new Conseiller(id, nom, prenom, type, portefeuilleClients));
			}
			return allConseiller;
		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoConseillerMySQL.getAllElement : Probleme de requete, " + ex);
		}
	}

	@Override
	public void updateElement(Conseiller t) throws DaoException {
		try {
			pstmtUpdateByKey.setLong(1, t.getId());
			pstmtUpdateByKey.setString(2, t.getTypeConseiller());
			pstmtUpdateByKey.setString(3, t.getNom());
			pstmtUpdateByKey.setString(4, t.getPrenom());

			if (0 == pstmtUpdateByKey.executeUpdate()) {
				throw new JdbcDaoException(
						"DaoConseillerMySQL.updateElement : modification de conseiller echoue, " + t);
			}

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoConseillerMySQL.updateElement : Probleme de requete, " + ex);
		}
	}

	@Override
	public void deleteElement(long key) throws DaoException {
		try {
			pstmtDeleteByKey.setLong(1, key);

			if (0 == pstmtDeleteByKey.executeUpdate()) {
				throw new JdbcDaoException("DaoConseillerMySQL.deleteElement : supresse de conseiller echoue, " + key);
			}

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoConseillerMySQL.deleteElement : Probleme de requete, " + ex);
		}

	}

	@Override
	public Conseiller selectConseillerByLogin(String login, String psw) throws JdbcDaoException {
		try {
			pstmtLogin.setString(1, login);
			pstmtLogin.setString(2, psw);

			ResultSet res = pstmtLogin.executeQuery();

			if (res.next()) {
				int id = res.getInt(1);
				String type = res.getString(2);
				String nom = res.getString(3);
				String prenom = res.getString(4);

				return new Conseiller(id, nom, prenom, type, null);
			} else {
				throw new JdbcDaoException(
						"DaoConseillerMySQL.selectConseillerByLogin : Conseiller non trouve " + login);
			}
		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoConseillerMySQL.selectConseillerByLogin : Probleme de requete, " + ex);
		}
	}

	// @Override
	// public Conseiller selectConseillerById(int idConseiller) {
	// IDaoClient daoClient = new DaoClientMySQL();
	// Conseiller conseiller = null;
	// Connection cnx = null;
	// try {
	// cnx = BDD.seConnecter();
	// String selectAllAgenceSQL = "SELECT IDCONSEILLER, NOM, PRENOM ,
	// TYPECONSEILLER"
	// + " FROM PROXYBANQUE.CONSEILLER, AGENCEBANCAIRE, TYPECONSEILLER "
	// + " WHERE CONSEILLER.IDAGENCEBANCAIRE=AGENCEBANCAIRE.IDAGENCEBANCAIRE"
	// + " AND CONSEILLER.IDTYPECONSEILLER=TYPECONSEILLER.IDTYPECONSEILLER"
	// + " AND CONSEILLER.IDCONSEILLER=" + idConseiller;
	// selectAllAgenceSQL = selectAllAgenceSQL.toLowerCase();
	//
	// Statement stat = cnx.createStatement();
	//
	// ResultSet rslts = stat.executeQuery(selectAllAgenceSQL);
	//
	// if (rslts.next()) {
	// conseiller = new Conseiller(rslts.getInt("IDCONSEILLER"),
	// rslts.getString("NOM"),
	// rslts.getString("PRENOM"), rslts.getString("TYPECONSEILLER"),
	// daoClient.selectAllClientByConseillerId(rslts.getInt("IDCONSEILLER")));
	// }
	//
	// } catch (ClassNotFoundException | SQLException ex) {
	// System.out.println("DaoConseillerMySQL : selectConseillerById() : " +
	// ex.getMessage());
	// } finally {
	// if (cnx != null) {
	// BDD.seDeconnecter(cnx);
	// }
	// }
	// return conseiller;
	// }

	@Override
	public Conseiller selectConseillerByType(int typeConseiller) {

		Conseiller conseiller = null;
		try {
			Connection cnx = BDD.getConnection();
			String selectAllAgenceSQL = "SELECT IDCONSEILLER, NOM, PRENOM , TYPECONSEILLER"
					+ "    FROM PROXYBANQUE.CONSEILLER, AGENCEBANCAIRE, TYPECONSEILLER "
					+ "    WHERE CONSEILLER.IDAGENCEBANCAIRE=AGENCEBANCAIRE.IDAGENCEBANCAIRE"
					+ "    AND CONSEILLER.IDTYPECONSEILLER=TYPECONSEILLER.IDTYPECONSEILLER"
					+ "    AND CONSEILLER.IDTYPECONSEILLER=" + typeConseiller;
			selectAllAgenceSQL = selectAllAgenceSQL.toLowerCase();

			Statement stat = cnx.createStatement();

			ResultSet rslts = stat.executeQuery(selectAllAgenceSQL);

			if (rslts.next()) {
				conseiller = new Conseiller(rslts.getInt("IDCONSEILLER"), rslts.getString("NOM"),
						rslts.getString("PRENOM"), rslts.getString("TYPECONSEILLER"),
						mDaoClient.selectAllClientByConseillerId(rslts.getInt("IDCONSEILLER")));
			}

		} catch (SQLException ex) {
			System.out.println("DaoConseillerMySQL : selectConseillerById() : " + ex.getMessage());
		}
		return conseiller;
	}

	@Override
	public List<Conseiller> selectAllByAgenceId(int idAgence) {

		List<Conseiller> lstConseillers = new ArrayList<>();

		try {
			Connection cnx = BDD.getConnection();
			String selectAllAgenceSQL = "SELECT IDCONSEILLER, NOM, PRENOM , TYPECONSEILLER"
					+ "    FROM PROXYBANQUE.CONSEILLER, AGENCEBANCAIRE, TYPECONSEILLER "
					+ "    WHERE CONSEILLER.IDAGENCEBANCAIRE=AGENCEBANCAIRE.IDAGENCEBANCAIRE"
					+ "    AND CONSEILLER.IDTYPECONSEILLER=TYPECONSEILLER.IDTYPECONSEILLER"
					+ "    AND CONSEILLER.IDAGENCEBANCAIRE=" + idAgence;
			selectAllAgenceSQL = selectAllAgenceSQL.toLowerCase();

			Statement stat = cnx.createStatement();

			ResultSet rslts = stat.executeQuery(selectAllAgenceSQL);

			while (rslts.next()) {
				lstConseillers.add(new Conseiller(rslts.getInt("IDCONSEILLER"), rslts.getString("NOM"),
						rslts.getString("PRENOM"), rslts.getString("TYPECONSEILLER"),
						mDaoClient.selectAllClientByConseillerId(rslts.getInt("IDCONSEILLER"))));
			}

		} catch (SQLException ex) {
			System.out.println("DaoConseillerMySQL : selectAllByAgenceId() : " + ex.getMessage());
		}
		return lstConseillers;
	}

	@Override
	protected String getTableName() {

		return Conseiller.TABLENAME;
	}

	@Override
	protected String getTableColumns() {
		return Conseiller.TABLECOLUMNS;
	}

	@Override
	protected String getKeyName() {
		return Conseiller.KEYNAME;
	}

	@Override
	protected String getAllColumnParams() {
		return Conseiller.ALLCOLUMNPARAMS;
	}

}
