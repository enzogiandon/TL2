package entregable_2;
import java.util.List;
public interface CuentaDAO {
	boolean guardarCuenta(Cuenta cuenta);
	boolean existeMoneda (String idMoneda);
	List<Cuenta> listarCuentas(); //Método para listar activos
	void registrarTransaccion(String descripcion); // Método para registrar una transacción
	// Nuevo método para actualizar cuentas
    boolean actualizarCuentas(List<Cuenta> cuentas);
    //Nuevo método para saber cuantas monedas tengo en mi cuenta
    double obtenerCantidadMoneda(String idCuenta, String idMoneda);

}
