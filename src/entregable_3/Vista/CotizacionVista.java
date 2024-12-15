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
        frame.setSize(600, 400); // Tamaño más largo y ancho
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
        
        
     // Inicializar los botones de instancia
        botonCompraBTC = new JButton("Comprar BTC");
        botonCompraETH = new JButton("Comprar ETH");
        botonCompraUSDC = new JButton("Comprar USDC");
        botonCompraUSDT = new JButton("Comprar USDT");
        botonCompraDOGE = new JButton("Comprar DOGE");
        
     // Agregar los botones a la ventana (ajustar el diseño según sea necesario)
        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 10, 10)); // 2 filas, 3 columnas, con espaciado
        panelBotones.add(botonCompraBTC);
        panelBotones.add(botonCompraETH);
        panelBotones.add(botonCompraUSDC);
        panelBotones.add(botonCompraUSDT);
        panelBotones.add(botonCompraDOGE);
        frame.add(panelBotones, BorderLayout.NORTH);
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