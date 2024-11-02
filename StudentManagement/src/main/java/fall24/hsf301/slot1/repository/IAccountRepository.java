package fall24.hsf301.slot1.repository;

import fall24.hsf301.slot1.pojo.Account;

public interface IAccountRepository {
	Account findUser(String username, String password);
}
