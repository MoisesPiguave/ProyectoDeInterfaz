package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.modelo.Proveedor;
import ec.edu.ups.poo.vista.MenuView;

import java.util.ArrayList;
import java.util.List;

public class MainView {

    public static void main(String[] args) {
        List<Proveedor> proveedores = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();

        new MenuView(proveedores, productos);
    }
}


