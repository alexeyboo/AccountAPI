package utils;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnectionUtils {
	private static DBConnectionUtils instance;
	private static Connection connection;
	private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String DATABASE_USER = "postgres";
	private static final String DATABASE_USER_PASSWORD = "1234";

	private DBConnectionUtils() {
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
		} catch (SQLException e) {
			LogManager.getLogger().log(Level.ERROR, "Couldn't establish the connection to database");
		}
		ScriptRunner scriptRunner = new ScriptRunner(connection);
		try {
			scriptRunner.runScript(new FileReader("src\\main\\resources\\schema.sql"));
			LogManager.getLogger().log(Level.DEBUG, "Init schema was created");
			scriptRunner.runScript(new FileReader("src\\main\\resources\\test-data.sql"));
			LogManager.getLogger().log(Level.DEBUG, "Test data were added");
		} catch (FileNotFoundException e) {
			LogManager.getLogger().log(Level.ERROR, "Couldn't find SQL initialization files");
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
