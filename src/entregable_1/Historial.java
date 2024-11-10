package entregable_1;

import java.util.List;
import java.util.LinkedList;

/**
 * Clase que representa un historial de transacciones.
 */
public class Historial {
    private List<Transaccion> transacciones;

    /**
     * Constructor por defecto de la clase Historial.
     * Inicializa una lista vacía de transacciones.
     */
    public Historial() {
        this.transacciones = new LinkedList<Transaccion>();
    }

    /**
     * Constructor de la clase Historial que añade una transacción inicial.
     *
     * @param t La transacción que se añadirá al historial.
     */
    public Historial(Transaccion t) {
        this.transacciones = new LinkedList<Transaccion>();
        transacciones.add(t);
    }

    /**
     * Método para añadir una transacción al historial.
     *
     * @param transaccion La transacción que se va a añadir.
     */
    public void añadir(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    /**
     * Método para informar sobre todas las transacciones en el historial.
     * Imprime en la consola la representación de cada transacción.
     */
    public void informarTran() {
        for (Transaccion transaccion : transacciones) {
            System.out.println(transaccion.toString());
        }
    }
}

