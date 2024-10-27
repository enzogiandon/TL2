package entregable_2;

public interface CuentaDAO {
	boolean guardarCuenta(Cuenta cuenta);
	boolean existeMoneda (String idMoneda);
}
