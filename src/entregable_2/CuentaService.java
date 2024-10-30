package entregable_2;
import java.util.Collections;
import java.sql.SQLException;
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

    private MonedaDAO monedaDAO = new MonedaDAOjdbc();

    public boolean simularSwap(String criptoConvertirCodigo, double cantidad, String criptoEsperadaCodigo) {
        List<Cuenta> cuentas = cuentaDAO.listarCuentas();

        CuentaCripto cuentaConvertir = null;
        CuentaCripto cuentaEsperada = null;
        
        for (Cuenta cuenta : cuentas) {
            if (cuenta instanceof CuentaCripto) {
                CuentaCripto cuentaCripto = (CuentaCripto) cuenta;
                if (cuentaCripto.getId().equals(criptoConvertirCodigo)) {
                    cuentaConvertir = cuentaCripto;
                } else if (cuentaCripto.getId().equals(criptoEsperadaCodigo)) {
                    cuentaEsperada = cuentaCripto;
                }
            }
        }

        if (cuentaConvertir == null || cuentaEsperada == null) {
            System.out.println("Error: Una de las criptomonedas no está en Mis Activos.");
            return false;
        }

        if (cuentaConvertir.getSaldo() < cantidad) {
            System.out.println("Error: Stock insuficiente de " + criptoConvertirCodigo);
            return false;
        }

        double tasaCambio = obtenerTasaCambio(criptoConvertirCodigo, criptoEsperadaCodigo);
        double cantidadRecibida = cantidad * tasaCambio;

        // Crear el swap y actualizar los activos
        Swap swap = new Swap(
            new Cripto(criptoConvertirCodigo, 0, "Cripto", cuentaConvertir.getSaldo()),
            new Cripto(criptoEsperadaCodigo, 0, "Cripto", cuentaEsperada.getSaldo()),
            cantidad,
            cantidadRecibida
        );
        swap.confirmar();
        cuentaConvertir.setSaldo(cuentaConvertir.getSaldo() - cantidad);
        cuentaEsperada.setSaldo(cuentaEsperada.getSaldo() + cantidadRecibida);

        cuentaDAO.guardarCuenta(cuentaConvertir);
        cuentaDAO.guardarCuenta(cuentaEsperada);

        cuentaDAO.registrarTransaccion(swap.getDescripcion());

        System.out.println("Swap realizado exitosamente.");
        return true;
    }

    private double obtenerTasaCambio(String criptoConvertirCodigo, String criptoEsperadaCodigo) {
        // Consultar MonedaDAO para obtener la tasa de cambio (ejemplo básico)
        try {
            List<Moneda> monedas = monedaDAO.listarMonedas(ConexionBD.getConnection());
            double precioCriptoConvertir = 0;
            double precioCriptoEsperada = 0;

            for (Moneda moneda : monedas) {
                if (moneda.getCodigo().equals(criptoConvertirCodigo)) {
                    precioCriptoConvertir = moneda.getPrecioEnDolar();
                } else if (moneda.getCodigo().equals(criptoEsperadaCodigo)) {
                    precioCriptoEsperada = moneda.getPrecioEnDolar();
                }
            }
            if (precioCriptoEsperada == 0) {
                System.out.println("Error: Moneda esperada no encontrada.");
                return 0;
            }
            return precioCriptoConvertir / precioCriptoEsperada;

        } catch (SQLException e) {
            System.out.println("Error en obtenerTasaCambio: " + e.getMessage());
            return 0;
        }
    }

}
