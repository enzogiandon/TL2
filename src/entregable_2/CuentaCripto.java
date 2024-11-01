package entregable_2;


public class CuentaCripto extends Cuenta {
	protected Cripto cripto;
	private String direccion;

	public CuentaCripto() {
	}

	public CuentaCripto(String id, double saldo, String direccion) {
		super(id, saldo);
		this.direccion = direccion;
	}

	// Getters y Setters
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
