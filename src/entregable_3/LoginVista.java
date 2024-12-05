package entregable_3;
import javax.swing.*;
import java.awt.*;


public class LoginVista extends JFrame{
	private JTextField campoEmail = new JTextField(10); //mail
	private JTextField campoPasswd = new JTextField(10); //paswd
	private JButton botonLogin = new JButton ("Login");
	private JTextField campoResultado = new JTextField(10); //acá podría ir el mensaje de inicio correcto
	
	public LoginVista() {
		setTitle ("-- LOGIN DE USUARIO --");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,150);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(3, 5));
		
		add (new JLabel("email:")); add (campoEmail); add (new JLabel("password:")); add (campoPasswd);
		add(new JLabel("")); add(new JLabel("")); add(new JLabel("")); add(botonLogin);
		add(new JLabel("")); add(new JLabel("")); add(new JLabel("Estado: ")); add(campoResultado);
		
		campoResultado.setEditable(false);
	}
	
	//Getters para los campos y botón
	public String getEmail() {
		return campoEmail.getText();
	}
	public String getPasswd() {
		return campoPasswd.getText();
	}
	
	//public void setResultado ()
	
	public JButton getBotonLogin() {
		return botonLogin;
	}
	
	public void mostrarMensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
}
