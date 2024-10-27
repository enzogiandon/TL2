package entregable_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	private static Connection con = null;
	static {
		try {
			con = DriverManager.getConnection("jdbc:sqlite:billetera.db");
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}
	}
	public static Connection getCon() {
		return con;
	}
	private MyConnection() {
		
	}

}
