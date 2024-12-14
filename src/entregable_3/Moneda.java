package entregable_3;

public class Moneda implements Comparable<Moneda> {
	protected int id;
	protected String codigo; // nomenclatura de la moneda, ej USD
	protected double precioEnDolar;
	protected String nombre;
	protected double stock;
	protected double volatilidad;

	public Moneda() {
	}

	
	// Constructor sin volatilidad

	public Moneda(int id, String codigo, double precioEnDolar, String nombre, double stock) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.precioEnDolar = precioEnDolar;
		this.nombre = nombre;
		this.stock = stock;
		this.volatilidad = Math.random() * 1000 + 1; // entre 1 y 1000
	}

	// Constructor con volatilidad
	public Moneda(int id,String codigo, double precioEnDolar, String nombre, double stock, double volatilidad) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.precioEnDolar = precioEnDolar;
		this.nombre = nombre;
		this.stock = stock;
		this.volatilidad = volatilidad;
	}

	// Getters, setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public double getVolatilidad() {
		return volatilidad;
	}

	public void setVolatilidad(double volatilidad) {
		this.volatilidad = volatilidad;
	}

	@Override
	public String toString() {
		return "Moneda [id=" + id + ", codigo=" + codigo + ", precioEnDolar=" + precioEnDolar + ", nombre=" + nombre
				+ ", stock=" + stock + ", volatilidad=" + volatilidad + "]";
	}


	@Override
	public int compareTo(Moneda m) {
		return Double.compare(this.precioEnDolar, m.precioEnDolar);
	}
}