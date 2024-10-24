package entregable_1;

import java.util.Scanner;

import java.time.*;

public class Envios extends Transaccion {
	static final Scanner In = new Scanner(System.in);
	private String emisorID;
	private String receptorID;

	// Constructor
	public Envios(Usuario usuario, double monto, String tipo, LocalDate fecha, double comisiones, LocalTime tiempoElegido,
			boolean confirmacion, String emisorID, String receptorID) {
		super(usuario, monto, tipo, fecha, comisiones, tiempoElegido, confirmacion);
		this.emisorID = emisorID;
		this.receptorID = receptorID;
	}

	// Getters y Setters
	public String getEmisorID() {
		return emisorID;
	}

	public void setEmisorID(String emisorID) {
		this.emisorID = emisorID;
	}

	public String getReceptorID() {
		return receptorID;
	}

	public void setReceptorID(String receptorID) {
		this.receptorID = receptorID;
	}


	// Método para enviar criptomonedas
	public void enviar(Cripto c, String dir) {
		int opcion = -1;
		// System.out.println("Las opciones son: " +
		// Blockchain.calcularOpciones(cripto)+);
		System.out.println("Elija un número de opción");
		In.nextInt(opcion);

		// double montoElegido = Blockchain.elegir(opcion,c,dir)
		// usuario.administrarCuentas(montoElegido);

		this.confirmar();
	}

	// Método para recibir criptomonedas
	public void recibir(Cripto c) {

		// usuario.recibir(c); internamente administra la cuenta y suma el monto
		this.confirmar();

	}

	// Método para confirmar el envío o recepción
	@Override
	public void confirmar() {
		this.setConfirmacion(true);
		super.notificar();
	}

	// Método para notificar
	public void notificar() {
		// this.getUsuario().notificar(this);
	}

	@Override
	public String toString() {
		return super.toString() + "Envios [emisorID=" + emisorID + ", receptorID=" + receptorID + "]";
	}
}
