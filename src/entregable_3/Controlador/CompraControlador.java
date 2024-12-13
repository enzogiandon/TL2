package entregable_3.Controlador;

import entregable_3.Vista.CompraVista;

public class CompraControlador {
    private CompraVista vista;
    private String criptoNombre;
    private double precio;

    public CompraControlador(CompraVista vista, String criptoNombre, double precio) {
        this.vista = vista;
        this.criptoNombre = criptoNombre;
        this.precio = precio;

        inicializarVista();
    }

    private void inicializarVista() {
        vista.setStock("Disponible"); // Aquí podrías conectar con la lógica real de stock
        vista.setPrecio(String.format("USD %.2f por unidad.", precio));
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}
