package entregable_3.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entregable_3.ConexionBD;
import entregable_3.Usuario;

public class LoginModelo {

	public Usuario iniciar(String email, String passwd) {
		String sql = "SELECT u.ID, u.EMAIL, u.PASSWORD FROM USUARIO u WHERE u.EMAIL = ? AND u.PASSWORD = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			// Setear parámetros
			pstmt.setString(1, email);
			pstmt.setString(2, passwd);

			// Ejecutar consulta
			try (ResultSet rs = pstmt.executeQuery();) {

				if (rs.next()) {
					// Usuario encontrado, devolver objeto Usuario
					return new Usuario(rs.getString("EMAIL"), rs.getString("PASSWORD"));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar usuario: " + e.getMessage());
			e.printStackTrace();
		}

		// Usuario no encontrado
		return null;
	}
}
