package entregable_3;

public interface TransaccionDAO {
	/**
	 * 
	 * @param descripcion Descripcion de la transaccion
	 * @return	si se actualizó correctamente
	 */
	boolean guardarTransaccion(String descripcion);
}