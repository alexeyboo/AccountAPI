package service;

import domain.Account;

public interface AccountService<T> {
	T findById(String id);

	T updateLastName(Account account);
}
