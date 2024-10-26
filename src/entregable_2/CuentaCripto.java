package entregable_2;

public class CuentaCripto extends Cuenta {

	private String direccion;
	
	public CuentaCripto (String id, double saldo, String direccion) {
		super(id, saldo);
		this.direccion = direccion;
	}
	
	//Getters y Setters
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
