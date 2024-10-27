package entregable_2;

public class Moneda {
	protected String codigo;
	protected double precioEnDolar;
	
	public Moneda(String codigo, double precioEnDolar) {
		this.codigo = codigo;
		this.precioEnDolar = precioEnDolar;
	}
	
	//Getters y Setters;
	public String getCodigo() {
		return codigo;
	}
	
	public double getPrecioEnDolar() {
		return precioEnDolar;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setPrecioEnDolar(double precioEnDolar) {
		this.precioEnDolar = precioEnDolar;
	}

}
