package entregable_3;

import java.util.Comparator;

public class MonedasPorNomenclatura implements Comparator<Moneda> {
	@Override
	public int compare(Moneda m1, Moneda m2) {
		return m1.codigo.compareTo(m2.codigo);
	}
}