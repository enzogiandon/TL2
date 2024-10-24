package entregable_1;

import java.time.*;

public class Swap extends Transaccion {
	private Cripto recibida;
	private Cripto enviada;

	// Constructor
	public Swap(Usuario usuario,double monto, String tipo, LocalDate fecha, double comisiones, LocalTime tiempoElegido, boolean confirmacion,
			Cripto recibida, Cripto enviada) {
		super(usuario,monto, tipo, fecha, comisiones, tiempoElegido, confirmacion);
		this.recibida = recibida;
		this.enviada = enviada;
	}

	// Getters y Setters
	public Cripto getRecibida() {
		return recibida;
	}

	public void setRecibida(Cripto recibida) {
		this.recibida = recibida;
	}

	public Cripto getEnviada() {
		return enviada;
	}

	public void setEnviada(Cripto enviada) {
		this.enviada = enviada;
	}

	// Método para swap
	public Cripto swap(Cripto enviada, String id) {
	/*	if (API.consultarStock(id)) {
			recibida = API.operar(enviada);
			// usuario.administrarCuentas(enviada);
			this.confirmar();
			return recibida;
		}*/

		System.out.println("No se pudo realizar el swap");
		return null;
	}

	// Implementación de confirmar()
	@Override
	public void confirmar() {
		this.setConfirmacion(true);
	}

	@Override
	public String toString() {
		return super.toString() + "Swap [recibida=" + recibida + ", enviada=" + enviada +  "]";
	}

}
