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
        }
        return cuentas;
    }
}