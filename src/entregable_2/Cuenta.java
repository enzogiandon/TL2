package entregable_2;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//para que las cuentas puedan ordenarse por saldo de manera descendente, implementa la 
//interfaz Comparable en la clase cuenta
public class Cuenta implements Comparable<Cuenta>{
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
	
	@Override
	public int compareTo(Cuenta cuenta1) {
		//orden descendente por saldo
		return Double.compare(cuenta1.saldo, this.saldo);
	}
	
	@Override
	public String toString() {
		return ("ID: " + id + ". Saldo: " + saldo);
	}
	
	//ANTES ESTABA EN SERVICE
private CuentaDAO cuentaDAO = new CuentaDAOjdbc();
	
	public boolean crearCuenta(String idCuenta, double saldo, String codigoMoneda) {
		if (cuentaDAO.existeMoneda(codigoMoneda)) {
			Cuenta cuenta = new CuentaCripto(idCuenta, saldo, null);
			return cuentaDAO.guardarCuenta(cuenta);
		}else {
			System.out.println("Error: la moneda ingresada no existe.");
			return false;
		}
	}
	
	public void listarCuentas(boolean ordenarPorSaldo) {
		List<Cuenta> cuentas = cuentaDAO.listarCuentas();
		if (ordenarPorSaldo) {
			Collections.sort(cuentas); //ordena por saldo descendente (usando Comparable)
		}else {
			cuentas.sort(new OrdenarPorNomenclatura()); //ordena por nomenclatura (usando Comparator)
		}
		//Muestra en pantalla la lista de cuentas ordenada
		for (Cuenta cuenta : cuentas) {
			System.out.println(cuenta);
		}
	}

}
