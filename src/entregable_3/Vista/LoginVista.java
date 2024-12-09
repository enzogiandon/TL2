package entregable_3.Vista;
import javax.swing.*;
import java.awt.*;


public class LoginVista extends JFrame{
	private JTextField campoEmail;
    private JPasswordField campoPasswd;

	private JButton botonLogin;
	private JButton botonRegistro;
	private JTextField campoResultado;
	
	public LoginVista() {
		
		campoEmail = new JTextField(10); //mail
	    campoPasswd = new JPasswordField(10);//paswd

		botonLogin = new JButton ("Login");
		campoResultado = new JTextField(10); //acá podría ir el mensaje de inicio correcto
		botonRegistro = new JButton ("Registrarse"); //botón para registrarse
		
		setTitle ("-- LOGIN DE USUARIO --");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,400);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(6, 5));
		
		add (new JLabel("email:")); add (campoEmail); add (new JLabel("password:")); add (campoPasswd);
		add(new JLabel("")); add(new JLabel("")); add(new JLabel("")); add(botonLogin);
		add(new JLabel("")); add(new JLabel("")); add(new JLabel("Estado: ")); add(campoResultado);
		add(new JLabel("")); add(new JLabel("")); add(new JLabel("")); add(botonRegistro);
		campoResultado.setEditable(false);
	}
	
	//Getters para los campos y botón
	public String getEmail() {
		return campoEmail.getText();
	}
	public String getPasswd() {
		return campoPasswd.getText();
	}
	
	public void setResultado (boolean resultado) {
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