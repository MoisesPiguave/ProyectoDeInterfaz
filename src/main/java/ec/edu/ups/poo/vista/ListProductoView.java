package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.modelo.Proveedor;

import java.awt.*;
import java.util.List;

public class ListProductoView extends Frame {

    public ListProductoView(List<Proveedor> proveedores) {
        super("Lista de Productos con Proveedores");

        Panel mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(0, 1, 0, 8));


        Panel panelcito = new Panel(new FlowLayout(FlowLayout.LEFT, 10, 3));
        panelcito.add(new Label("Producto "));
        panelcito.add(new Label("Cantidad "));
        panelcito.add(new Label("Precio "));
        panelcito.add(new Label("Proveedor "));
        mainPanel.add(panelcito);


        for (Proveedor proveedor : proveedores) {
            List<Producto> productos = proveedor.getProductos();
            for (Producto producto : productos) {
                Panel panelsote = new Panel(new FlowLayout(FlowLayout.LEFT, 10, 3));
                panelsote.add(new Label(producto.getNombreDeProducto()));
                panelsote.add(new Label(String.valueOf(producto.getCantidadEnStock())));
                panelsote.add(new Label(String.format("%.2f", producto.getPrecioUnidad())));
                panelsote.add(new Label(proveedor.getNombre()));
                mainPanel.add(panelsote);
            }
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(mainPanel);

        add(scrollPane);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}



