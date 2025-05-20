package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuscarUsuarioView extends Frame {
    private Button btnSalirBuscarUsuario;

    private TextField txtCedulaBusqueda;
    private Button btnBuscar;
    private Label resultadoLabel;

    private List<Usuario> usuarios;

    public BuscarUsuarioView(List<Usuario> usuarios) {
        super("Buscar Usuario");
        btnSalirBuscarUsuario = new Button("Salir");

        this.usuarios = usuarios;

        Panel mmpa = new Panel();
        mmpa.setLayout(new GridLayout(3, 2, 10, 10));

        mmpa.add(new Label("Ingrese Cédula:"));
        txtCedulaBusqueda = new TextField(20);
        mmpa.add(txtCedulaBusqueda);

        btnBuscar = new Button("Buscar");
        mmpa.add(btnBuscar);

        resultadoLabel = new Label("");
        mmpa.add(resultadoLabel);


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

        setLayout(new BorderLayout());

        btnSalirBuscarUsuario = new Button("Salir");
        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnSalirBuscarUsuario);
        add(panelBoton, BorderLayout.SOUTH);



        btnSalirBuscarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(mmpa);



        setSize(1000, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}


