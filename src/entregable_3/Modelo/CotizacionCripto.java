package entregable_3.Modelo;

public class CotizacionCripto {
    private String codigo;
    private double precio;

    public CotizacionCripto(String codigo, double precio) {
        this.codigo = codigo;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return codigo + ": $" + precio;
    }
}
