package entregable_2;


//para que las cuentas puedan ordenarse por saldo de manera descendente, implementa la 
//interfaz Comparable en la clase cuenta
public class Cuenta implements Comparable<Cuenta> {
	protected double saldo;
	protected String id;

	// Constructor, getters y setters
	public Cuenta(String id, double saldo) {
		this.saldo = saldo;
		this.id = id;
	}

	public Cuenta() {

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

	@Override
	public int compareTo(Cuenta cuenta1) {
		// orden descendente por saldo
		return Double.compare(cuenta1.saldo, this.saldo);
	}

	@Override
	public String toString() {
		return ("ID: " + id + ". Saldo: " + saldo);
	}

}