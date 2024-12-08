package entregable_3.Modelo;

import java.util.List;

import entregable_3.Transaccion;

public interface TransaccionDAO {
	/**
	 * 
	 * @param descripcion Descripcion de la transaccion
	 * @return	si se actualizó correctamente
	 */
	boolean guardarTransaccion(String descripcion);
	public List<Transaccion> getTransacciones();
	public String getDescripcionById(int id);
}
