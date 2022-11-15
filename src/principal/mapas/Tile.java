package principal.mapas;

import principal.sprites.Sprite;

import java.awt.*;

public class Tile {
    private final int id;
    private final Sprite sprite;
    private boolean solid;

    public Tile(final int id, final Sprite sprite) {
        this.id = id;
        this.sprite = sprite;
        this.solid = false;
    }

    public Tile(final int id, final Sprite sprite, final boolean solid) {
        this.id = id;
        this.sprite = sprite;
        this.solid = solid;
    }

    public int getId() {
        return id;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public Rectangle getLimit(final int x, final int y) {
        return new Rectangle(x, y, sprite.getWIDTH(), sprite.getHEIGHT());
    }
}