package entregable_3.Controlador;

import entregable_3.Vista.*;
import entregable_3.Cripto;
import entregable_3.Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entregable_3.ConexionBD;

public class CotizacionControlador {
    
    private CotizacionVista vista;
    private CotizacionModelo modelo;
    private CompraVista vistaComp;
    private TransaccionDAO modeloComp;
    private Cripto BTC;
    private Cripto ETH;
    private Cripto USDC;
    private Cripto USDT;
    private Cripto DOGE;
    private int ID; //ID DE USUARIO
    
    //metodo para cargar los datos de cada moneda
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
    	this.ID = ID; 					//le pase el id del usuario desde balance
    	vistaComp = new CompraVista();
    	cargarCriptos();		
    	
    	this.vista.getBotonComprarBTC().addActionListener(e -> {
    		vista.dispose();
    		new CompraControlador(vistaComp, BTC, ID); 
    		vistaComp.setVisible(true);
        	
    	});
    	this.vista.getBotonComprarETH().addActionListener(e -> {
    		vista.dispose();
    		new CompraControlador(vistaComp, ETH, ID);
    		vistaComp.setVisible(true);
        	
    	});
    	this.vista.getBotonComprarUSDC().addActionListener(e -> {
    		vista.dispose();
    		new CompraControlador(vistaComp, USDC, ID);
    		vistaComp.setVisible(true);
        	
    	});
    	this.vista.getBotonComprarUSDT().addActionListener(e -> {
    		vista.dispose();
    		new CompraControlador(vistaComp, USDT, ID);
    		vistaComp.setVisible(true);
        	
    	});
    	this.vista.getBotonComprarDOGE().addActionListener(e -> {
    		vista.dispose();
    		new CompraControlador(vistaComp, DOGE, ID);
    		vistaComp.setVisible(true);
        	
    	});
    	
    }
    
    public void iniciar() {
        vista.mostrarVentana(); // Muestra la ventana de la aplicación
    }
}