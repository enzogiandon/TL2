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
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		try (Connection con = ConexionBD.getConnection()) {
			if (con == null) { // Test de conexion
				System.out.println("No se pudo conectar con la base de datos.");
				in.close();
				System.exit(1);
			}
			System.out.println("Conexión establecida");
			CreacionBD.crearTablas();
		}catch(SQLException e){
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
}