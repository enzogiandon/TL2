package entregable_3.Controlador;

import entregable_3.Vista.*;
import entregable_3.Modelo.*;
import entregable_3.Cripto;
import entregable_3.ConexionBD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CotizacionControlador {    
    private CotizacionVista vista;
    private CotizacionModelo modelo;
    private CompraVista vistaComp;
    private Cripto BTC;
    private Cripto ETH;
    private Cripto USDC;
    private Cripto USDT;
    private Cripto DOGE; 
    private int ID; // ID del usuario
    
    // Método para cargar los datos de cada moneda desde la base de datos
    private void cargarCriptos(){
        String query = "SELECT ID, NOMENCLATURA, VALOR_DOLAR, NOMBRE, STOCK FROM MONEDA WHERE NOMENCLATURA IN ('BTC', 'ETH', 'USDC', 'USDT', 'DOGE')";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String codigo = rs.getString("NOMENCLATURA");
                double precioEnDolar = rs.getDouble("VALOR_DOLAR");
                String nombre = rs.getString("NOMBRE");
                double stock = rs.getDouble("STOCK");
                switch (codigo) {
                    case "BTC":
                        BTC = new Cripto(id, codigo, precioEnDolar, nombre, stock);
                        break;
                    case "ETH":
                        ETH = new Cripto(id, codigo, precioEnDolar, nombre, stock);
                        break;
                    case "USDC":
                        USDC = new Cripto(id, codigo, precioEnDolar, nombre, stock);
                        break;
                    case "USDT":
                        USDT = new Cripto(id, codigo, precioEnDolar, nombre, stock);
                        break;
                    case "DOGE":
                        DOGE = new Cripto(id, codigo, precioEnDolar, nombre, stock);
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al cargar las criptomonedas: " + e.getMessage());
        }
    }
    
    public CotizacionControlador(CotizacionModelo modelo, CotizacionVista vista, int ID) {
        this.modelo = modelo;
        this.vista = vista;
        this.ID = ID; // ID del usuario pasado desde Balance
        vistaComp = new CompraVista();
        cargarCriptos();

        // Asignar listeners para cada botón de compra
        this.vista.getBotonCompraBTC().addActionListener(e -> abrirCompra(BTC));
        this.vista.getBotonCompraETH().addActionListener(e -> abrirCompra(ETH));
        this.vista.getBotonCompraUSDC().addActionListener(e -> abrirCompra(USDC));
        this.vista.getBotonCompraUSDT().addActionListener(e -> abrirCompra(USDT));
        this.vista.getBotonCompraDOGE().addActionListener(e -> abrirCompra(DOGE));

        // Asignar listener al botón de actualizar precios
        this.vista.getBotonActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCotizaciones();
            }
        });
    }

    // Método para abrir la vista de compra con la criptomoneda seleccionada
    private void abrirCompra(Cripto cripto) {
        vista.dispose();
        new CompraControlador(vistaComp, cripto, ID); 
        vistaComp.setVisible(true);
    }

    // Método para actualizar las cotizaciones en la vista
    private void actualizarCotizaciones() {
        String cotizaciones = CotizacionModelo.obtenerCotizaciones();
        if (cotizaciones != null) {
            vista.setTextoCotizaciones(cotizaciones);
        } else {
            vista.setTextoCotizaciones("No se pudieron obtener las cotizaciones.");
        }
    }

    public void iniciar() {
        vista.mostrarVentana(); // Muestra la ventana de la aplicación
        actualizarCotizaciones(); // Actualiza las cotizaciones al iniciar
    }
}
