package entregable_3.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entregable_3.*;
import entregable_3.Vista.*;
import entregable_3.Modelo.*;

public class TransaccionControlador {
	private TransaccionDAO modelo;
	private TransaccionVista vista;

	public TransaccionControlador(TransaccionDAO modelo, TransaccionVista vista) {
		this.modelo = modelo;
		this.vista = vista;

	
		List<Transaccion> transacciones = modelo.getTransacciones();

		cargarTransacciones(transacciones);	
		
		
		
		this.vista.getMostrarResumenButton().addActionListener(new ActionListener() {
			@Override
			// Accion del boton de mostrar resumen
			public void actionPerformed(ActionEvent e) {
				mostrarResumen();
			}
		});

	}
	
	private void cargarTransacciones(List<Transaccion> transacciones) {
		try {
			List<Integer> ids = new ArrayList<>();
			for (Transaccion t: transacciones)
				ids.add(t.getId());
			vista.actualizarListaTransacciones(ids);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void mostrarResumen() {
		try {
			int idSeleccionado = vista.obtenerIdSeleccionado();
			if (idSeleccionado < 1) { // si es un ID invalido
				JOptionPane.showMessageDialog(vista, "Por favor selecciona una transaccion.");
				return;
			}
			// Muestro la descripcion
			String resumen = modelo.getDescripcionPorId(idSeleccionado);
			vista.mostrarResumen(resumen);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(vista, "Error al mostrar el resumen");
			System.out.println("Error: " + e.getMessage());
		}
	}

}