package entregable_3;
//para que las cuentas puedan ordenarse por saldo de manera descendente, implementa la 
//interfaz Comparable en la clase cuenta
public class Cuenta implements Comparable<Cuenta> {

	private int id;
	private int idUsuario;
	private double saldo;
	private Moneda moneda;
	
	// Constructor, getters y setters
	public Cuenta(int id, int idUsuario, double saldo, Moneda moneda) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.saldo = saldo;
		this.moneda = moneda;
	}
	
	public Cuenta() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	@Override
	public int compareTo(Cuenta cuenta1) {
		// orden descendente por saldo
		return Double.compare(cuenta1.saldo, this.saldo);
	}

	@Override
	public String toString() {
		return ("ID: " + id + ". ID Usuario: "+ idUsuario +". Saldo: " + saldo + ". Moneda: " + moneda.toString());
	}

}