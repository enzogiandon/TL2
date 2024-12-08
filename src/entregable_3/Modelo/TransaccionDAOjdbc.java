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
	public boolean guardarTransaccion(String descripcion, int idUsuario) {
		String sql = "INSERT INTO TRANSACCION (RESUMEN, FECHA_HORA,ID_USUARIO) VALUES (?, CURRENT_TIMESTAMP, ?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, descripcion);
			pst.setInt(2, idUsuario);
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
	public String getDescripcionPorId(int id){
		String sql = "SELECT RESUMEN FROM TRANSACCION WHERE ID = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next())
					return rs.getString("RESUMEN");
				else
					return ("Transaccion no encontrada");
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL: ");
			e.printStackTrace();
			return "Error de acceso.";
		}
	}

	@Override
	public List<Transaccion> getTransacciones() {
		String sql = "SELECT ID, RESUMEN, FECHA_HORA FROM TRANSACCION";
		List<Transaccion> transacciones = new ArrayList<>();

		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("ID");
				String resumen = rs.getString("RESUMEN");
				Timestamp fechaHora = rs.getTimestamp("FECHA_HORA");

				transacciones.add(new Transaccion(id, resumen, fechaHora.toLocalDateTime().toLocalDate()));
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL: ");
			e.printStackTrace();
		}

		return transacciones;
	}
}
