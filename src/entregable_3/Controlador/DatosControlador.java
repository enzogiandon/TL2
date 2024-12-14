package entregable_3.Controlador;

import entregable_3.Modelo.DatosModelo;
import entregable_3.Vista.DatosVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatosControlador {

    private DatosModelo modelo;
    private DatosVista vista;

    public DatosControlador(DatosModelo modelo, DatosVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.agregarListenerCargarDatos(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });
    }

    private void cargarDatos() {
        try {
            //modelo.crearTablas();
            modelo.cargarDatosIniciales();
            vista.mostrarMensaje("Las tablas y los datos iniciales se han cargado exitosamente.");
        } catch (Exception e) {
            vista.mostrarMensaje("Error al cargar datos: " + e.getMessage());
        }
    }

}
