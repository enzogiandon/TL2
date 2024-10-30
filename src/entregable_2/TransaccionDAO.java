package entregable_2;

import java.sql.Connection;

public interface TransaccionDAO {
	boolean guardarTransaccion(Connection con,String descripcion);
}
