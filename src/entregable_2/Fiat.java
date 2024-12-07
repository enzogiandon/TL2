package entregable_2;

public class Fiat extends Moneda {
	
	public Fiat (String codigo, double precioEnDolar, String nombre, double stock) {
		super(codigo,precioEnDolar,nombre,stock);
	}
	
	public Fiat (String codigo, double precioEnDolar, String nombre, double stock, double volatilidad) {
		super(codigo,precioEnDolar,nombre,stock,volatilidad);
	}
}
