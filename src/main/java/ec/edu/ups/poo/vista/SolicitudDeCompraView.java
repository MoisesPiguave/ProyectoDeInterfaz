package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.modelo.SolicitudDeCompra;
import ec.edu.ups.poo.modelo.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SolicitudDeCompraView extends Frame {

    private Choice usuarioChoice;
    private Choice productoChoice;
    private TextField cantidadField;
    private Button crearSolicitudButton;
    private Label estadoLabel;

    public SolicitudDeCompraView(List<Usuario> usuarios, List<Producto> productos, List<SolicitudDeCompra> solicitudes) {
        super("Crear Solicitud de Compra");

        setLayout(new GridLayout(5, 2, 10, 10));

        add(new Label("Seleccione Usuario:"));
        usuarioChoice = new Choice();
        for (Usuario usuario : usuarios) {
            usuarioChoice.add(usuario.getCedula() + " - " + usuario.getNombre());
        }
        add(usuarioChoice);

        add(new Label("Seleccione Producto:"));
        productoChoice = new Choice();
        for (Producto producto : productos) {
            productoChoice.add(producto.getIdProducto() + " - " + producto.getNombreDeProducto());
        }
        add(productoChoice);

        add(new Label("Cantidad:"));
        cantidadField = new TextField();
        add(cantidadField);

        crearSolicitudButton = new Button("Crear Solicitud");
        add(crearSolicitudButton);

        estadoLabel = new Label("");
        add(estadoLabel);

        crearSolicitudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioSeleccionado = usuarioChoice.getSelectedItem();
                String productoSeleccionado = productoChoice.getSelectedItem();
                String cantidadTexto = cantidadField.getText();

                if (usuarioSeleccionado == null || productoSeleccionado == null || cantidadTexto.isEmpty()) {
                    estadoLabel.setText("Debe completar todos los campos");
                    return;
                }

                try {
                    int cantidad = Integer.parseInt(cantidadTexto);
                    if (cantidad <= 0) {
                        estadoLabel.setText("Cantidad debe ser positiva");
                        return;
                    }

                    String cedulaUsuario = usuarioSeleccionado.split(" - ")[0];
                    String codigoProducto = productoSeleccionado.split(" - ")[0];

                    Usuario usuario = null;
                    for (Usuario u : usuarios) {
                        if (u.getCedula().equals(cedulaUsuario)) {
                            usuario = u;
                            break;
                        }
                    }

                    Producto producto = null;
                    for (Producto p : productos) {
                        if (p.getIdProducto().equals(codigoProducto)) {
                            producto = p;
                            break;
                        }
                    }

                    if (usuario == null || producto == null) {
                        estadoLabel.setText("Usuario o producto no encontrados");
                        return;
                    }

                    SolicitudDeCompra solicitud = new SolicitudDeCompra();
                    solicitud.setUsuario(usuario);
                    solicitud.agregarProducto(producto, cantidad);
                    solicitudes.add(solicitud);

                    estadoLabel.setText("Solicitud creada correctamente");

                } catch (NumberFormatException ex) {
                    estadoLabel.setText("Cantidad inválida");
                }
            }
        });

        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
