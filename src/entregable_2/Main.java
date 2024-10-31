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
			if (con != null) {
				System.out.println("Conexión establecida");

				// probar cosas acá

			//	CreacionBD.crearTablas(con);

				
				
				
				
				MonedaDAO mDAO = FactoryDAO.getMonedaDAO();

			
				for (int i = 0; i < 5; i++) {
					Moneda m = crearMoneda(in);
					mDAO.guardarMoneda(m);
				}
				Moneda m = mDAO.getMoneda(con, "BTC");
				System.out.println(m.toString());
				
		//		Compra.comprar(con, "BTC", "USD", 50000);
				/*
				imprimirMonedas(con, mDAO,in);
				
				// MARTI PRUEBA
				// Crear cuentas de criptomonedas para probar el swap
				
                CuentaCripto cuentaBTC = new CuentaCripto("BTC", 10.0, "direccionBTC");
                CuentaCripto cuentaETH = new CuentaCripto("ETH", 5.0, "direccionETH");

                // Llamar al método realizarSwap
                realizarSwap(cuentaBTC, cuentaETH, 5.0);

                // Llamar a listar stock
                listarStock(con, mDAO, in);
                */
			} else {
				System.out.println("No se pudo conectar con la base de datos.");
			}

		} catch (SQLException e) {
			System.out.println("Error al conectar: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			in.close();
		}
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
	
	/**
     * Método para realizar un swap entre dos cuentas de criptomonedas.
     *
     * @param cuentaConvertir   La cuenta de la criptomoneda que se desea convertir.
     * @param cuentaEsperada    La cuenta de la criptomoneda esperada.
     * @param cantidad          La cantidad a intercambiar.
     */
    public static void realizarSwap(CuentaCripto cuentaConvertir, CuentaCripto cuentaEsperada, double cantidad) {
        if (cuentaConvertir.simularSwap(cuentaConvertir.getId(), cantidad, cuentaEsperada.getId())) {
            System.out.println("Swap realizado exitosamente");
        } else {
            System.out.println("Error al realizar el swap"); 
        }
    }
	
    /**
     * Método para listar el stock disponible de las monedas.
     * Muestra en pantalla la información de cada moneda, permitiendo
     * al usuario elegir si desea ordenar el listado por nomenclatura
     * o por cantidad en orden descendente.
     *
     * @param con    La conexión con la base de datos para acceder a las monedas.
     * @param mDAO   El objeto de acceso a datos para las monedas.
     * @param in     El objeto Scanner para leer la entrada del usuario.
     */
    public static void listarStock(Connection con, MonedaDAO mDAO, Scanner in) {
        List<Moneda> monedas = mDAO.listarMonedas(con);

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
}