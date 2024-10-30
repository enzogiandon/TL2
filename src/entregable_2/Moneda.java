package entregable_2;

public class Moneda implements Comparable<Moneda>{
	protected String codigo; // nomenclatura de la moneda, ej USD
	protected double precioEnDolar;
	protected String nombre;
	protected double stock;
	protected double volatilidad;
	
	public Moneda() {
	}
	
	// Constructor sin volatilidad
	
	public Moneda(String codigo, double precioEnDolar, String nombre, double stock) {
		super();
		this.codigo = codigo;
		this.precioEnDolar = precioEnDolar;
		this.nombre = nombre;
		this.stock = stock;
	}

	// Constructor con volatilidad
	public Moneda(String codigo, double precioEnDolar, String nombre, double stock, double volatilidad) {
		super();
		this.codigo = codigo;
		this.precioEnDolar = precioEnDolar;
		this.nombre = nombre;
		this.stock = stock;
		this.volatilidad = volatilidad;
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
	
	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Moneda [codigo=" + codigo + ", precioEnDolar=" + precioEnDolar + ", nombre=" + nombre + ", stock="
				+ stock + "]";
	}
	
	@Override
	public int compareTo(Moneda m) {
		return Double.compare(this.precioEnDolar, m.precioEnDolar);
	}
	
	public int compareToNomenclatura(Moneda m) {
		return this.codigo.compareTo(m.codigo);
	}
	

}