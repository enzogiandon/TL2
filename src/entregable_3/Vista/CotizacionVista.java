package entregable_3.Vista;

import javax.swing.*;
import java.awt.*;

public class CotizacionVista extends JFrame {
	private JTextArea areaTexto;
    private JButton botonActualizar;
    private JButton botonCompraBTC;
    private JButton botonCompraETH;
    private JButton botonCompraUSDC;
    private JButton botonCompraUSDT;
    private JButton botonCompraDOGE;

    public CotizacionVista() {
        setTitle("Cotización de Criptomonedas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Colores y fuentes
        Color fondo = new Color(245, 245, 245); // Gris claro
        Color botones = new Color(30, 35, 55); // azul marino
        Font fuente = new Font("Verdana", Font.PLAIN, 22);

        // Área de texto
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setBackground(fondo);
        areaTexto.setFont(fuente);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

        // Botón actualizar
        botonActualizar = new JButton("ACTUALIZAR PRECIOS");
        botonActualizar.setBackground(botones);
        botonActualizar.setForeground(Color.WHITE);
        add(botonActualizar, BorderLayout.SOUTH);

        // Panel con botones e imágenes
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 5, 10, 10));
        panelBotones.setBackground(fondo);

        // Crear botones con imágenes
        botonCompraBTC = crearBotonConImagen("Comprar BTC", "bitcoin.jpg");
        botonCompraETH = crearBotonConImagen("Comprar ETH", "ethereum.jpg");
        botonCompraUSDC = crearBotonConImagen("Comprar USDC", "usdcoin.jpg");
        botonCompraUSDT = crearBotonConImagen("Comprar USDT", "tether.jpg");
        botonCompraDOGE = crearBotonConImagen("Comprar DOGE", "dogecoin.jpg");

        // Agregar los paneles individuales al panel principal
        panelBotones.add(botonCompraBTC.getParent());
        panelBotones.add(botonCompraETH.getParent());
        panelBotones.add(botonCompraUSDC.getParent());
        panelBotones.add(botonCompraUSDT.getParent());
        panelBotones.add(botonCompraDOGE.getParent());

        add(panelBotones, BorderLayout.NORTH);
    }

    // Método para crear un botón con imagen y encapsularlo en un JPanel
    private JButton crearBotonConImagen(String texto, String nombreImagen) {
        // Crear el panel que contendrá el botón y la imagen
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245));

        // Crear el botón
        JButton boton = new JButton(texto);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setBackground(new Color(30, 35, 55));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);

        // Crear la etiqueta con la imagen
        JLabel etiquetaImagen;
        try {
            // Cargar la imagen desde la ruta
            ImageIcon icono = new ImageIcon(getClass().getResource("/entregable_3/Imagenes/" + nombreImagen));
            Image imagenEscalada = icono.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            etiquetaImagen = new JLabel(new ImageIcon(imagenEscalada));
        } catch (Exception e) {
            etiquetaImagen = new JLabel("Imagen no encontrada");
            etiquetaImagen.setForeground(Color.RED);
        }

        etiquetaImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar los componentes al panel
        panel.add(boton);
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre botón e imagen
        panel.add(etiquetaImagen);

        // Envolver el botón con el panel
        return boton;
    }

    // Getters para cada botón
    public JButton getBotonActualizar() {
        return botonActualizar;
    }

    public JButton getBotonCompraBTC() {
        return botonCompraBTC;
    }

    public JButton getBotonCompraETH() {
        return botonCompraETH;
    }

    public JButton getBotonCompraUSDC() {
        return botonCompraUSDC;
    }

    public JButton getBotonCompraUSDT() {
        return botonCompraUSDT;
    }

    public JButton getBotonCompraDOGE() {
        return botonCompraDOGE;
    }

    public void setTextoCotizaciones(String texto) {
        areaTexto.setText(texto);
    }

    public void mostrarVentana() {
        setVisible(true);
    }
}
