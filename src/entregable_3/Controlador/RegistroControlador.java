package entregable_3.Controlador;
import entregable_3.Persona;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entregable_3.Modelo.RegistroModelo;
import entregable_3.Vista.RegistroVista;

public class RegistroControlador {
    private RegistroModelo modelo;
    private RegistroVista vista;

    public RegistroControlador(RegistroModelo modelo, RegistroVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Vincula el botón al controlador
        this.vista.getBotonRegistrar().addActionListener(new RegistrarListener());
    }

    class RegistrarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String nombres = vista.getNombres();
                String apellidos = vista.getApellidos();
                String email = vista.getEmail();
                String password = vista.getPassword();
                boolean aceptaTerminos = vista.isTerminosAceptados();

                if (!aceptaTerminos) {
                    vista.mostrarMensajeError("Debe aceptar los términos y condiciones.");
                    return;
                }

                if (modelo.emailExistente(email)) {
                    vista.mostrarMensajeError("El email ya está registrado.");
                    return;
                }

                Persona persona = new Persona(nombres, apellidos);
                boolean registrado = modelo.registrarUsuario(persona, email, password, aceptaTerminos);

                if (registrado) {
                    vista.mostrarResultado("Registro exitoso.");
                } else {
                    vista.mostrarResultado("Error en el registro.");
                }
            } catch (Exception ex) {
                vista.mostrarMensajeError("Error: " + ex.getMessage());
            }
        }
    }
}
