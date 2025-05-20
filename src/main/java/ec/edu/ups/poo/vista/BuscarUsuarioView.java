package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuscarUsuarioView extends Frame {

    private TextField txtCedulaBusqueda;
    private Button btnBuscar;
    private Label resultadoLabel;

    private List<Usuario> usuarios;

    public BuscarUsuarioView(List<Usuario> usuarios) {
        super("Buscar Usuario");

        this.usuarios = usuarios;

        setLayout(new GridLayout(3, 2, 10, 10));

        add(new Label("Ingrese Cédula:"));
        txtCedulaBusqueda = new TextField(20);
        add(txtCedulaBusqueda);

        btnBuscar = new Button("Buscar");
        add(btnBuscar);

        resultadoLabel = new Label("");
        add(resultadoLabel);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedulaBusqueda.getText().trim();
                if (cedula.isEmpty()) {
                    resultadoLabel.setText("Ingrese una cédula para buscar.");
                    return;
                }

                Usuario encontrado = null;
                for (Usuario u : usuarios) {
                    if (u.getCedula().equals(cedula)) {
                        encontrado = u;
                        break;
                    }
                }

                if (encontrado != null) {
                    resultadoLabel.setText("Usuario: " + encontrado.getNombre() + " " + encontrado.getApellido() +
                            ", Rol: " + encontrado.getRol() +
                            ", Departamento: " + encontrado.getDepartamento());
                } else {
                    resultadoLabel.setText("Usuario no encontrado.");
                }
            }
        });

        setSize(400, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}


