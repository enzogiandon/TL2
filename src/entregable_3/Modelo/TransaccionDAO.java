package entregable_3.Modelo;

import java.util.List;
import entregable_3.TransaccionBD;

public interface TransaccionDAO {
	/**
	 * 
	 * @param descripcion Descripcion de la transaccion
	 * @return	si se actualizó correctamente
	 */
	boolean guardarTransaccion(int id,String descripcion, int idUsuario);
	public List<TransaccionBD> getTransacciones();
}
