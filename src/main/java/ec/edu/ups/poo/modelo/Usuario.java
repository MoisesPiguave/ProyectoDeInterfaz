package ec.edu.ups.poo.modelo;

public class Usuario extends Persona {
    private Rol rol;
    private String departamentoDeTrabajo;

    public Usuario() {
        super("", "", "", "");
        this.rol = null;
        this.departamentoDeTrabajo = "";
    }

    public Usuario(String cedula, String nombre, String apellido, String telefono, Rol rol, String departamento) {
        super(cedula, nombre, apellido, telefono);
        this.rol = rol;
        this.departamentoDeTrabajo = departamento;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getDepartamento() {
        return departamentoDeTrabajo;
    }

    @Override
    public String toString() {
        return super.toString() + ", Rol: " + rol + ", Departamento: " + departamentoDeTrabajo;
    }
}
