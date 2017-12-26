package com.proxibanque.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.proxibanque.dao.JdbcDaoException;
import com.proxibanque.util.BDD;

/**
 * Class abstract DAO qui prepare les requetes CRUD en utilisant Template
 * Pattern
 *
 * @author JL JFA
 */
public abstract class AbstractJdbcDao {

	protected PreparedStatement pstmtInsert;
	protected PreparedStatement pstmtInsertGetKey;
	protected PreparedStatement pstmtSelectByKey;
	protected PreparedStatement pstmtSelectAll;
	protected PreparedStatement pstmtUpdateByKey;
	protected PreparedStatement pstmtDeleteByKey;

	public AbstractJdbcDao() throws JdbcDaoException {
		// Preparer les requetes
		prepareStatement();
	}

	/**
	 * Prepare l'ensemble des requetes devant etre utilises par les differents DAO 
	 * 
	 * @throws JdbcDaoException
	 */
	private void prepareStatement() throws JdbcDaoException {
		try {
			Connection mCnx = BDD.getConnection();
			// insertion
			pstmtInsert = mCnx.prepareStatement("insert into " + getTableName() + "(" + getTableColumns() + ") values("
					+ getAllColumnParams() + ")");

			// insertion d'objet et recupere ID auto genere
			pstmtInsertGetKey = mCnx.prepareStatement(
					"insert into " + getTableName() + "(" + getTableColumns().substring(getKeyName().length() + 2)
							+ ") values(" + getAllColumnParams().substring(2) + ")",
					Statement.RETURN_GENERATED_KEYS);

			// get by Key
			pstmtSelectByKey = mCnx.prepareStatement(
					"select " + getTableColumns() + " from " + getTableName() + " where " + getKeyName() + " = ?");

			// get all
			pstmtSelectAll = mCnx.prepareStatement(
					"select " + getTableColumns() + " from " + getTableName() + " order by " + getKeyName());

			// update
			// TODO: utiliser UPDATE au lieu de REPLACE INTO, il faut Tokeniser les colonnes
			// valorisees generiquement
			pstmtUpdateByKey = mCnx.prepareStatement("replace into " + getTableName() + "(" + getTableColumns()
					+ ") values(" + getAllColumnParams() + ")");

			// delete
			pstmtDeleteByKey = mCnx
					.prepareStatement("delete from " + getTableName() + " where " + getKeyName() + " = ?");

			// Deconnection

		} catch (SQLException ex) {
			throw new JdbcDaoException("AbstractJdbcDao.prepareStatement : preparation des requetes echoue " + ex);
		}
	}

	protected abstract String getTableName();

	protected abstract String getTableColumns();

	protected abstract String getKeyName();

	protected abstract String getAllColumnParams();
}
