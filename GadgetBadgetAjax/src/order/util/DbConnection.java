package order.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * prepare connection to the DB
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 *
 */
public class DbConnection {
	
	// Provide the correct details: DBServer/DBName, username, password
	private static String url = "jdbc:mysql://localhost:3306/productservicedb";
	private static String username = "root";
	private static String password = "Asiyaamysql1";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
