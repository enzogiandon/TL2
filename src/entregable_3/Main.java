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

		/**
		 * // Login de usuario LoginModelo modeloLog = new LoginModelo(); LoginVista
		 * vistaLog = new LoginVista(); new LoginControlador(modeloLog, vistaLog);
		 * 
		 * // Mostrar la Vista vistaLog.setVisible(true);
		 * 
		 * // Registro de usuario RegistroModelo modeloReg = new RegistroModelo();
		 * RegistroVista vistaReg = new RegistroVista(); new
		 * RegistroControlador(modeloReg, vistaReg);
		 * 
		 * // Mostrar la Vista de Registro vistaReg.setVisible(true);
		 * 
		 */

		/*
		 * // Transaccion (habria que probarlo con una compra o algo xd) TransaccionDAO
		 * tDAO = FactoryDAO.getTransaccionDAO(); //modelo
		 * 
		 * // Me hago una compra imaginaria
		 * 
		 * Compra compra = new Compra(1,bitcoin,1,dolar);
		 * 
		 * // La guardo con un id random int idRandom = (int)
		 * Math.ceil(Math.random()*10000);
		 * tDAO.guardarTransaccion(idRandom,compra.getDescripcion(), 1);
		 * 
		 * 
		 * // La muestro TransaccionVista tVista = new TransaccionVista();
		 * 
		 * new TransaccionControlador(tVista);
		 * 
		 * tVista.setVisible(true);
		 */

		/*
		 * //Balance CuentaDAOjdbc modeloBal = new CuentaDAOjdbc(); BalanceVista
		 * vistaBal = new BalanceVista(); new BalanceControlador(modeloBal, vistaBal);
		 * //Mostrar la Vista de Balance vistaBal.setVisible(true);
		 * 
		 * // Crear las instancias del Modelo, Vista y Controlador // Cotización de
		 * Criptomonedas // Cotización de Criptomonedas CotizacionModelo modeloCot = new
		 * CotizacionModelo(); CotizacionVista vistaCot = new CotizacionVista(); new
		 * CotizacionControlador(modeloCot, vistaCot);
		 * 
		 * // Mostrar la Vista de Cotización vistaCot.mostrarVentana();
		 */
		//testMoneda();
		testCuenta();

	}

	public static void testMoneda(){
		int id;

		id = (int) (Math.random() * 10000) + 1;
		Cripto bitcoin = new Cripto(id, "BTC", 123, "Bitcoin", 100);

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
		CuentaFiat cf = new CuentaFiat(64234,2,1000,f);

		cDAO.guardarCuenta(cf);

		Cripto c = (Cripto) mDAO.getMoneda("BTC");
		CuentaCripto cc = new CuentaCripto(4325,2,100,c);

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
		
		
		//revisar de aca en adelante
		cantUSD = cDAO.obtenerCantidadFiat("USD", 2);
		cantBTC = cDAO.obtenerCantidadFiat("BTC", 2);
		System.out.printf("Tengo %.2f USD y %.2f BTC. ",cantUSD,cantBTC);
	
		cDAO.getCuentaFiat("USD",2).toString();

		cDAO.getCuentaCripto("BTC",2).toString();
	
	}
}