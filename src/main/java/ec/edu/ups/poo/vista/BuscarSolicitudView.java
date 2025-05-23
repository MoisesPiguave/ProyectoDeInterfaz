package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.SolicitudDeCompra;
import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.modelo.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuscarSolicitudView extends Frame {

    private TextField txtNombreBusqueda;
    private Button btnBuscar;
    private Label resultadoLabel;
    private TextArea detallesArea;
    private Button btnSalir;
    private List<SolicitudDeCompra> solicitudes;

    public BuscarSolicitudView(List<SolicitudDeCompra> solicitudes) {
        super("Buscar Solicitud de Compra");
        this.solicitudes = solicitudes;

        Panel panelSuperior = new Panel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelSuperior.add(new Label("Nombre de usuario:"));
        txtNombreBusqueda = new TextField(20);
        panelSuperior.add(txtNombreBusqueda);
        btnBuscar = new Button("Buscar");
        panelSuperior.add(btnBuscar);

        resultadoLabel = new Label(" ");
        resultadoLabel.setAlignment(Label.LEFT);

        detallesArea = new TextArea(15, 60);
        detallesArea.setEditable(false);
        detallesArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        detallesArea.setBackground(new Color(245, 245, 245));

        btnSalir = new Button("Salir");
        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnSalir);

        setLayout(new BorderLayout(10, 10));
        add(panelSuperior, BorderLayout.NORTH);
        add(resultadoLabel, BorderLayout.CENTER);
        add(detallesArea, BorderLayout.EAST);
        add(panelBoton, BorderLayout.SOUTH);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarSolicitud();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(750, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buscarSolicitud() {
        String nombre = txtNombreBusqueda.getText();

        if (nombre.isEmpty()) {
            resultadoLabel.setText("Por favor ingrese un nombre de usuario.");
            detallesArea.setText("");
            return;
        }

        List<SolicitudDeCompra> encontradas = new java.util.ArrayList<>();

        for (SolicitudDeCompra s : solicitudes) {
            if (s.getUsuario().getNombre().equalsIgnoreCase(nombre)) {
                encontradas.add(s);
            }
        }

        if (encontradas.isEmpty()) {
            resultadoLabel.setText("No se encontró la solicitud para el usuario: " + nombre);
            detallesArea.setText("");
            return;
        }

        resultadoLabel.setText("Se encontraron " + encontradas.size() + " solicitud(es) para: " + nombre);

        StringBuilder texto = new StringBuilder();

        for (int idx = 0; idx < encontradas.size(); idx++) {
            SolicitudDeCompra encontrada = encontradas.get(idx);
            Usuario u = encontrada.getUsuario();

            texto.append("SOLICITUD #").append(idx + 1).append("\n");
            texto.append("DATOS DEL USUARIO\n");
            texto.append("Cédula: ").append(u.getCedula()).append("\n");
            texto.append("Nombre: ").append(u.getNombre()).append(" ").append(u.getApellido()).append("\n");
            texto.append("Teléfono: ").append(u.getTelefono()).append("\n");
            texto.append("Departamento: ").append(u.getDepartamento()).append("\n");
            texto.append("Rol: ").append(u.getRol() != null ? u.getRol().name() : "No definido").append("\n");
            texto.append("Estado de la solicitud: ").append(encontrada.getEstado().name()).append("\n\n");

            texto.append("PRODUCTOS SOLICITADOS\n");
            List<Producto> productos = encontrada.getProductos();
            List<Integer> cantidades = encontrada.getCantidades();
            double total = 0;

            for (int i = 0; i < productos.size(); i++) {
                Producto p = productos.get(i);
                int cantidad = cantidades.get(i);
                double subtotal = p.getPrecioUnidad() * cantidad;
                total += subtotal;

                texto.append("- Producto: ").append(p.getNombreDeProducto()).append("\n");
                texto.append("  Cantidad: ").append(cantidad).append("\n");
                texto.append("  Precio unitario: $").append(p.getPrecioUnidad()).append("\n");
                texto.append("  Subtotal: $").append(subtotal).append("\n");
            }

            texto.append("TOTAL A PAGAR: $").append(total).append("\n");
            texto.append("\n---------------------------------------------\n\n");
        }

        detallesArea.setText(texto.toString());
    }
}
