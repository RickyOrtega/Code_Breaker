package principal.interfazusuario;

import principal.Constantes;
import principal.herramientas.DibujoDebug_R;
import principal.herramientas.MedidorString;
import principal.mapas.Mapa;

import java.awt.*;
import java.util.ArrayList;


public class MenuLateral_R {
    private Rectangle areaInformacionMapa;

    public MenuLateral_R(){

    }

    public void dibujar(final Graphics g, final Mapa mapa){
        dibujarInformacionMapa(g, mapa);
    }

    private void dibujarInformacionMapa(final Graphics g, final Mapa mapa){

        ArrayList<String> informacionMapa = mapa.getInformacionMapa();

        final int ancho = Constantes.ANCHO_JUEGO/4;
        final int alto = MedidorString.medirAltoPixeles(g, "A")*informacionMapa.size();
        final int x = Constantes.ANCHO_JUEGO - ancho - 10;
        final int y = 10;

        DibujoDebug_R.dibujarRectangulo(g, x, y, ancho, alto, new Color(0,0,0,255).darker(), true);
        DibujoDebug_R.dibujarRectangulo(g, x, y, ancho, alto, Color.WHITE, false);

        g.setColor(Color.WHITE);
        g.setFont(Constantes.FUENTE_GLOBAL);

        for (int i = 0; i < informacionMapa.size(); i++) {
            DibujoDebug_R.dibujarString(g, informacionMapa.get(i), x+2, y + 20 + i * 8);
        }
    }
}