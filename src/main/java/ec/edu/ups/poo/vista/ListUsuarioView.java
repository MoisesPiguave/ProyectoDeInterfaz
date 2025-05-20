package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Usuario;

import java.awt.*;
import java.util.List;

public class ListUsuarioView extends Frame {

    public ListUsuarioView(List<Usuario> usuarios) {
        super("Lista de Usuarios");

        Panel mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(0, 1, 0, 6));

        Panel headerPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        headerPanel.add(new Label("Cédula"));
        headerPanel.add(new Label("Nombre"));
        headerPanel.add(new Label("Apellido"));
        headerPanel.add(new Label("Teléfono"));
        headerPanel.add(new Label("Rol"));
        headerPanel.add(new Label("Departamento"));
        mainPanel.add(headerPanel);

        for (Usuario usuario : usuarios) {
            Panel rowPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 20, 5));
            rowPanel.add(new Label(usuario.getCedula()));
            rowPanel.add(new Label(usuario.getNombre()));
            rowPanel.add(new Label(usuario.getApellido()));
            rowPanel.add(new Label(usuario.getTelefono()));
            rowPanel.add(new Label(usuario.getRol().name()));
            rowPanel.add(new Label(usuario.getDepartamento()));
            mainPanel.add(rowPanel);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(mainPanel);

        add(scrollPane);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}


