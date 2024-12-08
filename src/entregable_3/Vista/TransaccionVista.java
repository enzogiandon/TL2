package entregable_3.Vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TransaccionVista extends JFrame {

    private JList<Integer> transaccionList; // Para mostrar IDs
    private DefaultListModel<Integer> listModel; // Modelo de la lista
    private JTextArea resumenArea; // Para mostrar el resumen
    private JButton mostrarResumenButton; // Botón para mostrar el resumen

    public TransaccionVista() {
        // Configuración de la ventana
        setTitle("Transacciones");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializar componentes
        listModel = new DefaultListModel<>();
        transaccionList = new JList<>(listModel);
        resumenArea = new JTextArea();
        mostrarResumenButton = new JButton("Mostrar Resumen");

        // Panel izquierdo: lista de transacciones
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JScrollPane(transaccionList), BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        // Panel derecho: área de resumen y botón
        JPanel rightPanel = new JPanel(new BorderLayout());
        resumenArea.setEditable(false);
        rightPanel.add(new JScrollPane(resumenArea), BorderLayout.CENTER);
        rightPanel.add(mostrarResumenButton, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.CENTER);
    }

    public void actualizarListaTransacciones(List<Integer> transaccionIds) {
        listModel.clear();
        for (Integer id : transaccionIds) {
            listModel.addElement(id);
        }
    }

    public int obtenerIdSeleccionado() {
        return transaccionList.getSelectedValue();
    }

    public void mostrarResumen(String resumen) {
        resumenArea.setText(resumen);
    }

    public JButton getMostrarResumenButton() {
        return mostrarResumenButton;
    }
}
