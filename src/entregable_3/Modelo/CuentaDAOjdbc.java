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
					+ "ON CONFLICT(ID) DO UPDATE SET CANTIDAD = excluded.CANTIDAD";
		else if (cuenta instanceof CuentaFiat)
			sql = "INSERT INTO ACTIVO_FIAT (ID, ID_USUARIO, ID_MONEDA, CANTIDAD) VALUES (?,?, ?, ?) "
					+ "ON CONFLICT(ID) DO UPDATE SET CANTIDAD = excluded.CANTIDAD";
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

	// @formatter:off
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
		return listarCuentas(sql,idUsuario);
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
	// @formatter:on

	private boolean actualizarCuentas(String sql, List<Cuenta> cuentas) {

		try (Connection con = ConexionBD.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(sql)) {
				con.setAutoCommit(false);
				for (Cuenta cuenta : cuentas) {
					pst.setDouble(1, cuenta.getSaldo());
					pst.setInt(2, cuenta.getId());
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

	@Override
	public boolean actualizarCuentasCripto(List<CuentaCripto> criptos) {
		List<Cuenta> cuentas = new ArrayList<>();
		cuentas.addAll(criptos);

		String sql = "UPDATE ACTIVO_FIAT SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
		return actualizarCuentas(sql, cuentas);
	}

	@Override
	public boolean actualizarCuentasFiat(List<CuentaFiat> fiats) {
		List<Cuenta> cuentas = new ArrayList<>();
		cuentas.addAll(fiats);

		String sql = "UPDATE ACTIVO_FIAT SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
		return actualizarCuentas(sql, cuentas);
	}

	private double obtenerCantidadMoneda(String sql, String nomMoneda, int idUsuario) {
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, nomMoneda.toUpperCase());
			pst.setInt(2, idUsuario);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				double valor = rs.getDouble("CANTIDAD");
				con.close();
				return valor;
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}
		return 0;
	}

	@Override
	public double obtenerCantidadCripto(String nomCripto, int idUsuario) {
		String sql = "SELECT CANTIDAD FROM ACTIVO_CRIPTO WHERE NOMENCLATURA = ? AND ID_USUARIO = ?";
		return obtenerCantidadMoneda(sql, nomCripto, idUsuario);
	}

	@Override
	public double obtenerCantidadFiat(String nomFiat, int idUsuario) {
		String sql = "SELECT CANTIDAD FROM ACTIVO_FIAT WHERE NOMENCLATURA = ? AND ID_USUARIO = ?";
		return obtenerCantidadMoneda(sql, nomFiat, idUsuario);
	}

	@Override
	public CuentaFiat getCuentaFiat(String nomenclatura, int idUsuario) {
		String sql = "SELECT * FROM ACTIVO_FIAT WHERE NOMENCLATURA = ? AND ID_USUARIO = ?";
		CuentaFiat cf = null;

		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, nomenclatura.toUpperCase());
			pst.setInt(2, idUsuario);

			ResultSet rs = pst.executeQuery();
			MonedaDAO mDAO = FactoryDAO.getMonedaDAO();

			// @formatter:off
			if (rs.next()) {
				Fiat fiat = (Fiat) mDAO.getMoneda(nomenclatura);
				cf = new CuentaFiat(
						rs.getInt("ID"),
						rs.getInt("ID_USUARIO"), 
						rs.getDouble("CANTIDAD"),
						fiat);
			}

			// @formatter:on
			pst.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}

		return cf;
	}

	@Override
	public CuentaCripto getCuentaCripto(String nomenclatura, int idUsuario) {
		String sql = "SELECT * FROM ACTIVO_FIAT WHERE NOMENCLATURA = ? AND ID_USUARIO = ?";
		CuentaCripto cc = null;

		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, nomenclatura.toUpperCase());
			pst.setInt(2, idUsuario);

			ResultSet rs = pst.executeQuery();
			MonedaDAO mDAO = FactoryDAO.getMonedaDAO();

			// @formatter:off
			if (rs.next()) {
				Cripto cripto = (Cripto) mDAO.getMoneda(nomenclatura);
				cc = new CuentaCripto(
						rs.getInt("ID"),
						rs.getInt("ID_USUARIO"), 
						rs.getDouble("CANTIDAD"),
						cripto);
			}

			// @formatter:on

			pst.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}
		return cc;
	}

}