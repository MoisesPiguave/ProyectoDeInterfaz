package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoView extends Frame {

    private TextField txtIdProducto;
    private TextField txtNombreProducto;
    private TextField txtPrecioUnidad;
    private TextField txtCantidadStock;

    private Button btnCrearProducto;
    private Button btnGuardarProducto;
    private Button btnSalirProducto;

    private Label mensajeLabel;

    private Producto producto;
    private List<Producto> productos;

    public ProductoView(List<Producto> productos) {
        super("Producto");

        this.productos = productos;
        producto = new Producto("", "", 0, 0);

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));

        txtIdProducto = new TextField(20);
        txtNombreProducto = new TextField(20);
        txtPrecioUnidad = new TextField(20);
        txtCantidadStock = new TextField(20);

        btnCrearProducto = new Button("Crear Producto");
        btnGuardarProducto = new Button("Guardar Producto");
        btnSalirProducto = new Button("Salir");

        mensajeLabel = new Label("");

        panel.add(new Label("ID Producto:"));
        panel.add(txtIdProducto);
        panel.add(new Label("Nombre Producto:"));
        panel.add(txtNombreProducto);
        panel.add(new Label("Precio Unidad:"));
        panel.add(txtPrecioUnidad);
        panel.add(new Label("Cantidad en Stock:"));
        panel.add(txtCantidadStock);

        panel.add(btnCrearProducto);
        panel.add(btnGuardarProducto);
        panel.add(new Label(""));
        panel.add(btnSalirProducto);

        panel.add(new Label(""));
        panel.add(mensajeLabel);

        btnCrearProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdProducto.getText();
                String nombre = txtNombreProducto.getText();
                String precioStr = txtPrecioUnidad.getText();
                String cantidadStr = txtCantidadStock.getText();

                if (id.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
                    mensajeLabel.setText("Complete todos los campos.");
                    return;
                }
                if (!esNumero(precioStr) || !esEntero(cantidadStr)) {
                    mensajeLabel.setText("Precio debe ser número y cantidad entero.");
                    return;
                }

                double precio = Double.parseDouble(precioStr);
                int cantidad = Integer.parseInt(cantidadStr);

                producto.setIdProducto(id);
                producto.setNombreDeProducto(nombre);
                producto.setPrecioUnidad(precio);
                producto.setCantidadEnStock(cantidad);

                mensajeLabel.setText("Producto creado.");
            }
        });

        btnGuardarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productos.add(producto);
                mensajeLabel.setText("Producto guardado.");
            }
        });

        btnSalirProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(panel);
        setSize(450, 350);
        setVisible(true);
    }

    private boolean esNumero(String texto) {
        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c) && c != '.') return false;
        }
        return true;
    }

    private boolean esEntero(String texto) {
        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
