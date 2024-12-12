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

    public BalanceVista() {
    	botonCotizacion = new JButton ("Cotizaciones"); //Boton de cotizaciones
    	add(botonCotizacion);
        setTitle("Balance y Mis Cuentas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Título
        JLabel lblTitulo = new JLabel("Balance y Mis Cuentas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabla para mostrar las cuentas
        tableCuentas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableCuentas);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con balance total y botón de exportar
        JPanel panelInferior = new JPanel(new BorderLayout());
        lblBalanceTotal = new JLabel("Balance Total: $0.00", SwingConstants.LEFT);
        lblBalanceTotal.setFont(new Font("Arial", Font.BOLD, 14));
        panelInferior.add(lblBalanceTotal, BorderLayout.WEST);

        btnExportar = new JButton("Exportar a CSV");
        panelInferior.add(btnExportar, BorderLayout.EAST);
        //agrego boton para cotizaciones
        botonCotizacion = new JButton("Cotizaciones");
        panelInferior.add(botonCotizacion, BorderLayout.NORTH);

        add(panelInferior, BorderLayout.SOUTH);
    }

    public void setTableData(Object[][] data, String[] columnNames) {
        tableCuentas.setModel(new DefaultTableModel(data, columnNames));
    }

    public void setBalanceTotal(String balance) {
        lblBalanceTotal.setText("Balance Total: " + balance);
    }

    public void addExportListener(ActionListener listener) {
        btnExportar.addActionListener(listener);
    }
}