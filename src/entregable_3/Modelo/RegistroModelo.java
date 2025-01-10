package entregable_3.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entregable_3.ConexionBD;
import entregable_3.Persona;

public class RegistroModelo {

    // Verifica si el email ya está registrado
    public boolean emailExistente(String email) {
        String sql = "SELECT 1 FROM USUARIO WHERE EMAIL = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Retorna true si encuentra un registro
        } catch (SQLException e) {
            System.out.println("Error al verificar email: " + e.getMessage());
            e.printStackTrace();
            return true; // En caso de error, asumimos que el email existe
        }
    }

    // Registra un nuevo usuario
    public boolean registrarUsuario(Persona persona, String email, String password, boolean aceptaTerminos) {
        String sqlPersona = "INSERT INTO PERSONA (NOMBRES, APELLIDOS) VALUES (?, ?)";
        String sqlUsuario = "INSERT INTO USUARIO (ID_PERSONA, EMAIL, PASSWORD, ACEPTA_TERMINOS) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConnection()) {
            con.setAutoCommit(false); // Inicia transacción

            // Inserta persona
            try (PreparedStatement pstmtPersona = con.prepareStatement(sqlPersona, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmtPersona.setString(1, persona.getNombres());
                pstmtPersona.setString(2, persona.getApellidos());
                pstmtPersona.executeUpdate();

                // Obtiene el ID generado para la persona
                ResultSet rs = pstmtPersona.getGeneratedKeys();
                if (rs.next()) {
                    int idPersona = rs.getInt(1);

                    // Inserta usuario asociado a la persona
                    try (PreparedStatement pstmtUsuario = con.prepareStatement(sqlUsuario)) {
                        pstmtUsuario.setInt(1, idPersona);
                        pstmtUsuario.setString(2, email);
                        pstmtUsuario.setString(3, password);
                        pstmtUsuario.setBoolean(4, aceptaTerminos);
                        pstmtUsuario.executeUpdate();
                    }
                }
            }

            con.commit(); // Confirma transacción
            return true; // Registro exitoso
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();
            return false; // Fallo en el registro
        }
    }
}

