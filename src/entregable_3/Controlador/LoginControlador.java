package entregable_3.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entregable_3.Usuario;
import entregable_3.Modelo.*;
import entregable_3.Vista.*;

public class LoginControlador {
	private LoginModelo modelo;
	private LoginVista vista;
	private RegistroVista vistaReg;
	private RegistroModelo modeloReg;
	
	public LoginControlador(LoginModelo modelo, LoginVista vista) {	
        
		this.modelo = modelo;
		this.vista = vista;
		
		modeloReg = new RegistroModelo();
        vistaReg = new RegistroVista();
        new RegistroControlador(modeloReg, vistaReg);

		//Asigna el controlador al botón de la vista
		this.vista.getBotonLogin().addActionListener(new IniciarListener());
		
		this.vista.getBotonRegistro().addActionListener(e -> {
            vista.dispose(); // Cierra la ventana de login
            vistaReg.setVisible(true); // Abre la ventana de registro
        });
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
					vista.dispose(); // Cierra la ventana de login
					//new PrincipalVista(user).setVisible(true); // Abre la vista principal
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