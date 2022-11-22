package principal.mapas;

import java.awt.*;

public class CapaColisiones extends CapaTiled{

    private Rectangle[] colisiones;

    public CapaColisiones(long ancho, long alto, long x, long y, Rectangle[] colisiones) {
        super(ancho, alto, x, y);
        this.colisiones = colisiones;
    }

    public Rectangle[] getArrayColisiones(){
        return colisiones;
    }
}
