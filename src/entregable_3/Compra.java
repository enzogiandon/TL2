package entregable_3;

public class Compra extends Transaccion {
	protected Fiat fiat;
	protected Cripto cripto;
	protected double cantidadFiat;
	
	public Compra(int id, int idUsuario,double monto,Cripto cripto,double cantidadFiat,Fiat fiat) {
		super(id,idUsuario,monto);
		this.fiat = fiat;
		this.cripto = cripto;
		this.cantidadFiat = cantidadFiat;
	}
	public Compra() {
		super();
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
}