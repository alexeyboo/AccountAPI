package repo;

import domain.Account;

public interface Repo<T> {

	T findById(String id);

	T update(Account account);

}
