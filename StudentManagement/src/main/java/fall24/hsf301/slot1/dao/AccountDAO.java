package fall24.hsf301.slot1.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fall24.hsf301.slot1.pojo.Account;

public class AccountDAO {
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public AccountDAO(String persistenceName) {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(persistenceName);
		}
		this.em = emf.createEntityManager();
	}

	public Account findByUsernameAndPassword(String username, String password) {
		Account account = null;
		try {
			TypedQuery<Account> query = em.createQuery(
					"SELECT a FROM Account a WHERE a.username = :username AND a.password = :password", Account.class);
			query.setParameter("username", username);
			query.setParameter("password", password);
			account = query.getSingleResult();
		} catch (NoResultException e) {

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return account;
	}
}
