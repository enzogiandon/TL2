package entregable_2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


// CLASE MAIN para hacer pruebas

public class Main {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		try (Connection con = ConexionBD.getConnection()) {
			if (con == null) { // Test de conexion
				System.out.println("No se pudo conectar con la base de datos.");
				in.close();
				System.exit(0);
			}
			System.out.println("Conexión establecida");

			CreacionBD.crearTablas(con);

			
			con.close();
			int opcion;

			menu: while (true) {
				mostrarMenu();

				opcion = in.nextInt();
				in.nextLine();
				MonedaDAO mDAO = FactoryDAO.getMonedaDAO();

				switch (opcion) {

				case 1:
					Moneda m = crearMoneda(in);
					mDAO.guardarMoneda(m);
					break;
				case 2:
					listarMonedas(mDAO, in);
					break;
				case 3:
					mDAO.stockRandomizer();
					break;
				case 4:
					listarStock(mDAO, in);
					break;
				case 5:
					System.out.println("Ingrese los datos de la moneda a guardar en la nueva cuenta.");
					Moneda m1 = crearMoneda(in);

					System.out.println("Ingrese el monto de la nueva cuenta");
					double montoCuenta = in.nextDouble();

					Cuenta c = null;
					if (m1 instanceof Fiat) {
						c = new CuentaFiat(m1.getCodigo(), montoCuenta);
					} else if (m1 instanceof Cripto) {
						System.out.println("Ingrese la direccion de la cuenta cripto");
						String dir = in.next();
						c = new CuentaCripto(m1.getCodigo(), montoCuenta, dir);
					} else {
						System.out.println("No se pudo crear la cuenta");
						break;
					}
					cDAO.guardarCuenta(c);
					break;

				case 6:

					listarCuentas(cDAO, in);
					break;

				case 7:
					System.out.println("Ingrese la nomenclatura de la moneda que quiere enviar en el swap");
					String swapEnvio = in.next();

					System.out.println("Ingrese el monto que desea enviar de esa moneda");
					double montoSwap = in.nextDouble();

					System.out.println("Ingrese la nomenclatura de la moneda que quiere recibir en el swap");
					String swapRecibo = in.next();
					simularSwap(swapEnvio, montoSwap, swapRecibo, cDAO, mDAO);
					break;

				case 8:
					System.out.println("Ingrese la nomenclatura de la Cripto que quiere comprar: ");
					String criptoCompra = in.nextLine();

					System.out.println(
							"Ingrese la nomenclatura de la Fiat que usará para comprar " + criptoCompra + ": ");
					String fiatCompra = in.nextLine();

					System.out.printf("Ingrese el monto en %s que desea comprar de %s: ", fiatCompra, criptoCompra);
					double montoCompra = in.nextDouble();

					comprar(criptoCompra, fiatCompra, montoCompra);
					break;
				case 9:
					System.out.println("Saliendo del programa.");
					break menu;

				default:
					System.out.println("Opción incorrecta, intente nuevamente.");
					break;
				}

			}

		} catch (SQLException e) {
			System.out.println("Error al conectar: " + e.getMessage());
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("Se envió un tipo de dato incorrecto, intente nuevamente.");
		} finally {
			in.close();
		}
	}

	private static void mostrarMenu() {
		System.out.println("\n=== Menú principal ===");
		System.out.println("[1] Crear moneda");
		System.out.println("[2] Imprimir monedas");
		System.out.println("[3] Generar stock de monedas");
		System.out.println("[4] Listar stock");
		System.out.println("[5] Generar cuenta (activo)");
		System.out.println("[6] Listar cuentas (activos)");
		System.out.println("[7] Realizar swap");
		System.out.println("[8] Comprar criptomoneda");
		System.out.println("[9] Salir");
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
					Moneda m = null;
					if (tipo.equalsIgnoreCase("cripto"))
						m = new Cripto(nomenclatura, valorDolar, nombre, stock);
					if (tipo.equalsIgnoreCase("fiat"))
						m = new Fiat(nomenclatura, valorDolar, nombre, stock);
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
	public static void listarMonedas(Scanner in) {
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
	 * Método para listar el stock disponible de las monedas. Muestra en pantalla la
	 * información de cada moneda, permitiendo al usuario elegir si desea ordenar el
	 * listado por nomenclatura o por cantidad en orden descendente.
	 *
	 * @param con  La conexión con la base de datos para acceder a las monedas.
	 * @param mDAO El objeto de acceso a datos para las monedas.
	 * @param in   El objeto Scanner para leer la entrada del usuario.
	 */	
	public static void listarStock(Scanner in) {
		List<Moneda> monedas = mDAO.listarMonedas();

		System.out.println(
				"Ingrese 'S' si quiere ordenar por nomenclatura" + ", de lo contrario, ingrese cualquier valor: ");
		String opcion = in.nextLine();

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

	public static void listarCuentas(Scanner in) {
		CuentaDAO cuentaDAO = FactoryDAO.getCuentaDAO();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.addAll(cuentaDAO.listarCuentasCripto());
		cuentas.addAll(cuentaDAO.listarCuentasFiat());
		System.out.println(
				"Ingrese 'S' si quiere ordenar por nomenclatura" + ", de lo contrario, ingrese cualquier valor: ");
		String opcion = in.next();

		if (opcion.equalsIgnoreCase("S")) {
			Collections.sort(cuentas); // ordena por saldo descendente (usando Comparable)
		} else {
			cuentas.sort(new CuentasPorNomenclatura()); // ordena por nomenclatura (usando Comparator)
		}
		// Muestra en pantalla la lista de cuentas ordenada
		for (Cuenta cuenta : cuentas) {
			System.out.println(cuenta);
		}
	}

	public static boolean comprar(String criptoNomenclatura, String fiatNomenclatura, double cantidadFiat) {
		try {
			MonedaDAO mDAO = FactoryDAO.getMonedaDAO();
			CuentaDAO cDAO = FactoryDAO.getCuentaDAO();
			TransaccionDAO tDAO = FactoryDAO.getTransaccionDAO();
			
			double fondosFiat = cDAO.obtenerCantidadFiat(fiatNomenclatura);
			if (cantidadFiat > fondosFiat) {
				System.out.println("Fondos no suficientes en " + fiatNomenclatura.toUpperCase() + " para realizar esta operación.");
				return false;
			}

			
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

			double cantidadCripto = cantidadFiat / ((cripto.getPrecioEnDolar() / fiat.getPrecioEnDolar())); 
			// cantidad Fiat / valor de la cripto en esa fiat
			
			if (cantidadCripto < cripto.getStock()) { // verificar stock
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
				// Instancio una compra y la guardo
				Compra compra = new Compra(cantidadCripto, cripto, cantidadFiat, fiat);
				tDAO.guardarTransaccion(compra.getDescripcion());

				// actualizo los fondos y lo guardo en BD
				fondosFiat -= cantidadFiat;
				double fondosCripto = cDAO.obtenerCantidadCripto(criptoNomenclatura);
				fondosCripto += cantidadCripto;

				CuentaCripto cc = new CuentaCripto(criptoNomenclatura, fondosCripto, "1234");
				CuentaFiat cf = new CuentaFiat(fiatNomenclatura, fondosFiat);

				// añado a lista y actualizo la BD
				List<CuentaFiat> listaFiat = List.of(cf);
				cDAO.actualizarCuentasFiat(listaFiat);

				List<CuentaCripto> listaCripto = List.of(cc);
				cDAO.actualizarCuentasCripto(listaCripto);

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

	public static boolean simularSwap(String criptoConvertirCodigo, double cantidad, String criptoEsperadaCodigo) {
		List<Cuenta> cuentas = cuentaDAO.listarCuentasCripto();

		CuentaCripto cuentaConvertir = null;
		CuentaCripto cuentaEsperada = null;

		for (Cuenta cuenta : cuentas) {
			if (cuenta instanceof CuentaCripto) {
				CuentaCripto cuentaCripto = (CuentaCripto) cuenta;
				if (cuentaCripto.getId().equals(criptoConvertirCodigo)) {
					cuentaConvertir = cuentaCripto;
				} else if (cuentaCripto.getId().equals(criptoEsperadaCodigo)) {
					cuentaEsperada = cuentaCripto;
				}
			}
		}

		if (cuentaConvertir == null || cuentaEsperada == null) {
			System.out.println("Error: Una de las criptomonedas no está en Mis Activos.");
			return false;
		}

		if (cuentaConvertir.getSaldo() < cantidad) {
			System.out.println("Error: Stock insuficiente de " + criptoConvertirCodigo);
			return false;
		}

		double tasaCambio = obtenerTasaCambio(criptoConvertirCodigo, criptoEsperadaCodigo, monedaDAO);
		double cantidadRecibida = cantidad * tasaCambio;

		// Crear el swap y actualizar los activos
		Swap swap = new Swap(new Cripto(criptoConvertirCodigo, 0, "Cripto", cuentaConvertir.getSaldo()),
				new Cripto(criptoEsperadaCodigo, 0, "Cripto", cuentaEsperada.getSaldo()), cantidad, cantidadRecibida);
		swap.confirmar();
		cuentaConvertir.setSaldo(cuentaConvertir.getSaldo() - cantidad);
		cuentaEsperada.setSaldo(cuentaEsperada.getSaldo() + cantidadRecibida);

		cuentaDAO.guardarCuenta(cuentaConvertir);
		cuentaDAO.guardarCuenta(cuentaEsperada);

		// Actualizamos los saldos de las cuentas en la lista
		List<CuentaCripto> cuentasActualizadas = List.of(cuentaConvertir, cuentaEsperada);

		if (!cuentaDAO.actualizarCuentasCripto(cuentasActualizadas)) {
			System.out.println("Error al actualizar los activos en la base de datos.");
			return false;
		}

		cuentaDAO.registrarTransaccion(swap.getDescripcion());

		System.out.println("Swap realizado exitosamente.");
		return true;
	}

	private static double obtenerTasaCambio(String criptoConvertirCodigo, String criptoEsperadaCodigo) {
		// Consultar MonedaDAO para obtener la tasa de cambio (ejemplo básico)
		List<Moneda> monedas = monedaDAO.listarMonedas();
		double precioCriptoConvertir = 0;
		double precioCriptoEsperada = 0;

		for (Moneda moneda : monedas) {
			if (moneda.getCodigo().equals(criptoConvertirCodigo)) {
				precioCriptoConvertir = moneda.getPrecioEnDolar();
			} else if (moneda.getCodigo().equals(criptoEsperadaCodigo)) {
				precioCriptoEsperada = moneda.getPrecioEnDolar();
			}
		}
		
		if (precioCriptoEsperada == 0) {
			System.out.println("Error: Moneda esperada no encontrada.");
			return 0;
		}
		
		
		return precioCriptoConvertir / precioCriptoEsperada;

	}
}
