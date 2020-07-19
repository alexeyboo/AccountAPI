package service;

import domain.Account;
import repo.Repo;

public class AccountService implements Service<Account> {

	private final Repo<Account> accountRepo;

	public AccountService(Repo<Account> accountRepo) {
		this.accountRepo = accountRepo;
	}

	@Override
	public Account findById(String id) {
		Account account = accountRepo.findById(id);

		return account;
	}

	@Override
	public Account updateLastName(Account account) {
		Account existedAccount = accountRepo.findById(account.getId());
		existedAccount.setLastName(account.getLastName());
		accountRepo.update(existedAccount);

		return existedAccount;
	}
}
