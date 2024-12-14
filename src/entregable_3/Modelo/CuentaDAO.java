package entregable_3.Modelo;

import entregable_3.ConexionBD;
import entregable_3.Cuenta;
import entregable_3.CuentaFiat;
import entregable_3.CuentaCripto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public interface CuentaDAO {
	
	// Guardar una cuenta en la BD
		public boolean guardarCuenta(Cuenta cuenta);
		 
		// Métodos para listar activos de un usuario
		public List<CuentaCripto> listarCuentasCripto(int idUsuario);
		public List<CuentaFiat> listarCuentasFiat(int idUsuario);
		
		// Métodos para obtener saldo de una moneda para un usuario
	    public double obtenerCantidadCripto(String nomCripto, int idUsuario);
	    public double obtenerCantidadFiat(String nomFiat, int idUsuario);
	    
	    // Métodos para actualizar cuentas
	    public boolean actualizarCuentasFiat(List<CuentaFiat> fiats);
	    public boolean actualizarCuentasCripto(List<CuentaCripto> criptos);
	    
	    // Getters de una cuenta en especifico segun la nomenclatura y un id
		public CuentaFiat getCuentaFiat(String nomenclatura, int idUsuario);
		public CuentaCripto getCuentaCripto(String nomenclatura, int idUsuario);

	boolean existeMoneda (String idMoneda);
	void registrarTransaccion(String descripcion); // Método para registrar una transacción
	
}