package entregable_3;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaccion {
	private int id;
	private int idUsuario;
	private double monto;
	private LocalDate fecha;
	private double comisiones;
	private LocalTime hora;
	private boolean confirmacion;
	private String resumen;

	public Transaccion(int id, int idUsuario,double monto) {
		this.id = (int) (Math.random() * 1000 + 1);
		this.idUsuario = idUsuario;
		this.monto = monto;
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();
		this.confirmacion = false; // Pendiente de confirmación
	}
	
	
	
	
	public Transaccion() {
	}

	// Método para confirmar la transacción
	public void confirmar() {
		confirmacion = true;
	}

	public int getId() {
		return id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		resumen = "Monto adquirido: " + monto + ", Fecha: " + fecha + ", Hora: " +  hora + ", Confirmación: " + confirmacion;
		return resumen;
	}
}