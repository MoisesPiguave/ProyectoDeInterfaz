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
    private TextField txtStockProducto;

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

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));

        txtCodigoProveedor = new TextField(20);
        txtNombreProveedor = new TextField(20);
        txtCodigoProducto = new TextField(20);
        txtNombreProducto = new TextField(20);
        txtPrecioProducto = new TextField(20);
        txtStockProducto = new TextField(20);

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
        panel.add(new Label(""));

        // Campos producto
        panel.add(new Label("Código Producto:"));
        panel.add(txtCodigoProducto);
        panel.add(new Label("Nombre Producto:"));
        panel.add(txtNombreProducto);
        panel.add(new Label("Precio Producto:"));
        panel.add(txtPrecioProducto);
        panel.add(new Label("Stock Disponible:"));
        panel.add(txtStockProducto);
        panel.add(btnAñadirProducto);
        panel.add(new Label(""));

        // Botones finales
        panel.add(btnGuardarProveedor);
        panel.add(btnSalirProveedor);

        panel.add(new Label(""));
        panel.add(mensajeLabel);

        // Validaciones para nombres (solo letras y espacios)
        final String regexNombres = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";

        // Crear proveedor
        btnCrearProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = txtCodigoProveedor.getText().trim();
                String nombre = txtNombreProveedor.getText().trim();

                if (codigo.isEmpty() || nombre.isEmpty()) {
                    mensajeLabel.setText("Debe ingresar código y nombre del proveedor.");
                    return;
                }

                if (!nombre.matches(regexNombres)) {
                    mensajeLabel.setText("Nombre del proveedor solo puede contener letras y espacios.");
                    return;
                }

                proveedor = new Proveedor();
                proveedor.setCedula(codigo);
                proveedor.setNombre(nombre);
                mensajeLabel.setText("Proveedor creado.");
            }
        });

        // Añadir producto
        btnAñadirProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveedor == null || proveedor.getCedula() == null || proveedor.getNombre() == null) {
                    mensajeLabel.setText("Debe crear un proveedor antes de añadir productos.");
                    return;
                }

                String codigo = txtCodigoProducto.getText().trim();
                String nombre = txtNombreProducto.getText().trim();
                String precioTexto = txtPrecioProducto.getText().trim();
                String stockTexto = txtStockProducto.getText().trim();

                if (codigo.isEmpty() || nombre.isEmpty() || precioTexto.isEmpty() || stockTexto.isEmpty()) {
                    mensajeLabel.setText("Debe completar todos los campos del producto.");
                    return;
                }

                if (!nombre.matches(regexNombres)) {
                    mensajeLabel.setText("Nombre del producto solo puede contener letras y espacios.");
                    return;
                }

                try {
                    double precio = Double.parseDouble(precioTexto);
                    if (precio < 0) {
                        mensajeLabel.setText("El precio debe ser un número positivo.");
                        return;
                    }
                    int stock = Integer.parseInt(stockTexto);
                    if (stock < 0) {
                        mensajeLabel.setText("El stock debe ser un número positivo.");
                        return;
                    }

                    Producto producto = new Producto(codigo, nombre, precio, stock);
                    proveedor.agregarProducto(producto);
                    mensajeLabel.setText("Producto añadido.");

                    // Limpiar campos de producto
                    txtCodigoProducto.setText("");
                    txtNombreProducto.setText("");
                    txtPrecioProducto.setText("");
                    txtStockProducto.setText("");

                } catch (NumberFormatException ex) {
                    mensajeLabel.setText("El precio debe ser decimal y el stock entero válidos.");
                }
            }
        });

        // Guardar proveedor
        btnGuardarProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveedor == null || proveedor.getCedula() == null || proveedor.getNombre() == null) {
                    mensajeLabel.setText("Debe crear un proveedor antes de guardar.");
                    return;
                }

                proveedores.add(proveedor);
                mensajeLabel.setText("Proveedor guardado con " + proveedor.getProductos().size() + " producto(s).");

                // Limpiar todo
                txtCodigoProveedor.setText("");
                txtNombreProveedor.setText("");
                txtCodigoProducto.setText("");
                txtNombreProducto.setText("");
                txtPrecioProducto.setText("");
                txtStockProducto.setText("");
                proveedor = null;
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
        setSize(500, 450);
        setVisible(true);
    }
}

