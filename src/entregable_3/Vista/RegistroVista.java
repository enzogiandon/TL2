package entregable_3.Vista;

import javax.swing.*;
import java.awt.*;

public class RegistroVista extends JFrame {
    private JTextField campoNombres = new JTextField(10);
    private JTextField campoApellidos = new JTextField(10);
    private JTextField campoEmail = new JTextField(10);
    private JPasswordField campoPassword = new JPasswordField(10);
    private JCheckBox checkboxTerminos = new JCheckBox("Acepto los términos y condiciones");
    private JButton botonRegistrar = new JButton("Registrar");
    private JTextField campoResultado = new JTextField(20);

    public RegistroVista() {
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