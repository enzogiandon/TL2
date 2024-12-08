package entregable_3.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JOptionPane;

import entregable_3.*;
import entregable_3.Vista.*;
import entregable_3.Modelo.*;

public class TransaccionControlador {
	private TransaccionVista vista;
	private TransaccionDAOjdbc modelo;

	public TransaccionControlador(TransaccionVista vista, TransaccionDAOjdbc modelo) {
		this.vista = vista;
		this.modelo = modelo;

		this.vista.getMostrarResumenButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarResumen();
			}
		});

	}
	
//	private void cargarTransacciones() {}
		
	
	
	
	private void mostrarResumen() {
		try {
			int idSeleccionado = vista.obtenerIdSeleccionado();
			if (idSeleccionado < 1) { //si es un ID invalido
				JOptionPane.showMessageDialog(vista,"Por favor selecciona una transaccion.");
				return;
			}
			//Muestro la descripcion 
			String resumen = modelo.getDescripcionPorId(idSeleccionado);
			vista.mostrarResumen(resumen);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vista, "Error al mostrar el resumen");
			System.out.println("Error: " + e.getMessage());
		}
	}

	
}