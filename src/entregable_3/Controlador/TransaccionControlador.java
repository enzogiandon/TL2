package entregable_3.Controlador;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entregable_3.*;
import entregable_3.Vista.*;
import entregable_3.Modelo.*;

public class TransaccionControlador {
	private TransaccionDAO modelo;
	private TransaccionVista vista;
	private List<TransaccionBD> transacciones;  // variable obtener las transacciones y no acceder muchas veces a la BD

	public TransaccionControlador(TransaccionVista vista,int idUsuario) {
		this.vista = vista;
		
		//Inicializo el DAO
		this.modelo = FactoryDAO.getTransaccionDAO();
		
		// Obtener transacciones
		transacciones = modelo.getTransacciones(idUsuario);
		 

		// Cargo la lista en la vista
		cargarTransacciones();
		
		
		// Muestro el resumen si lo encuentro
		this.vista.getTransaccionList().addListSelectionListener(new ListSelectionListener(){
			@Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Solo actuar cuando se confirme la selección
                    mostrarResumen();
                }
            }
			
		});
		
		
		
		}
		
		/*
		this.vista.getMostrarResumenButton().addActionListener(new ActionListener() {
			@Override
			// Accion del boton de mostrar resumen
			public void actionPerformed(ActionEvent e) {
				mostrarResumen();
			}
		});*/

	
	
	private void cargarTransacciones() {
		try {
			List<Integer> ids = new ArrayList<>();
			for (TransaccionBD t: transacciones)
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
			// Busco la descripcion segun id seleccionado
			for (TransaccionBD t: transacciones) {
				if (t.getId() == idSeleccionado) {
					vista.mostrarResumen(t.getResumen());
				}
					
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vista, "Error al mostrar el resumen");
			System.out.println("Error: " + e.getMessage());
		}
	}

}