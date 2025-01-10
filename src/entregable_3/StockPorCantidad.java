package entregable_3;

import java.util.Comparator;

public class StockPorCantidad implements Comparator<Moneda> {
    @Override
    public int compare(Moneda m1, Moneda m2) {
        return Double.compare(m2.getStock(), m1.getStock()); // Ordena en orden descendente
    }
}

