package entregable_3;

public class CuentaCripto extends Cuenta {
	protected Cripto cripto;
	private String direccion;

	public CuentaCripto() {
	}

	public CuentaCripto(String id, double saldo, String direccion) {
		super(id, saldo);
		this.direccion = direccion;
	}
	public CuentaCripto(String id, double saldo, String direccion,Cripto cripto) {
		super(id, saldo);
		this.direccion = direccion;
		this.cripto = cripto;
	}

	// Getters y Setters
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Cripto getCripto() {
		return cripto;
	}
	public void setCripto(Cripto cripto){
		this.cripto = cripto;
	}
	

	// Implementación de guardarDefi(Cripto)
	public void guardarDefi(Cripto cripto) {
		// hay que implementar
	}

	public Cripto retirar(Cripto defi) {
		// hay que implementar
		return defi;
	}

}
