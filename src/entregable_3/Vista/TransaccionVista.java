package entregable_3.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TransaccionVista extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7819207669631740816L;
	private JList<Integer> transaccionList;
    private JTextArea resumenArea;
    private JButton cerrarButton;

    public TransaccionVista() {
        setTitle("Historial de Transacciones");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Fondo
        getContentPane().setBackground(new Color(245, 245, 245));

        // Título
        JLabel titulo = new JLabel("Historial de Transacciones");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(new Color(30, 35, 55));
        titulo.setBounds(180, 20, 300, 30);
        add(titulo);

        // Lista de transacciones
        transaccionList = new JList<>();
        transaccionList.setFont(new Font("Arial", Font.PLAIN, 14));
        transaccionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPaneLista = new JScrollPane(transaccionList);
        scrollPaneLista.setBounds(50, 80, 200, 300);
        add(scrollPaneLista);

        // Área de resumen
        JLabel resumenLabel = new JLabel("Resumen:");
        resumenLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resumenLabel.setBounds(300, 80, 100, 25);
        add(resumenLabel);

        resumenArea = new JTextArea();
        resumenArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resumenArea.setLineWrap(true);
        resumenArea.setWrapStyleWord(true);
        resumenArea.setEditable(false);

        JScrollPane scrollPaneResumen = new JScrollPane(resumenArea);
        scrollPaneResumen.setBounds(300, 110, 250, 270);
        add(scrollPaneResumen);
        
     // Botón de cierre
        cerrarButton = new JButton("CERRAR");
        cerrarButton.setFont(new Font("Arial", Font.BOLD, 14));
        cerrarButton.setBackground(new Color(153, 0, 0)); // Rojo oscuro
        cerrarButton.setForeground(Color.WHITE);
        cerrarButton.setFocusPainted(false);
        cerrarButton.setBounds(250, 400, 100, 40); // Ajusta las coordenadas y el tamaño
        add(cerrarButton);
            }

    public JList<Integer> getTransaccionList() {
        return transaccionList;
    }

    public void actualizarListaTransacciones(List<Integer> ids) {
        transaccionList.setListData(ids.toArray(new Integer[0]));
    }

    public int obtenerIdSeleccionado() {
        return transaccionList.getSelectedValue();
    }

    public void mostrarResumen(String resumen) {
        resumenArea.setText(resumen);
    }
    
    public void cerrarListener(ActionListener listener) {
        cerrarButton.addActionListener(listener);
    }
}
