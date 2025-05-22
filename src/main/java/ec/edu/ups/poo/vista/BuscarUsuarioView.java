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
        this.usuarios = usuarios;

        Panel formPanel = new Panel(new GridLayout(2, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setPreferredSize(new Dimension(400, 100));

        formPanel.add(new Label("Ingrese Cédula:"));
        txtCedulaBusqueda = new TextField(20);
        formPanel.add(txtCedulaBusqueda);

        btnBuscar = new Button("Buscar");
        formPanel.add(btnBuscar);

        resultadoLabel = new Label("");
        formPanel.add(resultadoLabel);

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

        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        btnSalirBuscarUsuario = new Button("Salir");
        panelBoton.add(btnSalirBuscarUsuario);

        btnSalirBuscarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        setSize(600, 180);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
