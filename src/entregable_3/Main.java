package entregable_3;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.ArrayList;
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
		} catch (SQLException e) {
			System.out.println("Error de sql: " + e.getMessage());
			System.exit(2);
			;
		}

		// testMoneda();
		// testCuenta();
		// testCompra();

		// @formatter:off
		/*
		// Carga de datos en la base de datos
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
		*/
		// @formatter:on
		
//		testCompra();
//		testTransacciones();
		
		
		
		
		

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

	public static void testMoneda() {
		int id;

		id = (int) (Math.random() * 10000) + 1;
		Cripto bitcoin = new Cripto(id, "BTC", 100000, "Bitcoin", 100);

		id = (int) (Math.random() * 10000) + 1;
		Fiat dolar = new Fiat(id, "USD", 1, "Dolar", 10000);

		MonedaDAO mDAO = FactoryDAO.getMonedaDAO();

		mDAO.guardarMoneda(dolar);
		mDAO.guardarMoneda(bitcoin);
		mDAO.stockRandomizer();

		List<Moneda> lista = new ArrayList<Moneda>();

		lista = mDAO.listarMonedas();

		System.out.println("Lista de monedas:");
		for (Moneda moneda : lista) {
			System.out.println(moneda.toString());
		}

		Moneda m = mDAO.getMoneda("USD");
		if (m != null)
			System.out.println("Otra moneda: " + m.toString());

	}

	public static void testCuenta() {
		CuentaDAO cDAO = FactoryDAO.getCuentaDAO();
		MonedaDAO mDAO = FactoryDAO.getMonedaDAO();

		Fiat f = (Fiat) mDAO.getMoneda("USD");
		CuentaFiat cf = new CuentaFiat(64234, 2, 1000, f);

		cDAO.guardarCuenta(cf);

		Cripto c = (Cripto) mDAO.getMoneda("BTC");
		CuentaCripto cc = new CuentaCripto(4325, 2, 100, c);

		cDAO.guardarCuenta(cc);

		List<CuentaCripto> listac = cDAO.listarCuentasCripto(2);

		System.out.println("Lista de cuentas Cripto:");
		for (CuentaCripto cuenta : listac) {
			System.out.println(cuenta.toString());
		}

		List<CuentaFiat> listaf = cDAO.listarCuentasFiat(2);

		System.out.println("Lista de cuentas Fiat:");
		for (CuentaFiat cuenta : listaf) {
			System.out.println(cuenta.toString());
		}

		double cantUSD, cantBTC;

		// revisar de aca en adelante

		cantUSD = cDAO.obtenerCantidadFiat("USD", 2);
		cantBTC = cDAO.obtenerCantidadCripto("BTC", 2);
		System.out.printf("Tengo %.2f USD y %.2f BTC. \n\n", cantUSD, cantBTC);

		System.out.println(cDAO.getCuentaFiat("USD", 2).toString());

		System.out.println(cDAO.getCuentaCripto("BTC", 2).toString());
	}


	
	public static void testTransacciones() {
		TransaccionDAO tDAO = FactoryDAO.getTransaccionDAO();
		
		// Me hago una compra imaginaria
		int id;
		id = (int) (Math.random() * 10000) + 1;
		Cripto bitcoin = new Cripto(id, "BTC", 123, "Bitcoin", 100);
		id = (int) (Math.random() * 10000) + 1;
		Fiat dolar = new Fiat(id, "USD", 1, "Dolar", 10000);
		
		// La guardo con un id random
		int idRandom = (int) Math.ceil(Math.random() * 10000);
		Compra compra = new Compra(idRandom,1,1, bitcoin, 1, dolar);
		
		tDAO.guardarTransaccion(compra);
		
		// La muestro 
		TransaccionVista tVista = new TransaccionVista();		
		
		new TransaccionControlador(tVista,1);
		tVista.setVisible(true);
	}
	
	
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