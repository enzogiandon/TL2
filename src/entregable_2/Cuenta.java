package entregable_2;

public class Cuenta {
	protected double saldo;
	protected String id;
	
	// Constructor, getters y setters
	public Cuenta (String id, double saldo) {
		this.saldo = saldo;
		this.id = id;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	

}
