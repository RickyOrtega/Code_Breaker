package principal.inventario.trofeos;

import principal.Constantes;
import principal.inventario.ItemInventario;
import principal.sprites.Sprite;
import principal.sprites.SpriteSheet;

public class Trofeo extends ItemInventario {

    private SpriteSheet hojaTrofeos = new SpriteSheet(Constantes.RUTA_OBJETOS_INVENTARIO, Constantes.LADO_SPRITE, false);

    public Trofeo(int id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }

    public Trofeo(int id, String nombre, String descripcion, int cantidad) {
        super(id, nombre, descripcion, cantidad);
    }

    @Override
    public Sprite getSprite() {
        return hojaTrofeos.getSprite(id);
    }
}