package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.SolicitudDeCompra;
import ec.edu.ups.poo.modelo.Producto;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ListSolicitudesView extends Frame {
    private Button btnSalirListSolicitudes;

    public ListSolicitudesView(List<SolicitudDeCompra> solicitudes) {
        super("Listado de Solicitudes de Compra");

        btnSalirListSolicitudes = new Button("Salir");

        Panel mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(0, 1, 5, 5));

        Panel cabecera = new Panel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        cabecera.add(new Label("Usuario"));
        cabecera.add(new Label("Productos"));
        cabecera.add(new Label("Precio Total"));
        cabecera.add(new Label("Estado"));
        mainPanel.add(cabecera);

        for (SolicitudDeCompra solicitud : solicitudes) {
            Panel solicitudPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 15, 5));

            String usuario = solicitud.getUsuario().getNombre();
            solicitudPanel.add(new Label(usuario));

            StringBuilder productosTexto = new StringBuilder();
            List<Producto> productos = solicitud.getProductos();
            List<Integer> cantidades = solicitud.getCantidades();
            for (int i = 0; i < productos.size(); i++) {
                productosTexto.append(productos.get(i).getNombreDeProducto())
                        .append(" x").append(cantidades.get(i));
                if (i < productos.size() - 1) {
                    productosTexto.append("; ");
                }
            }
            solicitudPanel.add(new Label(productosTexto.toString()));

            double total = 0.0;
            for (int i = 0; i < productos.size(); i++) {
                total += productos.get(i).getPrecioUnidad() * cantidades.get(i);
            }
            solicitudPanel.add(new Label("$" + String.format("%.2f", total)));

            String estado = solicitud.getEstado().name();
            Label estadoLabel = new Label(estado);
            switch (estado) {
                case "APROBADA":
                    estadoLabel.setForeground(Color.GREEN);
                    break;
                case "RECHAZADA":
                    estadoLabel.setForeground(Color.RED);
                    break;
                default:
                    estadoLabel.setForeground(Color.BLUE);
            }
            solicitudPanel.add(estadoLabel);

            mainPanel.add(solicitudPanel);
        }


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(mainPanel);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnSalirListSolicitudes);
        add(panelBoton, BorderLayout.SOUTH);

        btnSalirListSolicitudes.addActionListener(e -> dispose());


        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}



