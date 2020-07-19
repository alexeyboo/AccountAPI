package service;

import domain.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repo.AccountRepo;

import static org.mockito.Mockito.when;

public class AccountServiceTest {

	private AccountService accountService;

	@Mock
	private AccountRepo accountRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		accountService = new AccountService(accountRepo);
	}

	@Test
	public void findById() {
		Account before = new Account("login", "Dmitry", "Vavilov");

		when(accountRepo.findById("login")).thenReturn(before);

		Account after = accountService.findById("login");

		Assert.assertEquals(before, after);
	}

	@Test
	public void updateLastName() {
		Account before = new Account("login", "Dmitry", "Vavilov");

//		when(accountRepo.update(before)).thenReturn(before);
		when(accountRepo.findById(before.getId())).thenReturn(before);

		Account after = accountService.updateLastName(before);

		Assert.assertEquals(before.getLastName(), after.getLastName());
	}
}