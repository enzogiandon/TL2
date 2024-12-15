package entregable_3.Controlador;

import entregable_3.Vista.*;
import entregable_3.Cripto;
import entregable_3.Modelo.*;


public class CotizacionControlador {
    
    private CotizacionVista vista;
    private CotizacionModelo modelo;
    private CompraVista vistaComp;
    private TransaccionDAO modeloComp;
    private Cripto BTC;
    private Cripto ETH;
    private Cripto USDC;
    private Cripto USDT;
    private Cripto DOGE;
    //HABRIA QUE PASAR EL ID DE USUARIO y 
    //TODO ESTO ESTA SIENDO INTEGRADO POR MARTI H!!
    public CotizacionControlador(CotizacionModelo modelo, CotizacionVista vista) {
    	this.modelo = modelo;
    	this.vista = vista;
    	
    	vistaComp = new CompraVista();
		//CompraVista vista, Cripto criptoSeleccionada, int idUsuario
    	//podría hacer los 5 botones, uno para cada moneda
    	this.vista.getBotonComprarBTC().addActionListener(e -> {
    		vista.dispose();
    		new CompraControlador(vistaComp, BTC, 1);
    		vistaComp.setVisible(true);
        	
    	});
    	this.vista.getBotonComprarETH().addActionListener(e -> {
    		vista.dispose();
    		new CompraControlador(vistaComp, ETH, 1);
    		vistaComp.setVisible(true);
        	
    	});
    	this.vista.getBotonComprarUSDC().addActionListener(e -> {
    		vista.dispose();
    		new CompraControlador(vistaComp, USDC, 1);
    		vistaComp.setVisible(true);
        	
    	});
    	this.vista.getBotonComprarUSDT().addActionListener(e -> {
    		vista.dispose();
    		new CompraControlador(vistaComp, USDT, 1);
    		vistaComp.setVisible(true);
        	
    	});
    	this.vista.getBotonComprarDOGE().addActionListener(e -> {
    		vista.dispose();
    		new CompraControlador(vistaComp, DOGE, 1);
    		vistaComp.setVisible(true);
        	
    	});
    	
    }
    
    public void iniciar() {
        vista.mostrarVentana(); // Muestra la ventana de la aplicación
    }
}