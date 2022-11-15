package principal.interfazusuario;

import principal.Constantes;
import principal.entes.Jugador;
import principal.herramientas.DibujoDebug;

import java.awt.*;

public class MenuInferior {
    private Rectangle areaInventario;
    private Rectangle bordeAreaInventario;

    public MenuInferior(final Jugador jugador){

        int altoMenuItems = 64;

        areaInventario = new Rectangle(0, Constantes.ALTO_JUEGO - altoMenuItems, Constantes.ANCHO_JUEGO, altoMenuItems);
        bordeAreaInventario = new Rectangle(areaInventario.x, areaInventario.y-1, areaInventario.width, 1);
    }

    public void dibujar(final Graphics g, final Jugador jugador){
        dibujarAreaInventario(g);
        dibujarBarraVitalidad_R(g);
        dibujarBarraPoder_R(g);
        dibujarBarraResistencia_R(g, jugador.getResistencia());
        dibujarBarraExperiencia_R(g);
        dibujarRanurasObjetos(g);
    }

    public void dibujarAreaInventario(final Graphics g){
        DibujoDebug.dibujarRectangulo(g, bordeAreaInventario, Color.WHITE, true);
        DibujoDebug.dibujarRectangulo(g, areaInventario, new Color(200,50,50,150).darker(), true);
    }

    private void dibujarBarraVitalidad_R(final Graphics g){
        final int medidaVertical = 4;
        final int medidaHorizontal = 100;

        DibujoDebug.dibujarRectangulo(g, areaInventario.x + 35, areaInventario.y+medidaVertical, medidaHorizontal, medidaVertical*2, new Color(150,0,0,150).darker().darker().darker(), true);

        g.setColor(Color.WHITE);
        g.setFont(Constantes.FUENTE_GLOBAL);
        DibujoDebug.dibujarString(g, "VID", areaInventario.x + 8, areaInventario.y+medidaVertical*3);
        DibujoDebug.dibujarString(g, "3", medidaHorizontal + 45, areaInventario.y+medidaVertical*3);
    }

    private void dibujarBarraPoder_R(final Graphics g){
        final int medidaVertical = 4;
        final int medidaHorizontal = 100;

        DibujoDebug.dibujarRectangulo(g, areaInventario.x + 35, areaInventario.y+medidaVertical*4, medidaHorizontal, medidaVertical*2, new Color(0,132,168,150).darker().darker().darker(), true);

        g.setColor(Color.WHITE);
        DibujoDebug.dibujarString(g, "POW", areaInventario.x + 8, areaInventario.y+medidaVertical*6);
        DibujoDebug.dibujarString(g, "100", medidaHorizontal + 45, areaInventario.y+medidaVertical*6);
    }

    public void dibujarBarraResistencia_R(final Graphics g, final int resistencia){
        final int medidaVertical = 4;
        final int medidaHorizontal = 100;
        final int anchoActual = medidaHorizontal * resistencia / 600;

        DibujoDebug.dibujarRectangulo(g, areaInventario.x + 35, areaInventario.y+medidaVertical*7, medidaHorizontal, medidaVertical*2, new Color(0,150,0,150).darker().darker().darker(), true);

        DibujoDebug.dibujarRectangulo(g, areaInventario.x + 35, areaInventario.y+medidaVertical*7, anchoActual, medidaVertical, new Color(0,255,0), true);
        DibujoDebug.dibujarRectangulo(g, areaInventario.x + 35, areaInventario.y+medidaVertical*8, anchoActual, medidaVertical, new Color(0,150,0), true);

        g.setColor(Color.WHITE);
        DibujoDebug.dibujarString(g, "STA", areaInventario.x + 8, areaInventario.y+medidaVertical*9);
        DibujoDebug.dibujarString(g, "" + anchoActual, medidaHorizontal + 45, areaInventario.y+medidaVertical*9);
    }

    private void dibujarBarraExperiencia_R(final Graphics g){
        final int medidaVertical = 4;
        final int medidaHorizontal = 100;

        DibujoDebug.dibujarRectangulo(g, areaInventario.x + 35, areaInventario.y+medidaVertical*10, medidaHorizontal, medidaVertical*2, new Color(124,0,74,150).darker().darker().darker(), true);

        g.setColor(Color.WHITE);
        DibujoDebug.dibujarString(g, "EXP", areaInventario.x + 8, areaInventario.y+medidaVertical*12);
        DibujoDebug.dibujarString(g, "100", medidaHorizontal + 45, areaInventario.y+medidaVertical*12);
    }

    private void dibujarRanurasObjetos(final Graphics g){
        final int anchoRanura = 32;
        final int numeroRanuras = 10;
        final int espaciadoRanuras = 10;
        final int anchoTotal = anchoRanura * numeroRanuras + espaciadoRanuras * numeroRanuras;
        final int xInicial = Constantes .ANCHO_JUEGO - anchoTotal;
        final int anchoRanuraEspacio = anchoRanura + espaciadoRanuras;

        g.setColor(Color.WHITE);

        for(int i = 0; i < numeroRanuras; i++){
            int xActual = xInicial + anchoRanuraEspacio * i;
            Rectangle ranura = new Rectangle(xActual, areaInventario.y + 4, anchoRanura, anchoRanura);

            DibujoDebug.dibujarRectangulo(g, ranura, Color.WHITE, true);
            DibujoDebug.dibujarString(g,String.valueOf(i), xActual + 13, areaInventario.y + 54, Color.WHITE);
        }
    }
}