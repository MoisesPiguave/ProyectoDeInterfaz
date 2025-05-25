package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Usuario;
import ec.edu.ups.poo.modelo.Rol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UsuarioView extends Frame {

    private TextField txtCedula;
    private TextField txtNombre;
    private TextField txtApellido;
    private TextField txtTelefono;
    private Choice choiceRol; // ComboBox para roles
    private TextField txtDepartamento;

    private Button btnRegistrar;
    private Button btnSalir;

    private Label mensajeLabel;

    private List<Usuario> usuarios;

    public UsuarioView(List<Usuario> usuarios) {
        super("Registro de Usuario");

        this.usuarios = usuarios;

        Panel panel = new Panel();

        panel.setLayout(new GridLayout(0, 2, 15, 15));

        txtCedula = new TextField(20);
        txtNombre = new TextField(20);
        txtApellido = new TextField(20);
        txtTelefono = new TextField(20);
        choiceRol = new Choice();
        txtDepartamento = new TextField(20);

        for (Rol rol : Rol.values()) {
            choiceRol.add(rol.name());
        }

        btnRegistrar = new Button("Registrar");
        btnSalir = new Button("Salir");

        mensajeLabel = new Label("");

        panel.add(new Label("Cédula:"));
        panel.add(txtCedula);

        panel.add(new Label("Nombre:"));
        panel.add(txtNombre);

        panel.add(new Label("Apellido:"));
        panel.add(txtApellido);

        panel.add(new Label("Teléfono:"));
        panel.add(txtTelefono);


        panel.add(new Label(""));
        panel.add(new Label(""));

        panel.add(new Label("Rol:"));
        panel.add(choiceRol);

        panel.add(new Label("Departamento:"));
        panel.add(txtDepartamento);


        panel.add(new Label(""));
        panel.add(new Label(""));

        panel.add(btnRegistrar);
        panel.add(btnSalir);


        panel.add(new Label(""));
        panel.add(mensajeLabel);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedula.getText().trim();
                String nombre = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                String telefono = txtTelefono.getText().trim();
                String rolTexto = choiceRol.getSelectedItem();
                String departamento = txtDepartamento.getText().trim();

                if (cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()
                        || departamento.isEmpty()) {
                    mensajeLabel.setText("Debe llenar todos los campos.");
                    return;
                }

                
                boolean rolValido = false;
                for (Rol r : Rol.values()) {
                    if (r.name().equals(rolTexto)) {
                        rolValido = true;
                        break;
                    }
                }
                if (!rolValido) {
                    mensajeLabel.setText("Rol inválido.");
                    return;
                }

                Usuario nuevoUsuario = new Usuario(cedula, nombre, apellido, telefono, Rol.valueOf(rolTexto), departamento);
                usuarios.add(nuevoUsuario);
                mensajeLabel.setText("Usuario registrado correctamente.");


                txtCedula.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                txtTelefono.setText("");
                txtDepartamento.setText("");
                choiceRol.select(0);
            }
        });

        btnSalir.addActionListener(e -> dispose());

        add(panel);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}


