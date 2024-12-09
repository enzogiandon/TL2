package entregable_3.Vista;

import javax.swing.*;
import java.awt.*;
import entregable_3.Modelo.CotizacionModelo;

public class CotizacionVista {
    
    private JFrame frame;
    private JTextArea areaTexto;

    public CotizacionVista() {
        // Configuración de la ventana principal
        frame = new JFrame("Cotización de Criptomonedas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}
