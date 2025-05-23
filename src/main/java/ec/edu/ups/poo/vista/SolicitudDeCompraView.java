package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.modelo.SolicitudDeCompra;
import ec.edu.ups.poo.modelo.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SolicitudDeCompraView extends Frame {

    private TextField fechaField;
    private Choice usuarioChoice;
    private List<Producto> productos;
    private List<Usuario> usuarios;
    private List<SolicitudDeCompra> solicitudes;

    private TextArea detalleArea;
    private Label estadoLabel;
    private Label totalLabel;
    private Button crearSolicitudButton;
    private Button salirButton;


    private java.util.List<TextField> cantidadFields;

    public SolicitudDeCompraView(List<Usuario> usuarios, List<Producto> productos, List<SolicitudDeCompra> solicitudes) {
        super("Crear Solicitud de Compra");

        this.usuarios = usuarios;
        this.productos = productos;
        this.solicitudes = solicitudes;

        setLayout(new BorderLayout(10, 10));

        Panel formPanel = new Panel(new GridLayout(0, 2, 10, 10));

        formPanel.add(new Label("Fecha (dd/mm/aaaa):"));
        fechaField = new TextField();
        formPanel.add(fechaField);

        formPanel.add(new Label("Seleccione Usuario:"));
        usuarioChoice = new Choice();
        for (Usuario usuario : usuarios) {
            usuarioChoice.add(usuario.getCedula() + " - " + usuario.getNombre());
        }
        formPanel.add(usuarioChoice);

        formPanel.add(new Label("Productos y Cantidades:"));
        Panel productosPanel = new Panel(new GridLayout(productos.size(), 2, 5, 5));
        cantidadFields = new java.util.ArrayList<>();

        for (Producto producto : productos) {
            productosPanel.add(new Label(producto.getIdProducto() + " - " + producto.getNombreDeProducto()));
            TextField cantidadField = new TextField("0");  // default 0
            cantidadFields.add(cantidadField);
            productosPanel.add(cantidadField);
        }
        formPanel.add(productosPanel);

        formPanel.add(new Label("Detalles:"));
        detalleArea = new TextArea(3, 20);
        formPanel.add(detalleArea);

        crearSolicitudButton = new Button("Crear Solicitud");
        salirButton = new Button("Salir");

        Panel botonesPanel = new Panel(new FlowLayout());
        botonesPanel.add(crearSolicitudButton);
        botonesPanel.add(salirButton);

        formPanel.add(new Label("")); // espacio vacío
        formPanel.add(botonesPanel);

        add(formPanel, BorderLayout.CENTER);

        Panel statusPanel = new Panel(new BorderLayout());
        estadoLabel = new Label("", Label.CENTER);
        totalLabel = new Label("Total: $0.00", Label.CENTER);
        estadoLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        statusPanel.add(estadoLabel, BorderLayout.NORTH);
        statusPanel.add(totalLabel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        crearSolicitudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearSolicitud();
            }
        });

        salirButton.addActionListener(e -> dispose());

        setSize(850, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void crearSolicitud() {
        String fechaTexto = fechaField.getText();
        String usuarioSeleccionado = usuarioChoice.getSelectedItem();
        String detalleTexto = detalleArea.getText();

        if (fechaTexto.isEmpty() || usuarioSeleccionado == null) {
            estadoLabel.setText("Debe completar todos los campos obligatorios.");
            totalLabel.setText("Total: $0.00");
            return;
        }

        String cedulaUsuario = usuarioSeleccionado.split(" - ")[0];
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getCedula().equals(cedulaUsuario)) {
                usuario = u;
                break;
            }
        }
        if (usuario == null) {
            estadoLabel.setText("Usuario no encontrado.");
            totalLabel.setText("Total: $0.00");
            return;
        }

        SolicitudDeCompra solicitud = new SolicitudDeCompra();
        solicitud.setUsuario(usuario);
        double total = 0;
        boolean hayProductoSeleccionado = false;

        for (int i = 0; i < productos.size(); i++) {
            String cantidadStr = cantidadFields.get(i).getText().trim();
            if (!cantidadStr.isEmpty()) {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad > 0) {
                        hayProductoSeleccionado = true;
                        Producto p = productos.get(i);
                        solicitud.agregarProducto(p, cantidad);
                        total += p.getPrecioUnidad() * cantidad;
                    }
                } catch (NumberFormatException ex) {
                    estadoLabel.setText("Cantidad inválida para producto: " + productos.get(i).getNombreDeProducto());
                    totalLabel.setText("Total: $0.00");
                    return;
                }
            }
        }

        if (!hayProductoSeleccionado) {
            estadoLabel.setText("Debe ingresar cantidad mayor a 0 en al menos un producto.");
            totalLabel.setText("Total: $0.00");
            return;
        }

        solicitudes.add(solicitud);
        estadoLabel.setText("Solicitud creada correctamente.");
        totalLabel.setText(String.format("Total: $%.2f", total));
    }
}
