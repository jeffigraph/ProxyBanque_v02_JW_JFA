package com.proxibanque.service;

import com.proxibanque.dao.IDaoClient;
import com.proxibanque.dao.IDaoCompte;
import com.proxibanque.dao.IDaoConseiller;
import com.proxibanque.dao.IDaoVirement;
import com.proxibanque.dao.JdbcDaoException;
import com.proxibanque.dao.mysql.DaoClientMySQL;
import com.proxibanque.dao.mysql.DaoCompteMySQL;
import com.proxibanque.dao.mysql.DaoConseillerMySQL;
import com.proxibanque.dao.mysql.DaoVirementMySQL;
import com.proxibanque.util.BDD;

/**
 * Classe de service, elle regroupe les differents services fournis par
 * l'Application. C'est ici que les objets implementant une interface DAO sont
 * instanties. Le type de DAO peut etre configure par Config - actuellement en
 * Memoire
 * 
 * A faire : rentre ce Engine en Singleton
 * 
 * @author JL JFA
 *
 */
public class ServiceEngine {

	private static final String URL = "jdbc:mysql://localhost:3306/proxybanque";
	private static final String USER = "proxybanque";
	private static final String PASSWORD = "tp_01";

	// private IDaoAgence mDaoAgence;
	private IDaoClient mDaoClient;
	private IDaoConseiller mDaoConseiller;
	private IDaoCompte mDaoCompte;
	private IDaoVirement mDaoVirement;

	private ServiceAgence serviceAgence;
	private ServiceLogin serviceLogin;
	private ServiceGestionClient serviceGestionClient;
	private ServiceVirement serviceVirement;

	/**
	 * Instancie l'ensemble des services accedants a la base de donnees
	 * 
	 * @throws JdbcDaoException
	 */
	public ServiceEngine() throws JdbcDaoException {

		BDD.addDatabase(URL, USER, PASSWORD);

		// this.mDaoAgence = new DaoAgenceMySQL();
		this.mDaoClient = new DaoClientMySQL();
		this.mDaoConseiller = new DaoConseillerMySQL();
		this.mDaoCompte = new DaoCompteMySQL();
		this.mDaoVirement = new DaoVirementMySQL();

		// serviceAgence = new ServiceAgence(mDaoAgence);
		serviceLogin = new ServiceLogin(mDaoConseiller);
		serviceGestionClient = new ServiceGestionClient(mDaoClient);
		serviceVirement = new ServiceVirement(mDaoCompte, mDaoVirement);
	}

	public ServiceLogin getServiceLogin() {
		return serviceLogin;
	}

	public ServiceAgence getServiceAgence() {
		return serviceAgence;
	}

	public ServiceGestionClient getServiceGestionClient() {
		return serviceGestionClient;
	}

	public ServiceVirement getServiceVirement() {
		return serviceVirement;
	}

}
