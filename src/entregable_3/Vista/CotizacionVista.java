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
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Colores y fuentes
        Color fondo = new Color(245, 245, 245); // Gris claro
        Color botones = new Color(34, 139, 34); // Verde
        Font fuente = new Font("Verdana", Font.PLAIN, 13);

        // Área de texto
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setBackground(fondo);
        areaTexto.setFont(fuente);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

        // Botón actualizar
        botonActualizar = new JButton("Actualizar Precios");
        botonActualizar.setBackground(botones);
        botonActualizar.setForeground(Color.WHITE);
        add(botonActualizar, BorderLayout.SOUTH);

        // Botones de compra
        JPanel panelBotones = new JPanel(new GridLayout(1, 5, 10, 10));
        botonCompraBTC = new JButton("Comprar BTC");
        botonCompraETH = new JButton("Comprar ETH");
        botonCompraUSDC = new JButton("Comprar USDC");
        botonCompraUSDT = new JButton("Comprar USDT");
        botonCompraDOGE = new JButton("Comprar DOGE");

        JButton[] botonesCompra = {
            botonCompraBTC, botonCompraETH, botonCompraUSDC, botonCompraUSDT, botonCompraDOGE
        };

        for (JButton boton : botonesCompra) {
            boton.setBackground(botones);
            boton.setForeground(Color.WHITE);
            panelBotones.add(boton);
        }

        add(panelBotones, BorderLayout.NORTH);
    }

    public JButton getBotonActualizar() {
        return botonActualizar;
    }

    public void setTextoCotizaciones(String texto) {
        areaTexto.setText(texto);
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
    
    public void mostrarVentana() {
        setVisible(true);
    }
}
