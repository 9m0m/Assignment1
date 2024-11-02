package fall24.hsf301.slot1.service;

import fall24.hsf301.slot1.pojo.Account;
import fall24.hsf301.slot1.repository.IAccountRepository;

public class AccountService implements IAccountService {
	private IAccountRepository accountRepository;

	public AccountService(IAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public Account login(String username, String password) {
		return accountRepository.findUser(username, password);
	}
}
