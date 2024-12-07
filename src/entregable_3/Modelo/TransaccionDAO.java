package entregable_3.Modelo;

public interface TransaccionDAO {
	/**
	 * 
	 * @param descripcion Descripcion de la transaccion
	 * @return	si se actualizó correctamente
	 */
	boolean guardarTransaccion(String descripcion);
}