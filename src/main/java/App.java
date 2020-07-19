import domain.Account;
import repo.AccountRepo;
import service.AccountService;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException {
		AccountService accountService = new AccountService(new AccountRepo());

		Account otherlogin = accountService.findById("somelogin");
		System.out.println("first entry: " +  otherlogin);

		accountService.updateLastName(new Account("otherlogin", "firstName", "Sidorov"));
		Account somelogin = accountService.findById("otherlogin");
		System.out.println("updated second entry: " + somelogin);
	}
}
