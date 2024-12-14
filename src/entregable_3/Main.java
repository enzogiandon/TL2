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
				System.exit(1);
			}
			System.out.println("Conexión establecida");

			CreacionBD.crearTablas();

		}catch(SQLException e) {
			System.out.println("Error de sql: "+ e.getMessage());
			System.exit(2);;
		}
		//Carga de datos en la base de datos
		DatosModelo modelo = new DatosModelo();
        DatosVista vista = new DatosVista();
        new DatosControlador(modelo, vista);

        vista.setVisible(true);
        
		// Login de usuario
		
        LoginModelo modeloLog = new LoginModelo();
        LoginVista vistaLog = new LoginVista();
        new LoginControlador(modeloLog, vistaLog);

        // Mostrar la Vista
        vistaLog.setVisible(true);
       
        
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
						//m = new Cripto(nomenclatura, valorDolar, nombre, stock);
					if (tipo.equalsIgnoreCase("fiat"))
						//m = new Fiat(nomenclatura, valorDolar, nombre, stock);
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
		cuentas.addAll(cuentaDAO.listarCuentasCripto(1)); //NUMEROS QUE NO VAN
		cuentas.addAll(cuentaDAO.listarCuentasFiat(1));
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

	public static boolean comprar(String criptoNomenclatura, String fiatNomenclatura, double cantidadFiat, Scanner in) {
		try {
			MonedaDAO mDAO = FactoryDAO.getMonedaDAO();
			CuentaDAO cDAO = FactoryDAO.getCuentaDAO();
			TransaccionDAO tDAO = FactoryDAO.getTransaccionDAO();
			//el cero pasado de parametro NO VA!
			double fondosFiat = cDAO.obtenerCantidadFiat(fiatNomenclatura,0);
			if (cantidadFiat > fondosFiat) {
				System.out.println("Fondos no suficientes en " + fiatNomenclatura.toUpperCase()
						+ " para realizar esta operación.");
				return false;
			}
			//nuevamente, el cero pasado de parametro NO VA!
			if (cDAO.getCuentaCripto(criptoNomenclatura,0) == null) {
				System.out.println("Usted no posee una cuenta de " + criptoNomenclatura + ". Intente creando una.");
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

			if (cantidadCripto > cripto.getStock()) { // verificar stock
				System.out.println("No hay suficiente stock de la criptomoneda " + criptoNomenclatura + ".");
				return false;
			}

			System.out.printf(
					"Con %.2f %s puedes comprar %.6f %s. Ingrese 'S' para confirmar u otro valor para cancelar: ",
					cantidadFiat, fiatNomenclatura, cantidadCripto, criptoNomenclatura);
			
			String confirmacion = in.next();

			if (confirmacion.equalsIgnoreCase("S")) {
				// Instancio una compra y la guardo
				//int id, int idUsuario,double monto,Cripto cripto,double cantidadFiat,Fiat fiat)
				//Compra compra = new Compra(cantidadCripto, cripto, cantidadFiat, fiat);
				//tDAO.guardarTransaccion(compra.getDescripcion());

				// actualizo los fondos y lo guardo en BD
				fondosFiat -= cantidadFiat;
				double fondosCripto = cDAO.obtenerCantidadCripto(criptoNomenclatura,0); //el cero no va
				fondosCripto += cantidadCripto;

				CuentaCripto cc = new CuentaCripto();
				CuentaFiat cf = new CuentaFiat();

				// añado a lista y actualizo la BD
				List<CuentaFiat> listaFiat = List.of(cf);
				cDAO.actualizarCuentasFiat(listaFiat);

				List<CuentaCripto> listaCripto = List.of(cc);
				cDAO.actualizarCuentasCripto(listaCripto);

				//compra.confirmar();

				return true;
			} else {
				System.out.println("Operación cancelada.");
				return false;
			}
		} catch (ClassCastException e) {
			System.out.println("No se encontró una cripto con esa nomenclatura, intente nuevamente.");
			e.printStackTrace();
			return false;
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