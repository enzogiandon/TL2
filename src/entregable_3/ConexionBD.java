package entregable_3;

import java.sql.*;
import org.sqlite.SQLiteDataSource;

public class ConexionBD {

	// Atributos
	private static final String URL = "jdbc:sqlite:test.db";
	private static SQLiteDataSource ds = null;

	// Constructor privado para evitar crear instancias
	private ConexionBD() {
	}

	// Comportamiento
	static {
		try {
			ds = new SQLiteDataSource();
			ds.setUrl(URL);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// getters
	public static SQLiteDataSource getDataSource() {
		return ds;
	}

	public static Connection getConnection() throws SQLException {
		if (ds != null)
			return ds.getConnection();
		else
			throw new SQLException("DataSource no inicializado");
	}
}
