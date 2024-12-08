package entregable_3;

import java.sql.*;

//

public class CreacionBD {

	/**
	 * Este método se encarga de la creación de las tablas donde se almacenará la
	 * información de los objetos. Una vez establecida una conexión, debería ser lo
	 * próximo a ser ejecutado.
	 *
	 * @param connection objeto conexion a la base de datos SQLite
	 * @throws SQLException
	 */

	public static void crearTablas() throws SQLException {
		try (Connection con = ConexionBD.getConnection()) {
			creacionDeTablasEnBD(con);
		}
	}

	private static void creacionDeTablasEnBD(Connection connection) throws SQLException {
		Statement stmt = null;

		try {
			stmt = connection.createStatement();

			// Tabla moneda
			String sql = "CREATE TABLE IF NOT EXISTS MONEDA " + "(" + " TIPO       VARCHAR(1)    NOT NULL, "
					+ " NOMBRE       VARCHAR(50)    NOT NULL, " + " NOMENCLATURA VARCHAR(10)  PRIMARY KEY   NOT NULL, "
					+ " VALOR_DOLAR	REAL     NOT NULL, " + " VOLATILIDAD	REAL     NULL, " + " STOCK	REAL     NULL "
					+ ")";
			stmt.executeUpdate(sql);

			// Tabla activo cripto
			sql = "CREATE TABLE IF NOT EXISTS ACTIVO_CRIPTO" + "("
					+ " NOMENCLATURA VARCHAR(10)  PRIMARY KEY     NOT NULL, " + " CANTIDAD	REAL    NOT NULL " + ")";
			stmt.executeUpdate(sql);

			// Tabla activo fiat
			sql = "CREATE TABLE IF NOT EXISTS ACTIVO_FIAT" + "("
					+ " NOMENCLATURA VARCHAR(10)  PRIMARY KEY     NOT NULL, " + " CANTIDAD	REAL    NOT NULL " + ")";
			stmt.executeUpdate(sql);

			// Tabla transaccion
			sql = "CREATE TABLE IF NOT EXISTS TRANSACCION" + "(" + " RESUMEN VARCHAR(1000)   NOT NULL, "
					+ " FECHA_HORA		DATETIME  NOT NULL " + ")";
			stmt.executeUpdate(sql);
			
			//Tabla Persona
			sql = "CREATE TABLE  IF NOT EXISTS PERSONA "
					+ "("
					+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
					+ " NOMBRES       VARCHAR(50)    NOT NULL, "
					+ " APELLIDOS       VARCHAR(50)    NOT NULL "
					+ ")";
			stmt.executeUpdate(sql);
			
			//Tabla Usuario
			sql = "CREATE TABLE  IF NOT EXISTS USUARIO " + "(" + " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
					+ " ID_PERSONA       INTEGER   NOT NULL, "
					+ " EMAIL       VARCHAR(50)    NOT NULL, "
					+ " PASSWORD       VARCHAR(50)    NOT NULL, "
					+ " ACEPTA_TERMINOS       BOOLEAN    NOT NULL, "
					+ " FOREIGN KEY(ID_PERSONA) REFERENCES PERSONA(ID)"
					+ ")";
			stmt.executeUpdate(sql);

			System.out.println("Las tablas se crearon exitosamente");

		} catch (SQLException e) {
			System.out.println("Error al crear las tablas: " + e.getMessage());
			e.printStackTrace();
			throw e;

		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}

	}
}