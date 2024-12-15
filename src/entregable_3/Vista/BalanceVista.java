package entregable_3.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class BalanceVista extends JFrame {
    private JTable tableCuentas;
    private JButton btnExportar;
    private JLabel lblBalanceTotal;
    private JButton botonCotizacion;
    private JButton botonMovimientos;

    public BalanceVista() {
        setTitle("Balance y Mis Cuentas");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Título
        JLabel lblTitulo = new JLabel("Balance y Mis Cuentas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(33, 97, 140));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabla para mostrar las cuentas
        tableCuentas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableCuentas);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Detalle de Cuentas"));
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con balance total y botones
        JPanel panelInferior = new JPanel(new GridLayout(2, 1, 10, 10));
        panelInferior.setBackground(new Color(240, 248, 255));

        // Balance total
        lblBalanceTotal = new JLabel("Balance: USD $0.00", SwingConstants.CENTER);
        lblBalanceTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblBalanceTotal.setForeground(new Color(34, 139, 34));
        panelInferior.add(lblBalanceTotal);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnExportar = new JButton("Exportar a CSV");
        botonCotizacion = new JButton("Cotizaciones");
        botonMovimientos = new JButton("Movimientos");

        estilizarBoton(btnExportar);
        estilizarBoton(botonCotizacion);
        estilizarBoton(botonMovimientos);

        panelBotones.add(btnExportar);
        panelBotones.add(botonCotizacion);
        panelBotones.add(botonMovimientos);

        panelInferior.add(panelBotones);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(70, 130, 180));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    public void setTableData(Object[][] data, String[] columnNames) {
        tableCuentas.setModel(new DefaultTableModel(data, columnNames));
    }

    public void setBalanceTotal(String balance) {
        lblBalanceTotal.setText("Balance: USD " + balance);
    }

    public void addExportListener(ActionListener listener) {
        btnExportar.addActionListener(listener);
    }

    public JButton getBotonCotizar() {
        return botonCotizacion;
    }
}
