package entregable_2;
import java.sql.*;

public interface MonedaDAO {
	boolean guardarMoneda(Moneda moneda) throws SQLException;
	public boolean crearMoneda(Connection con) throws SQLException;
	boolean listarMonedas();
}
