package entregable_3.Controlador;

import entregable_3.CuentaFiat;
import entregable_3.CuentaCripto;
import entregable_3.Cuenta;
import entregable_3.Modelo.*;
import entregable_3.Vista.*;
import entregable_3.Usuario;
import entregable_3.Cuenta.*;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BalanceControlador {
    private CuentaDAO modelo;
    private BalanceVista vista;
    private CotizacionModelo modeloCot;
    private CotizacionVista vistaCot;
    private int id;
    

    public BalanceControlador(CuentaDAO modelo, BalanceVista vista, int id) {
        this.modelo = modelo;
        this.vista = vista;
        this.id = id;
        modeloCot = new CotizacionModelo();
        vistaCot = new CotizacionVista();
        new CotizacionControlador(modeloCot, vistaCot);
        cargarDatosEnVista();
        this.vista.addExportListener(new ExportListener());
        
      //Asigna el controlador al botón de la vista
        this.vista.getBotonCotizar().addActionListener(new IniciarListener());
        
    }

  //clase interna que implementa la accion de Iniciar Sesión
  	class IniciarListener implements ActionListener{
  		public void actionPerformed(ActionEvent e) {
  			try {
  				
  					vista.dispose(); // Cierra la ventana de Balance
  					
  			        //Cotizacion 
  			        modeloCot = new CotizacionModelo();
  			        vistaCot = new CotizacionVista();
  			        new CotizacionControlador(modeloCot,vistaCot);
  			        
  			        //Mostrar la Vista de Controlador
  			        vistaCot.mostrarVentana(); //internamente la pone en true
  				
  			}catch(NumberFormatException ex) {
  				//excepcion
  			}
  				
  			
  		}
  	}

    private void cargarDatosEnVista() {
        // Obtener datos del modelo
    	System.out.println("ID USUARIO: " + id);
        List<CuentaCripto> cuentasCripto = modelo.listarCuentasCripto(id); //ESTOS NUMEROS NO VAN
        List<CuentaFiat> cuentasFiat = modelo.listarCuentasFiat(id); //ESTOS NOMEROS NO VAN
        System.out.println("Cuentas Cripto: " + cuentasCripto.size());
        System.out.println("Cuentas Fiat: " + cuentasFiat.size());

        // Combinar datos en una tabla
        Object[][] data = new Object[cuentasCripto.size() + cuentasFiat.size()][3];
        double balanceTotal = 0;

        int i = 0;
        for (Cuenta cuenta : cuentasCripto) {
            data[i][0] = "Cripto";
            data[i][1] = cuenta.getId();
            data[i][2] = cuenta.getSaldo();
            balanceTotal += cuenta.getSaldo();
            i++;
        }
        for (Cuenta cuenta : cuentasFiat) {
            data[i][0] = "Fiat";
            data[i][1] = cuenta.getId();
            data[i][2] = cuenta.getSaldo();
            balanceTotal += cuenta.getSaldo();
            i++;
        }

        // Configurar datos en la vista
        vista.setTableData(data, new String[]{"Tipo", "ID", "Saldo"});
        vista.setBalanceTotal(String.format("$%.2f", balanceTotal));
    }

    private class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	
            List<CuentaCripto> cuentasCripto = modelo.listarCuentasCripto(id); //ESTOS NUMEROS NO VAN!!
            List<CuentaFiat> cuentasFiat = modelo.listarCuentasFiat(id);

            try (FileWriter writer = new FileWriter("cuentas.csv")) {
                writer.write("Tipo,ID,Saldo\n");
                for (Cuenta cuenta : cuentasCripto) {
                    writer.write(String.format("Cripto,%s,%.2f\n", cuenta.getId(), cuenta.getSaldo()));
                }
                for (Cuenta cuenta : cuentasFiat) {
                    writer.write(String.format("Fiat,%s,%.2f\n", cuenta.getId(), cuenta.getSaldo()));
                }
                JOptionPane.showMessageDialog(vista, "Datos exportados exitosamente a cuentas.csv");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(vista, "Error al exportar datos: " + ex.getMessage());
            }
        }
    }
}