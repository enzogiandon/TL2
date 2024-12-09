package entregable_3.Vista;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class CompraVista extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel stockLabel;
	private JLabel precioLabel;
	private JTextField cantidadFiatField;
	private JComboBox<String> fiatDropdown;
	private JButton convertirButton;
	private JLabel equivalenteLabel;

	public CompraVista() {
		setTitle("Compra");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(6, 2, 10, 10));

		// Inicializo las variables de los elementos de la GUI
		stockLabel = new JLabel("Stock disponible: -");
		precioLabel = new JLabel("Precio de compra: -");
		cantidadFiatField = new JTextField();
		fiatDropdown = new JComboBox<>();
		convertirButton = new JButton("Convertir");
		equivalenteLabel = new JLabel("Equivale a: -");

		// Agregar elementos a la ventana
		add(new JLabel("Stock disponible:"));
		add(stockLabel);
		add(new JLabel("Precio de compra:"));
		add(precioLabel);
		add(new JLabel("Quiero comprar con:"));
		add(cantidadFiatField);
		add(new JLabel("Seleccionar moneda Fiat:"));
		add(fiatDropdown);
		add(new JLabel(""));
		add(convertirButton);
		add(new JLabel("Equivale a:"));
		add(equivalenteLabel);
	}

	public double getCantidadFiat() {
		return Double.valueOf(cantidadFiatField.getText());
	}
	
	public String getFiatSeleccionada() {
		return (String) fiatDropdown.getSelectedItem();
	}
	
	public void botonListener(ActionListener listener) {
		convertirButton.addActionListener(listener);

	}

	public void setEquivalente(String equivalente) {
		equivalenteLabel.setText(equivalente);	
	}
	
	public void setStock(String stock) {
		stockLabel.setText(stock);
	}
	
	public void setPrecio(String precio) {
		precioLabel.setText(precio);
	}
	
	public void setOpcionesFiat(List<String> opciones) {
		fiatDropdown.removeAllItems();
		for (String opcion: opciones)
			fiatDropdown.addItem(opcion);
	}
}
