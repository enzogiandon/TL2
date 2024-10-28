package entregable_2;

import java.sql.*;
import java.util.Scanner;

// CLASE MAIN para hacer pruebas

public class Main {
	public static void main(String[] args) {
		try (Connection con = ConexionBD.getConnection()) {
			if (con != null) {
				System.out.println("Conexión establecida");

				// probar cosas acá

				// CreacionBD.crearTablas(con);
				MonedaDAOjdbc m = new MonedaDAOjdbc();
				m.crearMoneda(con);
				
				
				
				
				
				
				
				
				
				
				
			} else {
				System.out.println("No se pudo conectar con la base de datos.");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al conectar: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	

}
