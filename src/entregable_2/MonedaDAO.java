package entregable_2;
import java.sql.*;
import java.util.List;

public interface MonedaDAO {
	boolean guardarMoneda(Moneda moneda) throws SQLException;
	List<Moneda> listarMonedas(Connection con);
	boolean stockRandomizer(Connection con);
}
