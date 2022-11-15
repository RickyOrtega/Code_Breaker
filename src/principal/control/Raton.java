package principal.control;

import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Raton extends MouseAdapter {
    private final Cursor cursor;
    private Point posicion;
    private boolean clic;

    public Raton(final SuperficieDibujo sd){
        Toolkit configuracion = Toolkit.getDefaultToolkit();
        BufferedImage imagen = CargadorRecursos.cargarImagenCompatibleTranslucida_R("/imagenes/icons/iconcursor.png");

        Constantes.LADO_CURSOR = imagen.getWidth();

        Point punta = new Point(0,0);
        this.cursor = configuracion.createCustomCursor(imagen, punta, "Cursor por defecto");

        posicion = new Point();
        actualizarPosicion(sd);

        clic = false;
    }

    public void actualizar(final SuperficieDibujo sd){
        actualizarPosicion(sd);
    }

    public Cursor getCursor(){
        return this.cursor;
    }

    public Point getPosicion(){
        return this.posicion;
    }

    public Rectangle getRectanguloPosicion(final int ancho, final int alto){
        return new Rectangle(posicion.x, posicion.y, ancho, alto);
    }

    public Rectangle getRectanguloPosicion(){
        return new Rectangle(posicion.x, posicion.y, 1, 1);
    }
    private void actualizarPosicion(final SuperficieDibujo sd){
        final Point posicionRaton = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(posicionRaton, sd);
        posicion.setLocation(posicionRaton.getX(), posicionRaton.getY());
    }

    public void mouseClicked(MouseEvent e){

        if(!clic){
            clic = true;
        }
    }

    public boolean getClic(){
        return this.clic;
    }

    public void resetClic(){
        if(clic){
            clic = false;
        }
    }
}