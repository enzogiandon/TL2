package entregable_3;

public class LoginModelo {
    private Usuario[] usuariosRegistrados;

    public LoginModelo() {
        // Usuarios de ejemplo
        usuariosRegistrados = new Usuario[] {
            new Usuario("test@example.com", "1234"),
            new Usuario("user@example.com", "pass")
        };
    }

    public Usuario iniciar(String email, String passwd) {
        for (Usuario usuario : usuariosRegistrados) {
            if (usuario.getEmail().equals(email) && usuario.getPasswd().equals(passwd)) {
                return usuario; // Usuario autenticado
            }
        }
        return null; // Usuario no encontrado o credenciales incorrectas
    }
}

