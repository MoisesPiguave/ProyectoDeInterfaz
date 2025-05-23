package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.modelo.Proveedor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListProductoView extends Frame {
    private Button btnSalirListProducto;

    public ListProductoView(List<Proveedor> proveedores) {
        super("Lista de Productos con Proveedores");

        Panel mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(0, 1, 0, 5));

        Panel encabezadoPanel = new Panel(new GridLayout(1, 4));
        encabezadoPanel.add(new Label("Producto", Label.CENTER));
        encabezadoPanel.add(new Label("Cantidad", Label.CENTER));
        encabezadoPanel.add(new Label("Precio", Label.CENTER));
        encabezadoPanel.add(new Label("Proveedor", Label.CENTER));
        mainPanel.add(encabezadoPanel);

        for (Proveedor proveedor : proveedores) {
            List<Producto> productos = proveedor.getProductos();
            for (Producto producto : productos) {
                Panel filaPanel = new Panel(new GridLayout(1, 4));
                filaPanel.add(new Label(producto.getNombreDeProducto(), Label.CENTER));
                filaPanel.add(new Label("" + producto.getCantidadEnStock(), Label.CENTER));
                double precio = Math.round(producto.getPrecioUnidad() * 100) / 100.0;
                filaPanel.add(new Label("" + precio, Label.CENTER));
                filaPanel.add(new Label(proveedor.getNombre(), Label.CENTER));
                mainPanel.add(filaPanel);
            }
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(mainPanel);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        btnSalirListProducto = new Button("Salir");
        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnSalirListProducto);
        add(panelBoton, BorderLayout.SOUTH);

        btnSalirListProducto.addActionListener(e -> dispose());

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
