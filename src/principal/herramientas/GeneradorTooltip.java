package principal.herramientas;

import principal.Constantes;
import principal.graficos.SuperficieDibujo;

import java.awt.*;

public class GeneradorTooltip {
    private static Point generarTooltip(final Point pi) {

        final int x = pi.x;
        final int y = pi.y;

        final Point centroCanvas = new Point(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y);
        final Point centroCanvasEscalado = new Point(EscaladorElementos.escalarPuntoSuperior(centroCanvas).x, EscaladorElementos.escalarPuntoSuperior(centroCanvas).y);

        final int margenCursor = 5;

        final Point pf = new Point();

        if(x <= centroCanvasEscalado.x){
            if ( y <= centroCanvasEscalado.y){
                pf.x = x + Constantes.LADO_CURSOR + margenCursor;
                pf.y = y + Constantes.LADO_CURSOR + margenCursor;
            } else {
                pf.x = x + Constantes.LADO_CURSOR + margenCursor;
                pf.y = y - Constantes.LADO_CURSOR - margenCursor;
            }
        } else {
            if ( y <= centroCanvasEscalado.y){
                pf.x = x - Constantes.LADO_CURSOR - margenCursor;
                pf.y = y + Constantes.LADO_CURSOR + margenCursor;
            } else {
                pf.x = x - Constantes.LADO_CURSOR - margenCursor;
                pf.y = y - Constantes.LADO_CURSOR - margenCursor;
            }
        }

        return pf;
    }

    private static String getPosicionTooltip(final Point pi){

        final int x = pi.x;
        final int y = pi.y;

        final Point centroCanvas = new Point(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y);
        final Point centroCanvasEscalado = new Point(EscaladorElementos.escalarPuntoSuperior(centroCanvas).x, EscaladorElementos.escalarPuntoSuperior(centroCanvas).y);

        String posicion = "";

        if(x <= centroCanvasEscalado.x){
            if ( y <= centroCanvasEscalado.y){
                posicion = "no";
            } else {
                posicion = "so";
            }
        } else {
            if ( y <= centroCanvasEscalado.y){
                posicion = "ne";
            } else {
                posicion = "se";
            }
        }

        return posicion;
    }

    public static void dibujarTooltip(final Graphics g, final SuperficieDibujo sd, final String texto){
        final Point posicionRaton = sd.getRaton().getPosicion();
        final Point posicionTooltip = GeneradorTooltip.generarTooltip(posicionRaton);
        final String pistaPosicion = GeneradorTooltip.getPosicionTooltip(posicionRaton);
        final Point posicionTooltipEscalada = EscaladorElementos.escalarPuntoInferior(posicionTooltip);

        final int anchoTooltip = MedidorString.medirAnchoPixeles(g, texto);
        final int altoTooltip = MedidorString.medirAltoPixeles(g, texto);
        final int margenFuente = 4;

        Rectangle tooltip = null;

        switch (pistaPosicion){
            case "no":
                tooltip = new Rectangle(posicionTooltipEscalada.x, posicionTooltipEscalada.y, anchoTooltip + margenFuente * 2, altoTooltip);
                break;
            case "ne":
                tooltip = new Rectangle(posicionTooltipEscalada.x - anchoTooltip, posicionTooltipEscalada.y, anchoTooltip + margenFuente * 2, altoTooltip);
                break;
            case "so":
                tooltip = new Rectangle(posicionTooltipEscalada.x, posicionTooltipEscalada.y - altoTooltip, anchoTooltip, altoTooltip);
                break;
            case "se":
                tooltip = new Rectangle(posicionTooltipEscalada.x - anchoTooltip, posicionTooltipEscalada.y - altoTooltip, anchoTooltip + margenFuente * 2, altoTooltip);
                break;
        }

        DibujoDebug.dibujarRectangulo(g, tooltip, Color.BLACK,true);
        DibujoDebug.dibujarString(g, texto, tooltip.x + margenFuente, tooltip.y + altoTooltip - margenFuente, Color.WHITE);
    }
}