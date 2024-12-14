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
import entregable_3.CuentaFiat;
import entregable_3.Fiat;

public class CompraControlador {
	private CompraVista vista;
	private TransaccionDAO modelo;
	private MonedaDAO mDAO;
	private CuentaDAO cDAO;
	private Cripto criptoSeleccionada;

	public CompraControlador(CompraVista vista, Cripto criptoSeleccionada,int idUsuario) {
		this.vista = vista;
		modelo = FactoryDAO.getTransaccionDAO();
		mDAO = FactoryDAO.getMonedaDAO();
		cDAO = FactoryDAO.getCuentaDAO();
		this.criptoSeleccionada = criptoSeleccionada;

		inicializarVista(idUsuario);
		this.vista.botonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				convertir();
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

		Fiat fiatSeleccionada = (Fiat) mDAO.getMoneda(vista.getFiatSeleccionada());
		double cantidadFiat = vista.getCantidadFiat();

		double cantidadCripto = cantidadFiat
				/ ((criptoSeleccionada.getPrecioEnDolar() / fiatSeleccionada.getPrecioEnDolar()));

		vista.setEquivalente(String.format("%.5f %s", cantidadCripto, criptoSeleccionada.getCodigo()));

	}
}
