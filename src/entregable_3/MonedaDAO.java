package entregable_3;
import java.sql.*;
import java.util.List;

public interface MonedaDAO {
	boolean guardarMoneda(Moneda moneda) throws SQLException;
	List<Moneda> listarMonedas();
	boolean stockRandomizer();
	Moneda getMoneda(String nomenclatura) throws SQLException;
}