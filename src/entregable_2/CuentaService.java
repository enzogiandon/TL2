package entregable_2;
import java.util.Collections;
import java.util.List;
//implementa los servicios
public class CuentaService {
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
