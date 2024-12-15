package entregable_3.Vista;

import javax.swing.*;
import java.awt.*;
import entregable_3.Modelo.CotizacionModelo;

public class CotizacionVista extends JFrame {
    
    private JFrame frame;
    private JTextArea areaTexto;
    private JButton botonCompraBTC;
    private JButton botonCompraETH;
    private JButton botonCompraUSDC;
    private JButton botonCompraUSDT;
    private JButton botonCompraDOGE;

    public CotizacionVista() {
        // Configuración de la ventana principal
        frame = new JFrame("Cotización de Criptomonedas");
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        
        // Crear un área de texto para mostrar los resultados
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);
        frame.add(scroll, BorderLayout.CENTER);
        
        // Crear un botón para actualizar la cotización
        JButton botonActualizar = new JButton("Actualizar Precios");
        botonActualizar.addActionListener(e -> mostrarCotizaciones());
        frame.add(botonActualizar, BorderLayout.SOUTH);
        
        //Crear un botón para la accion de comprar alguna cripto
        JButton botonCompraBTC = new JButton("Comprar BTC");
        botonCompraBTC.addActionListener(null);
        frame.add(botonCompraBTC, BorderLayout.NORTH);
        
      //Crear un botón para la accion de comprar alguna cripto
        JButton botonCompraETH = new JButton("Comprar ETH");
        botonCompraETH.addActionListener(null);
        frame.add(botonCompraETH, BorderLayout.EAST);
        
      //Crear un botón para la accion de comprar alguna cripto
        JButton botonCompraUSDC = new JButton("Comprar USDC");
        botonCompraUSDC.addActionListener(null);
        frame.add(botonCompraUSDC, BorderLayout.WEST);
      
        //Crear un botón para la accion de comprar alguna cripto
        JButton botonCompraUDST = new JButton("Comprar USDT");
        botonCompraUSDT.addActionListener(null);
        frame.add(botonCompraUSDT, BorderLayout.NORTH); //HAY QUE VER DONDE LO PONGO
      
        //Crear un botón para la accion de comprar alguna cripto
        JButton botonCompraDOGE = new JButton("Comprar DOGE");
        botonCompraDOGE.addActionListener(null);
        frame.add(botonCompraDOGE, BorderLayout.NORTH); //HAY QUE VER DONDE LO PONGO
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    public void mostrarCotizaciones() {
        // Obtiene los datos de la cotización desde el modelo
        String respuesta = CotizacionModelo.obtenerCotizaciones();
        if (respuesta != null) {
            // Muestra las cotizaciones en el área de texto
            areaTexto.setText(respuesta);
        } else {
            areaTexto.setText("Error al obtener cotizaciones.");
        }
    }
    
    public JButton getBotonComprarBTC() {
    	return botonCompraBTC;
    }
    
    public JButton getBotonComprarETH() {
    	return botonCompraETH;
    }
    
    public JButton getBotonComprarUSDC() {
    	return botonCompraUSDC;
    }
    
    public JButton getBotonComprarUSDT() {
    	return botonCompraUSDT;
    }
    
    public JButton getBotonComprarDOGE() {
    	return botonCompraDOGE;
    }
    
}