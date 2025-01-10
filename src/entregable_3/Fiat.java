package entregable_3;

public class Fiat extends Moneda {

	public Fiat(int id, String codigo, double precioEnDolar, String nombre, double stock) {
		super(id, codigo, precioEnDolar, nombre, stock);
	}

	public Fiat(int id, String codigo, double precioEnDolar, String nombre, double stock, double volatilidad) {
		super(id, codigo, precioEnDolar, nombre, stock, volatilidad);
	}
	public Fiat() {
	}
}
