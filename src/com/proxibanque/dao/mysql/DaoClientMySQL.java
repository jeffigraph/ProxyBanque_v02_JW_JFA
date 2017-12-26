package com.proxibanque.dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proxibanque.dao.DaoException;
import com.proxibanque.dao.IDaoClient;
import com.proxibanque.dao.IDaoCompte;
import com.proxibanque.dao.JdbcDaoException;
import com.proxibanque.model.Client;
import com.proxibanque.util.BDD;

/**
 * Class DAO client qui utilise les statement prepares CRUD et interface generique 
 * 
 * @author JL FJA
 *
 */
public class DaoClientMySQL extends AbstractJdbcDao implements IDaoClient {

	private IDaoCompte daoCompte;

	public DaoClientMySQL() throws JdbcDaoException {
		daoCompte = new DaoCompteMySQL();
	}

	@Override
	public void addElement(Client t) throws DaoException {
		try {
			pstmtInsert.setLong(1, t.getIdClient());
			pstmtInsert.setString(2, t.getNom());
			pstmtInsert.setString(3, t.getPrenom());
			pstmtInsert.setString(4, t.getAdresse());
			pstmtInsert.setString(5, t.getVille());
			pstmtInsert.setInt(6, t.getCodePostal());
			pstmtInsert.setString(7, t.getTelephone());

			if (0 == pstmtInsert.executeUpdate()) {
				throw new JdbcDaoException("DaoClientMySQL.addElement : ajout de conseiller echoue, " + t);
			}

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoClientMySQL.addElement : Probleme de requete, " + ex);
		}
	}

	/* (non-Javadoc)
	 * @see com.proxibanque.dao.IDaoObjet#addElementAndGetKey(java.lang.Object)
	 */
	@Override
	public void addElementAndGetKey(Client t) throws DaoException {
		try {
			pstmtInsertGetKey.setString(1, t.getNom());
			pstmtInsertGetKey.setString(2, t.getPrenom());
			pstmtInsertGetKey.setString(3, t.getAdresse());
			pstmtInsertGetKey.setString(4, t.getVille());
			pstmtInsertGetKey.setInt(5, t.getCodePostal());
			pstmtInsertGetKey.setString(6, t.getTelephone());

			if (0 == pstmtInsertGetKey.executeUpdate()) {
				throw new JdbcDaoException("DaoClientMySQL.addElementAndGetKey : ajout de conseiller echoue, " + t);
			} else {
				// recupre ID
				ResultSet rs = pstmtInsertGetKey.getGeneratedKeys();
				rs.first();
				t.setIdClient(Integer.parseInt(rs.getString(1)));
			}

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoClientMySQL.addElementAndGetKey : Probleme de requete, " + ex);
		}
	}

	@Override
	public Client getElementById(long k) throws DaoException {
		try {
			pstmtSelectByKey.setLong(1, k);

			ResultSet res = pstmtSelectByKey.executeQuery();

			if (res.next()) {
				int id = res.getInt(1);
				String nom = res.getString(2);
				String prenom = res.getString(3);
				String adresse = res.getString(4);
				String ville = res.getString(5);
				int codepostal = res.getInt(6);
				String tel = res.getString(7);

				return new Client(id, nom, prenom, adresse, ville, codepostal, tel,
						daoCompte.selectCompteCourantByIdClient(id), daoCompte.selectCompteEpargneByIdClient(id));
			} else {
				throw new JdbcDaoException("DaoClientMySQL.getElementById : Conseiller non trouve " + k);
			}
		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoClientMySQL.getElementById : Probleme de requete, " + ex);
		}
	}

	@Override
	public List<Client> getAllElement() throws DaoException {
		try {
			ResultSet res = pstmtSelectAll.executeQuery();

			List<Client> allClient = new ArrayList<>();
			while (res.next()) {
				int id = res.getInt(1);
				String nom = res.getString(2);
				String prenom = res.getString(3);
				String adresse = res.getString(4);
				String ville = res.getString(5);
				int codepostal = res.getInt(6);
				String tel = res.getString(7);

				allClient.add(new Client(id, nom, prenom, adresse, ville, codepostal, tel,
						daoCompte.selectCompteCourantByIdClient(id), daoCompte.selectCompteEpargneByIdClient(id)));
			}
			return allClient;
		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoClientMySQL.getAllElement : Probleme de requete, " + ex);
		}
	}

	@Override
	public void updateElement(Client t) throws DaoException {
		try {
			Connection cnx = BDD.getConnection();

			pstmtUpdateByKey = cnx.prepareStatement("update  " + getTableName()
					+ " set nom = ?, prenom = ?, adresse = ?, ville = ?, codepostal = ?, telephone = ? "
					+ " where idclient = ?");

			pstmtUpdateByKey.setString(1, t.getNom());
			pstmtUpdateByKey.setString(2, t.getPrenom());
			pstmtUpdateByKey.setString(3, t.getAdresse());
			pstmtUpdateByKey.setString(4, t.getVille());
			pstmtUpdateByKey.setInt(5, t.getCodePostal());
			pstmtUpdateByKey.setString(6, t.getTelephone());
			pstmtUpdateByKey.setLong(7, t.getIdClient());

			if (0 == pstmtUpdateByKey.executeUpdate()) {
				throw new JdbcDaoException("DaoClientMySQL.updateElement : modification de client echoue, " + t);
			}

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoClientMySQL.updateElement : Probleme de requete, " + ex);
		}
	}

	@Override
	public void deleteElement(long key) throws DaoException {
		try {
			pstmtDeleteByKey.setLong(1, key);

			if (0 == pstmtDeleteByKey.executeUpdate()) {
				throw new JdbcDaoException("DaoClientMySQL.deleteElement : supresse de client echoue, " + key);
			}

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoClientMySQL.deleteElement : Probleme de requete, " + ex);
		}

	}

	@Override
	public List<Client> selectAllClientByConseillerId(int idConseiller) {

		List<Client> listClients = new ArrayList<>();

		// fetch and return the list of all Clients from the Database in a Map order by
		// the idCLient
		try {
			Connection cnx = BDD.getConnection();
			String selectAllClientSQL = "SELECT DISTINCT IDCLIENT, NOM, PRENOM, ADRESSE, VILLE, CODEPOSTAL, TELEPHONE"
					+ "     FROM COMPTES_CLIENTS" + "     WHERE IDCONSEILLER=" + idConseiller;
			selectAllClientSQL = selectAllClientSQL.toLowerCase();

			System.out.println(selectAllClientSQL);

			Statement stat = cnx.createStatement();

			ResultSet rslts = stat.executeQuery(selectAllClientSQL);

			while (rslts.next()) {
				listClients.add(new Client(rslts.getInt("IDCLIENT"), rslts.getString("NOM"), rslts.getString("PRENOM"),
						rslts.getString("ADRESSE"), rslts.getString("VILLE"), rslts.getInt("CODEPOSTAL"),
						rslts.getString("TELEPHONE"), daoCompte.selectCompteCourantByIdClient(rslts.getInt("IDCLIENT")),
						daoCompte.selectCompteEpargneByIdClient(rslts.getInt("IDCLIENT"))));
			}
		} catch (SQLException ex) {
			System.out.println("DaoClientMySQL : selectAllClientByConseillerId() : " + ex.getMessage());
		}
		return listClients;
	}

	@Override
	protected String getTableName() {
		return Client.TABLENAME;
	}

	@Override
	protected String getTableColumns() {
		return Client.TABLECOLUMNS;
	}

	@Override
	protected String getKeyName() {
		return Client.KEYNAME;
	}

	@Override
	protected String getAllColumnParams() {
		return Client.ALLCOLUMNPARAMS;
	}
}
