package entregable_3.Modelo;

import entregable_3.Cuenta;
import entregable_3.CuentaFiat;
import entregable_3.CuentaCripto;

import java.sql.SQLException;
import java.util.List;
public interface CuentaDAO {
	/**
	 * 
	 * 	@param cuenta
	 * @return
	 */
	boolean guardarCuenta(Cuenta cuenta);
	boolean existeMoneda (String idMoneda);
	 //Métodos para listar activos
	List<Cuenta> listarCuentasCripto(int idUsuario);
	List<Cuenta> listarCuentasFiat(int idUsuario);
	void registrarTransaccion(String descripcion); // Método para registrar una transacción
	// Nuevo método para actualizar cuentas
    public double obtenerCantidadCripto(String idCripto);
    public double obtenerCantidadFiat(String idFiat);
    public boolean actualizarCuentasFiat(List<CuentaFiat> fiats);
    public boolean actualizarCuentasCripto(List<CuentaCripto> criptos);
	public CuentaFiat getCuentaFiat(String nomenclatura) throws SQLException;
	public CuentaCripto getCuentaCripto(String nomenclatura) throws SQLException;
}
