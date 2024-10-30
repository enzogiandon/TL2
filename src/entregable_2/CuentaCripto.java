package entregable_2;

import java.sql.SQLException;
import java.util.List;

public class CuentaCripto extends Cuenta {

	private String direccion;
    private CuentaDAO cuentaDAO = new CuentaDAOjdbc();
	
	public CuentaCripto (String id, double saldo, String direccion) {
		super(id, saldo);
		this.direccion = direccion;
	}
	
	//Getters y Setters
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	//Implementación de guardarDefi(Cripto)
	public void guardarDefi(Cripto cripto) {
		//hay que implementar
	}
	
	public Cripto retirar(Cripto defi) {
		// hay que implementar
		return defi;
	}
	
	//ANTES ESTABA EN SERVICE
	
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
