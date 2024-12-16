package entregable_3.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CompraVista extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel stockLabel;
    private JLabel precioLabel;
    private JTextField cantidadFiatField;
    private JComboBox<String> fiatDropdown;
    private JButton convertirButton;
    private JButton comprarButton;
    private JButton cerrarButton;
    private JLabel equivalenteLabel;

    public CompraVista() {
        setTitle("Compra de Criptomonedas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Colores y fuentes
        Color fondo = new Color(240, 248, 255); // Azul claro
        Color botones = new Color(0, 128, 255); // Azul oscuro
        Color texto = new Color(0, 51, 102); // Azul oscuro
        Font fuente = new Font("Arial", Font.BOLD, 14);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new GridLayout(6, 2, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(fondo);

        // Inicializo los elementos
        stockLabel = new JLabel("-");
        precioLabel = new JLabel("-");
        cantidadFiatField = new JTextField();
        fiatDropdown = new JComboBox<>();
        convertirButton = new JButton("Convertir");
        comprarButton = new JButton("Comprar");
        cerrarButton = new JButton("CERRAR");
        equivalenteLabel = new JLabel("-");

        // Estética
        stockLabel.setForeground(texto);
        precioLabel.setForeground(texto);
        equivalenteLabel.setForeground(texto);
        convertirButton.setBackground(botones);
        convertirButton.setForeground(Color.WHITE);
        comprarButton.setBackground(botones);
        comprarButton.setForeground(Color.WHITE);
        cerrarButton.setBackground(new Color(220, 53, 69)); //rojo
        cerrarButton.setForeground(Color.WHITE);

        stockLabel.setFont(fuente);
        precioLabel.setFont(fuente);
        equivalenteLabel.setFont(fuente);

        // Agregar elementos al panel
        panelPrincipal.add(new JLabel("Stock disponible:", JLabel.RIGHT));
        panelPrincipal.add(stockLabel);
        panelPrincipal.add(new JLabel("Precio de compra:", JLabel.RIGHT));
        panelPrincipal.add(precioLabel);
        panelPrincipal.add(new JLabel("Cantidad Fiat:", JLabel.RIGHT));
        panelPrincipal.add(cantidadFiatField);
        panelPrincipal.add(new JLabel("Moneda Fiat:", JLabel.RIGHT));
        panelPrincipal.add(fiatDropdown);
        panelPrincipal.add(new JLabel("Equivalente en Cripto:", JLabel.RIGHT));
        panelPrincipal.add(equivalenteLabel);

        // Panel inferior para botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(fondo);
        panelBotones.add(convertirButton);
        panelBotones.add(comprarButton);
        panelBotones.add(cerrarButton);

        // Añadir paneles al frame
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    // Métodos de acceso y eventos
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
    
    public void cerrarListener(ActionListener listener) {
        cerrarButton.addActionListener(listener);
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
