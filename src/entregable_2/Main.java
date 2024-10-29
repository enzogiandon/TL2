package entregable_2;

import java.sql.*;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// CLASE MAIN para hacer pruebas

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try (Connection con = ConexionBD.getConnection()) {
			if (con != null) {
				System.out.println("Conexión establecida");

				// probar cosas acá

				CreacionBD.crearTablas(con);

				MonedaDAO mDAO = FactoryDAO.getMonedaDAO();
				Moneda m = crearMoneda();

				for (int i = 0; i < 5; i++) {
					m = crearMoneda();
					mDAO.guardarMoneda(m);
				}
				imprimirMonedas(con, mDAO,in);

			} else {
				System.out.println("No se pudo conectar con la base de datos.");
			}

		} catch (SQLException e) {
			System.out.println("Error al conectar: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Método para crear una moneda.
	 * 
	 * @param con La conexión con la base de datos
	 */
	public static Moneda crearMoneda() {
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
			System.out.println("Nomenclatura: " + nomenclatura.toUpperCase());
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
					System.out.println("Moneda creada exitosamente.");
					in.close();
					return m;
				}
			}
			System.out.println("Operación cancelada.");
			return null;
		} catch (InputMismatchException e) {
			System.out.println("Se envió un tipo de dato incorrecto, intente nuevamente.");
			return null;
		} finally {
			in.close();
		}

	}

	/**
	 * 
	 * @param con          La conexion con la base de datos
	 * @param nomenclatura indica si se debe ordenar por nomenclatura
	 * @param in Scanner para leer datos de teclado.
	 */
	public static void imprimirMonedas(Connection con, MonedaDAO mDAO, Scanner in) {
		List<Moneda> monedas = mDAO.listarMonedas(con);

		System.out.println("Ingrese 'S' si quiere ordenar por nomenclatura"
				+ ", de lo contrario, ingrese cualquier valor: ");

		if (in.nextLine().equalsIgnoreCase("S")) {
			Collections.sort(monedas, new MonedasPorNomenclatura());
		} else {
			Collections.sort(monedas);
		}

		System.out.println("Monedas disponibles: ");
		for (Moneda moneda : monedas)
			System.out.println(moneda.toString());
	}
}
