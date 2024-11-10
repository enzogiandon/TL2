package entregable_1;

import java.util.Scanner;
import java.time.*;

/**
 * Clase que representa una transacción de envío de criptomonedas.
 */
public class Envios extends Transaccion {
    static final Scanner In = new Scanner(System.in);
    private String emisorID;
    private String receptorID;

    /**
     * Constructor de la clase Envios.
     *
     * @param usuario       El usuario que realiza la transacción.
     * @param monto        El monto de la transacción.
     * @param tipo         El tipo de transacción.
     * @param fecha        La fecha en que se realiza la transacción.
     * @param comisiones    Las comisiones asociadas a la transacción.
     * @param tiempoElegido El tiempo en que se desea realizar la transacción.
     * @param confirmacion  Indica si la transacción ha sido confirmada.
     * @param emisorID     El identificador del emisor de la criptomoneda.
     * @param receptorID    El identificador del receptor de la criptomoneda.
     */
    public Envios(Usuario usuario, double monto, String tipo, LocalDate fecha, double comisiones, LocalTime tiempoElegido,
                  boolean confirmacion, String emisorID, String receptorID) {
        super(usuario, monto, tipo, fecha, comisiones, tiempoElegido, confirmacion);
        this.emisorID = emisorID;
        this.receptorID = receptorID;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador del emisor.
     *
     * @return El emisorID.
     */
    public String getEmisorID() {
        return emisorID;
    }

    /**
     * Establece el identificador del emisor.
     *
     * @param emisorID El emisorID a establecer.
     */
    public void setEmisorID(String emisorID) {
        this.emisorID = emisorID;
    }

    /**
     * Obtiene el identificador del receptor.
     *
     * @return El receptorID.
     */
    public String getReceptorID() {
        return receptorID;
    }

    /**
     * Establece el identificador del receptor.
     *
     * @param receptorID El receptorID a establecer.
     */
    public void setReceptorID(String receptorID) {
        this.receptorID = receptorID;
    }

    /**
     * Método para enviar criptomonedas.
     *
     * @param c   La criptomoneda que se va a enviar.
     * @param dir La dirección a la que se enviará la criptomoneda.
     */
    public void enviar(Cripto c, String dir) {
        int opcion = -1;
        System.out.println("Elija un número de opción");
        In.nextInt(opcion);

        // double montoElegido = Blockchain.elegir(opcion,c,dir);
        // usuario.administrarCuentas(montoElegido);

        this.confirmar();
    }

    /**
     * Método para recibir criptomonedas.
     *
     * @param c La criptomoneda que se va a recibir.
     */
    public void recibir(Cripto c) {
        // usuario.recibir(c); internamente administra la cuenta y suma el monto
        this.confirmar();
    }

    /**
     * Método para confirmar el envío o recepción.
     * Establece la confirmación de la transacción y notifica al usuario.
     */
    @Override
    public void confirmar() {
        this.setConfirmacion(true);
        super.notificar();
    }

    /**
     * Método para notificar al usuario sobre la transacción.
     */
    public void notificar() {
        // this.getUsuario().notificar(this);
    }

    /**
     * Devuelve una representación en forma de cadena de la transacción de envío.
     *
     * @return Cadena que representa el envío.
     */
    @Override
    public String toString() {
        return super.toString() + "Envios [emisorID=" + emisorID + ", receptorID=" + receptorID + "]";
    }
}

