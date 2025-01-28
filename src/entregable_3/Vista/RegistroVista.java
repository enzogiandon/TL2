package entregable_3.Vista;

import javax.swing.*;
import java.awt.*;

public class RegistroVista extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6113396190751485680L;
	private JTextField campoNombre, campoApellidos, campoEmail;
    private JPasswordField campoClave;
    private JCheckBox terminosCheckbox;
    private JButton botonRegistrar;

    public RegistroVista() {
        setTitle("Registro de Usuario");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Fondo
        getContentPane().setBackground(new Color(230, 240, 250));

        // Título
        JLabel titulo = new JLabel("Registro de Usuario");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(new Color(50, 70, 150));
        titulo.setBounds(100, 20, 250, 30);
        add(titulo);

        // Etiqueta y campo para el nombre
        JLabel nombreLabel = new JLabel("Nombres:");
        nombreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nombreLabel.setBounds(50, 80, 100, 25);
        add(nombreLabel);

        campoNombre = new JTextField();
        campoNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        campoNombre.setBounds(150, 80, 200, 25);
        add(campoNombre);

        // Etiqueta y campo para apellidos
        JLabel apellidosLabel = new JLabel("Apellidos:");
        apellidosLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        apellidosLabel.setBounds(50, 140, 100, 25);
        add(apellidosLabel);

        campoApellidos = new JTextField();
        campoApellidos.setFont(new Font("Arial", Font.PLAIN, 14));
        campoApellidos.setBounds(150, 140, 200, 25);
        add(campoApellidos);

        // Etiqueta y campo para email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setBounds(50, 200, 100, 25);
        add(emailLabel);

        campoEmail = new JTextField();
        campoEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        campoEmail.setBounds(150, 200, 200, 25);
        add(campoEmail);

        // Etiqueta y campo para contraseña
        JLabel claveLabel = new JLabel("Contraseña:");
        claveLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        claveLabel.setBounds(50, 260, 100, 25);
        add(claveLabel);

        campoClave = new JPasswordField();
        campoClave.setFont(new Font("Arial", Font.PLAIN, 14));
        campoClave.setBounds(150, 260, 200, 25);
        add(campoClave);

        // Checkbox para aceptar términos
        terminosCheckbox = new JCheckBox("Acepto los términos y condiciones");
        terminosCheckbox.setFont(new Font("Arial", Font.PLAIN, 12));
        terminosCheckbox.setBackground(new Color(230, 240, 250));
        terminosCheckbox.setBounds(50, 320, 300, 25);
        add(terminosCheckbox);

        // Botón de registrar
        botonRegistrar = new JButton("Registrar");
        botonRegistrar.setFont(new Font("Arial", Font.BOLD, 16));
        botonRegistrar.setBackground(new Color(30, 35, 55));
        botonRegistrar.setForeground(Color.WHITE);
        botonRegistrar.setBounds(130, 370, 120, 35);
        add(botonRegistrar);
    }

    public String getCampoNombre() {
        return campoNombre.getText();
    }

    public String getApellidos() {
        return campoApellidos.getText();
    }

    public String getEmail() {
        return campoEmail.getText();
    }

    public String getPassword() {
        return new String(campoClave.getPassword());
    }

    public boolean isTerminosAceptados() {
        return terminosCheckbox.isSelected();
    }

    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarResultado(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }
}