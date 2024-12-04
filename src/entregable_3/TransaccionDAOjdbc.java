package entregable_3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransaccionDAOjdbc implements TransaccionDAO {

	@Override
	public boolean guardarTransaccion(String descripcion) {
		String sql = "INSERT INTO TRANSACCION (RESUMEN, FECHA_HORA) VALUES (?, CURRENT_TIMESTAMP)";
		try (Connection con = ConexionBD.getConnection();PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, descripcion);
			pst.executeUpdate();
			System.out.println("Se guardó correctamente la descripción de la transacción en la base de datos.");
			return true;
		} catch (SQLException e) {
			System.out.println("Error de SQL al listar monedas: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}