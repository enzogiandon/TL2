package entregable_3;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import entregable_3.Modelo.*;


public class Compra extends Transaccion {
	protected Fiat fiat;
	protected Cripto cripto;
	protected double cantidadFiat;
	
	public Compra(double monto,Cripto cripto,double cantidadFiat,Fiat fiat) {
		super(monto);
		this.fiat = fiat;
		this.cripto = cripto;
		this.cantidadFiat = cantidadFiat;
	}
	public Compra(Cripto cripto,double cantidadFiat,Fiat fiat) {
		super();
		this.fiat = fiat;
		this.cripto = cripto;
		this.cantidadFiat = cantidadFiat;
	}
	
	/**
	 * 
	 * @param con Conexion con la base de datos.
	 * @param criptoNomenclatura La nomenclatura de la cripto que voy a comprar.
	 * @param fiatNomenclatura La nomenclatura de la fiat con la que voy a hacer la compra.
	 * @param cantidadFiat La cantidad de fiat que uso para comprar de esa cripto. 
	 * @return
	 */
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	
	@Override 
	public String getDescripcion() {
		String resumen = super.getDescripcion() +   ", Tipo: Compra" + ", Cripto adquirida: " + cripto.getCodigo() + ", Fiat enviado: " + fiat.getCodigo() + ", Cantidad del fiat enviado: " + cantidadFiat;;
		return resumen;
		}
	@Override
	public void confirmar() {
		super.confirmar();
        System.out.println("Compra realizada con éxito.");
	}
	
	public boolean comprar(String criptoNomenclatura, String fiatNomenclatura, double cantidadFiat, Scanner in) {
		try {
			MonedaDAO mDAO = FactoryDAO.getMonedaDAO();
			CuentaDAO cDAO = FactoryDAO.getCuentaDAO();
			TransaccionDAO tDAO = FactoryDAO.getTransaccionDAO();

			double fondosFiat = cDAO.obtenerCantidadFiat(fiatNomenclatura);
			if (cantidadFiat > fondosFiat) {
				System.out.println("Fondos no suficientes en " + fiatNomenclatura.toUpperCase()
						+ " para realizar esta operación.");
				return false;
			}
			if (cDAO.getCuentaCripto(criptoNomenclatura) == null) {
				System.out.println("Usted no posee una cuenta de " + criptoNomenclatura + ". Intente creando una.");
				return false;
			}

			Cripto cripto = (Cripto) mDAO.getMoneda(criptoNomenclatura);

			Fiat fiat = (Fiat) mDAO.getMoneda(fiatNomenclatura);

			if (cripto == null) {
				System.out.println("La moneda cripto elegida no existe.");
				return false;
			}
			if (fiat == null) {
				System.out.println("La moneda fiat elegida no existe.");
				return false;
			}

			double cantidadCripto = cantidadFiat / ((cripto.getPrecioEnDolar() / fiat.getPrecioEnDolar()));
			// cantidad Fiat / valor de la cripto en esa fiat

			if (cantidadCripto > cripto.getStock()) { // verificar stock
				System.out.println("No hay suficiente stock de la criptomoneda " + criptoNomenclatura + ".");
				return false;
			}

			System.out.printf(
					"Con %.2f %s puedes comprar %.6f %s. Ingrese 'S' para confirmar u otro valor para cancelar: ",
					cantidadFiat, fiatNomenclatura, cantidadCripto, criptoNomenclatura);
			
			String confirmacion = in.next();

			if (confirmacion.equalsIgnoreCase("S")) {
				// Instancio una compra y la guardo
				
				this.setMonto(cantidadCripto);
				this.cripto = cripto;
				this.cantidadFiat = cantidadFiat;
				this.fiat = fiat;
				
				
				confirmar();

				//falta la implementación del usuario
				tDAO.guardarTransaccion(this.id,this.getDescripcion(),iUsuario);

				// actualizo los fondos y lo guardo en BD
				fondosFiat -= cantidadFiat;
				double fondosCripto = cDAO.obtenerCantidadCripto(criptoNomenclatura);
				fondosCripto += cantidadCripto;

				CuentaCripto cc = new CuentaCripto(criptoNomenclatura, fondosCripto, "1234");
				CuentaFiat cf = new CuentaFiat(fiatNomenclatura, fondosFiat);

				// añado a lista y actualizo la BD
				List<CuentaFiat> listaFiat = List.of(cf);
				cDAO.actualizarCuentasFiat(listaFiat);

				List<CuentaCripto> listaCripto = List.of(cc);
				cDAO.actualizarCuentasCripto(listaCripto);


				return true;
			} else {
				System.out.println("Operación cancelada.");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassCastException e) {
			System.out.println("No se encontró una cripto con esa nomenclatura, intente nuevamente.");
			e.printStackTrace();
			return false;
		}
	}

}
