package entregable_2;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MonedaDAOjdbc implements MonedaDAO {

	@Override
	public boolean guardarMoneda(Moneda moneda) {
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, STOCK) VALUES (?,?,?,?,?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, String.valueOf('F'));
			pst.setString(2, moneda.getNombre());
			pst.setString(3, moneda.getCodigo());
			pst.setDouble(4, moneda.getPrecioEnDolar());
			pst.setDouble(5, moneda.getStock());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Moneda> listarMonedas(Connection con) {
		List<Moneda> monedas = new ArrayList<>();
		String sql = "SELECT * MONEDA";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Moneda m = new Moneda();
				m.setNombre(rs.getString(2));
				m.setCodigo(rs.getString(3));
				m.setPrecioEnDolar(rs.getDouble(4));
				m.setStock(rs.getDouble(6));
				monedas.add(m);
			}
			return monedas;
		} catch (SQLException e) {
			System.out.println("Error de SQL al listar monedas: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Método para crear una moneda y almacenarla en la BD.
	 * 
	 * @param con La conexión con la base de datos
	 */
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
			String nomenclatura = in.nextLine().toUpperCase();

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
					Moneda m = new Moneda(nomenclatura, valorDolar, nombre, stock);
					guardarMoneda(m);
					System.out.println("Moneda guardada exitosamente.");
					in.close();
					return true;
				}
			}
			System.out.println("Operación cancelada.");
			return false;
		} catch (InputMismatchException e) {
			System.out.println("Se envió un tipo de dato incorrecto, intente nuevamente.");
			return false;
		} finally {
			in.close();
		}

	}

	/**
	 * 
	 * @param con          La conexion con la base de datos
	 * @param nomenclatura indica si se debe ordenar por nomenclatura
	 * @return
	 */
	public void imprimirMonedas(Connection con) {
		Scanner in = new Scanner(System.in);
		List<Moneda> monedas = listarMonedas(con);
		try {

			System.out.println("Si desea ordenar por nomenclatura ingrese 'S'. Si no, ingrese cualquier valor");
			boolean nomenclatura = (in.nextLine().equalsIgnoreCase("S"));

			if (nomenclatura) {
				Collections.sort(monedas, new MonedasPorNomenclatura());
			} else {
				Collections.sort(monedas);
			}

			System.out.println("Monedas disponibles: ");
			for (Moneda moneda : monedas) {
				System.out.println(moneda);
			}
		} finally {
			in.close();
		}
	}

}
