package entregable_3.Modelo;

import entregable_3.Moneda;
import java.sql.*;
import java.util.List;

public interface MonedaDAO {
	boolean guardarMoneda(Moneda moneda);
	List<Moneda> listarMonedas();
	int stockRandomizer(); // retorna la cantidad de filas actualizadas
	Moneda getMoneda(String nomenclatura);
}