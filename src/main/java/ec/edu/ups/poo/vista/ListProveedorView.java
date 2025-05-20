package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Proveedor;

import java.awt.*;
import java.util.List;

public class ListProveedorView extends Frame {

    public ListProveedorView(List<Proveedor> proveedores) {
        super("Lista de Proveedores");

        Panel mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(0, 1, 0, 8)); // espacio vertical entre filas

        // Cabecera con FlowLayout y espacio interno
        Panel header = new Panel(new FlowLayout(FlowLayout.LEFT, 40, 5));
        header.add(new Label("ID"));
        header.add(new Label("Nombre"));
        header.add(new Label("Teléfono"));
        mainPanel.add(header);

        int id = 1;
        for (Proveedor proveedor : proveedores) {
            Panel row = new Panel(new FlowLayout(FlowLayout.LEFT, 40, 5));
            row.add(new Label(String.valueOf(id++)));
            row.add(new Label(proveedor.getNombre()));
            row.add(new Label(proveedor.getTelefono()));
            mainPanel.add(row);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(mainPanel);

        add(scrollPane);

        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

