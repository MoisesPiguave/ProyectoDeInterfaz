package ec.edu.ups.poo.modelo;

import java.util.ArrayList;
import java.util.List;

public class SolicitudDeCompra {

    private Usuario usuario;
    private List<Producto> productos;
    private List<Integer> cantidades;
    private Estado estado;

    public SolicitudDeCompra() {
        productos = new ArrayList<>();
        cantidades = new ArrayList<>();
        this.estado = Estado.SOLICITADA; // Estado inicial
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        String resultado = "Solicitud de Compra para " + usuario.getNombre() + ":\n";
        for (int i = 0; i < productos.size(); i++) {
            resultado += "- " + productos.get(i).getNombreDeProducto() + " x " + cantidades.get(i) + "\n";
        }
        resultado += "Estado: " + (estado != null ? estado.name() : "N/A");
        return resultado;
    }
}
