package repo;

import domain.Account;

public interface AccountRepo<T> {

	T findById(String id);

	T update(Account account);

}
