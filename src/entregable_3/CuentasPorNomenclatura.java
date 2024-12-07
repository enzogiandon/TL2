package entregable_3;
import java.util.Comparator;

//Agrego un Comparator para ordenar las cuentas por nomenclatura
public class CuentasPorNomenclatura implements Comparator<Cuenta>{
	@Override
	public int compare(Cuenta c1, Cuenta c2) {
		return c1.getId().compareTo(c2.getId());
	}
}