package entregable_1;


import java.util.List;
import java.util.LinkedList;

public class Historial {
	private List<Transaccion> transacciones;

	// Constructores
	public Historial() {
		this.transacciones = new LinkedList<Transaccion>();
	}

	public Historial(Transaccion t) {
		this.transacciones = new LinkedList<Transaccion>();
		transacciones.add(t);
	}

	// Método para añadir una transacción
	public void añadir(Transaccion transaccion) {
		transacciones.add(transaccion);
	}

	// Método para informar de las transacciones
	public void informarTran() {
		for (Transaccion transaccion : transacciones) {
			System.out.println(transaccion.toString());
		}
	}
}
