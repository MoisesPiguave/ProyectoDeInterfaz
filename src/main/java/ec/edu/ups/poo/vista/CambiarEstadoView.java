package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Estado;
import ec.edu.ups.poo.modelo.SolicitudDeCompra;
import ec.edu.ups.poo.modelo.Usuario;
import ec.edu.ups.poo.modelo.Rol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Arrays;

public class CambiarEstadoView extends Frame {

    private List<SolicitudDeCompra> solicitudes;
    private Usuario usuarioActual;
    private java.awt.List listaSolicitudes;
    private Choice estadoChoice;
    private Button btnCambiarEstado, btnSalir;
    private Label mensajeLabel;
    private List<Estado> estadosDisponibles;

    public CambiarEstadoView(List<SolicitudDeCompra> solicitudes, Usuario usuarioActual) {
        super("Cambiar Estado");

        this.solicitudes = solicitudes;
        this.usuarioActual = usuarioActual;



        setLayout(new BorderLayout());

        listaSolicitudes = new java.awt.List();
        for (SolicitudDeCompra s : solicitudes) {
            listaSolicitudes.add(s.toString());
        }
        add(listaSolicitudes, BorderLayout.WEST);

        Panel panel = new Panel(new GridLayout(5,1));
        estadoChoice = new Choice();
        estadosDisponibles = Arrays.asList(Estado.values());
        for (Estado e : estadosDisponibles) {
            estadoChoice.add(e.name());
        }

        btnCambiarEstado = new Button("Cambiar Estado");
        btnSalir = new Button("Salir");
        mensajeLabel = new Label("", Label.CENTER);

        panel.add(new Label("Nuevo Estado:"));
        panel.add(estadoChoice);
        panel.add(btnCambiarEstado);
        panel.add(btnSalir);
        panel.add(mensajeLabel);
        add(panel, BorderLayout.CENTER);

        btnCambiarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idx = listaSolicitudes.getSelectedIndex();
                if (idx < 0) {
                    mensajeLabel.setText("Seleccione solicitud");
                    return;
                }
                int estadoIdx = estadoChoice.getSelectedIndex();
                Estado nuevo = estadosDisponibles.get(estadoIdx);
                solicitudes.get(idx).setEstado(nuevo);
                listaSolicitudes.replaceItem(solicitudes.get(idx).toString(), idx);
                mensajeLabel.setText("Estado cambiado");
            }
        });

        btnSalir.addActionListener(e -> dispose());

        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
