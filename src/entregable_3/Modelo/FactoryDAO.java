package entregable_3.Modelo;

public class FactoryDAO {
	public static MonedaDAO getMonedaDAO() {
		return new MonedaDAOjdbc();
	}
	public static CuentaDAO getCuentaDAO() {
		return new CuentaDAOjdbc();
	}
	public static TransaccionDAO getTransaccionDAO() {
		return new TransaccionDAOjdbc();
	}
}