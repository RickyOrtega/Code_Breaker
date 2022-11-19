package principal.inventario.prendas;

import java.awt.*;
import java.util.ArrayList;

public class Predeterminado extends Prenda{
    public Predeterminado(int id, String nombre, String descripcion, int aumentoVelocidadMin, int aumentoVelocidadMax) {
        super(id, nombre, descripcion, aumentoVelocidadMin, aumentoVelocidadMax);
    }

    public Predeterminado(int id, String nombre, String descripcion, int cantidad, int aumentoVelocidadMin, int aumentoVelocidadMax) {
        super(id, nombre, descripcion, cantidad, aumentoVelocidadMin, aumentoVelocidadMax);
    }

    @Override
    protected ArrayList<Rectangle> getAumento() {
        return null;
    }
}