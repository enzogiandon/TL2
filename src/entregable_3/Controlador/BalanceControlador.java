package entregable_3.Controlador;

import entregable_3.Modelo.CuentaDAOjdbc;
import entregable_3.Vista.BalanceVista;
import entregable_3.Cuenta;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BalanceControlador {
    private CuentaDAOjdbc modelo;
    private BalanceVista vista;

    public BalanceControlador(CuentaDAOjdbc modelo, BalanceVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        initController();
    }

    private void initController() {
        cargarDatosEnVista();
        vista.addExportListener(new ExportListener());
    }

    private void cargarDatosEnVista() {
        // Obtener datos del modelo
        List<Cuenta> cuentasCripto = modelo.listarCuentasCripto();
        List<Cuenta> cuentasFiat = modelo.listarCuentasFiat();

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
            List<Cuenta> cuentasCripto = modelo.listarCuentasCripto();
            List<Cuenta> cuentasFiat = modelo.listarCuentasFiat();

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