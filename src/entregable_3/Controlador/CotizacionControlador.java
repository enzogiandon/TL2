package entregable_3.Controlador;

import entregable_3.Vista.CotizacionVista;
import entregable_3.Modelo.CotizacionModelo;

public class CotizacionControlador {
    
    private CotizacionVista vista;
    private CotizacionModelo modelo;
    
    public CotizacionControlador(CotizacionModelo modelo, CotizacionVista vista) {
    	this.modelo = modelo;
    	this.vista = vista;
    }
    
    public void iniciar() {
        vista.mostrarVentana(); // Muestra la ventana de la aplicación
    }
}
