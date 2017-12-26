package com.proxibanque.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.proxibanque.dao.DaoException;
import com.proxibanque.dao.IDaoCarteBancaire;
import com.proxibanque.dao.IDaoCompte;
import com.proxibanque.dao.JdbcDaoException;
import com.proxibanque.model.Client;
import com.proxibanque.model.Compte;
import com.proxibanque.model.CompteCourant;
import com.proxibanque.model.CompteEpargne;
import com.proxibanque.util.BDD;

/**
 * Class DAO Compte qui utilise les statement prepares CRUD mais pas tout implmentes 
 * 
 * @author JL JFA
 *
 */
public class DaoCompteMySQL extends AbstractJdbcDao implements IDaoCompte {

	private IDaoCarteBancaire daoCarteBancaire;

	private PreparedStatement pstmtUpdateCompteCourantForClient;
	private PreparedStatement pstmtUpdateCompteEpargneForClient;
	private PreparedStatement pstmtUpdateCompteSolde;

	public DaoCompteMySQL() throws JdbcDaoException {
		super();
		daoCarteBancaire = new DaoCarteBancaireMySQL();

		prepareStatement();
	}

	private void prepareStatement() throws JdbcDaoException {
		try {
			Connection cnx = BDD.getConnection();

			pstmtUpdateCompteCourantForClient = cnx.prepareStatement("update compte c, comptecourant cc set "
					+ "c.solde = ?, cc.decouvertautorise = ?, cc.numerocartebancaire = ? "
					+ "where c.numerocompte = cc.numerocompte and c.numerocompte = ?");

			pstmtUpdateCompteEpargneForClient = cnx
					.prepareStatement("update compte c, compteepargne ce set " + "c.solde = ?, ce.tauxannuel = ? "
							+ "where c.numerocompte = ce.numerocompte and c.numerocompte = ?");

			pstmtUpdateCompteSolde = cnx.prepareStatement("update compte set solde = ? where numerocompte = ?");

			// Deconnetion

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoCompteMySQL.prepareStatement : preparation des requetes echoue " + ex);
		}
	}

	@Override
	public CompteCourant selectCompteCourantByIdClient(int idClient) {

		CompteCourant cptCourant = null;

		try {
			Connection cnx = BDD.getConnection();
			String selectCompteCourantByIdClientSQL = "SELECT COMPTE.DATEOUVERTURE DATEOUVERTURE, COMPTE.SOLDE SOLDE, COMPTECOURANT.NUMEROCOMPTE NUMCOMPTE,"
					+ " COMPTECOURANT.DECOUVERTAUTORISE DECOUVERTAUTORISE, "
					+ " COMPTECOURANT.NUMEROCARTEBANCAIRE NUMEROCARTEBANCAIRE, COMPTE.IDCLIENT IDCLIENT"
					+ "    FROM COMPTECOURANT, COMPTE" + "    WHERE COMPTECOURANT.NUMEROCOMPTE = COMPTE.NUMEROCOMPTE"
					+ "    AND COMPTE.IDCLIENT = " + idClient;
			selectCompteCourantByIdClientSQL = selectCompteCourantByIdClientSQL.toLowerCase();
			Statement stat = cnx.createStatement();

			ResultSet rslts = stat.executeQuery(selectCompteCourantByIdClientSQL);

			if (rslts.next()) {
				cptCourant = new CompteCourant(rslts.getString("NUMCOMPTE"), rslts.getDouble("SOLDE"),
						rslts.getString("DATEOUVERTURE"), CompteCourant.TYPE_COMPTE,
						daoCarteBancaire.selectCarteByNumero(rslts.getString("NUMEROCARTEBANCAIRE")),
						rslts.getInt("DECOUVERTAUTORISE"));
			}

		} catch (SQLException ex) {
			System.out.println("DaoCompteMySQL : selectCompteCourantByIdClient() : " + ex.getMessage());
		}

		return cptCourant;
	}

	@Override
	public CompteEpargne selectCompteEpargneByIdClient(int idClient) {
		CompteEpargne cpteEpargne = null;

		try {
			Connection cnx = BDD.getConnection();
			String selectCompteEpargneByIdClientSQL = "SELECT COMPTE.DATEOUVERTURE DATEOUVERTURE, COMPTE.SOLDE SOLDE, compteepargne.NUMEROCOMPTE NUMCOMPTE,"
					+ " compteepargne.tauxannuel tauxannuel,  COMPTE.IDCLIENT IDCLIENT"
					+ "    FROM compteepargne, COMPTE" + "    WHERE compteepargne.NUMEROCOMPTE = COMPTE.NUMEROCOMPTE"
					+ "    AND COMPTE.IDCLIENT = " + idClient;
			selectCompteEpargneByIdClientSQL = selectCompteEpargneByIdClientSQL.toLowerCase();
			Statement stat = cnx.createStatement();

			ResultSet rslts = stat.executeQuery(selectCompteEpargneByIdClientSQL);

			while (rslts.next()) {
				cpteEpargne = new CompteEpargne(rslts.getString("NUMCOMPTE"), rslts.getDouble("SOLDE"),
						rslts.getString("DATEOUVERTURE"), CompteEpargne.TYPE_COMPTE);
			}

		} catch (SQLException ex) {
			System.out.println("DaoCompteMySQL : selectCompteEpargneByIdClient() : " + ex.getMessage());
		}

		return cpteEpargne;
	}

	@Override
	public void updateCompteCourantForClient(Client c) throws JdbcDaoException {
		try {
			pstmtUpdateCompteCourantForClient.setDouble(1, c.getCompteCourant().getSolde());
			pstmtUpdateCompteCourantForClient.setInt(2, c.getCompteCourant().getDecouvertAutorise());
			pstmtUpdateCompteCourantForClient.setString(3, c.getCompteCourant().getCarteBancaire().NUMERO_CARTE);
			pstmtUpdateCompteCourantForClient.setString(4, c.getCompteCourant().getNumeroCompte());

			if (0 == pstmtUpdateCompteCourantForClient.executeUpdate()) {
				throw new JdbcDaoException(
						"DaoCompteMySQL.updateCompteCourantForClient : modification de compte courant echoue, "
								+ c.getCompteCourant());
			}

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoCompteMySQL.updateCompteCourantForClient : Probleme de requete, " + ex);
		}
	}

	@Override
	public void updateCompteEpargneForClient(Client c) throws JdbcDaoException {
		try {
			pstmtUpdateCompteEpargneForClient.setDouble(1, c.getCompteEpargne().getSolde());
			pstmtUpdateCompteEpargneForClient.setDouble(2, c.getCompteEpargne().getTauxRemuneration().getTaux());
			pstmtUpdateCompteEpargneForClient.setString(3, c.getCompteEpargne().getNumeroCompte());

			if (0 == pstmtUpdateCompteEpargneForClient.executeUpdate()) {
				throw new JdbcDaoException(
						"DaoCompteMySQL.updateCompteEpargneForClient : modification de compte epargen echoue, "
								+ c.getCompteEpargne());
			}

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoCompteMySQL.updateCompteEpargneForClient : Probleme de requete, " + ex);
		}
	}

	@Override
	public Compte getCompteExist(String noCompte) throws JdbcDaoException {
		try {
			pstmtSelectByKey.setString(1, noCompte);

			ResultSet res = pstmtSelectByKey.executeQuery();

			if (res.next()) {
				String no = res.getString(1);
				String date = res.getString(2);
				double solde = res.getDouble(3);
				String type = res.getString(4);
				
				// Ici c'est pas propre. A revoir le modele Compte/ CompteCourant / CompteEpargne
				// Dans les tables, avec un numero de compte, à priopri, on ne sais pas son type de compte
				return new CompteCourant(no, solde, date, type, null, 0);
			} else {
				return null;
			}
		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoCompteMySQL.getCompteExist : Probleme de requete, " + ex);
		}
	}

	@Override
	public void updateCompteSolde(Compte c) throws DaoException {
		try {
			pstmtUpdateCompteSolde.setDouble(1, c.getSolde());
			pstmtUpdateCompteSolde.setString(2, c.getNumeroCompte());

			if (0 == pstmtUpdateCompteSolde.executeUpdate()) {
				throw new JdbcDaoException(
						"DaoCompteMySQL.updateCompteSolde : modification de compte solde echoue, "
								+ c);
			}

		} catch (SQLException ex) {
			throw new JdbcDaoException("DaoCompteMySQL.updateCompteSolde : Probleme de requete, " + ex);
		}

	}

	@Override
	protected String getTableName() {
		return Compte.TABLENAME;
	}

	@Override
	protected String getTableColumns() {
		return Compte.TABLECOLUMNS;
	}

	@Override
	protected String getKeyName() {
		return Compte.KEYNAME;
	}

	@Override
	protected String getAllColumnParams() {
		return Compte.ALLCOLUMNPARAMS;
	}

}
