package entregable_3.Vista;

import javax.swing.*;
import java.awt.*;

public class RegistroVista extends JFrame {
    private JTextField campoNombres;
    private JTextField campoApellidos;
    private JTextField campoEmail;
    private JPasswordField campoPassword;
    private JCheckBox checkboxTerminos;
    private JButton botonRegistrar;
    private JTextField campoResultado;

    public RegistroVista() {
    	campoNombres = new JTextField(10);
        campoApellidos = new JTextField(10);
        campoEmail = new JTextField(10);
        campoPassword = new JPasswordField(10);
        checkboxTerminos = new JCheckBox("Acepto los términos y condiciones");
        botonRegistrar = new JButton("Registrar");
        campoResultado = new JTextField(20);
        setTitle("-- REGISTRO DE USUARIO --");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Nombres:"));
        add(campoNombres);
        add(new JLabel("Apellidos:"));
        add(campoApellidos);
        add(new JLabel("Email:"));
        add(campoEmail);
        add(new JLabel("Contraseña:"));
        add(campoPassword);
        add(checkboxTerminos);
        add(new JLabel(""));
        add(new JLabel("Estado:"));
        add(campoResultado);
        add(botonRegistrar);

        campoResultado.setEditable(false);
    }

    // Getters para acceder a los campos
    public String getNombres() {
        return campoNombres.getText();
    }

    public String getApellidos() {
        return campoApellidos.getText();
    }

    public String getEmail() {
        return campoEmail.getText();
    }

    public String getPassword() {
        return new String(campoPassword.getPassword());
    }

    public boolean isTerminosAceptados() {
        return checkboxTerminos.isSelected();
    }

    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    public void mostrarResultado(String mensaje) {
        campoResultado.setText(mensaje);
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}