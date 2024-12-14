package entregable_3;

public class CuentaCripto extends Cuenta {
	private String direccion;

	public CuentaCripto() {
	}

	public CuentaCripto(int id, int idUsuario, double saldo, String direccion, Cripto cripto) {
		super(id, idUsuario, saldo,cripto);
		this.direccion = direccion;
	}

	public CuentaCripto(int id, int idUsuario, double saldo, Cripto cripto) {
		super(id, idUsuario, saldo,cripto);
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