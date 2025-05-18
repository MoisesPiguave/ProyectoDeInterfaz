
package ec.edu.ups.poo.modelo;
import java.util.ArrayList;
import java.util.List;
public class Proveedor extends Persona {
    private List<Producto> productos;

    public Proveedor(String cedula, String nombre, String apellido, String telefono) {
        super(cedula, nombre, apellido, telefono);
        this.productos = new ArrayList<>();
    }

    public Proveedor() {
        super();
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public String toString() {
        return super.toString() + ", Proveedor de " + productos.size() + " productos";
    }
}

