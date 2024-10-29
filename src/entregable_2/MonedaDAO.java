package entregable_2;
import java.sql.*;
import java.util.List;

public interface MonedaDAO {
	boolean guardarMoneda(Moneda moneda) throws SQLException;
	public boolean crearMoneda(Connection con) throws SQLException;
	public List<Moneda> listarMonedas(Connection con);
}
