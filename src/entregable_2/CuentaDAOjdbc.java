package entregable_2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaDAOjdbc implements CuentaDAO{
	
	@Override
	
	public boolean guardarCuenta(Cuenta cuenta) {
		String sql = "INSERT INTO cuentas (id, saldo) VALUES (?, ?)";
		try (Connection con = MyConnection.getCon();
				PreparedStatement pst = con.prepareStatement(sql)){
					pst.setString(1, getId());
					pst.setDouble(2, getSaldo());
					pst.executeUpdate();
					return true;
				} catch (SQLException e) {
					System.out.println("Error de SQL: " + e.getMessage());
					return false;
				}
	}
	
	@Override
	public boolean existeMoneda(String codigoMoneda) {
		String sql = "SELECT COUNT(*) FROM monedas WHERE codigo ?";
		try (Connection con = MyConnection.getCon();
				PreparedStatement pst = con.prepareStatement(sql)){
			pst.setString(1, codigoMoneda);
			ResultSet rs = pst.executeQuery();
			rs.next();
			return rs.getInt(1) > 0;
		} catch (SQLException e) {
			System.out.println ("Error de SQL: " + e.getMessage());
			return false;
		}
	}

}
