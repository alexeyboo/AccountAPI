package service;

import domain.Account;
import repo.AccountRepo;

public class AccountLastNameService implements AccountService<Account> {

	private final AccountRepo<Account> accountRepo;

	public AccountLastNameService(AccountRepo<Account> accountRepo) {
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
