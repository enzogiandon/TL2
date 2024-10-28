package entregable_2;

import java.sql.*;
import java.util.Scanner;

public class MonedaDAOjdbc implements MonedaDAO {

	@Override
	public boolean guardarMoneda(Moneda moneda) {
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, STOCK) VALUES (?,?,?,?,?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setCharacterStream(1, 'F');
			pst.setString(2, moneda.getNombre());
			pst.setString(3, moneda.getCodigo());
			pst.setDouble(3, moneda.getPrecioEnDolar());
			pst.setString(2, moneda.getStock());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Método para crear una moneda y almacenarla en la BD.
	 * 
	 * @param con La conexión con la base de datos
	 */

	@SuppressWarnings("finally")
	@Override
	public boolean crearMoneda(Connection con) throws SQLException {
		Scanner in = new Scanner(System.in);

		try {
			System.out.println("Ingrese los datos de la nueva moneda:");

			String tipo = "";
			while (!tipo.equalsIgnoreCase("Cripto") && !tipo.equalsIgnoreCase("FIAT")) {
				System.out.print("Elija un tipo de moneda (Cripto o FIAT): ");
				tipo = in.nextLine();
				if (!tipo.equalsIgnoreCase("Cripto") && !tipo.equalsIgnoreCase("FIAT")) {
					System.out.println("Tipo inválido. Debe ser 'Cripto' o 'FIAT'.");
				}
			}

			System.out.print("Nombre: ");
			String nombre = in.nextLine();

			System.out.print("Nomenclatura: ");
			String nomenclatura = in.nextLine();

			System.out.print("Valor en dólar: ");
			double valorDolar = in.nextDouble();

			System.out.print("Stock disponible: ");
			double stock = in.nextDouble();

			// Confirmar antes de guardar
			System.out.println("\nDatos ingresados:");
			System.out.println("Tipo: " + tipo);
			System.out.println("Nombre: " + nombre);
			System.out.println("Nomenclatura: " + nomenclatura);
			System.out.println("Valor en dólar: " + valorDolar);
			System.out.println("Stock: " + stock);

			String confirmacion = "";

			while (!confirmacion.equalsIgnoreCase("S") && !confirmacion.equalsIgnoreCase("N")) {
				System.out.print("\n¿Desea guardar estos datos en la base de datos? (S/N): ");
				confirmacion = in.nextLine();
				if (!confirmacion.equalsIgnoreCase("S") && !confirmacion.equalsIgnoreCase("N")) {
					System.out.println("Por favor ingrese S para confirmar o N para cancelar la operación");
				}
				if (confirmacion.equalsIgnoreCase("S")) {
					// Guardar en la base de datos
					Moneda m = new Moneda(nomenclatura, valorDolar, nombre);
					guardarMoneda(m);
					// MonedaDAOjdbc.insertarMoneda(con, tipo, nombre, nomenclatura, valorDolar,
					// stock);
					System.out.println("Moneda guardada exitosamente.");
					in.close();
					return true;
				}
			}
		} finally {
			System.out.println("Operación cancelada.");
			in.close();
			return false;
		}

	}

	@Override
	public boolean listarMonedas() {
		// TODO Auto-generated method stub
		return false;
	}

}
