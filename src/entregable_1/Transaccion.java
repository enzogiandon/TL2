package entregable_1;

import java.time.*;

/**
 * Clase abstracta que representa una transacción financiera.
 */
public abstract class Transaccion {
    private Usuario usuario;
    private double monto;
    private String tipo;
    private LocalDate fecha;
    private double comisiones;
    private LocalTime tiempoElegido;
    private boolean confirmacion;

    /**
     * Constructor de la clase Transaccion.
     *
     * @param usuario       El usuario que realiza la transacción.
     * @param monto        El monto de la transacción.
     * @param tipo         El tipo de transacción.
     * @param fecha        La fecha en que se realiza la transacción.
     * @param comisiones    Las comisiones asociadas a la transacción.
     * @param tiempoElegido El tiempo en que se desea realizar la transacción.
     * @param confirmacion  Indica si la transacción ha sido confirmada.
     */
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

    /**
     * Obtiene el usuario asociado a la transacción.
     *
     * @return El usuario que realiza la transacción.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado a la transacción.
     *
     * @param usuario El usuario a establecer.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el monto de la transacción.
     *
     * @return El monto de la transacción.
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Establece el monto de la transacción.
     *
     * @param monto El monto a establecer.
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * Obtiene el tipo de transacción.
     *
     * @return El tipo de transacción.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de transacción.
     *
     * @param tipo El tipo a establecer.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la fecha de la transacción.
     *
     * @return La fecha de la transacción.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la transacción.
     *
     * @param fecha La fecha a establecer.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene las comisiones asociadas a la transacción.
     *
     * @return Las comisiones de la transacción.
     */
    public double getComisiones() {
        return comisiones;
    }

    /**
     * Establece las comisiones de la transacción.
     *
     * @param comisiones Las comisiones a establecer.
     */
    public void setComisiones(double comisiones) {
        this.comisiones = comisiones;
    }

    /**
     * Obtiene el tiempo elegido para la transacción.
     *
     * @return El tiempo elegido.
     */
    public LocalTime getTiempoElegido() {
        return tiempoElegido;
    }

    /**
     * Establece el tiempo elegido para la transacción.
     *
     * @param tiempoElegido El tiempo a establecer.
     */
    public void setTiempoElegido(LocalTime tiempoElegido) {
        this.tiempoElegido = tiempoElegido;
    }

    /**
     * Obtiene el estado de confirmación de la transacción.
     *
     * @return true si la transacción está confirmada, false en caso contrario.
     */
    public boolean getConfirmacion() {
        return confirmacion;
    }

    /**
     * Establece el estado de confirmación de la transacción.
     *
     * @param confirmacion El estado de confirmación a establecer.
     */
    public void setConfirmacion(boolean confirmacion) {
        this.confirmacion = confirmacion;
    }

    /**
     * Método abstracto para confirmar la transacción.
     */
    public abstract void confirmar();

    /**
     * Método para notificar al usuario sobre la transacción.
     */
    public void notificar() {
        // usuario.notificar(this);
    }

    /**
     * Devuelve una representación en forma de cadena de la transacción.
     *
     * @return Cadena que representa la transacción.
     */
    @Override
    public String toString() {
        return "Transaccion [Usuario=" + usuario + ", monto=" + monto + ", tipo=" + tipo + ", fecha=" + fecha +
               ", comisiones=" + comisiones + ", tiempoElegido=" + tiempoElegido + ", confirmacion=" + confirmacion + "]";
    }
}
