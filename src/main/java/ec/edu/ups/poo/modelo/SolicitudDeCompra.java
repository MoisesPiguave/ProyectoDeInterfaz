package ec.edu.ups.poo.modelo;

import java.util.ArrayList;
import java.util.List;

public class SolicitudDeCompra {

    private Usuario usuario;
    private List<Producto> productos;
    private List<Integer> cantidades;

    public SolicitudDeCompra() {
        productos = new ArrayList<>();
        cantidades = new ArrayList<>();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        productos.add(producto);
        cantidades.add(cantidad);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Integer> getCantidades() {
        return cantidades;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Solicitud de Compra para ").append(usuario.getNombre()).append(":\n");
        for (int i = 0; i < productos.size(); i++) {
            sb.append("- ")
                    .append(productos.get(i).getNombreDeProducto())
                    .append(" x ").append(cantidades.get(i))
                    .append("\n");
        }
        return sb.toString();
    }
}
