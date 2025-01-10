package entregable_3.Vista;

import javax.swing.*;
import java.awt.*;

public class LoginVista extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4574118564183103125L;
	private JTextField campoEmail;
    private JPasswordField campoPasswd;
    private JButton botonLogin;
    private JButton botonRegistro;
    private JTextField campoResultado;

    public LoginVista() {
        // Configuración de la ventana principal
        setTitle("Login de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Panel principal
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo para email
        JLabel etiquetaEmail = new JLabel("Email:");
        etiquetaEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(etiquetaEmail, gbc);

        campoEmail = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelPrincipal.add(campoEmail, gbc);

        // Campo para contraseña
        JLabel etiquetaPasswd = new JLabel("Password:");
        etiquetaPasswd.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(etiquetaPasswd, gbc);

        campoPasswd = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelPrincipal.add(campoPasswd, gbc);
        campoPasswd.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "login");
        
        // Esto es para que al tocar Enter en el campo de la contraseña ya inicie

        // Botón de login
        botonLogin = new JButton("Login");
        botonLogin = new JButton("Iniciar sesión");
        botonLogin.setBackground(new Color(30, 35, 100)); // Color azul claro
        botonLogin.setForeground(Color.WHITE);
        botonLogin.setFocusPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panelPrincipal.add(botonLogin, gbc);

        // Botón de registro
        botonRegistro = new JButton("Registrarse");
        botonRegistro.setBackground(new Color(30, 35, 55)); 
        botonRegistro.setForeground(Color.WHITE);
        botonRegistro.setFocusPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panelPrincipal.add(botonRegistro, gbc);

        // Campo para mostrar mensajes
        campoResultado = new JTextField();
        campoResultado.setEditable(false);
        campoResultado.setHorizontalAlignment(JTextField.CENTER);
        campoResultado.setFont(new Font("Arial", Font.PLAIN, 12));
        campoResultado.setForeground(Color.DARK_GRAY);
        campoResultado.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Añadir los paneles a la ventana
        add(panelPrincipal, BorderLayout.CENTER);
        add(campoResultado, BorderLayout.SOUTH);
    }

    // Getters para los campos y botones
    public String getEmail() {
        return campoEmail.getText();
    }

    public String getPasswd() {
        return new String(campoPasswd.getPassword());
    }

    public void setResultado(boolean resultado) {
        campoResultado.setText(resultado ? "Inicio exitoso" : "Fallo en inicio");
    }

    public JButton getBotonLogin() {
        return botonLogin;
    }

    public JButton getBotonRegistro() {
        return botonRegistro;
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
