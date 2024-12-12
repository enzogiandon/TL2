package entregable_3;

import entregable_3.Modelo.*;
import java.util.List;

public class Cripto extends Moneda {

	// Constructor con los mismos parámetros que Moneda
	public Cripto(String codigo, double precioEnDolar, String nombre, double stock) {
		super(codigo, precioEnDolar, nombre, stock);
	}

	public Cripto(String codigo, double precioEnDolar, String nombre, double stock, double volatilidad) {
		super(codigo, precioEnDolar, nombre, stock, volatilidad);
	}

	public Cripto() {
	}

	@Override
	public String toString() {
		return "Cripto [codigo=" + codigo + ", precioEnDolar=" + precioEnDolar + ", nombre=" + nombre + ", stock="
				+ stock + ", volatilidad=" + volatilidad + "]";
	}
}