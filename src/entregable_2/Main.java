package entregable_2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// CLASE MAIN para hacer pruebas

public class Main {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		try (Connection con = ConexionBD.getConnection()) {
			if (con == null) {
				System.out.println("No se pudo conectar con la base de datos.");
				in.close();
				System.exit(0);
			}
			System.out.println("Conexión establecida");

			// probar cosas acá

			int opcion;

			menu: while (true) {
				mostrarMenu();

				opcion = in.nextInt();

				MonedaDAO mDAO = FactoryDAO.getMonedaDAO();

				switch (opcion) {

				case 1:
					Moneda m = crearMoneda(in);
					mDAO.guardarMoneda(m);
					break;
				case 2:
					imprimirMonedas(con, mDAO, in);
					break;
				case 3:
					listarStock(con, mDAO, in);
				case 4:
					break menu;
				}

			}

		} catch (SQLException e) {
			System.out.println("Error al conectar: " + e.getMessage());
			e.printStackTrace();
		} finally {
			in.close();
		}
	}

	private static void mostrarMenu() {
		System.out.println("\n=== Menú Principal ===");
		System.out.println("[1] Crear Moneda");
		System.out.println("[2] Imprimir Monedas");
		System.out.println("[3] Listar Stock");
		System.out.println("[4] Realizar Swap");
		System.out.println("[5] Comprar Criptomoneda");
		System.out.println("[6] Salir");
	}

	/**
	 * Método para crear una moneda.
	 * 
	 * @param con La conexión con la base de datos
	 */
	public static Moneda crearMoneda(Scanner in) {
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
					Moneda m = new Moneda(nomenclatura, valorDolar, nombre, stock);
					System.out.println("Moneda creada exitosamente.");
					return m;
				}
			}
			System.out.println("Operación cancelada.");
			return null;
		} catch (InputMismatchException e) {
			System.out.println("Se envió un tipo de dato incorrecto, intente nuevamente.");
			return null;
		}
	}

	/**
	 * 
	 * @param con          La conexion con la base de datos
	 * @param nomenclatura indica si se debe ordenar por nomenclatura
	 * @param in           Scanner para leer datos de teclado.
	 */
	public static void imprimirMonedas(Connection con, MonedaDAO mDAO, Scanner in) {
		List<Moneda> monedas = mDAO.listarMonedas();

		System.out.println(
				"Ingrese 'S' si quiere ordenar por nomenclatura" + ", de lo contrario, ingrese cualquier valor: ");

		if (in.nextLine().equalsIgnoreCase("S")) {
			Collections.sort(monedas, new MonedasPorNomenclatura());
		} else {
			Collections.sort(monedas);
		}

		System.out.println("Monedas disponibles: ");
		for (Moneda moneda : monedas)
			System.out.println(moneda.toString());
	}

	/**
	 * Método para realizar un swap entre dos cuentas de criptomonedas.
	 *
	 * @param cuentaConvertir La cuenta de la criptomoneda que se desea convertir.
	 * @param cuentaEsperada  La cuenta de la criptomoneda esperada.
	 * @param cantidad        La cantidad a intercambiar.
	 */
	public static void realizarSwap(

	CuentaCripto cuentaConvertir, CuentaCripto cuentaEsperada,
	double cantidad, CuentaDAO cuentaDAO,
	MonedaDAO monedaDAO)
	{
		if (cuentaConvertir.simularSwap(cuentaConvertir.getId(), cantidad, cuentaEsperada.getId(), cuentaDAO,
				monedaDAO)) {
			System.out.println("Swap realizado exitosamente");
		} else {
			System.out.println("Error al realizar el swap");
		}
	}

	/**
	 * Método para listar el stock disponible de las monedas. Muestra en pantalla la
	 * información de cada moneda, permitiendo al usuario elegir si desea ordenar el
	 * listado por nomenclatura o por cantidad en orden descendente.
	 *
	 * @param con  La conexión con la base de datos para acceder a las monedas.
	 * @param mDAO El objeto de acceso a datos para las monedas.
	 * @param in   El objeto Scanner para leer la entrada del usuario.
	 */
	public static void listarStock(Connection con, MonedaDAO mDAO, Scanner in) {
		List<Moneda> monedas = mDAO.listarMonedas();

		System.out.println("¿Desea ordenar por nomenclatura? (S/N): ");
		String opcion = in.nextLine().trim();

		if (opcion.equalsIgnoreCase("S")) {
			Collections.sort(monedas, new MonedasPorNomenclatura());
		} else {
			Collections.sort(monedas, new StockPorCantidad());
		}

		System.out.println("Stock disponible: ");
		for (Moneda moneda : monedas) {
			System.out.println(moneda.toString() + " - Stock: " + moneda.getStock());
		}
	}


	public static boolean comprar(String criptoNomenclatura, String fiatNomenclatura, double cantidadFiat,
			MonedaDAO mDAO, CuentaDAO cDAO, TransaccionDAO tDAO) {
		try {
			Cripto cripto = (Cripto) mDAO.getMoneda(criptoNomenclatura);
			Fiat fiat = (Fiat) mDAO.getMoneda(fiatNomenclatura);

			if (cripto == null) {
				System.out.println("La moneda cripto elegida no existe.");
				return false;
			}
			if (fiat == null) {
				System.out.println("La moneda fiat elegida no existe.");
				return false;
			}

			double cantidadCripto = cantidadFiat / ((cripto.getPrecioEnDolar() / fiat.getPrecioEnDolar())); // cantidad
																											// Fiat /
																											// valor de
																											// la cripto
																											// en esa
																											// fiat

			if (cantidadCripto > cripto.getStock()) { // verificar stock
				System.out.println("No hay suficiente stock de la criptomoneda " + criptoNomenclatura + ".");
				return false;
			}

			Scanner in = new Scanner(System.in);
			System.out.printf(
					"Con %.2f %s puedes comprar %.6f %s. Ingrese 'S' para confirmar u otro valor para cancelar: ",
					cantidadFiat, fiatNomenclatura, cantidadCripto, criptoNomenclatura);
			String confirmacion = in.nextLine();
			in.close();

			if (confirmacion.equalsIgnoreCase("S")) {
				Compra compra = new Compra(cantidadCripto, cripto, cantidadFiat, fiat);
				// actualizarActivos()
				tDAO.guardarTransaccion(compra.getDescripcion());
				compra.confirmar();

				return true;
			} else {
				System.out.println("Operación cancelada.");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassCastException e) {
			System.out.println("No se encontró una cripto con esa nomenclatura, intente nuevamente.");
			e.printStackTrace();
			return false;
		}
	}
}
