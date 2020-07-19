package service;

import domain.Account;

public interface Service<T> {
	T findById(String id);

	T updateLastName(Account account);
}
