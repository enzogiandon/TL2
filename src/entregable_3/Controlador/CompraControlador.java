package entregable_3.Controlador;

import entregable_3.Vista.CompraVista;
import entregable_3.Modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import entregable_3.*;

public class CompraControlador {
	private CompraVista vista;
	private TransaccionDAO modelo;
	private MonedaDAO mDAO;
	private CuentaDAO cDAO;
	private Cripto criptoSeleccionada;

	public CompraControlador(CompraVista vista, Cripto criptoSeleccionada, int idUsuario) {
		this.vista = vista;
		modelo = FactoryDAO.getTransaccionDAO();
		mDAO = FactoryDAO.getMonedaDAO();
		cDAO = FactoryDAO.getCuentaDAO();
		this.criptoSeleccionada = criptoSeleccionada;
		inicializarVista(idUsuario);
		this.vista.convertirListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				convertir();
			}
		});
		this.vista.comprarListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comprar(idUsuario);
			}
		});
		this.vista.cerrarListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vista.dispose();
			}
		});
	}

	private void inicializarVista(int idUsuario) {
		vista.setStock(criptoSeleccionada.getStock() + " " + criptoSeleccionada.getNombre() + " ("
				+ criptoSeleccionada.getCodigo() + ")");
		vista.setPrecio(String.format("USD %.2f por unidad.", criptoSeleccionada.getPrecioEnDolar()));
		List<String> opciones = new ArrayList<String>();
		List<CuentaFiat> cuentas = cDAO.listarCuentasFiat(idUsuario);
		for (CuentaFiat cuenta : cuentas) {
			opciones.add(String.format("%s", cuenta.getMoneda().getCodigo()));
		}
		vista.setOpcionesFiat(opciones);
	}

	private void convertir() {
		try {
			Fiat fiatSeleccionada = (Fiat) mDAO.getMoneda(vista.getFiatSeleccionada());
			double cantidadFiat = vista.getCantidadFiat();
			double cantidadCripto = cantidadFiat
					/ ((criptoSeleccionada.getPrecioEnDolar() / fiatSeleccionada.getPrecioEnDolar()));
			vista.setEquivalente(String.format("%.5f %s", cantidadCripto, criptoSeleccionada.getCodigo()));
		} catch (ArithmeticException e) {
			System.out.println("Se intento dividir por cero");
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(vista, "Por favor ingresa un dato adecuado.");
			System.out.println(e.getMessage());
		}
	}

	private void comprar(int idUsuario) {
		try {
			CuentaFiat cuentaFiat = cDAO.getCuentaFiat(vista.getFiatSeleccionada(), idUsuario);
			CuentaCripto cuentaCripto = cDAO.getCuentaCripto(criptoSeleccionada.getCodigo(), idUsuario);
			if (cuentaCripto == null || cuentaFiat == null) {
				JOptionPane.showMessageDialog(vista, "No existe una cuenta para alguna de las monedas seleccionadas.");
				return;
			}
			Fiat fiatSeleccionada = (Fiat) cuentaFiat.getMoneda();
			double cantidadFiat = vista.getCantidadFiat();
			if (cantidadFiat > cuentaFiat.getSaldo()) {
				JOptionPane.showMessageDialog(vista, "Fondos insuficientes para realizar la compra.");
				return;
			}

			double cantidadCripto;
			try {
				cantidadCripto = cantidadFiat
						/ ((criptoSeleccionada.getPrecioEnDolar() / fiatSeleccionada.getPrecioEnDolar()));

			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(vista, "Ocurrio un error, intenta nuevamente.");
				System.out.println("Se intentó una división por cero." + e.getMessage());
				return;
			}

			if (cantidadCripto > criptoSeleccionada.getStock()) {
				JOptionPane.showMessageDialog(vista,
						"Lo sentimos, no tenemos stock suficiente de la moneda seleccionada.");
				return;
			}
			// Crear objeto Compra
			int idRandom = (int) (Math.random() * 10000) + 1;
			Compra compra = new Compra(idRandom, idUsuario, cantidadCripto, criptoSeleccionada, cantidadFiat,
					fiatSeleccionada);
			compra.confirmar();
			// Guardar en la base de datos
			modelo.guardarTransaccion(compra);
			double nuevoSaldoFiat = cuentaFiat.getSaldo() - cantidadFiat;
			cuentaFiat.setSaldo(nuevoSaldoFiat);
			cDAO.guardarCuenta(cuentaFiat); // update
			double nuevoSaldoCripto = cuentaCripto.getSaldo() + cantidadCripto;
			cuentaCripto.setSaldo(nuevoSaldoCripto);
			cDAO.guardarCuenta(cuentaCripto); // update
			JOptionPane.showMessageDialog(vista, "Compra realizada exitosamente.");
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(vista, "Ocurrio un error, intenta nuevamente.");
			System.out.println("Error de acceso a puntero null:" + e.getMessage());

		}
	}
}