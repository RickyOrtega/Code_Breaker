package principal.inventario.prendas;

import java.awt.*;
import java.util.ArrayList;

public class OutfitOficina extends Prenda{
    public OutfitOficina(int id, String nombre, String descripcion, int aumentoVelocidadMin, int aumentoVelocidadMax) {
        super(id, nombre, descripcion, aumentoVelocidadMin, aumentoVelocidadMax);
    }

    public OutfitOficina(int id, String nombre, String descripcion, int cantidad, int aumentoVelocidadMin, int aumentoVelocidadMax) {
        super(id, nombre, descripcion, cantidad, aumentoVelocidadMin, aumentoVelocidadMax);
    }

    @Override
    protected ArrayList<Rectangle> getAumento() {
        return null;
    }
}