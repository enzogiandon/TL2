package entregable_3.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class BalanceVista extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4408864746618104326L;
	private JTable tableCuentas;
    private JButton btnExportar;
    private JLabel lblBalanceTotal;
    private JButton botonCotizacion;
    private JButton botonMovimientos;
    private JButton botonSalir;

    public BalanceVista() {
        setTitle("Balance y Mis Cuentas");
        setSize(800, 500); // Tamaño más amplio para una mejor distribución
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fondo general
        getContentPane().setBackground(new Color(240, 248, 255)); // Azul claro

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Título
        JLabel lblTitulo = new JLabel("Balance y Mis Cuentas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(30, 35, 55)); // Azul fuerte
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabla para mostrar las cuentas
        tableCuentas = new JTable();
        tableCuentas.setFont(new Font("Arial", Font.PLAIN, 14));
        tableCuentas.setRowHeight(25); // Altura de las filas
        tableCuentas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Fuente de la cabecera
        tableCuentas.getTableHeader().setBackground(new Color(30, 35, 55)); 
        tableCuentas.getTableHeader().setForeground(Color.WHITE); // Texto blanco en la cabecera
        JScrollPane scrollPane = new JScrollPane(tableCuentas);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Cuentas Activas"));
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para balance y botones
        JPanel panelInferior = new JPanel(new GridLayout(2, 1, 10, 10)); // Dos filas: Balance y Botones
        panelInferior.setBackground(new Color(240, 248, 255));

        // Balance Total
        lblBalanceTotal = new JLabel("Balance: USD $0.00", SwingConstants.LEFT);
        lblBalanceTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblBalanceTotal.setForeground(new Color(30, 35, 55)); // Azul osc
        panelInferior.add(lblBalanceTotal);

        // Panel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelBotones.setBackground(new Color(240, 248, 255));

        btnExportar = new JButton("Exportar a CSV");
        btnExportar.setFont(new Font("Arial", Font.BOLD, 14));
        btnExportar.setBackground(new Color(30, 35, 55)); // Azul oscuro
        btnExportar.setForeground(Color.WHITE);
        panelBotones.add(btnExportar);

        botonCotizacion = new JButton("Cotizaciones");
        botonCotizacion.setFont(new Font("Arial", Font.BOLD, 14));
        botonCotizacion.setBackground(new Color(30, 35, 55)); // Azul oscuro
        botonCotizacion.setForeground(Color.WHITE);
        panelBotones.add(botonCotizacion);

        botonMovimientos = new JButton("Movimientos");
        botonMovimientos.setFont(new Font("Arial", Font.BOLD, 14));
        botonMovimientos.setBackground(new Color(30, 35, 55)); // Azul oscuro
        botonMovimientos.setForeground(Color.WHITE);
        panelBotones.add(botonMovimientos);
        
        botonSalir = new JButton("SALIR");
        botonSalir.setFont(new Font("Arial", Font.BOLD, 14));
        botonSalir.setBackground(new Color(153, 0, 0)); // Rojo
        botonSalir.setForeground(Color.WHITE);
        panelBotones.add(botonSalir);

        panelInferior.add(panelBotones);

        add(panelInferior, BorderLayout.SOUTH);
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
    
    public JButton getBotonMovimientos() {
    	return botonMovimientos;
    }
    
    public JButton getBotonSalir() {
        return botonSalir;
    }
    
    
}
