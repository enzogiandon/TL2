package entregable_3.Modelo;

import entregable_3.*;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAOjdbc implements TransaccionDAO {

	@Override
	public boolean guardarTransaccion(Transaccion t) {
		String sql = "INSERT INTO TRANSACCION (ID, RESUMEN, FECHA_HORA,ID_USUARIO) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, t.getId());
			pst.setString(2, t.getDescripcion());
			pst.setInt(3, t.getIdUsuario());
			pst.executeUpdate();
			System.out.println("Se guardó correctamente la descripción de la transacción en la base de datos.");
			con.close();
			pst.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Error de SQL al listar monedas: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<TransaccionBD> getTransacciones(int idUsuario) {
		String sql = "SELECT ID, RESUMEN, FECHA_HORA, ID_USUARIO FROM TRANSACCION WHERE ID_USUARIO = ?";
		List<TransaccionBD> transacciones = new ArrayList<>();

		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, idUsuario);
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					int id = rs.getInt("ID");
					String resumen = rs.getString("RESUMEN");
					Timestamp fechaHora = rs.getTimestamp("FECHA_HORA");

					transacciones
							.add(new TransaccionBD(id, resumen, fechaHora.toLocalDateTime().toLocalDate(), idUsuario));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL: ");
			e.printStackTrace();
		}

		return transacciones;
	}
}