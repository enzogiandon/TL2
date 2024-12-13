package entregable_3.Modelo;
import entregable_3.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAOjdbc implements CuentaDAO {

	@Override
	public boolean guardarCuenta(Cuenta cuenta) {
		String sql;
		if (cuenta instanceof CuentaCripto)
			// Usamos UPDATE con ON CONFLICT
			sql = "INSERT INTO ACTIVO_CRIPTO (ID, ID_USUARIO, ID_MONEDA, CANTIDAD) VALUES (?,?, ?, ?) "
					+ "ON CONFLICT(ID_MONEDA) DO UPDATE SET CANTIDAD = excluded.CANTIDAD";
		else if (cuenta instanceof CuentaFiat)
			sql = "INSERT INTO ACTIVO_FIAT (ID, ID_USUARIO, ID_MONEDA, CANTIDAD) VALUES (?,?, ?, ?) "
					+ "ON CONFLICT(ID_MONEDA) DO UPDATE SET CANTIDAD = excluded.CANTIDAD";
		else {
			System.out.println("No se pudo guardar la cuenta.");
			return false;
		}
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, cuenta.getId());
			pst.setInt(2, cuenta.getIdUsuario());
			pst.setInt(3, cuenta.getMoneda().getId());
			pst.setDouble(4, cuenta.getSaldo());
			pst.executeUpdate();
			System.out.println("Cuenta guardada exitosamente");
			con.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
			return false;
		}
	}


	@Override
	public List<Cuenta> listarCuentasCripto() {
		List<Cuenta> cuentas = new ArrayList<>();
		String sql = "SELECT CANTIDAD FROM ACTIVO_CRIPTO";
		try (Connection con = ConexionBD.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				String id = rs.getString("NOMENCLATURA");
				double CANTIDAD = rs.getDouble("CANTIDAD");
				Cuenta cuenta = new Cuenta(id, CANTIDAD);
				cuentas.add(cuenta);
			}
			con.close();

		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return cuentas;
	}

	@Override
	public List<Cuenta> listarCuentasFiat() {
		List<Cuenta> cuentas = new ArrayList<>();
		String sql = "SELECT CANTIDAD FROM ACTIVO_FIAT";
		try (Connection con = ConexionBD.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				String id = rs.getString("NOMENCLATURA");
				double CANTIDAD = rs.getDouble("CANTIDAD");
				Cuenta cuenta = new Cuenta(id, CANTIDAD);
				cuentas.add(cuenta);
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return cuentas;
	}

	@Override

	public void registrarTransaccion(String descripcion) {
		String sql = "INSERT INTO TRANSACCION (descripcion) VALUES (?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, descripcion);
			pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}
	}

	@Override
	public boolean actualizarCuentasCripto(List<CuentaCripto> criptos) {
		String sql = "UPDATE ACTIVO_CRIPTO SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
		try (Connection con = ConexionBD.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(sql)) {
				con.setAutoCommit(false); // Para asegurar transacción atómica PREGUNTAR AL CHAT
				for (CuentaCripto cripto : criptos) {
					pst.setDouble(1, cripto.getSaldo());
					pst.setString(2, cripto.getId().toUpperCase());
					pst.addBatch();
				}
				pst.executeBatch();
				con.commit();
				con.close();
				return true;
			} catch (SQLException e) {
				con.rollback(); // Revertir cambios en caso de error
				System.out.println("Error de SQL en actualizarCuentas: " + e.getMessage());
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Error de conexión: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean actualizarCuentasFiat(List<CuentaFiat> fiats) {
		String sql = "UPDATE ACTIVO_FIAT SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
		try (Connection con = ConexionBD.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(sql)) {
				con.setAutoCommit(false); // Para asegurar transacción atómica PREGUNTAR AL CHAT
				for (CuentaFiat fiat : fiats) {
					pst.setDouble(1, fiat.getSaldo());
					pst.setString(2, fiat.getId().toUpperCase());
					pst.addBatch();
				}
				pst.executeBatch();
				con.commit();
				return true;
			} catch (SQLException e) {
				con.rollback(); // Revertir cambios en caso de error
				System.out.println("Error de SQL en actualizarCuentas: " + e.getMessage());
				con.close();
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Error de conexión: " + e.getMessage());
			return false;
		}
	}

	// dos métodos, uno para cripto y otro para fiat, vamos a tener que cambiar
	// despues el CuentaDAO, y chequear que lo haga Compra en comprar()

	@Override
	public double obtenerCantidadCripto(String idCripto) {
		// debería fijarse para cripto
		String sql = "SELECT CANTIDAD FROM ACTIVO_CRIPTO WHERE NOMENCLATURA = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, idCripto.toUpperCase());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				double valor = rs.getDouble("CANTIDAD");
				con.close();
				return valor;
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}
		return 0; // Retorna 0 si no se encuentra la cuenta o hay un error
	}

	@Override
	public double obtenerCantidadFiat(String idFiat) {
		// debería fijarse para cripto
		String sql = "SELECT CANTIDAD FROM ACTIVO_FIAT WHERE NOMENCLATURA = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, idFiat.toUpperCase());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				double valor = rs.getDouble("CANTIDAD");
				con.close();
				return valor;
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}
		return 0; // Retorna 0 si no se encuentra la cuenta o hay un error
	}

	@Override
	public CuentaFiat getCuentaFiat(String nomenclatura) throws SQLException {
		String sql = "SELECT * FROM ACTIVO_FIAT WHERE NOMENCLATURA = ?";
		CuentaFiat cf = null;

		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, nomenclatura.toUpperCase());
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				MonedaDAO mDAO = FactoryDAO.getMonedaDAO();
				Fiat fiat = (Fiat) mDAO.getMoneda(nomenclatura);
				cf = new CuentaFiat(rs.getString("NOMENCLATURA"), rs.getDouble("CANTIDAD"), fiat);
			}
			con.close();
		}
		return cf;
	}

	@Override
	public CuentaCripto getCuentaCripto(String nomenclatura) throws SQLException {
		String sql = "SELECT * FROM ACTIVO_CRIPTO WHERE NOMENCLATURA = ?";
		CuentaCripto cc = null;

		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, nomenclatura.toUpperCase());
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				MonedaDAO mDAO = FactoryDAO.getMonedaDAO();
				Cripto cripto = (Cripto) mDAO.getMoneda(nomenclatura);
				cc = new CuentaCripto(rs.getString("NOMENCLATURA"), rs.getDouble("CANTIDAD"), "1234", cripto);
			}

			pst.close();
			con.close();
		}
		return cc;
	}

}