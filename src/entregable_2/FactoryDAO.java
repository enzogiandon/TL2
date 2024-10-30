package entregable_2;

public class FactoryDAO {
	public static MonedaDAO getMonedaDAO() {
		return new MonedaDAOjdbc();
	}
}