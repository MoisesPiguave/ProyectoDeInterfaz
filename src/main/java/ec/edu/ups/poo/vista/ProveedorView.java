package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.modelo.Proveedor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProveedorView extends Frame {

    private TextField txtCedulaProveedor;
    private TextField txtNombreProveedor;
    private TextField txtTelefonoProveedor;
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

        txtCedulaProveedor = new TextField(20);
        txtNombreProveedor = new TextField(20);
        txtTelefonoProveedor = new TextField(20);
        txtCodigoProducto = new TextField(20);
        txtNombreProducto = new TextField(20);
        txtPrecioProducto = new TextField(20);
        txtStockProducto = new TextField(20);

        btnCrearProveedor = new Button("Crear Proveedor");
        btnAñadirProducto = new Button("Añadir Producto");
        btnGuardarProveedor = new Button("Guardar Proveedor");
        btnSalirProveedor = new Button("Salir Proveedor");

        mensajeLabel = new Label("");

        panel.add(new Label("Cédula Proveedor:"));
        panel.add(txtCedulaProveedor);
        panel.add(new Label("Nombre Proveedor:"));
        panel.add(txtNombreProveedor);
        panel.add(new Label("Teléfono Proveedor:"));
        panel.add(txtTelefonoProveedor);
        panel.add(btnCrearProveedor);
        panel.add(new Label(""));

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

        panel.add(btnGuardarProveedor);
        panel.add(btnSalirProveedor);

        panel.add(new Label(""));
        panel.add(mensajeLabel);

        btnCrearProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedulaProveedor.getText();
                String nombre = txtNombreProveedor.getText();
                String telefono = txtTelefonoProveedor.getText();

                if (cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
                    mensajeLabel.setText("Debe ingresar cédula, nombre y teléfono del proveedor.");
                    return;
                }

                proveedor = new Proveedor();
                proveedor.setCedula(cedula);
                proveedor.setNombre(nombre);
                proveedor.setTelefono(telefono);
                mensajeLabel.setText("Proveedor creado.");
            }
        });

        btnAñadirProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveedor == null || proveedor.getCedula() == null || proveedor.getNombre() == null) {
                    mensajeLabel.setText("Debe crear un proveedor antes de añadir productos.");
                    return;
                }

                String codigo = txtCodigoProducto.getText();
                String nombre = txtNombreProducto.getText();
                String precioTexto = txtPrecioProducto.getText();
                String stockTexto = txtStockProducto.getText();

                if (codigo.isEmpty() || nombre.isEmpty() || precioTexto.isEmpty() || stockTexto.isEmpty()) {
                    mensajeLabel.setText("Complete todos los campos del producto.");
                    return;
                }

                double precio = Double.parseDouble(precioTexto);
                if (precio < 0) {
                    mensajeLabel.setText("El precio debe ser positivo.");
                    return;
                }
                int stock = Integer.parseInt(stockTexto);
                if (stock < 0) {
                    mensajeLabel.setText("El stock debe ser positivo.");
                    return;
                }

                Producto producto = new Producto(codigo, nombre, precio, stock);
                proveedor.agregarProducto(producto);
                mensajeLabel.setText("Producto añadido.");

                txtCodigoProducto.setText("");
                txtNombreProducto.setText("");
                txtPrecioProducto.setText("");
                txtStockProducto.setText("");
            }
        });

        btnGuardarProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveedor == null || proveedor.getCedula() == null || proveedor.getNombre() == null) {
                    mensajeLabel.setText("Debe crear un proveedor antes de guardar.");
                    return;
                }

                proveedores.add(proveedor);
                mensajeLabel.setText("Proveedor guardado con " + proveedor.getProductos().size() + " producto(s).");

                txtCedulaProveedor.setText("");
                txtNombreProveedor.setText("");
                txtTelefonoProveedor.setText("");
                txtCodigoProducto.setText("");
                txtNombreProducto.setText("");
                txtPrecioProducto.setText("");
                txtStockProducto.setText("");
                proveedor = null;
            }
        });

        btnSalirProveedor.addActionListener(e -> dispose());

        add(panel);
        setSize(500, 500);
        setVisible(true);
    }
}
