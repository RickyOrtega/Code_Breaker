package principal.inventario.prendas;

import principal.Constantes;
import principal.inventario.ItemInventario;
import principal.sprites.Sprite;
import principal.sprites.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Prenda extends ItemInventario {

    private Random random;

    private SpriteSheet hojaRopa = new SpriteSheet(Constantes.RUTA_ROPA, Constantes.LADO_SPRITE, false);

    protected int aumentoVelocidadMin = 0;
    protected int aumentoVelocidadMax = 0;

    public Prenda(int id, String nombre, String descripcion, int aumentoVelocidadMin, int aumentoVelocidadMax) {
        super(id, nombre, descripcion);
        this.aumentoVelocidadMin = aumentoVelocidadMin;
        this.aumentoVelocidadMax = aumentoVelocidadMax;
    }

    public Prenda(int id, String nombre, String descripcion, int cantidad, int aumentoVelocidadMin, int aumentoVelocidadMax) {
        super(id, nombre, descripcion, cantidad);
        this.aumentoVelocidadMin = aumentoVelocidadMin;
        this.aumentoVelocidadMax = aumentoVelocidadMax;

    }

    protected abstract ArrayList<Rectangle> getAumento();

    @Override
    public Sprite getSprite() {
        return null;
    }

    protected int getAumentoVelocidad(){
        Random random = new Random();

        return random.nextInt(aumentoVelocidadMax - aumentoVelocidadMin) + aumentoVelocidadMin;
    }
}