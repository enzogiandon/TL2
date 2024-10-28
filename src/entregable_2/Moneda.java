package entregable_2;

public class Moneda {
	protected String codigo; // nomenclatura de la moneda, ej USD
	protected double precioEnDolar;
	protected String nombre;
	protected double stock;
	
	public Moneda() {
	}
	
	public Moneda(String codigo, double precioEnDolar, String nombre) {
		super();
		this.codigo = codigo;
		this.precioEnDolar = precioEnDolar;
		this.nombre = nombre;
		
	}

	// Getters, setters
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getPrecioEnDolar() {
		return precioEnDolar;
	}

	public void setPrecioEnDolar(double precioEnDolar) {
		this.precioEnDolar = precioEnDolar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
