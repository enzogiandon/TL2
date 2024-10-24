package entregable_1;

import java.time.*;

public class CompraVenta extends Transaccion {

	private Moneda monedaCompra;
	private Moneda monedaVenta;

	// Constructor
	public CompraVenta(Usuario usuario, double monto, String tipo, LocalDate fecha, double comisiones, LocalTime tiempoElegido,
			boolean confirmacion, Moneda monedaCompra, Moneda monedaVenta) {
		super(usuario, monto, tipo, fecha, comisiones, tiempoElegido, confirmacion);
		this.monedaCompra = monedaCompra;
		this.monedaVenta = monedaVenta;
	}

	// Getters y Setters
	public Moneda getMonedaCompra() {
		return monedaCompra;
	}

	public void setMonedaCompra(Moneda monedaCompra) {
		this.monedaCompra = monedaCompra;
	}

	public Moneda getMonedaVenta() {
		return monedaVenta;
	}

	public void setMonedaVenta(Moneda monedaVenta) {
		this.monedaVenta = monedaVenta;
	}

	// Métodos para comprar y vender
	public Cripto comprar(Fiat m, String id) {
/*		if (API.consultarStock(id)) {
			monedaCompra = API.operar(m, id);
			// usuario.administrarCuentas(m);
			this.confirmar();
			return monedaCompra;
		}*/

		System.out.println("No se pudo realizar la compra");
		return null;
	}

	public Fiat vender(Cripto m, String id) {
/*		if (API.consultarStock(id)) {
			monedaVenta = API.vender(m, id);
			// usuario.administrarCuentas(m);
			this.confirmar();
			return monedaVenta;
		}*/

		System.out.println("No se pudo realizar la venta");
		return null;
	}

	// Implementación de confirmar()
	@Override
	public void confirmar() {
		this.setConfirmacion(true);;
		this.notificar();
	}

	// Implementación de notificar()
	@Override
	public void notificar() {
		// usuario.notificar(this)
	}

	@Override
	public String toString() {
		return super.toString() + "CompraVenta [monedaCompra=" + monedaCompra + ", monedaVenta=" + monedaVenta + "]";
	}

}
