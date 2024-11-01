package entregable_2;

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
	
	
	// esto podría estar en el main
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
		return super.getDescripcion() + ", Tipo: Compra" + ", Cripto adquirida: " + cripto.getCodigo() + ", Fiat enviado: " + fiat.getCodigo() + ", Cantidad del fiat enviado: " + cantidadFiat;
	}
	@Override
	public void confirmar() {
		this.confirmacion = true;
        System.out.println("Compra realizada con éxito.");
	}

}
