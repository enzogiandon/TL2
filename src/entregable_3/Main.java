package entregable_3;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entregable_3.Modelo.*;
import entregable_3.Vista.*;
import entregable_3.Controlador.*;

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

			CreacionBD.crearTablas();

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
					listarMonedas(in);
					break;
				case 3:
					mDAO.stockRandomizer();
					break;
				case 4:
					listarStock(in);
					break;
				case 5:
					System.out.println("Ingrese la nomenclatura de la moneda a guardar en la nueva cuenta.");
					String nomenclatura = in.next();
					Moneda m1 = mDAO.getMoneda(nomenclatura);

					if (m1 == null) {
						System.out.println(
								"No se encontró esa moneda para poner en una cuenta, intenta creando una nueva.");
						break;
					}

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
					CuentaDAO cDAO = FactoryDAO.getCuentaDAO();
					cDAO.guardarCuenta(c);
					break;

				case 6:

					listarCuentas(in);
					break;

				case 7:
					System.out.println("ACA ESTABA EL SWAP");
				
				case 8:
					System.out.println("Ingrese la nomenclatura de la Cripto que quiere comprar: ");
					String criptoCompra = in.nextLine();

					System.out.println(
							"Ingrese la nomenclatura de la Fiat que usará para comprar " + criptoCompra + ": ");
					String fiatCompra = in.nextLine();

					System.out.printf("Ingrese el monto en %s que desea comprar de %s: ", fiatCompra, criptoCompra);
					double montoCompra = in.nextDouble();
					Compra comp = null; //?? no se si esto va
					//comp.comprar(criptoCompra, fiatCompra, montoCompra, in);
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
	
		// Login de usuario
		
        LoginModelo modeloLog = new LoginModelo();
        LoginVista vistaLog = new LoginVista();
        new LoginControlador(modeloLog, vistaLog);

        // Mostrar la Vista
        vistaLog.setVisible(true);
        
        
     // Transaccion (habria que probarlo con una compra o algo xd)

        // La muestro
       // TransaccionVista tVista = new TransaccionVista();
        
        //new TransaccionControlador(tVista);
        
        //tVista.setVisible(true);
            
        //Balance
        CuentaDAOjdbc modeloBal = new CuentaDAOjdbc();
        BalanceVista vistaBal = new BalanceVista();
        new BalanceControlador(modeloBal, vistaBal);
        //Mostrar la Vista de Balance
        vistaBal.setVisible(true);
        

     // Cotización de Criptomonedas
        CotizacionModelo modeloCot = new CotizacionModelo();
        CotizacionVista vistaCot = new CotizacionVista();
        new CotizacionControlador(modeloCot, vistaCot);
        
        // Mostrar la Vista de Cotización
        vistaCot.mostrarVentana();

        
        
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
		MonedaDAO mDAO = FactoryDAO.getMonedaDAO();
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
		MonedaDAO mDAO = FactoryDAO.getMonedaDAO();

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

	

	private static double obtenerTasaCambio(String criptoConvertirCodigo, String criptoEsperadaCodigo)
			throws SQLException {
		// Consultar MonedaDAO para obtener la tasa de cambio (ejemplo básico)
		MonedaDAO monedaDAO = FactoryDAO.getMonedaDAO();
		double precioCriptoConvertir = 0;
		double precioCriptoEsperada = 0;

		precioCriptoEsperada = monedaDAO.getMoneda(criptoEsperadaCodigo).precioEnDolar;
		precioCriptoConvertir = monedaDAO.getMoneda(criptoConvertirCodigo).precioEnDolar;

		if (precioCriptoEsperada == 0) {
			System.out.println("Error: Moneda esperada no encontrada.");
			return 0;
		}

		return precioCriptoConvertir / precioCriptoEsperada;

	}
}