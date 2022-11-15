package principal.herramientas;

import principal.Constantes;

import java.awt.*;

public class EscaladorElementos {
    public static Rectangle escalarRectanguloSuperior(final Rectangle r){

        final Rectangle rectanguloEscalado = new Rectangle((int) (r.x * Constantes.FACTOR_ESCALADO_X),
                                                           (int) (r.y * Constantes.FACTOR_ESCALADO_Y),
                                                           (int) (r.width * Constantes.FACTOR_ESCALADO_X),
                                                           (int) (r.height * Constantes.FACTOR_ESCALADO_Y));
        return rectanguloEscalado;
    }

    public static Point escalarPuntoSuperior(final Point p){
        final Point puntoReescalado = new Point((int) (p.x * Constantes.FACTOR_ESCALADO_X),
                                                (int) (p.y * Constantes.FACTOR_ESCALADO_Y));
        return puntoReescalado;
    }

    public static Point escalarPuntoInferior(final Point p){
        final Point puntoReescalado = new Point((int) (p.x / Constantes.FACTOR_ESCALADO_X),
                                                (int) (p.y / Constantes.FACTOR_ESCALADO_Y));
        return puntoReescalado;
    }
}