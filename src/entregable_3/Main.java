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
	
	
	//ESTO DEL TEST ES DE ENZO, lo pegue solo para ir viendolo mientras integro la vista de la compra
	public static void testCompra() {
		int idDolar = (int) (Math.random() * 10000) + 1;
		Fiat dolar = new Fiat(idDolar, "USD", 1, "Dolar", 10000);
		
		int idPeso = (int) (Math.random() * 10000) + 1;
		Fiat peso = new Fiat(idPeso, "ARS", 0.01, "Peso argentino", 100000);

		int idCuenta = (int) (Math.random() * 10000) + 1;
		CuentaFiat cDolar = new CuentaFiat(idCuenta, 1, 50000, dolar);

		idCuenta = (int) (Math.random() * 10000) + 1;
		CuentaFiat cPeso = new CuentaFiat(idCuenta, 1, 500000, peso);

		int idBitcoin = (int) (Math.random() * 10000) + 1;
		Cripto bitcoin = new Cripto(idBitcoin, "BTC", 100000.53, "Bitcoin", 100);

		idCuenta = (int) (Math.random() * 10000) + 1;
		CuentaCripto cBitcoin = new CuentaCripto(idCuenta,1,0,bitcoin);
		
		
		MonedaDAO mDAO = FactoryDAO.getMonedaDAO();
		CuentaDAO cDAO = FactoryDAO.getCuentaDAO();

		mDAO.guardarMoneda(dolar);
		mDAO.guardarMoneda(peso);
		mDAO.guardarMoneda(bitcoin);
		
		cDAO.guardarCuenta(cDolar);
		cDAO.guardarCuenta(cPeso);
		cDAO.guardarCuenta(cBitcoin);

		CompraVista cVista = new CompraVista();
		new CompraControlador(cVista, bitcoin, 1);
		cVista.setVisible(true);

	}
}