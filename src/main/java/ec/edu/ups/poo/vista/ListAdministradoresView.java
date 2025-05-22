package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Usuario;
import ec.edu.ups.poo.modelo.Rol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListAdministradoresView extends Frame {
    private TextArea administradoresArea;
    private Button btnSalir;

    public ListAdministradoresView(List<Usuario> usuarios) {
        super("Lista de Administradores");

        setLayout(new BorderLayout());

        administradoresArea = new TextArea();
        administradoresArea.setEditable(false);
        add(administradoresArea, BorderLayout.CENTER);

        btnSalir = new Button("Salir");
        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnSalir);
        add(panelBoton, BorderLayout.SOUTH);

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        mostrarAdministradores(usuarios);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void mostrarAdministradores(List<Usuario> usuarios) {
        String texto = "";
        for (Usuario u : usuarios) {
            if (u.getRol() == Rol.ADMINISTRADOR) {
                texto += "Cédula: " + u.getCedula() + "\n" +
                        "Nombre: " + u.getNombre() + " " + u.getApellido() + "\n" +
                        "Departamento: " + u.getDepartamento() + "\n" +
                        "--------------------------\n";
            }
        }

        if (texto.isEmpty()) {
            administradoresArea.setText("No hay administradores registrados.");
        } else {
            administradoresArea.setText(texto);
        }
    }
}

