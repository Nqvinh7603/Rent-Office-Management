package site.rentofficevn.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@PropertySource("classpath:application.properties")
public class ConnectionUtils {

	@Value("${spring.datasource.url}")
	private static String DB_URL;

	@Value("${spring.datasource.username}")
	private static String USER;

	@Value("${spring.datasource.password}")
	private static String PASS;
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}