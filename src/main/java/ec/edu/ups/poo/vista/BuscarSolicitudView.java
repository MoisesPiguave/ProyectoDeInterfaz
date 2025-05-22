package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.SolicitudDeCompra;
import ec.edu.ups.poo.modelo.ProductoSolicitado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuscarSolicitudView extends Frame {
    private TextField txtIdBusqueda;
    private Button btnBuscar;
    private Label resultadoLabel;
    private TextArea detallesArea;
    private Button btnSalir;

    private List<SolicitudDeCompra> solicitudes;

    public BuscarSolicitudView(List<SolicitudDeCompra> solicitudes) {
        super("Buscar Solicitud de Compra");
        this.solicitudes = solicitudes;

        Panel panelBusqueda = new Panel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBusqueda.add(new Label("Ingrese ID:"));
        txtIdBusqueda = new TextField(10);
        panelBusqueda.add(txtIdBusqueda);
        btnBuscar = new Button("Buscar");
        panelBusqueda.add(btnBuscar);

        resultadoLabel = new Label("");
        detallesArea = new TextArea(10, 50);
        detallesArea.setEditable(false);

        btnSalir = new Button("Salir");

        setLayout(new BorderLayout());
        add(panelBusqueda, BorderLayout.NORTH);
        add(resultadoLabel, BorderLayout.CENTER);
        add(detallesArea, BorderLayout.SOUTH);

        Panel panelBotonSalir = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonSalir.add(btnSalir);
        add(panelBotonSalir, BorderLayout.SOUTH);

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

        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buscarSolicitud() {
        String idTexto = txtIdBusqueda.getText().trim();
        if (idTexto.isEmpty()) {
            resultadoLabel.setText("Por favor ingrese un ID.");
            detallesArea.setText("");
            return;
        }

        long idBuscado = 0;
        boolean valido = true;
        for (int i = 0; i < idTexto.length(); i++) {
            char c = idTexto.charAt(i);
            if (c < '0' || c > '9') {
                valido = false;
                break;
            }
            idBuscado = idBuscado * 10 + (c - '0');
        }

        if (!valido) {
            resultadoLabel.setText("ID inválido, debe ser un número.");
            detallesArea.setText("");
            return;
        }

        SolicitudDeCompra encontrada = null;
        for (SolicitudDeCompra s : solicitudes) {
            if (s.getId() == idBuscado) {
                encontrada = s;
                break;
            }
        }

        if (encontrada == null) {
            resultadoLabel.setText("No se encontró la solicitud con ID: " + idBuscado);
            detallesArea.setText("");
            return;
        }

        resultadoLabel.setText("Solicitud ID: " + encontrada.getId() + ", Usuario: " + encontrada.getUsuario().getNombre() + ", Estado: " + encontrada.getEstado());

        String texto = "Items:\n";
        for (ProductoSolicitado item : encontrada.getItems()) {
            texto += "- " + item.getProducto().getNombreDeProducto() + " x" + item.getCantidadSolicitada() + "\n";
        }
        texto += "\nTotal: $" + encontrada.calcularTotal();

        detallesArea.setText(texto);
    }
}

