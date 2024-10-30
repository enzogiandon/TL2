package entregable_2;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Transaccion {
    protected double monto;
    protected LocalDate fecha;
    protected double comisiones;
    protected LocalTime tiempoElegido;
    protected boolean confirmacion;

    public Transaccion(double monto) {
        this.monto = monto;
        this.fecha = LocalDate.now();
        this.tiempoElegido = LocalTime.now();
        this.confirmacion = false; // Pendiente de confirmación
    }

    // Método para confirmar la transacción
    public abstract void confirmar();

    public String getDescripcion() {
        return "Monto: " + monto + ", Fecha: " + fecha + ", Confirmación: " + confirmacion;
    }
}
