package entregable_2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Compra extends Transaccion {
	protected Fiat fiat;
	protected Cripto cripto;
	protected double cantidadFiat;

	private Compra(double monto,Cripto cripto,double cantidadFiat,Fiat fiat) {
		super(monto);
		this.fiat = fiat;
		this.cripto = cripto;
		this.cantidadFiat = cantidadFiat;
	}
	
	
	// esto podría estar en el main
	public static boolean comprar(Connection con,String criptoNomenclatura, String fiatNomenclatura, double cantidadFiat) {
		MonedaDAO mDAO = FactoryDAO.getMonedaDAO();
		try {
			Cripto cripto = (Cripto) mDAO.getMoneda(con, criptoNomenclatura);
			Fiat fiat = (Fiat) mDAO.getMoneda(con, fiatNomenclatura);
			
			if (cripto == null) {
				System.out.println("La moneda cripto elegida no existe.");
				return false;
			}
			if (fiat == null) {
				System.out.println("La moneda fiat elegida no existe.");
				return false;
			}
			
			double cantidadCripto = cantidadFiat / ((cripto.getPrecioEnDolar() / fiat.getPrecioEnDolar())); // cantidad Fiat / valor de la cripto en esa fiat
			
			if (cantidadCripto > cripto.getStock()) { //verificar stock
	            System.out.println("No hay suficiente stock de la criptomoneda " + criptoNomenclatura + ".");
	            return false;
	        }
			

	        Scanner in = new Scanner(System.in);
	        System.out.printf("Con %.2f %s puedes comprar %.6f %s. Ingrese 'S' para confirmar u otro valor para cancelar: ", cantidadFiat, fiatNomenclatura, cantidadCripto, criptoNomenclatura);
	        String confirmacion = in.nextLine();
	        in.close();
	        
	        if (confirmacion.equalsIgnoreCase("S")) {
	        	Compra compra = new Compra(cantidadCripto, cripto, cantidadFiat,fiat);
	        	// actualizarActivos()
	        	TransaccionDAO tDAO = FactoryDAO.getTransaccionDAO();
	        	tDAO.guardarTransaccion(con, compra.getDescripcion());
	            compra.confirmar();
	           
	            return true;
	        } else {
	            System.out.println("Operación cancelada.");
	            return false;
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	@Override 
	public String getDescripcion() {
		return super.getDescripcion() + ", Tipo: Compra" + ", Cripto adquirida: " + cripto.getCodigo() + ", Fiat enviado: " + fiat.getCodigo() + ", Cantidad del fiat enviado: " + cantidadFiat;
	}
	@Override
	public void confirmar() {
		this.confirmacion = true;
        System.out.println("Compra realizada con éxito.");
	}

}