package entregable_3;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entregable_3.Controlador.LoginControlador;
import entregable_3.Controlador.RegistroControlador;
import entregable_3.Controlador.TransaccionControlador;
import entregable_3.Modelo.CuentaDAO;
import entregable_3.Modelo.FactoryDAO;
import entregable_3.Modelo.LoginModelo;
import entregable_3.Modelo.MonedaDAO;
import entregable_3.Modelo.RegistroModelo;
import entregable_3.Modelo.TransaccionDAO;
import entregable_3.Vista.LoginVista;
import entregable_3.Vista.RegistroVista;
import entregable_3.Vista.TransaccionVista;

// CLASE MAIN para hacer pruebas

//Por ahora, saqué todo lo de swap, ya que eso es para los grupos de 3 personas

//hay que hacer el login del usuario

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
			} catch (SQLException e) {
				System.out.println("Error de sql: " + e.getMessage());
				return;
			}
			
		/**
		// Login de usuario
        LoginModelo modeloLog = new LoginModelo();
        LoginVista vistaLog = new LoginVista();
        new LoginControlador(modeloLog, vistaLog);

        // Mostrar la Vista
        vistaLog.setVisible(true);
        
     // Registro de usuario
        RegistroModelo modeloReg = new RegistroModelo();
        RegistroVista vistaReg = new RegistroVista();
        new RegistroControlador(modeloReg, vistaReg);

        // Mostrar la Vista de Registro
        vistaReg.setVisible(true);
        
        */
			
			
			
        // Transaccion (habria que probarlo con una compra o algo xd)
        TransaccionDAO tDAO = FactoryDAO.getTransaccionDAO(); //modelo
        
        // Me hago una compra imaginaria
        Cripto bitcoin = new Cripto("BTC",123,"Bitcoin",100);
        Fiat dolar = new Fiat("USD",1,"Dolar",10000);
        
        Compra compra = new Compra(1,bitcoin,1,dolar);
        
        // La guardo con un id random
        int idRandom =  (int) Math.ceil(Math.random()*10000);
        tDAO.guardarTransaccion(idRandom,compra.getDescripcion(), 1);


        // La muestro
        TransaccionVista tVista = new TransaccionVista();
        
        new TransaccionControlador(tVista);
        
        tVista.setVisible(true);
        
        
        
        	
        	
        
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
	public static void listarMonedas(MonedaDAO mDAO, Scanner in) {
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

	/**
	 * Método para listar el stock disponible de las monedas. Muestra en pantalla la
	 * información de cada moneda, permitiendo al usuario elegir si desea ordenar el
	 * listado por nomenclatura o por cantidad en orden descendente.
	 *
	 * @param con  La conexión con la base de datos para acceder a las monedas.
	 * @param mDAO El objeto de acceso a datos para las monedas.
	 * @param in   El objeto Scanner para leer la entrada del usuario.
	 */
	public static void listarStock(MonedaDAO mDAO, Scanner in) {
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
	
	//QUE HACE ESTO ACÁ !!!!

	public static void listarCuentas(CuentaDAO cuentaDAO, Scanner in) {
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

	public static boolean comprar(String criptoNomenclatura, String fiatNomenclatura, double cantidadFiat,
			MonedaDAO mDAO, CuentaDAO cDAO, TransaccionDAO tDAO) {
		try {
			double fondosFiat = cDAO.obtenerCantidadFiat(fiatNomenclatura);
			if (cantidadFiat < fondosFiat) {
				System.out.println("Fondos no suficientes en " + fiatNomenclatura + "para realizar esta operación.");
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

			Scanner in = new Scanner(System.in);
			System.out.printf(
					"Con %.2f %s puedes comprar %.6f %s. Ingrese 'S' para confirmar u otro valor para cancelar: ",
					cantidadFiat, fiatNomenclatura, cantidadCripto, criptoNomenclatura);
			String confirmacion = in.nextLine();
			in.close();

			if (confirmacion.equalsIgnoreCase("S")) {
				// Instancio una compra y la guardo
				Compra compra = new Compra(cantidadCripto, cripto, cantidadFiat, fiat);
				//tDAO.guardarTransaccion(compra.getDescripcion());

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

	

	private static double obtenerTasaCambio(String criptoConvertirCodigo, String criptoEsperadaCodigo,
			MonedaDAO monedaDAO) {
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