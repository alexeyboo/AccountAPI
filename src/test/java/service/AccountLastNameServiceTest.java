package service;

import domain.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repo.AccountRepoImpl;

import static org.mockito.Mockito.when;

public class AccountLastNameServiceTest {

	private AccountLastNameService accountLastNameService;

	@Mock
	private AccountRepoImpl accountRepoImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		accountLastNameService = new AccountLastNameService(accountRepoImpl);
	}

	@Test
	public void findById() {
		Account before = new Account("login", "Dmitry", "Vavilov");

		when(accountRepoImpl.findById("login")).thenReturn(before);

		Account after = accountLastNameService.findById("login");

		Assert.assertEquals(before, after);
	}

	@Test
	public void updateLastName() {
		Account before = new Account("login", "Dmitry", "Vavilov");

//		when(accountRepo.update(before)).thenReturn(before);
		when(accountRepoImpl.findById(before.getId())).thenReturn(before);

		Account after = accountLastNameService.updateLastName(before);

		Assert.assertEquals(before.getLastName(), after.getLastName());
	}
}