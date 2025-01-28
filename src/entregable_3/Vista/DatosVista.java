package entregable_3.Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class DatosVista extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4678786069994823883L;
	private JButton btnCargarDatos;
    private JButton btnSalir;
    private JTextArea areaMensajes;

    public DatosVista() {
        setTitle("Gestión de Datos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración general del panel
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelPrincipal.setBackground(new Color(240, 240, 240));

        // Área de mensajes con borde y scroll
        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        areaMensajes.setFont(new Font("SansSerif", Font.PLAIN, 14));
        areaMensajes.setBackground(new Color(245, 245, 245));
        areaMensajes.setBorder(BorderFactory.createTitledBorder("Mensajes"));

        JScrollPane scrollMensajes = new JScrollPane(areaMensajes);
        scrollMensajes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));

        btnCargarDatos = new JButton("Cargar Datos");
        btnCargarDatos.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCargarDatos.setBackground(new Color(30, 35, 55));
        btnCargarDatos.setForeground(Color.WHITE);
        btnCargarDatos.setFocusPainted(false);

        btnSalir = new JButton("Cerrar");
        btnSalir.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSalir.setBackground(new Color(153, 0, 0));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);

        panelBotones.add(btnCargarDatos);
        panelBotones.add(btnSalir);

        // Añadir componentes al panel principal
        panelPrincipal.add(scrollMensajes, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Añadir el panel principal al marco
        add(panelPrincipal);
    }

    public void agregarListenerCargarDatos(ActionListener listener) {
        btnCargarDatos.addActionListener(listener);
    }

    public void agregarListenerParaCerrar(ActionListener listener) {
        btnSalir.addActionListener(listener);
    }

    public void mostrarMensaje(String mensaje) {
        areaMensajes.append(mensaje + "\n");
    }
}