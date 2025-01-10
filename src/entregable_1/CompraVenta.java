package entregable_1;

import java.time.*;

/**
 * Clase que representa una transacción de compra y venta de monedas.
 */
public class CompraVenta extends Transaccion {

    private Moneda monedaCompra;
    private Moneda monedaVenta;

    /**
     * Constructor de la clase CompraVenta.
     *
     * @param usuario       El usuario que realiza la transacción.
     * @param monto        El monto de la transacción.
     * @param tipo         El tipo de transacción.
     * @param fecha        La fecha en que se realiza la transacción.
     * @param comisiones    Las comisiones asociadas a la transacción.
     * @param tiempoElegido El tiempo en que se desea realizar la transacción.
     * @param confirmacion  Indica si la transacción ha sido confirmada.
     * @param monedaCompra  La moneda que se está comprando.
     * @param monedaVenta   La moneda que se está vendiendo.
     */
    public CompraVenta(Usuario usuario, double monto, String tipo, LocalDate fecha, double comisiones, LocalTime tiempoElegido,
                       boolean confirmacion, Moneda monedaCompra, Moneda monedaVenta) {
        super(usuario, monto, tipo, fecha, comisiones, tiempoElegido, confirmacion);
        this.monedaCompra = monedaCompra;
        this.monedaVenta = monedaVenta;
    }

    // Getters y Setters

    /**
     * Obtiene la moneda que se está comprando.
     *
     * @return La moneda de compra.
     */
    public Moneda getMonedaCompra() {
        return monedaCompra;
    }

    /**
     * Establece la moneda que se está comprando.
     *
     * @param monedaCompra La moneda de compra a establecer.
     */
    public void setMonedaCompra(Moneda monedaCompra) {
        this.monedaCompra = monedaCompra;
    }

    /**
     * Obtiene la moneda que se está vendiendo.
     *
     * @return La moneda de venta.
     */
    public Moneda getMonedaVenta() {
        return monedaVenta;
    }

    /**
     * Establece la moneda que se está vendiendo.
     *
     * @param monedaVenta La moneda de venta a establecer.
     */
    public void setMonedaVenta(Moneda monedaVenta) {
        this.monedaVenta = monedaVenta;
    }

    /**
     * Método para realizar la compra de una moneda.
     *
     * @param m  La moneda Fiat que se va a utilizar para la compra.
     * @param id El identificador de la moneda a comprar.
     * @return La moneda comprada, o null si la compra falla.
     */
    public Cripto comprar(Fiat m, String id) {
        /* 
        if (API.consultarStock(id)) {
            monedaCompra = API.operar(m, id);
            // usuario.administrarCuentas(m);
            this.confirmar();
            return monedaCompra;
        }
        */

        System.out.println("No se pudo realizar la compra");
        return null;
    }

    /**
     * Método para realizar la venta de una moneda.
     *
     * @param m  La moneda Cripto que se va a vender.
     * @param id El identificador de la moneda a vender.
     * @return La moneda vendida, o null si la venta falla.
     */
    public Fiat vender(Cripto m, String id) {
        /*
        if (API.consultarStock(id)) {
            monedaVenta = API.vender(m, id);
            // usuario.administrarCuentas(m);
            this.confirmar();
            return monedaVenta;
        }
        */

        System.out.println("No se pudo realizar la venta");
        return null;
    }

    /**
     * Implementación del método confirmar. 
     * Establece la confirmación de la transacción y notifica al usuario.
     */
    @Override
    public void confirmar() {
        this.setConfirmacion(true);
        this.notificar();
    }

    /**
     * Implementación del método notificar.
     * Notifica al usuario sobre la transacción.
     */
    @Override
    public void notificar() {
        // usuario.notificar(this);
    }

    /**
     * Devuelve una representación en forma de cadena de la transacción de compra y venta.
     *
     * @return Cadena que representa la compra-venta.
     */
    @Override
    public String toString() {
        return super.toString() + "CompraVenta [monedaCompra=" + monedaCompra + ", monedaVenta=" + monedaVenta + "]";
    }
}

