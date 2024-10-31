package entregable_2;
import java.sql.Connection;
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
    public boolean actualizarCuentas(List<Cuenta> cuentas) {
        String sql = "UPDATE cuentas SET saldo = ? WHERE id = ?";
        try (Connection con = ConexionBD.getConnection()) {
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                con.setAutoCommit(false); // Para asegurar transacción atómica
                for (Cuenta cuenta : cuentas) {
                    pst.setDouble(1, cuenta.getSaldo());
                    pst.setString(2, cuenta.getId());
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
    public double obtenerCantidadMoneda(String idCuenta, String idMoneda) {
        String sql = "SELECT saldo FROM cuentas WHERE id = ? AND id_moneda = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, idCuenta);
            pst.setString(2, idMoneda);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getDouble("saldo");
            }
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        return 0; // Retorna 0 si no se encuentra la cuenta o hay un error
    }
    
}