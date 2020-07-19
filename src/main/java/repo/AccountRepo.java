package repo;

import domain.Account;
import utils.DBConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepo implements Repo<Account> {
	private static final String FIND_SQL_REQUEST = "SELECT * FROM account WHERE id = ?;";
	private static final String UPDATE_SQL_REQUEST = "UPDATE account SET firstname = ?, lastname = ? WHERE id = ?;";

	@Override
	public Account findById(String id) {
		PreparedStatement preparedStatement = getPreparedStatement(FIND_SQL_REQUEST);
		Account account = null;
		try {
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				account = new Account(id, firstName, lastName);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return account;
	}

	@Override
	public Account update(Account account) {
		PreparedStatement preparedStatement = getPreparedStatement(UPDATE_SQL_REQUEST);
		try {
			String id = account.getId();
			String firstName = account.getFirstName();
			String lastName = account.getLastName();
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, id);
			preparedStatement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return account;
	}

	private PreparedStatement getPreparedStatement(String sql) {
		Connection connection = DBConnectionUtils.getConnection();
		try {
			return connection.prepareStatement(sql);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return null;
	}
}
