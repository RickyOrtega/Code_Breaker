package principal.herramientas;

import principal.Constantes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DibujoDebug {

    public static int objetosDibujados = 0;

    public static void dibujarImagen(final Graphics g, final BufferedImage imagen, final int posicionX, final int posicionY) {
        objetosDibujados++;
        g.drawImage(imagen, posicionX, posicionY, null);
    }

    public static void dibujarImagen(final Graphics g, final BufferedImage imagen, final Point p) {
        objetosDibujados++;
        g.drawImage(imagen, p.x, p.y, null);
    }

    public static void dibujarString(final Graphics g, final String texto, final int posicionX, final int posicionY) {
        objetosDibujados++;
        g.setFont(Constantes.FUENTE_GLOBAL);
        g.drawString(texto, posicionX, posicionY);
    }

    public static void dibujarString(final Graphics g, final String texto, final Point p) {
        objetosDibujados++;
        g.setFont(Constantes.FUENTE_GLOBAL);
        g.drawString(texto, p.x, p.y);
    }

    public static void dibujarString(final Graphics g, final String texto, final int posicionX, final int posicionY, final Color color) {
        objetosDibujados++;
        g.setColor(color);
        g.setFont(Constantes.FUENTE_GLOBAL);
        g.drawString(texto, posicionX, posicionY);
    }

    public static void dibujarString(final Graphics g, final String texto, final Point p, final Color color) {
        objetosDibujados++;
        g.setColor(color);
        g.setFont(Constantes.FUENTE_GLOBAL);
        g.drawString(texto, p.x, p.y);
    }

    public static void dibujarRectangulo(final Graphics g, final Rectangle r, final Color color, final boolean relleno) {
        objetosDibujados++;
        g.setColor(color);
        if(relleno){
            g.fillRect(r.x, r.y, r.width, r.height);
        }else{
            g.drawRect(r.x, r.y, r.width, r.height);
        }
    }

    public static void dibujarRectangulo(final Graphics g, final int x, final int y, final int ancho, final int alto, final Color color, final boolean relleno) {
        objetosDibujados++;
        g.setColor(color);
        if(relleno){
            g.fillRect(x, y, ancho, alto);
        }else{
            g.drawRect(x, y, ancho, alto);
        }
    }

    public static int getObjetosDibujados() {
        return objetosDibujados;
    }

    public static void reiniciarContadorObjetosDibujados(){
        objetosDibujados = 0;
    }
}