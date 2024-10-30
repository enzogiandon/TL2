package entregable_2;

import java.util.List;

public class Cripto extends Moneda {

    // Constructor con los mismos parámetros que Moneda
    public Cripto(String codigo, double precioEnDolar, String nombre, double stock) {
        super(codigo, precioEnDolar, nombre, stock);
    }

    public Cripto(String codigo, double precioEnDolar, String nombre, double stock, double volatilidad) {
        super(codigo, precioEnDolar, nombre, stock, volatilidad);
    }

    // Calcula el saldo total disponible en todas las cuentas de esta cripto
    public double calcularSaldoTotal() {
        double saldoTotal = 0.0;

        // Obtener las cuentas desde el DAO y sumar los saldos de la cripto con el código actual
        CuentaDAO cuentaDAO = new CuentaDAOjdbc();
        List<Cuenta> cuentas = cuentaDAO.listarCuentas();

        for (Cuenta cuenta : cuentas) {
            if (cuenta instanceof CuentaCripto && cuenta.getId().equals(this.codigo)) {
                saldoTotal += cuenta.getSaldo();
            }
        }

        return saldoTotal;
    }

    // Calcula el saldo total para una cripto específica dada su nomenclatura
    public double calcularSaldoTotal(String codigo) {
        if (!this.codigo.equals(codigo)) {
            System.out.println("Error: Código de criptomoneda no coincide.");
            return 0.0;
        }

        return calcularSaldoTotal();
    }

    @Override
    public String toString() {
        return "Cripto [codigo=" + codigo + ", precioEnDolar=" + precioEnDolar + ", nombre=" + nombre +
                ", stock=" + stock + ", volatilidad=" + volatilidad + "]";
    }
}
