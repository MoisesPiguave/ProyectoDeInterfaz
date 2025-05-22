package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.SolicitudDeCompra;
import ec.edu.ups.poo.modelo.Producto;

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


        Panel arriba = new Panel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        arriba.add(new Label("Usuario"));
        arriba.add(new Label("Productos"));
        mainPanel.add(arriba);


        for (SolicitudDeCompra solicitud : solicitudes) {
            Panel solicitudPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 15, 5));

            String usuario = solicitud.getUsuario().getNombre();
            solicitudPanel.add(new Label(usuario));

            String productosTexto = "";
            List<Producto> productos = solicitud.getProductos();
            List<Integer> cantidades = solicitud.getCantidades();
            for (int i = 0; i < productos.size(); i++) {
                productosTexto += productos.get(i).getNombreDeProducto() + " x" + cantidades.get(i);
                if (i < productos.size() - 1) {
                    productosTexto += "; ";
                }
            }

            solicitudPanel.add(new Label(productosTexto));
            mainPanel.add(solicitudPanel);
        }


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(mainPanel);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);


        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnSalirListSolicitudes);
        add(panelBoton, BorderLayout.SOUTH);

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



