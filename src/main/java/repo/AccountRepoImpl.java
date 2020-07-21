package repo;

import domain.Account;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import utils.DBConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepoImpl implements AccountRepo<Account> {
	private static final String FIND_SQL_REQUEST = "SELECT * FROM account WHERE id = ?;";
	private static final String UPDATE_SQL_REQUEST = "UPDATE account SET firstname = ?, lastname = ? WHERE id = ?;";
	private static final String FIRST_NAME_COLUMN = "firstname";
	private static final String LAST_NAME_COLUMN = "lastname";

	@Override
	public Account findById(String id) {
		PreparedStatement preparedStatement = getPreparedStatement(FIND_SQL_REQUEST);
		Account account = null;
		try {
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String firstName = resultSet.getString(FIRST_NAME_COLUMN);
				String lastName = resultSet.getString(LAST_NAME_COLUMN);
				account = new Account(id, firstName, lastName);
			}
		} catch (SQLException throwables) {
			LogManager.getLogger().log(Level.ERROR, "Couldn't execute SELECT query in " + getClass().getName());
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
			LogManager.getLogger().log(Level.ERROR, "Couldn't execute UPDATE query in " + getClass().getName());
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
