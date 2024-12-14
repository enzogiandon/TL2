package entregable_3.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DatosVista extends JFrame {

    private JButton btnCargarDatos;
    private JTextArea areaMensajes;

    public DatosVista() {
        setTitle("Cargar Datos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        btnCargarDatos = new JButton("Cargar Datos");
        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);

        add(btnCargarDatos, BorderLayout.NORTH);
        add(new JScrollPane(areaMensajes), BorderLayout.CENTER);
    }

    public void agregarListenerCargarDatos(ActionListener listener) {
        btnCargarDatos.addActionListener(listener);
    }

    public void mostrarMensaje(String mensaje) {
        areaMensajes.append(mensaje + "\n");
    }
}
