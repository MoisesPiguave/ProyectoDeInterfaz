package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SolicitudDeCompraView extends Frame {

    private Choice choiceUsuarios;
    private TextArea txtInformacion;
    private Choice choiceProductos;
    private TextField txtCantidad;
    private Button btnCrearSolicitud;
    private Button btnAgregarProducto;
    private Button btnMostrarResumen;
    private Button btnSalir;
    private Label mensajeLabel;

    private SolicitudDeCompra solicitud;
    private List<Usuario> usuarios;
    private List<Producto> productos;

    public SolicitudDeCompraView(List<Usuario> usuarios, List<Producto> productos, List<SolicitudDeCompra> solicitudes) {
        super("Solicitud de Compra");

        this.usuarios = usuarios;
        this.productos = productos;

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));

        choiceUsuarios = new Choice();
        for (Usuario u : usuarios) {
            choiceUsuarios.add(u.getCedula() + " - " + u.getNombre());
        }

        txtInformacion = new TextArea(3, 20);

        choiceProductos = new Choice();
        for (Producto p : productos) {
            choiceProductos.add(p.getIdProducto() + " - " + p.getNombreDeProducto());
        }

        txtCantidad = new TextField(10);

        btnCrearSolicitud = new Button("Crear Solicitud");
        btnAgregarProducto = new Button("Agregar Producto");
        btnMostrarResumen = new Button("Mostrar Resumen");
        btnSalir = new Button("Salir");

        mensajeLabel = new Label("");

        panel.add(new Label("Usuario:"));
        panel.add(choiceUsuarios);

        panel.add(new Label("Información de compra:"));
        panel.add(txtInformacion);

        panel.add(btnCrearSolicitud);
        panel.add(new Label(""));

        panel.add(new Label("Producto:"));
        panel.add(choiceProductos);

        panel.add(new Label("Cantidad:"));
        panel.add(txtCantidad);

        panel.add(btnAgregarProducto);
        panel.add(new Label(""));

        panel.add(btnMostrarResumen);
        panel.add(btnSalir);

        panel.add(new Label(""));
        panel.add(mensajeLabel);

        btnCrearSolicitud.addActionListener((ActionEvent e) -> {
            int selectedIndex = choiceUsuarios.getSelectedIndex();
            String info = txtInformacion.getText().trim();

            if (selectedIndex < 0 || info.isEmpty()) {
                mensajeLabel.setText("Complete los campos para crear la solicitud.");
                return;
            }

            Usuario usuario = usuarios.get(selectedIndex);
            solicitud = new SolicitudDeCompra(usuario, info);
            mensajeLabel.setText("Solicitud creada.");
        });

        btnAgregarProducto.addActionListener((ActionEvent e) -> {
            if (solicitud == null) {
                mensajeLabel.setText("Primero cree una solicitud.");
                return;
            }

            int selectedIndex = choiceProductos.getSelectedIndex();
            String cantidadTexto = txtCantidad.getText().trim();

            if (selectedIndex < 0 || cantidadTexto.isEmpty()) {
                mensajeLabel.setText("Seleccione un producto y cantidad.");
                return;
            }

            boolean esNumero = cantidadTexto.chars().allMatch(Character::isDigit);
            if (!esNumero) {
                mensajeLabel.setText("Cantidad inválida.");
                return;
            }

            int cantidad = Integer.parseInt(cantidadTexto);
            if (cantidad <= 0) {
                mensajeLabel.setText("Cantidad debe ser mayor a cero.");
                return;
            }

            Producto producto = productos.get(selectedIndex);
            solicitud.agregarItem(producto, cantidad);
            mensajeLabel.setText("Producto agregado.");
            txtCantidad.setText("");
        });

        btnMostrarResumen.addActionListener((ActionEvent e) -> {
            if (solicitud == null) {
                mensajeLabel.setText("No hay solicitud creada.");
                return;
            }
            solicitud.mostrarResumen();
        });

        btnSalir.addActionListener(e -> dispose());

        add(panel);
        setSize(520, 480);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}


