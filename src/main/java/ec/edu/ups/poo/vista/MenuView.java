package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.modelo.Proveedor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuView extends Frame {

    private TextField txtUsuario;
    private TextField txtContrasena;
    private Button btnIniciarSesion;

    private Label mensajeLabel;

    private Panel loginPanel;
    private Panel menuPanel;

    private List<Proveedor> proveedores;
    private List<Producto> productos;

    public MenuView(List<Proveedor> proveedores, List<Producto> productos) {
        super("Sistema de Gestión");

        this.proveedores = proveedores;
        this.productos = productos;

        // Panel de login
        loginPanel = new Panel();
        loginPanel.setLayout(new GridLayout(3, 2, 5, 5));
        loginPanel.add(new Label("Usuario:"));
        txtUsuario = new TextField(20);
        loginPanel.add(txtUsuario);

        loginPanel.add(new Label("Contraseña:"));
        txtContrasena = new TextField(20);
        txtContrasena.setEchoChar('*');
        loginPanel.add(txtContrasena);

        btnIniciarSesion = new Button("Iniciar Sesión");
        loginPanel.add(new Label(""));
        loginPanel.add(btnIniciarSesion);

        mensajeLabel = new Label("");
        loginPanel.add(mensajeLabel);

        add(loginPanel);

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contra = txtContrasena.getText();

                if (usuario.isEmpty() || contra.isEmpty()) {
                    mensajeLabel.setText("Ingrese usuario y contraseña");
                    return;
                }

                // Para simplicidad, aceptamos cualquier usuario/contraseña
                mostrarMenu();
            }
        });

        setSize(400, 200);
        setVisible(true);
    }

    private void mostrarMenu() {
        remove(loginPanel);

        menuPanel = new Panel();
        menuPanel.setLayout(new GridLayout(0, 2, 10, 10));

        Label titulo = new Label("Sistema - Seleccione una opción");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        menuPanel.add(titulo);
        menuPanel.add(new Label(""));  // para balancear columnas

        // Botones para las opciones
        Button btnRegistrarUsuario = new Button("Registrar Usuario");
        Button btnRegistrarProveedor = new Button("Registrar Proveedor");
        Button btnRegistrarProducto = new Button("Registrar Producto");
        Button btnCrearSolicitudCompra = new Button("Crear Solicitud de Compra");
        Button btnListarProveedores = new Button("Listar Proveedores");
        Button btnListarProductos = new Button("Listar Productos");
        Button btnListarSolicitudes = new Button("Listar Solicitudes");
        Button btnBuscarUsuario = new Button("Buscar Usuario");
        Button btnBuscarProveedor = new Button("Buscar Proveedor");
        Button btnBuscarProducto = new Button("Buscar Producto");
        Button btnBuscarSolicitud = new Button("Buscar Solicitud");
        Button btnCambiarEstadoSolicitud = new Button("Cambiar Estado Solicitud");
        Button btnMostrarTotalSolicitud = new Button("Mostrar Total Solicitud");
        Button btnSalir = new Button("Salir");

        // Añadir botones al panel
        menuPanel.add(btnRegistrarUsuario);
        menuPanel.add(btnRegistrarProveedor);
        menuPanel.add(btnRegistrarProducto);
        menuPanel.add(btnCrearSolicitudCompra);
        menuPanel.add(btnListarProveedores);
        menuPanel.add(btnListarProductos);
        menuPanel.add(btnListarSolicitudes);
        menuPanel.add(btnBuscarUsuario);
        menuPanel.add(btnBuscarProveedor);
        menuPanel.add(btnBuscarProducto);
        menuPanel.add(btnBuscarSolicitud);
        menuPanel.add(btnCambiarEstadoSolicitud);
        menuPanel.add(btnMostrarTotalSolicitud);
        menuPanel.add(btnSalir);

        // Mensaje para mostrar estado
        Label estadoLabel = new Label("");
        menuPanel.add(estadoLabel);
        menuPanel.add(new Label(""));  // espacio para alinear

        add(menuPanel);
        validate();

        // Listeners botones

        btnRegistrarProveedor.addActionListener(e -> {
            new ProveedorView(proveedores);
            estadoLabel.setText("Abrió ventana Registrar Proveedor");
        });

        btnRegistrarProducto.addActionListener(e -> {
            new ProductoView(productos);
            estadoLabel.setText("Abrió ventana Registrar Producto");
        });


        btnRegistrarUsuario.addActionListener(e -> estadoLabel.setText("Funcionalidad Registrar Usuario no implementada"));
        btnCrearSolicitudCompra.addActionListener(e -> estadoLabel.setText("Funcionalidad Crear Solicitud no implementada"));
        btnListarProveedores.addActionListener(e -> estadoLabel.setText("Funcionalidad Listar Proveedores no implementada"));
        btnListarProductos.addActionListener(e -> estadoLabel.setText("Funcionalidad Listar Productos no implementada"));
        btnListarSolicitudes.addActionListener(e -> estadoLabel.setText("Funcionalidad Listar Solicitudes no implementada"));
        btnBuscarUsuario.addActionListener(e -> estadoLabel.setText("Funcionalidad Buscar Usuario no implementada"));
        btnBuscarProveedor.addActionListener(e -> estadoLabel.setText("Funcionalidad Buscar Proveedor no implementada"));
        btnBuscarProducto.addActionListener(e -> estadoLabel.setText("Funcionalidad Buscar Producto no implementada"));
        btnBuscarSolicitud.addActionListener(e -> estadoLabel.setText("Funcionalidad Buscar Solicitud no implementada"));
        btnCambiarEstadoSolicitud.addActionListener(e -> estadoLabel.setText("Funcionalidad Cambiar Estado Solicitud no implementada"));
        btnMostrarTotalSolicitud.addActionListener(e -> estadoLabel.setText("Funcionalidad Mostrar Total Solicitud no implementada"));

        btnSalir.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
    }
}
