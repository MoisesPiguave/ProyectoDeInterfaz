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
        mainPanel.setLayout(new GridLayout(0, 1, 0, 8)); // espacio vertical entre filas


        Panel alto = new Panel(new FlowLayout(FlowLayout.LEFT, 40, 5));
        alto.add(new Label("ID"));
        alto.add(new Label("Nombre"));
        alto.add(new Label("Teléfono"));
        mainPanel.add(alto);

        int id = 1;
        for (Proveedor proveedor : proveedores) {
            Panel row = new Panel(new FlowLayout(FlowLayout.LEFT, 40, 5));
            row.add(new Label(String.valueOf(id++)));
            row.add(new Label(proveedor.getNombre()));
            row.add(new Label(proveedor.getTelefono()));
            mainPanel.add(row);
        }
        setLayout(new BorderLayout());

        btnSalirListProovedor = new Button("Salir");
        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnSalirListProovedor);
        add(panelBoton, BorderLayout.SOUTH);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(mainPanel);

        add(scrollPane);

        btnSalirListProovedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

