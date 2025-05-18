// Producto.java
package ec.edu.ups.poo.modelo;

public class Producto {
    private String idProducto;
    private String nombreDeProducto;
    private double precioUnidad;
    private int cantidadEnStock;

    public Producto(String idProducto, String nombre, double precioUnidad, int cantidadDisponible) {
        this.idProducto = idProducto;
        this.nombreDeProducto = nombre;
        this.precioUnidad = precioUnidad;
        this.cantidadEnStock = cantidadDisponible;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreDeProducto() {
        return nombreDeProducto;
    }

    public void setNombreDeProducto(String nombreDeProducto) {
        this.nombreDeProducto = nombreDeProducto;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    @Override
    public String toString() {
        return nombreDeProducto + " (ID: " + idProducto + ", Precio: $" + precioUnidad + ", Stock: " + cantidadEnStock + ")";
    }
}

