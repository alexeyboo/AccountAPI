package utils;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnectionUtils {
	private static DBConnectionUtils instance;
	private static Connection connection;

	private DBConnectionUtils() {
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ScriptRunner scriptRunner = new ScriptRunner(connection);
		try {
			scriptRunner.runScript(new FileReader("src\\main\\resources\\schema.sql"));
			scriptRunner.runScript(new FileReader("src\\main\\resources\\test-data.sql"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (instance == null) {
			synchronized (DBConnectionUtils.class) {
				if (instance == null) {
					instance = new DBConnectionUtils();
				}
			}
		}
		return connection;
	}
}
