package entregable_2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAOjdbc implements CuentaDAO {

    @Override
    public boolean guardarCuenta(Cuenta cuenta) {
        String sql = "INSERT INTO cuentas (id, saldo) VALUES (?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, cuenta.getId());
            pst.setDouble(2, cuenta.getSaldo());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean existeMoneda(String codigoMoneda) {
        String sql = "SELECT COUNT(*) FROM monedas WHERE codigo = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, codigoMoneda);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Cuenta> listarCuentas() {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT id, saldo FROM cuentas";
        
        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String id = rs.getString("id");
                double saldo = rs.getDouble("saldo");
                Cuenta cuenta = new Cuenta(id, saldo);
                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
			e.printStackTrace();
            return null;
        }
        return cuentas;
    }
    
    
    @Override
    
    public void registrarTransaccion(String descripcion) {
        String sql = "INSERT INTO transacciones (descripcion) VALUES (?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, descripcion);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }
    
   
    
    @Override
    public boolean actualizarCuentasCripto(List<CuentaCripto> criptos) {
        String sql = "UPDATE TABLA_CRIPTO SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
        try (Connection con = ConexionBD.getConnection()) {
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                con.setAutoCommit(false); // Para asegurar transacción atómica PREGUNTAR AL CHAT
                for (CuentaCripto cripto : criptos) {
                    pst.setDouble(1, cripto.getSaldo());
                    pst.setString(2, cripto.getId());
                    pst.addBatch();
                }
                pst.executeBatch();
                con.commit();
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
        String sql = "UPDATE TABLA_FIAT SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
        try (Connection con = ConexionBD.getConnection()) {
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                con.setAutoCommit(false); // Para asegurar transacción atómica PREGUNTAR AL CHAT
                for (CuentaFiat fiat : fiats) {
                    pst.setDouble(1, fiat.getSaldo());
                    pst.setString(2, fiat.getId());
                    pst.addBatch();
                }
                pst.executeBatch();
                con.commit();
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
    
    //dos métodos, uno para cripto y otro para fiat, vamos a tener que cambiar 
    //despues el CuentaDAO, y chequear que lo haga Compra en comprar()
    
    @Override
    public double obtenerCantidadCripto(String idCripto) {
    	//debería fijarse para cripto
        String sql = "SELECT CANTIDAD FROM TABLA_CRIPTO WHERE NOMENCLATURA = ? ";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, idCripto);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getDouble("CANTIDAD");
            }
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        return 0; // Retorna 0 si no se encuentra la cuenta o hay un error
    }
    
    @Override
    public double obtenerCantidadFiat(String idFiat) {
    	//debería fijarse para cripto
        String sql = "SELECT CANTIDAD FROM cuentas WHERE id = ? AND id_moneda = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, idFiat);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getDouble("CANTIDAD");
            }
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        return 0; // Retorna 0 si no se encuentra la cuenta o hay un error
    }

	@Override
	public CuentaFiat getCuentaFiat(String nomenclatura) throws SQLException {	
		String sql = "SELECT * FROM TABLA_FIAT WHERE NOMENCLATURA = ?";
		CuentaFiat cf = null;

		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, nomenclatura);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				cf = new CuentaFiat(rs.getString("NOMENCLATURA"),rs.getDouble("CANTIDAD"));
			}
		}
		return cf;
	}
	
	@Override
	public CuentaCripto getCuentaCripto(String nomenclatura) throws SQLException {	
		String sql = "SELECT * FROM TABLA_FIAT WHERE NOMENCLATURA = ?";
		CuentaCripto cc = null;

		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, nomenclatura);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				cc = new CuentaCripto(rs.getString("NOMENCLATURA"),rs.getDouble("CANTIDAD"),"1234");
			}
		}
		return cc;
	}
	
    
}