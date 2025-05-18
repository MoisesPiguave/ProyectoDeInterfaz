package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.modelo.Proveedor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProveedorView extends Frame {

    private TextField txtCodigoProveedor;
    private TextField txtNombreProveedor;
    private TextField txtCodigoProducto;
    private TextField txtNombreProducto;
    private TextField txtPrecioProducto;

    private Button btnCrearProveedor;
    private Button btnAñadirProducto;
    private Button btnGuardarProveedor;
    private Button btnSalirProveedor;

    private Label mensajeLabel;

    private Proveedor proveedor;
    private List<Proveedor> proveedores;

    public ProveedorView(List<Proveedor> proveedores) {
        super("Proveedor");

        this.proveedores = proveedores;
        proveedor = new Proveedor();

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));

        txtCodigoProveedor = new TextField(20);
        txtNombreProveedor = new TextField(20);
        txtCodigoProducto = new TextField(20);
        txtNombreProducto = new TextField(20);
        txtPrecioProducto = new TextField(20);

        btnCrearProveedor = new Button("Crear Proveedor");
        btnAñadirProducto = new Button("Añadir Producto");
        btnGuardarProveedor = new Button("Guardar Proveedor");
        btnSalirProveedor = new Button("Salir Proveedor");

        mensajeLabel = new Label("");

        // Campos proveedor
        panel.add(new Label("Código Proveedor:"));
        panel.add(txtCodigoProveedor);
        panel.add(new Label("Nombre Proveedor:"));
        panel.add(txtNombreProveedor);
        panel.add(btnCrearProveedor);

        // Campos producto
        panel.add(new Label("Código Producto:"));
        panel.add(txtCodigoProducto);
        panel.add(new Label("Nombre Producto:"));
        panel.add(txtNombreProducto);
        panel.add(new Label("Precio Producto:"));
        panel.add(txtPrecioProducto);
        panel.add(btnAñadirProducto);

        // Botones finales
        panel.add(btnGuardarProveedor);
        panel.add(btnSalirProveedor);

        panel.add(new Label("")); // espacio
        panel.add(mensajeLabel);  // mensajes

        // Crear proveedor
        btnCrearProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = txtCodigoProveedor.getText();
                String nombre = txtNombreProveedor.getText();

                if (codigo.isEmpty() || nombre.isEmpty()) {
                    mensajeLabel.setText("Debe ingresar código y nombre del proveedor.");
                    return;
                }

                proveedor.setCedula(codigo);
                proveedor.setNombre(nombre);
                mensajeLabel.setText("Proveedor creado.");
            }
        });

        // Añadir producto
        btnAñadirProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = txtCodigoProducto.getText();
                String nombre = txtNombreProducto.getText();
                String precioTexto = txtPrecioProducto.getText();

                if (codigo.isEmpty() || nombre.isEmpty() || precioTexto.isEmpty()) {
                    mensajeLabel.setText("Debe completar todos los campos del producto.");
                    return;
                }
                double precio = Double.parseDouble(precioTexto);

                Producto producto = new Producto(codigo, nombre, precio, 0); // stock por defecto
                proveedor.agregarProducto(producto);
                mensajeLabel.setText("Producto añadido.");
            }
        });

        // Guardar proveedor
        btnGuardarProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proveedores.add(proveedor);
                mensajeLabel.setText("Proveedor guardado con " + proveedor.getProductos().size() + " producto(s).");
            }
        });

        // Salir
        btnSalirProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(panel);
        setSize(500, 400);
        setVisible(true);
    }

}

