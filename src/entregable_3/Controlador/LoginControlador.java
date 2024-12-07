package entregable_3.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entregable_3.Usuario;
import entregable_3.Modelo.LoginModelo;
import entregable_3.Vista.LoginVista;

public class LoginControlador {
	private LoginModelo modelo;
	private LoginVista vista;
	
	public LoginControlador(LoginModelo modelo, LoginVista vista) {
		this.modelo = modelo;
		this.vista = vista;
		//Asigna el controlador al botón de la vista
		this.vista.getBotonLogin().addActionListener(new IniciarListener());
	}
	
	
	
	//clase interna que implementa la accion de Iniciar Sesión
	class IniciarListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				String email = vista.getEmail();
				String passwd = vista.getPasswd();
				
				//Llamada al modelo para realizar la operación
				Usuario user = modelo.iniciar(email,passwd);
				
				if (user != null) {
					//Actualiza la vista con el resultado
					vista.setResultado(true);
				}else {
					vista.setResultado(false); //inicio incorrecto
					vista.mostrarMensajeError("Credenciales inválidas. Intente nuevamente.");
				}
				
			}catch(NumberFormatException ex) {
				vista.mostrarMensajeError("Por favor ingrese datos válidos");
			}
		}
	}

}
