package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Proveedor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuscarProveedorView extends Frame {

    private TextField txtCedula;
    private Button btnBuscar;
    private Button btnSalir;
    private Label resultadoLabel;

    private List<Proveedor> proveedores;

    public BuscarProveedorView(List<Proveedor> proveedores) {
        super("Buscar Proveedor");

        this.proveedores = proveedores;

        Panel panel = new Panel(new GridLayout(3, 2, 10, 10));

        panel.add(new Label("Ingrese cédula:"));
        txtCedula = new TextField(20);
        panel.add(txtCedula);

        btnBuscar = new Button("Buscar");
        panel.add(btnBuscar);

        resultadoLabel = new Label("");
        panel.add(resultadoLabel);

        btnSalir = new Button("Salir");
        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnSalir);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedula.getText();
                if (cedula.isEmpty()) {
                    resultadoLabel.setText("Ingrese una cédula.");
                    return;
                }
                Proveedor encontrado = null;
                for (Proveedor p : proveedores) {
                    if (p.getCedula().equals(cedula)) {
                        encontrado = p;
                        break;
                    }
                }
                if (encontrado != null) {
                    resultadoLabel.setText("Proveedor: " + encontrado.getNombre() + ", Productos: " + encontrado.getProductos().size());
                } else {
                    resultadoLabel.setText("Proveedor no encontrado.");
                }
            }
        });

        btnSalir.addActionListener(e -> dispose());

        setSize(400, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

