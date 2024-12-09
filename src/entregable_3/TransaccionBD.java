package entregable_3;

import java.time.LocalDate;

// El objetivo de esta clase es solo guardar los elementos de la transacción provenientes de la BD.
public class TransaccionBD {
	private int id;
	private String resumen;
	private LocalDate fecha;
	private int idUsuario;
	
	public TransaccionBD() {}

	public TransaccionBD(int id, String resumen) {
		this.id = id;
		this.resumen = resumen;
	}

	public TransaccionBD(int id, String resumen, LocalDate fecha, int idUsuario) {
		this.id = id;
		this.resumen = resumen;
		this.fecha = fecha;
		this.idUsuario = idUsuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
	

	
}