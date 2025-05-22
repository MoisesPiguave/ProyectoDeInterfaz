package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.SolicitudDeCompra;
import ec.edu.ups.poo.modelo.Producto;

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
        add(detallesArea, BorderLayout.SOUTH);
        add(panelBoton, BorderLayout.AFTER_LAST_LINE);

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
        String nombre = txtNombreBusqueda.getText().trim();

        if (nombre.isEmpty()) {
            resultadoLabel.setText("Por favor ingrese un nombre de usuario.");
            detallesArea.setText("");
            return;
        }

        SolicitudDeCompra encontrada = null;
        for (SolicitudDeCompra s : solicitudes) {
            if (s.getUsuario().getNombre().equalsIgnoreCase(nombre)) {
                encontrada = s;
                break;
            }
        }

        if (encontrada == null) {
            resultadoLabel.setText("No se encontró la solicitud para el usuario: " + nombre);
            detallesArea.setText("");
            return;
        }

        resultadoLabel.setText("Solicitud encontrada para: " + encontrada.getUsuario().getNombre());

        StringBuilder texto = new StringBuilder();
        texto.append("Usuario: " + encontrada.getUsuario().getNombre() + " " + encontrada.getUsuario().getApellido() + "\n");
        texto.append("Cédula: " + encontrada.getUsuario().getCedula() + "\n");
        texto.append("Departamento: " + encontrada.getUsuario().getDepartamento() + "\n");
        texto.append("Estado de la solicitud: " + encontrada.getEstado() + "\n\n");
        texto.append("Productos solicitados:\n");

        List<Producto> productos = encontrada.getProductos();
        List<Integer> cantidades = encontrada.getCantidades();

        double total = 0;

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            int cantidad = cantidades.get(i);
            double subtotal = p.getPrecioUnidad() * cantidad;
            total += subtotal;

            texto.append("- Producto: " + p.getNombreDeProducto() + "\n");
            texto.append("  Cantidad: " + cantidad + "\n");
            texto.append("  Precio unitario: $" + p.getPrecioUnidad() + "\n");
            texto.append("  Subtotal: $" + subtotal + "\n");
        }

        texto.append("TOTAL A PAGAR: $" + total);

        detallesArea.setText(texto.toString());
    }
}
