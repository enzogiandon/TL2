package entregable_1;

import java.time.*;

public abstract class Transaccion {
	private Usuario usuario;
	private double monto;
	private String tipo;
	private LocalDate fecha;
	private double comisiones;
	private LocalTime tiempoElegido;
	private boolean confirmacion;

	// Constructor
	public Transaccion(Usuario usuario, double monto, String tipo, LocalDate fecha, double comisiones, LocalTime tiempoElegido,
			boolean confirmacion) {
		this.usuario = usuario;
		this.monto = monto;
		this.tipo = tipo;
		this.fecha = fecha;
		this.comisiones = comisiones;
		this.tiempoElegido = tiempoElegido;
		this.confirmacion = confirmacion;
	}

	// Getters y Setters
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getComisiones() {
		return comisiones;
	}

	public void setComisiones(double comisiones) {
		this.comisiones = comisiones;
	}

	public LocalTime getTiempoElegido() {
		return tiempoElegido;
	}

	public void setTiempoElegido(LocalTime tiempoElegido) {
		this.tiempoElegido = tiempoElegido;
	}

	public boolean getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(boolean confirmacion) {
		this.confirmacion = confirmacion;
	}

	// Método abstracto confirmar()
	public abstract void confirmar();

	// Método para notificar
	public void notificar() {
		// usuario.notificar(this);
	}

	@Override
	public String toString() {
		return "Transaccion [Usuario= " + usuario + "monto=" + monto + ", tipo=" + tipo + ", fecha=" + fecha + ", comisiones=" + comisiones
				+ ", tiempoElegido=" + tiempoElegido + ", confirmacion=" + confirmacion + "]";
	}

}
