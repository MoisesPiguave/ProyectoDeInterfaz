package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuView extends Frame {

    private List<Proveedor> proveedores;
    private List<Usuario> usuarios;
    private List<SolicitudDeCompra> solicitudes;

    private Panel opcionPanel;
    private Panel loginPanel;
    private Panel menuPanel;

    private TextField txtUsuario;
    private TextField txtContrasena;
    private Button btnIniciarSesion;
    private Label mensajeLabelLogin;

    public MenuView(List<Proveedor> proveedores, List<Usuario> usuarios, List<SolicitudDeCompra> solicitudes) {
        super("Sistema de Gestión");

        this.proveedores = proveedores;
        this.usuarios = usuarios;
        this.solicitudes = solicitudes;

        opcionPanel = new Panel(new GridLayout(3, 1, 10, 20));
        Label preguntaLabel = new Label("Seleccione una opción", Label.CENTER);
        Button btnRegistrarUsuario = new Button("Registrar Usuario");
        Button btnIniciarSesionOpcion = new Button("Iniciar Sesión");

        opcionPanel.add(preguntaLabel);
        opcionPanel.add(btnRegistrarUsuario);
        opcionPanel.add(btnIniciarSesionOpcion);

        add(opcionPanel);

        btnRegistrarUsuario.addActionListener(e -> new UsuarioView(usuarios));
        btnIniciarSesionOpcion.addActionListener(e -> mostrarLogin());

        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void mostrarLogin() {
        remove(opcionPanel);

        loginPanel = new Panel(new BorderLayout(10, 15));

        Panel camposPanel = new Panel(new GridLayout(2, 2, 15, 15));
        camposPanel.add(new Label("Usuario:"));
        txtUsuario = new TextField(20);
        camposPanel.add(txtUsuario);

        camposPanel.add(new Label("Contraseña:"));
        txtContrasena = new TextField(20);
        txtContrasena.setEchoChar('*');
        camposPanel.add(txtContrasena);

        Panel botonPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnIniciarSesion = new Button("Iniciar Sesión");
        botonPanel.add(btnIniciarSesion);

        mensajeLabelLogin = new Label("", Label.CENTER);

        loginPanel.add(camposPanel, BorderLayout.NORTH);
        loginPanel.add(botonPanel, BorderLayout.CENTER);
        loginPanel.add(mensajeLabelLogin, BorderLayout.SOUTH);

        add(loginPanel);
        validate();

        btnIniciarSesion.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String contra = txtContrasena.getText();

            if (usuario.isEmpty() || contra.isEmpty()) {
                mensajeLabelLogin.setText("Ingrese usuario y contraseña");
                return;
            }

            mostrarMenu();
        });

        setSize(400, 220);
        setLocationRelativeTo(null);
        validate();
    }

    private void mostrarMenu() {
        remove(loginPanel);

        menuPanel = new Panel(new GridLayout(0, 2, 15, 15));

        Label titulo = new Label("Sistema - Seleccione una opción");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        menuPanel.add(titulo);
        menuPanel.add(new Label(""));

        Button btnRegistrarUsuario = new Button("Registrar Usuario");
        Button btnRegistrarProveedor = new Button("Registrar Proveedor");
        Button btnCrearSolicitudCompra = new Button("Crear Solicitud de Compra");
        Button btnListarProveedores = new Button("Listar Proveedores");
        Button btnListarProductos = new Button("Listar Productos");
        Button btnListarSolicitudes = new Button("Listar Solicitudes");
        Button btnBuscarUsuario = new Button("Buscar Usuario");
        Button btnBuscarProveedor = new Button("Buscar Proveedor");
        Button btnBuscarProducto = new Button("Buscar Producto");
        Button btnBuscarSolicitud = new Button("Buscar Solicitud");
        Button btnCambiarEstadoSolicitud = new Button("Cambiar Estado Solicitud");
        Button btnSalir = new Button("Salir");

        menuPanel.add(btnRegistrarUsuario);
        menuPanel.add(btnRegistrarProveedor);
        menuPanel.add(btnCrearSolicitudCompra);
        menuPanel.add(btnListarProveedores);
        menuPanel.add(btnListarProductos);
        menuPanel.add(btnListarSolicitudes);
        menuPanel.add(btnBuscarUsuario);
        menuPanel.add(btnBuscarProveedor);
        menuPanel.add(btnBuscarProducto);
        menuPanel.add(btnBuscarSolicitud);
        menuPanel.add(btnCambiarEstadoSolicitud);
        menuPanel.add(btnSalir);

        Label estadoLabel = new Label("");
        menuPanel.add(new Label(""));
        menuPanel.add(estadoLabel);

        add(menuPanel);
        validate();

        btnRegistrarProveedor.addActionListener(e -> {
            new ProveedorView(proveedores);
            estadoLabel.setText("Abrió ventana Registrar Proveedor");
        });

        btnRegistrarUsuario.addActionListener(e -> {
            new UsuarioView(usuarios);
            estadoLabel.setText("Abrió ventana Registrar Usuario");
        });

        btnCrearSolicitudCompra.addActionListener(e -> {
            List<Producto> productos = new ArrayList<>();
            for (Proveedor p : proveedores) {
                productos.addAll(p.getProductos());
            }
            new SolicitudDeCompraView(usuarios, productos, solicitudes);
            estadoLabel.setText("Abrió ventana Crear Solicitud de Compra");
        });

        btnListarProveedores.addActionListener(e -> {
            new ListProveedorView(proveedores);
            estadoLabel.setText("Mostrando lista de proveedores");
        });

        btnListarProductos.addActionListener(e -> {
            new ListProductoView(proveedores);
            estadoLabel.setText("Mostrando lista de productos");
        });

        btnListarSolicitudes.addActionListener(e -> {
            new ListSolicitudesView(solicitudes);
            estadoLabel.setText("Mostrando lista de solicitudes");
        });

        btnBuscarUsuario.addActionListener(e -> {
            new BuscarUsuarioView(usuarios);
            estadoLabel.setText("Abrió ventana Buscar Usuario");
        });

        btnBuscarProveedor.addActionListener(e -> {
            new BuscarProveedorView(proveedores);
            estadoLabel.setText("Abrió ventana Buscar Proveedor");
        });

        btnBuscarProducto.addActionListener(e -> {
            new BuscarProductoView(proveedores);
            estadoLabel.setText("Abrió ventana Buscar Producto");
        });

        btnBuscarSolicitud.addActionListener(e -> {
            new BuscarSolicitudView(solicitudes);
            estadoLabel.setText("Abrió ventana Buscar Solicitud");
        });

        btnCambiarEstadoSolicitud.addActionListener(e -> {
            Usuario usuarioAdmin = new Usuario();
            usuarioAdmin.setRol(Rol.ADMINISTRADOR);
            new CambiarEstadoView(solicitudes, usuarioAdmin);
            estadoLabel.setText("Abrió ventana Cambiar Estado Solicitud");
        });

        btnSalir.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        setSize(450, 400);
        setLocationRelativeTo(null);
        validate();
    }
}
