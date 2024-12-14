package entregable_3.Controlador;

import entregable_3.Vista.CompraVista;
import entregable_3.Modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entregable_3.Cripto;
import entregable_3.Cuenta;
import entregable_3.Fiat;

public class CompraControlador {
	private CompraVista vista;
	private TransaccionDAO modelo;
	private MonedaDAO mDAO;
	private CuentaDAO cDAO;
	private Cripto criptoSeleccionada;

	public CompraControlador(CompraVista vista, Cripto criptoSeleccionada) {
		this.vista = vista;
		modelo = FactoryDAO.getTransaccionDAO();
		mDAO = FactoryDAO.getMonedaDAO();
		cDAO = FactoryDAO.getCuentaDAO();
		this.criptoSeleccionada = criptoSeleccionada;

		inicializarVista();
		this.vista.botonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				convertir();
			}
		});
	}

	private void inicializarVista() {
		vista.setStock(criptoSeleccionada.getStock() + " " + criptoSeleccionada.getNombre() + " ("
				+ criptoSeleccionada.getCodigo() + ")");
		vista.setPrecio(String.format("USD %.2f por unidad.", criptoSeleccionada.getPrecioEnDolar()));

		List<String> opciones = new ArrayList<String>();
		List<Cuenta> cuentas = cDAO.listarCuentasFiat();

		for (Cuenta cuenta : cuentas) {
			opciones.add(String.format("%s", cuenta.getId()));
		}

		vista.setOpcionesFiat(opciones);
	}

	private void convertir() {

		Fiat fiatSeleccionada = (Fiat) mDAO.getMoneda(vista.getFiatSeleccionada());
		double cantidadFiat = vista.getCantidadFiat();

		double cantidadCripto = cantidadFiat
				/ ((criptoSeleccionada.getPrecioEnDolar() / fiatSeleccionada.getPrecioEnDolar()));

		vista.setEquivalente(String.format("%.f %s", cantidadCripto, criptoSeleccionada.getCodigo()));

	}
}
