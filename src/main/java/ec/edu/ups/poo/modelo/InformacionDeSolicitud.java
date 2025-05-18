// InformacionDeSolicitud.java
package ec.edu.ups.poo.modelo;

public abstract class InformacionDeSolicitud {
    protected int id;
    protected String informacionDeCompra;

    public abstract double calcularTotal();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformacionDeCompra() {
        return informacionDeCompra;
    }

    public void setInformacionDeCompra(String informacionDeCompra) {
        this.informacionDeCompra = informacionDeCompra;
    }
}

