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

	@Override
	public List<CuentaCripto> listarCuentasCripto(int idUsuario) {
		// @formatter:off
		String sql = "SELECT " +
		             "ACTIVO.ID AS ID, " +
		             "ACTIVO.ID_USUARIO AS ID_USUARIO, " +
		             "ACTIVO.CANTIDAD AS CANTIDAD, " +
		             "MONEDA.ID AS ID_MONEDA, " +
		             "MONEDA.NOMBRE AS NOMBRE, " +
		             "MONEDA.NOMENCLATURA AS NOMENCLATURA, " +
		             "MONEDA.VALOR_DOLAR AS VALOR_DOLAR, " +
		             "MONEDA.STOCK AS STOCK, " +
		             "MONEDA.VOLATILIDAD AS VOLATILIDAD " +
		             "FROM ACTIVO_CRIPTO AS ACTIVO " +
		             "JOIN MONEDA ON ACTIVO.ID_MONEDA = MONEDA.ID " +
		             "WHERE ACTIVO.ID_USUARIO = ?";
			// @formatter:on
		List<CuentaCripto> lista = new ArrayList<>();
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setInt(1, idUsuario);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {

					// @formatter:off
					Cripto cripto = new Cripto(
						rs.getInt("ID_MONEDA"),           
	                    rs.getString("NOMENCLATURA"),     
	                    rs.getDouble("VALOR_DOLAR"),      
	                    rs.getString("NOMBRE"),           
	                    rs.getDouble("STOCK"),            
	                    rs.getDouble("VOLATILIDAD")       
	                );

	                CuentaCripto cuentaCripto = new CuentaCripto(
	                    rs.getInt("ID"),                 
	                    rs.getInt("ID_USUARIO"),         
	                    rs.getDouble("CANTIDAD"),        
	                    cripto                           
	                );
		            // @formatter:on

					lista.add(cuentaCripto);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}

		return lista;
	}

	@Override
	public List<CuentaFiat> listarCuentasFiat(int idUsuario) {
		// @formatter:off
		String sql = "SELECT " +
		             "ACTIVO.ID AS ID, " +
		             "ACTIVO.ID_USUARIO AS ID_USUARIO, " +
		             "ACTIVO.CANTIDAD AS CANTIDAD, " +
		             "MONEDA.ID AS ID_MONEDA, " +
		             "MONEDA.NOMBRE AS NOMBRE, " +
		             "MONEDA.NOMENCLATURA AS NOMENCLATURA, " +
		             "MONEDA.VALOR_DOLAR AS VALOR_DOLAR, " +
		             "MONEDA.STOCK AS STOCK, " +
		             "MONEDA.VOLATILIDAD AS VOLATILIDAD " +
		             "FROM ACTIVO_FIAT AS ACTIVO " +
		             "JOIN MONEDA ON ACTIVO.ID_MONEDA = MONEDA.ID " +
		             "WHERE ACTIVO.ID_USUARIO = ?";
			// @formatter:on
		List<CuentaFiat> lista = new ArrayList<>();
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setInt(1, idUsuario);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {

					// @formatter:off
					Fiat fiat = new Fiat(
						rs.getInt("ID_MONEDA"),           
	                    rs.getString("NOMENCLATURA"),     
	                    rs.getDouble("VALOR_DOLAR"),      
	                    rs.getString("NOMBRE"),           
	                    rs.getDouble("STOCK"),            
	                    rs.getDouble("VOLATILIDAD")       
	                );

	                CuentaFiat cuentaFiat = new CuentaFiat(
	                    rs.getInt("ID"),                 
	                    rs.getInt("ID_USUARIO"),         
	                    rs.getDouble("CANTIDAD"),        
	                    fiat                           
	                );
		            // @formatter:on

					lista.add(cuentaFiat);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}

		return lista;
	}

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
		// @formatter:off
	    String sql = "SELECT " +
	                 "ACTIVO_CRIPTO.ID AS ID, " +
	                 "ACTIVO_CRIPTO.ID_USUARIO AS ID_USUARIO, " +
	                 "ACTIVO_CRIPTO.CANTIDAD AS CANTIDAD, " +
	                 "MONEDA.ID AS ID_MONEDA, " +
	                 "MONEDA.NOMENCLATURA AS NOMENCLATURA, " +
	                 "MONEDA.NOMBRE AS NOMBRE, " +
	                 "MONEDA.VALOR_DOLAR AS VALOR_DOLAR, " +
	                 "MONEDA.STOCK AS STOCK, " +
	                 "MONEDA.VOLATILIDAD AS VOLATILIDAD " +
	                 "FROM ACTIVO_CRIPTO " +
	                 "JOIN MONEDA ON ACTIVO_FIAT.ID_MONEDA = MONEDA.ID " +
	                 "WHERE MONEDA.NOMENCLATURA = ? AND ACTIVO_CRIPTO.ID_USUARIO = ?";
	    // @formatter:on

		CuentaFiat cf = null;

		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, nomenclatura.toUpperCase());
			pst.setInt(2, idUsuario);

			try (ResultSet rs = pst.executeQuery()) {
				// @formatter:off
	            if (rs.next()) {
	                Fiat fiat = new Fiat(
	                    rs.getInt("ID_MONEDA"),
	                    rs.getString("NOMENCLATURA"),
	                    rs.getDouble("VALOR_DOLAR"),
	                    rs.getString("NOMBRE"),
	                    rs.getDouble("STOCK"),
	                    rs.getDouble("VOLATILIDAD")
	                );

	                cf = new CuentaFiat(
	                    rs.getInt("ID"), 
	                    rs.getInt("ID_USUARIO"), 
	                    rs.getDouble("CANTIDAD"), 
	                    fiat
	                );
	            }
	            // @formatter:on
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}

		return cf;
	}

	@Override
	public CuentaCripto getCuentaCripto(String nomenclatura, int idUsuario) {
		// @formatter:off
	    String sql = "SELECT " +
	                 "ACTIVO_CRIPTO.ID AS ID, " +
	                 "ACTIVO_CRIPTO.ID_USUARIO AS ID_USUARIO, " +
	                 "ACTIVO_CRIPTO.CANTIDAD AS CANTIDAD, " +
	                 "MONEDA.ID AS ID_MONEDA, " +
	                 "MONEDA.NOMENCLATURA AS NOMENCLATURA, " +
	                 "MONEDA.NOMBRE AS NOMBRE, " +
	                 "MONEDA.VALOR_DOLAR AS VALOR_DOLAR, " +
	                 "MONEDA.STOCK AS STOCK, " +
	                 "MONEDA.VOLATILIDAD AS VOLATILIDAD " +
	                 "FROM ACTIVO_CRIPTO " +
	                 "JOIN MONEDA ON ACTIVO_CRIPTO.ID_MONEDA = MONEDA.ID " +
	                 "WHERE MONEDA.NOMENCLATURA = ? AND ACTIVO_CRIPTO.ID_USUARIO = ?";
	    // @formatter:on

		CuentaCripto cc = null;

		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, nomenclatura.toUpperCase());
			pst.setInt(2, idUsuario);

			try (ResultSet rs = pst.executeQuery()) {
				// @formatter:off
	            if (rs.next()) {
	                Cripto cripto = new Cripto(
	                    rs.getInt("ID_MONEDA"),
	                    rs.getString("NOMENCLATURA"),
	                    rs.getDouble("VALOR_DOLAR"),
	                    rs.getString("NOMBRE"),
	                    rs.getDouble("STOCK"),
	                    rs.getDouble("VOLATILIDAD")
	                );

	                cc = new CuentaCripto(
	                    rs.getInt("ID"), 
	                    rs.getInt("ID_USUARIO"), 
	                    rs.getDouble("CANTIDAD"), 
	                    cripto
	                );
	            }
	            // @formatter:on
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}

		return cc;
	}

}