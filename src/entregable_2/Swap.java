package entregable_2;

public class Swap extends Transaccion {
    private Cripto criptoEnviada;
    private Cripto criptoRecibida;
    private double cantidadEnviada;
    private double cantidadRecibida;

    public Swap(Cripto criptoEnviada, Cripto criptoRecibida, double cantidadEnviada, double cantidadRecibida) {
        super(cantidadEnviada); // Monto equivale a la cantidad enviada
        this.criptoEnviada = criptoEnviada;
        this.criptoRecibida = criptoRecibida;
        this.cantidadEnviada = cantidadEnviada;
        this.cantidadRecibida = cantidadRecibida;
    }

    @Override
    public void confirmar() {
        this.confirmacion = true;
    }

    @Override
    public String getDescripcion() {
        return "Swap: " + cantidadEnviada + " " + criptoEnviada.getCodigo() +
               " por " + cantidadRecibida + " " + criptoRecibida.getCodigo() +
               " - Confirmado: " + confirmacion;
    }
}