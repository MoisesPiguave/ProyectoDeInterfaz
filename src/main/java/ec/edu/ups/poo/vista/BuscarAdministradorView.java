package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Usuario;
import ec.edu.ups.poo.modelo.Rol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuscarAdministradorView extends Frame {
    private Button btnSalirBuscarAdministrador;
    private TextField txtCedulaBusqueda;
    private Button btnBuscar;
    private Label resultadoLabel;

    private List<Usuario> usuarios;

    public BuscarAdministradorView(List<Usuario> usuarios) {
        super("Buscar Administrador");
        this.usuarios = usuarios;

        Panel panelFormulario = new Panel(new GridLayout(3, 2, 10, 10));

        panelFormulario.add(new Label("Ingrese Cédula:"));
        txtCedulaBusqueda = new TextField(20);
        panelFormulario.add(txtCedulaBusqueda);

        btnBuscar = new Button("Buscar");
        panelFormulario.add(btnBuscar);

        resultadoLabel = new Label("");
        panelFormulario.add(resultadoLabel);

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
                    if (u.getCedula().equals(cedula) && u.getRol() == Rol.ADMINISTRADOR) {
                        encontrado = u;
                        break;
                    }
                }

                if (encontrado != null) {
                    resultadoLabel.setText("Administrador: " + encontrado.getNombre() + " " + encontrado.getApellido() +
                            ", Departamento: " + encontrado.getDepartamento());
                } else {
                    resultadoLabel.setText("Administrador no encontrado.");
                }
            }
        });

        setLayout(new BorderLayout());

        btnSalirBuscarAdministrador = new Button("Salir");
        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnSalirBuscarAdministrador);
        add(panelBoton, BorderLayout.SOUTH);

        btnSalirBuscarAdministrador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(panelFormulario);

        setSize(1000, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

