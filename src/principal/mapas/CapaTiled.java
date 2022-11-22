package principal.mapas;

public abstract class CapaTiled {
    protected long ancho;
    protected long alto;
    protected long x;
    protected long y;

    public CapaTiled(final long ancho, final long alto, final long x, final long y){
        this.ancho = ancho;
        this.alto = alto;
        this.x = x;
        this.y = y;
    }
}