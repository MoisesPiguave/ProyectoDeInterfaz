package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Proveedor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListProveedorView extends Frame {
    private Button btnSalirListProovedor;

    public ListProveedorView(List<Proveedor> proveedores) {
        super("Lista de Proveedores");
        btnSalirListProovedor = new Button("Salir");

        Panel mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(0, 1, 0, 5));

        Font headerFont = new Font("SansSerif", Font.BOLD, 14);
        Font rowFont = new Font("Monospaced", Font.PLAIN, 12);

        Panel encabezado = new Panel(new GridLayout(1, 3));
        Label lblId = new Label("ID", Label.LEFT);
        Label lblNombre = new Label("Nombre", Label.LEFT);
        Label lblTelefono = new Label("Teléfono", Label.LEFT);
        lblId.setFont(headerFont);
        lblNombre.setFont(headerFont);
        lblTelefono.setFont(headerFont);
        encabezado.add(lblId);
        encabezado.add(lblNombre);
        encabezado.add(lblTelefono);
        mainPanel.add(encabezado);

        for (Proveedor proveedor : proveedores) {
            Panel fila = new Panel(new GridLayout(1, 3));
            Label lblCedula = new Label(proveedor.getCedula(), Label.LEFT);
            Label lblNombreProveedor = new Label(proveedor.getNombre(), Label.LEFT);
            Label lblTelefonoProveedor = new Label(proveedor.getTelefono(), Label.LEFT);
            lblCedula.setFont(rowFont);
            lblNombreProveedor.setFont(rowFont);
            lblTelefonoProveedor.setFont(rowFont);
            fila.add(lblCedula);
            fila.add(lblNombreProveedor);
            fila.add(lblTelefonoProveedor);
            mainPanel.add(fila);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(mainPanel);

        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnSalirListProovedor);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        btnSalirListProovedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
