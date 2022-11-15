package principal.maquinaestado.estados.menujuego;

import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoDebug;

import java.awt.*;
import java.util.Random;

public abstract class SeccionMenu {
    protected final String nombreSeccion;
    protected final Rectangle etiquetaMenu;
    private final Random valorRGB = new Random();

    public SeccionMenu(final String nombreSeccion, final Rectangle etiquetaMenu) {
        this.nombreSeccion = nombreSeccion;
        this.etiquetaMenu = etiquetaMenu;
    }

    public abstract void actualizar();

    public abstract void dibujar(Graphics g, final SuperficieDibujo sd);

    public void dibujarEtiquetaInactiva(final Graphics g){
        DibujoDebug.dibujarRectangulo(g, etiquetaMenu, Color.WHITE, true);
        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 14, Color.BLACK);
    }

    public void dibujarEtiquetaInactivaResaltada(final Graphics g){
        DibujoDebug.dibujarRectangulo(g, etiquetaMenu, Color.WHITE, true);
        DibujoDebug.dibujarRectangulo(g, new Rectangle(etiquetaMenu.x + etiquetaMenu.width - 10, etiquetaMenu.y + 5, 5, etiquetaMenu.height - 10), new Color(100, 0, 0,255), true);
        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 14, Color.BLACK);
    }

    public void dibujarEtiquetaActivaResaltada(final Graphics g){
        dibujarEtiquetaActiva_R(g);
        DibujoDebug.dibujarRectangulo(g, new Rectangle(etiquetaMenu.x + etiquetaMenu.width - 10, etiquetaMenu.y + 5, 5, etiquetaMenu.height - 10), new Color(100, 0, 0,255), true);
    }

    public void dibujarEtiquetaActiva(final Graphics g){
        dibujarEtiquetaActiva_R(g);
    }
    private void dibujarEtiquetaActiva_R(final Graphics g) {

        int red = valorRGB.nextInt(0,255);

        DibujoDebug.dibujarRectangulo(g, etiquetaMenu, Color.WHITE, true);
        DibujoDebug.dibujarRectangulo(g, new Rectangle(etiquetaMenu.x,etiquetaMenu.y, 5, etiquetaMenu.height), new Color(red, 0, 0, 255), true);

        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 14, Color.BLACK);
    }
    public Rectangle getEtiquetaMenuReescalada() {

        final Rectangle etEsc = new Rectangle((int) (etiquetaMenu.x * Constantes.FACTOR_ESCALADO_X),
                                              (int) (etiquetaMenu.y * Constantes.FACTOR_ESCALADO_Y),
                                              (int) (etiquetaMenu.width * Constantes.FACTOR_ESCALADO_X),
                (int) (etiquetaMenu.height * Constantes.FACTOR_ESCALADO_Y));
        return etEsc;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public Rectangle getEtiquetaMenu() {
        return etiquetaMenu;
    }
}