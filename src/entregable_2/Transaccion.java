package entregable_2;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Transaccion {
    protected double monto;
    protected LocalDate fecha;
    protected LocalTime hora;
    protected double comisiones;
    protected boolean confirmacion;

    public Transaccion(double monto) {
        this.monto = monto;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.confirmacion = false; // Pendiente de confirmación
    }

    // Método para confirmar la transacción
    public abstract void confirmar();

    public String getDescripcion() {
        return "Monto adquirido: " + monto + ", Fecha y hora: " + fecha + hora + ", Confirmación: " + confirmacion;
    }
}