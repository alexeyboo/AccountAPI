import domain.Account;
import repo.AccountRepoImpl;
import service.AccountLastNameService;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException {
		AccountLastNameService accountLastNameService = new AccountLastNameService(new AccountRepoImpl());

		Account otherlogin = accountLastNameService.findById("somelogin");
		System.out.println("first entry: " +  otherlogin);

		accountLastNameService.updateLastName(new Account("otherlogin", "firstName", "Sidorov"));
		Account somelogin = accountLastNameService.findById("otherlogin");
		System.out.println("updated second entry: " + somelogin);
	}
}
