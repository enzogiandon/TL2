package entregable_3.Modelo;

import java.sql.SQLException;
import entregable_3.Usuario;

public interface LoginDAO {
	
	public Usuario iniciar(String email, String passwd) throws SQLException;

}
