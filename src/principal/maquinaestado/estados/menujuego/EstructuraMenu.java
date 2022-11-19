package principal.maquinaestado.estados.menujuego;

import principal.Constantes;
import principal.herramientas.DibujoDebug_R;

import java.awt.*;

public class EstructuraMenu {
    private final Color CFONDO;
    private final Color CBANNER_SUPERIOR;
    private final Color CBANNER_LATERAL;
    private final Rectangle BANNER_SUPERIOR;
    private final Rectangle BANNER_LATERAL;
    private final Rectangle FONDO;

    private final int MARGEN_HORIZONTAL;
    private final int MARGEN_VERTICAL;
    private final int ANCHO_ETIQUETAS;
    private final int ALTO_ETIQUETAS;

    public EstructuraMenu(){
        CBANNER_SUPERIOR = new Color(100,0,0,255);
        CBANNER_LATERAL = new Color(0, 0, 0, 255);
        CFONDO = new Color(255, 255, 255, 200);

        BANNER_SUPERIOR = new Rectangle(0, 0, Constantes.ANCHO_JUEGO, 20);
        BANNER_LATERAL = new Rectangle(0, BANNER_SUPERIOR.height, 140, Constantes.ALTO_JUEGO-BANNER_SUPERIOR.height);
        FONDO = new Rectangle(BANNER_LATERAL.width, BANNER_SUPERIOR.height, Constantes.ANCHO_JUEGO-BANNER_LATERAL.width, Constantes.ALTO_JUEGO-BANNER_SUPERIOR.height);

        MARGEN_HORIZONTAL = 20;
        MARGEN_VERTICAL = 20;
        ANCHO_ETIQUETAS = 100;
        ALTO_ETIQUETAS = 20;
    }

    public void actualizar(){

    }

    public void dibujar(final Graphics g){
        DibujoDebug_R.dibujarRectangulo(g, BANNER_SUPERIOR, CBANNER_SUPERIOR, true);
        DibujoDebug_R.dibujarRectangulo(g, BANNER_LATERAL, CBANNER_LATERAL, true);
        DibujoDebug_R.dibujarRectangulo(g, FONDO, CFONDO, true);
    }

    public Color getCFONDO() {
        return CFONDO;
    }

    public Color getCBANNER_SUPERIOR() {
        return CBANNER_SUPERIOR;
    }

    public Color getCBANNER_LATERAL() {
        return CBANNER_LATERAL;
    }

    public Rectangle getBANNER_SUPERIOR() {
        return BANNER_SUPERIOR;
    }

    public Rectangle getBANNER_LATERAL() {
        return BANNER_LATERAL;
    }

    public Rectangle getFONDO() {
        return FONDO;
    }

    public int getMARGEN_HORIZONTAL() {
        return MARGEN_HORIZONTAL;
    }

    public int getMARGEN_VERTICAL() {
        return MARGEN_VERTICAL;
    }

    public int getANCHO_ETIQUETAS() {
        return ANCHO_ETIQUETAS;
    }

    public int getALTO_ETIQUETAS() {
        return ALTO_ETIQUETAS;
    }
}