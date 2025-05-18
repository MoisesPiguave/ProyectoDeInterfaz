
package ec.edu.ups.poo;

public class ProductoSolicitado extends InformacionDeSolicitud {
    private Producto producto;
    private int cantidadSolicitada;

    public ProductoSolicitado(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidadSolicitada = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public double calcularTotal() {
        return producto.getPrecioUnidad() * cantidadSolicitada;
    }

    @Override
    public String toString() {
        return producto.getNombreDeProducto() + " x" + cantidadSolicitada + " = $" + calcularTotal();
    }
}


