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

	private List<Cuenta> listarCuentas(String sql, int idUsuario) {

		List<Cuenta> lista = new ArrayList<Cuenta>();
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				// @formatter:off
				Moneda moneda = new Moneda(
	                    rs.getInt("MONEDA.ID"),
	                    rs.getString("MONEDA.NOMENCLATURA"),
	                    rs.getDouble("MONEDA.VALOR_DOLAR"),
	                    rs.getString("MONEDA.NOMBRE"),
	                    rs.getDouble("MONEDA.STOCK"),
	                    rs.getDouble("MONEDA.VOLATILIDAD")
				);
				Cuenta cuenta = new Cuenta(
						rs.getInt("ACTIVO.ID"),
						rs.getInt("ACTIVO.ID_USUARIO"),
						rs.getDouble("ACTIVO.CANTIDAD"),
						moneda
						);
				// @formatter:on
				lista.add(cuenta);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}

		return lista;
	}

	@Override
	public List<Cuenta> listarCuentasCripto(int idUsuario) {
		String sql = "SELECT ACTIVO.ID_ACTIVO,"+
                "ACTIVO.ID_USUARIO, " +
                "ACTIVO.ID_MONEDA, " +
                "ACTIVO.CANTIDAD, " +
                "MONEDA.NOMBRE, " +
                "MONEDA.NOMENCLATURA, " +
                "MONEDA.VALOR_DOLAR " +
                "FROM ACTIVO_CRIPTO AS ACTIVO " +
                "JOIN MONEDA ON ACTIVO.ID_MONEDA = MONEDA.ID" + 
                "WHERE ACTIVO.ID_USUARIO = ?";
		return listarCuentas(idUsuario);
	}

	@Override
	public List<Cuenta> listarCuentasFiat(int idUsuario) {
		String sql = "SELECT ACTIVO.ID_ACTIVO,"+
                "ACTIVO.ID_USUARIO, " +
                "ACTIVO.ID_MONEDA, " +
                "ACTIVO.CANTIDAD, " +
                "MONEDA.NOMBRE, " +
                "MONEDA.NOMENCLATURA, " +
                "MONEDA.VALOR_DOLAR " +
                "FROM ACTIVO_FIAT AS ACTIVO " +
                "JOIN MONEDA ON ACTIVO.ID_MONEDA = MONEDA.ID" + 
                "WHERE ACTIVO.ID_USUARIO = ?";
		return listarCuentas(sql,idUsuario);
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