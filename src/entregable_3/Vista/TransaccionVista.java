package entregable_3.Vista;
import javax.swing.*;
import java.awt.*;

public class TransaccionVista extends JFrame {
    private JTextArea textArea;
    private JTextField idInputField;
    private JButton descripcionButton;

    public TransaccionVista() {
        setTitle("Vista de Transacciones");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        idInputField = new JTextField(10);
        descripcionButton = new JButton("Ver Descripción");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("ID:"));
        bottomPanel.add(idInputField);
        bottomPanel.add(descripcionButton);

        add(panel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JTextField getIdInputField() {
        return idInputField;
    }

    public JButton getDescripcionButton() {
        return descripcionButton;
    }
}

