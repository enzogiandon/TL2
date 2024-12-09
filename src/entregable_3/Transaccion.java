package entregable_3;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaccion {
	protected int id;
    protected double monto;
    protected LocalDate fecha;
    protected double comisiones;
    protected LocalTime tiempoElegido;
    protected boolean confirmacion;
    protected String resumen;

    public Transaccion(double monto) {
    	this.id= (int) (Math.random() * 1000 + 1);
        this.monto = monto;
        this.fecha = LocalDate.now();
        this.tiempoElegido = LocalTime.now();
        this.confirmacion = false; // Pendiente de confirmación
    }
    
    public Transaccion(int id, String resumen, LocalDate fecha) {
    	this.id = id;
    	this.resumen = resumen;
    	this.fecha = fecha;
    }
    
    // Método para confirmar la transacción
    public void confirmar() {
    	confirmacion = true;
    }


    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
        resumen = "Monto adquirido: " + monto + ", Fecha: " + fecha + ", Confirmación: " + confirmacion;
        return resumen;
    }
}