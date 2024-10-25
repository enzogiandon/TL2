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

				CreacionBD.crearTablas(con);
				
				
				
				
				
				
				
				
				
				
				
			} else {
				System.out.println("No se pudo conectar con la base de datos.");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al conectar: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
/**
 * Método para crear una moneda y almacenarla en la BD.
 * @param con La conexión con la base de datos
 */
	public static void crearMonedas(Connection con) {
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
                in.nextLine();  // Limpiar buffer
                confirmacion = in.nextLine();
                if (!confirmacion.equalsIgnoreCase("S") && !confirmacion.equalsIgnoreCase("N")) {
                    System.out.println("Por favor ingrese S para confirmar o N para cancelar la operación");
                }
                if (confirmacion.equalsIgnoreCase("S")) {
                    // Guardar en la base de datos
                    MonedaDAO.insertarMoneda(con, tipo, nombre, nomenclatura, valorDolar, stock);
                    System.out.println("Moneda guardada exitosamente.");
                } else {
                    System.out.println("Operación cancelada.");
                }
                
            }
            
			
		} catch (SQLException e) {
			System.out.println("Error al guardar la moneda en la base de datos: " + e.getMessage());
			e.printStackTrace();
			
		}
		
	}
}
