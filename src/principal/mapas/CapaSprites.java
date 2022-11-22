package principal.mapas;

public class CapaSprites extends CapaTiled{

    private long[] sprites;

    public CapaSprites(long ancho, long alto, long x, long y, long[] sprites) {
        super(ancho, alto, x, y);
        this.sprites = sprites;
    }

    public long[] getArraySprites() {
        return sprites;
    }
}
