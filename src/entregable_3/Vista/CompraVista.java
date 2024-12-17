package entregable_3.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CompraVista extends JFrame {
    private JLabel stockLabel;
    private JLabel precioLabel;
    private JTextField cantidadFiatField;
    private JComboBox<String> fiatDropdown;
    private JButton convertirButton;
    private JButton comprarButton;
    private JLabel equivalenteLabel;

    public CompraVista() {
        setTitle("Compra de Criptomonedas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Colores y fuentes
        Color fondo = new Color(240, 248, 255); // Azul claro
        Color botones = new Color(30, 144, 255); // Azul intenso
        Color texto = new Color(0, 51, 102); // Azul oscuro
        Font fuente = new Font("Arial", Font.BOLD, 14);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new GridLayout(7, 2, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(fondo);

        // Inicializo los elementos
        stockLabel = new JLabel("-");
        precioLabel = new JLabel("-");
        cantidadFiatField = new JTextField();
        fiatDropdown = new JComboBox<>();
        convertirButton = new JButton("Convertir");
        comprarButton = new JButton("Comprar");
        equivalenteLabel = new JLabel("-");

        // Estética
        stockLabel.setForeground(texto);
        precioLabel.setForeground(texto);
        equivalenteLabel.setForeground(texto);
        convertirButton.setBackground(botones);
        convertirButton.setForeground(Color.WHITE);
        comprarButton.setBackground(botones);
        comprarButton.setForeground(Color.WHITE);

        stockLabel.setFont(fuente);
        precioLabel.setFont(fuente);
        equivalenteLabel.setFont(fuente);

        // Agregar elementos al panel
        panelPrincipal.add(new JLabel("Stock disponible:"));
        panelPrincipal.add(stockLabel);
        panelPrincipal.add(new JLabel("Precio de compra:"));
        panelPrincipal.add(precioLabel);
        panelPrincipal.add(new JLabel("Cantidad Fiat:"));
        panelPrincipal.add(cantidadFiatField);
        panelPrincipal.add(new JLabel("Moneda Fiat:"));
        panelPrincipal.add(fiatDropdown);
        panelPrincipal.add(new JLabel(""));
        panelPrincipal.add(convertirButton);
        panelPrincipal.add(new JLabel("Equivalente en Cripto:"));
        panelPrincipal.add(equivalenteLabel);
        panelPrincipal.add(new JLabel(""));
        panelPrincipal.add(comprarButton);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    public double getCantidadFiat() {
        return Double.valueOf(cantidadFiatField.getText());
    }

    public String getFiatSeleccionada() {
        return (String) fiatDropdown.getSelectedItem();
    }

    public void convertirListener(ActionListener listener) {
        convertirButton.addActionListener(listener);
    }

    public void comprarListener(ActionListener listener) {
        comprarButton.addActionListener(listener);
    }

    public void setEquivalente(String equivalente) {
        equivalenteLabel.setText(equivalente);
    }

    public String getEquivalente() {
        return equivalenteLabel.getText();
    }

    public void setStock(String stock) {
        stockLabel.setText(stock);
    }

    public void setPrecio(String precio) {
        precioLabel.setText(precio);
    }

    public void setOpcionesFiat(List<String> opciones) {
        fiatDropdown.removeAllItems();
        for (String opcion : opciones) {
            fiatDropdown.addItem(opcion);
        }
    }
}
