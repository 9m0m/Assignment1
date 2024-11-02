package fall24.hsf301.slot1.repository;

import fall24.hsf301.slot1.dao.AccountDAO;
import fall24.hsf301.slot1.pojo.Account;

public class AccountRepository implements IAccountRepository {
	private AccountDAO accountDAO;

	public AccountRepository(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	public Account findUser(String username, String password) {
		return accountDAO.findByUsernameAndPassword(username, password);
	}
}
