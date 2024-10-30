package entregable_2;

public class CuentaFiat extends Cuenta {
	protected Fiat fiat;
	//deberíamos fijarnos si necesita algo más, pero por si las dudas ya la creo.
	public CuentaFiat (String id, double saldo) {
		super(id, saldo);
	}
}
