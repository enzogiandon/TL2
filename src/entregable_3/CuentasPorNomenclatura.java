package entregable_3;
import java.util.Comparator;

//Agrego un Comparator para ordenar las cuentas por nomenclatura
public class CuentasPorNomenclatura implements Comparator<Cuenta>{
	@Override
	public int compare(Cuenta c1, Cuenta c2) {
		Integer valorc1 = c1.getId();
		Integer valorc2 = c2.getId();
		int valor = valorc1.compareTo(valorc2);
		return valor;
	}
}