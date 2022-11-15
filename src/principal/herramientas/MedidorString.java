package principal.herramientas;

import principal.Constantes;

import java.awt.*;

public class MedidorString {
    public static int medirAnchoPixeles(final Graphics g, final String texto) {

        FontMetrics fm = g.getFontMetrics();

        return fm.stringWidth(texto);
    }

    public static int medirAltoPixeles(final Graphics g, final String texto) {

        FontMetrics fm = g.getFontMetrics();

        return (int) fm.getLineMetrics(texto, g).getHeight();
    }
}