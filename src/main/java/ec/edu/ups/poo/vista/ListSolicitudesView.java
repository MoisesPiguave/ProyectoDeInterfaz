package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.SolicitudDeCompra;
import ec.edu.ups.poo.modelo.ProductoSolicitado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListSolicitudesView extends Frame {
    private Button btnSalirListSolicitudes;

    public ListSolicitudesView(List<SolicitudDeCompra> solicitudes) {
        super("Listado de Solicitudes de Compra");
        btnSalirListSolicitudes = new Button("Salir");

        Panel mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(0, 1, 5, 5));

        Panel headerPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        headerPanel.add(new Label("ID"));
        headerPanel.add(new Label("Usuario"));
        headerPanel.add(new Label("Estado"));
        headerPanel.add(new Label("Items"));
        headerPanel.add(new Label("Total"));
        mainPanel.add(headerPanel);

        setLayout(new BorderLayout());

        btnSalirListSolicitudes = new Button("Salir");
        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnSalirListSolicitudes);
        add(panelBoton, BorderLayout.SOUTH);

        for (SolicitudDeCompra solicitud : solicitudes) {
            Panel solicitudPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 15, 5));

            solicitudPanel.add(new Label(String.valueOf(solicitud.getId())));
            solicitudPanel.add(new Label(solicitud.getUsuario().getNombre()));
            solicitudPanel.add(new Label(solicitud.getEstado().name()));

            String itemsDesc = "";
            for (ProductoSolicitado item : solicitud.getItems()) {
                itemsDesc += item.getProducto().getNombreDeProducto() + " x" + item.getCantidadSolicitada() + "; ";
            }
            solicitudPanel.add(new Label(itemsDesc));

            double total = solicitud.calcularTotal();
            solicitudPanel.add(new Label("$" + total));

            mainPanel.add(solicitudPanel);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(mainPanel);

        add(scrollPane);

        btnSalirListSolicitudes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(700, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}


