
package ec.edu.ups.poo;
import java.util.ArrayList;
import java.util.List;

public class SolicitudDeCompra extends InformacionDeSolicitud implements Calculable {
    private static int contador = 1;
    private Usuario usuario;
    private Estado estado;
    private List<ProductoSolicitado> items;

    public SolicitudDeCompra(Usuario usuario, String informacion) {
        this.id = contador++;
        this.usuario = usuario;
        this.informacionDeCompra = informacion;
        this.estado = Estado.SOLICITADA;
        this.items = new ArrayList<>();
    }

    @Override
    public double calcularSubTotal(ProductoSolicitado item) {
        return item.getProducto().getPrecioUnidad() * item.getCantidadSolicitada();
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (ProductoSolicitado item : items) {
            total += calcularSubTotal(item);
        }
        return total;
    }

    public void agregarItem(Producto producto, int cantidad) {
        if (cantidad > 0) {
            items.add(new ProductoSolicitado(producto, cantidad));
        }
    }

    public void cambiarEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public int getId() {
        return id;
    }

    public Estado getEstado() {
        return estado;
    }

    public List<ProductoSolicitado> getItems() {
        return items;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void mostrarResumen() {
        System.out.println("Solicitud #" + id);
        System.out.println("Usuario: " + usuario);
        System.out.println("Departamento: " + usuario.getDepartamento());
        System.out.println("Estado: " + estado);
        System.out.println("Detalle de la compra:");
        for (ProductoSolicitado item : items) {
            System.out.println(" - " + item);
        }
        System.out.println("Total: $" + calcularTotal());
    }
}


