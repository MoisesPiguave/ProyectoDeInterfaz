package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Proveedor;
import ec.edu.ups.poo.modelo.Producto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuscarProductoView extends Frame {
    private TextField txtNombreProducto;
    private Button btnBuscar;
    private Button btnSalir;
    private Label resultadoLabel;

    private List<Proveedor> proveedores;

    public BuscarProductoView(List<Proveedor> proveedores) {
        super("Buscar Producto");
        this.proveedores = proveedores;

        setLayout(new BorderLayout(10, 10));

        Panel inputPanel = new Panel(new FlowLayout());
        inputPanel.add(new Label("Nombre Producto:"));
        txtNombreProducto = new TextField(20);
        inputPanel.add(txtNombreProducto);

        btnBuscar = new Button("Buscar");
        inputPanel.add(btnBuscar);

        add(inputPanel, BorderLayout.NORTH);

        resultadoLabel = new Label("");
        add(resultadoLabel, BorderLayout.CENTER);

        btnSalir = new Button("Salir");
        Panel panelSalir = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelSalir.add(btnSalir);
        add(panelSalir, BorderLayout.SOUTH);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreBuscado = txtNombreProducto.getText();
                if (nombreBuscado == null || nombreBuscado.isEmpty()) {
                    resultadoLabel.setText("Ingrese un nombre válido.");
                    return;
                }
                boolean encontrado = false;
                for (Proveedor p : proveedores) {
                    for (Producto prod : p.getProductos()) {
                        if (prod.getNombreDeProducto().equalsIgnoreCase(nombreBuscado)) {
                            resultadoLabel.setText("Producto: " + prod.getNombreDeProducto() +
                                    ", Cantidad: " + prod.getCantidadEnStock() +
                                    ", Precio: $" + prod.getPrecioUnidad());
                            encontrado = true;
                            break;
                        }
                    }
                    if (encontrado) break;
                }
                if (!encontrado) {
                    resultadoLabel.setText("Producto no encontrado.");
                }
            }
        });

        btnSalir.addActionListener(e -> dispose());

        setSize(400, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
