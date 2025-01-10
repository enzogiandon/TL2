package entregable_1;

import java.time.*;

/**
 * Clase que representa una transacción de intercambio (swap) de criptomonedas.
 */
public class Swap extends Transaccion {
    private Cripto recibida;
    private Cripto enviada;

    /**
     * Constructor de la clase Swap.
     *
     * @param usuario       El usuario que realiza la transacción.
     * @param monto        El monto de la transacción.
     * @param tipo         El tipo de transacción.
     * @param fecha        La fecha en que se realiza la transacción.
     * @param comisiones    Las comisiones asociadas a la transacción.
     * @param tiempoElegido El tiempo en que se desea realizar la transacción.
     * @param confirmacion  Indica si la transacción ha sido confirmada.
     * @param recibida     La criptomoneda que se recibe en el intercambio.
     * @param enviada      La criptomoneda que se envía en el intercambio.
     */
    public Swap(Usuario usuario, double monto, String tipo, LocalDate fecha, double comisiones, LocalTime tiempoElegido, 
                boolean confirmacion, Cripto recibida, Cripto enviada) {
        super(usuario, monto, tipo, fecha, comisiones, tiempoElegido, confirmacion);
        this.recibida = recibida;
        this.enviada = enviada;
    }

    // Getters y Setters

    /**
     * Obtiene la criptomoneda recibida en el intercambio.
     *
     * @return La criptomoneda recibida.
     */
    public Cripto getRecibida() {
        return recibida;
    }

    /**
     * Establece la criptomoneda recibida en el intercambio.
     *
     * @param recibida La criptomoneda recibida a establecer.
     */
    public void setRecibida(Cripto recibida) {
        this.recibida = recibida;
    }

    /**
     * Obtiene la criptomoneda enviada en el intercambio.
     *
     * @return La criptomoneda enviada.
     */
    public Cripto getEnviada() {
        return enviada;
    }

    /**
     * Establece la criptomoneda enviada en el intercambio.
     *
     * @param enviada La criptomoneda enviada a establecer.
     */
    public void setEnviada(Cripto enviada) {
        this.enviada = enviada;
    }

    /**
     * Método para realizar el intercambio de criptomonedas.
     *
     * @param enviada La criptomoneda que se va a enviar.
     * @param id      El identificador de la criptomoneda.
     * @return La criptomoneda recibida, o null si el intercambio falla.
     */
    public Cripto swap(Cripto enviada, String id) {
        /*
        if (API.consultarStock(id)) {
            recibida = API.operar(enviada);
            // usuario.administrarCuentas(enviada);
            this.confirmar();
            return recibida;
        }
        */

        System.out.println("No se pudo realizar el swap");
        return null;
    }

    /**
     * Implementación del método confirmar.
     * Establece la confirmación de la transacción.
     */
    @Override
    public void confirmar() {
        this.setConfirmacion(true);
    }

    /**
     * Devuelve una representación en forma de cadena de la transacción de swap.
     *
     * @return Cadena que representa el intercambio.
     */
    @Override
    public String toString() {
        return super.toString() + "Swap [recibida=" + recibida + ", enviada=" + enviada + "]";
    }
}
