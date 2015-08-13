package example01.addrbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://blueskii.iptime.org:3306/team1";
		Connection conn;
		Class.forName(driver);
		conn = DriverManager.getConnection(url,"team1","123456");
		return conn;
	}
}
