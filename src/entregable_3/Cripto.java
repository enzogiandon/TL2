package entregable_3;

public class Cripto extends Moneda {

	// Constructor con los mismos parámetros que Moneda
	public Cripto(int id, String codigo, double precioEnDolar, String nombre, double stock) {
		super(id, codigo, precioEnDolar, nombre, stock);
	}

	public Cripto(int id, String codigo, double precioEnDolar, String nombre, double stock, double volatilidad) {
		super(id, codigo, precioEnDolar, nombre, stock, volatilidad);
	}

	public Cripto() {
	}

	@Override
	public String toString() {
		return "Cripto [codigo=" + codigo + ", precioEnDolar=" + precioEnDolar + ", nombre=" + nombre + ", stock="
				+ stock + ", volatilidad=" + volatilidad + "]";
	}
}