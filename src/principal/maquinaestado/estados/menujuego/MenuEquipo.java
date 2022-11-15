package principal.maquinaestado.estados.menujuego;

import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoDebug;
import principal.herramientas.EscaladorElementos;
import principal.herramientas.GeneradorTooltip;

import java.awt.*;

import static principal.entes.Jugador.limitePeso;
import static principal.entes.Jugador.pesoActual;

public class MenuEquipo extends SeccionMenu {

    final Rectangle panelObjetos = new Rectangle(em.getFONDO().x + MARGEN_GENERAL,
                                                 barraPeso.y + barraPeso.height + MARGEN_GENERAL,
                                              248,
            (Constantes.ALTO_JUEGO - barraPeso.y + barraPeso.height + MARGEN_GENERAL * 2 )-60);

    final Rectangle tituloPanelObjetos = new Rectangle(panelObjetos.x,
                                                      panelObjetos.y,
                                                      panelObjetos.width,
                                                      16);

    final Rectangle panelEquipamiento = new Rectangle(panelObjetos.x + panelObjetos.width + MARGEN_GENERAL,
                                                         panelObjetos.y,
                                                   88,
                                                         panelObjetos.height);

    final Rectangle tituloPanelEquipamiento = new Rectangle(panelEquipamiento.x,
                                                           panelEquipamiento.y,
                                                           panelEquipamiento.width,
                                                           16);

    final Rectangle panelAtributos = new Rectangle(panelEquipamiento.x + panelEquipamiento.width + MARGEN_GENERAL,
                                                      panelObjetos.y,
                                                132,
                                                      panelObjetos.height);

    final Rectangle tituloPanelAtributos = new Rectangle(panelAtributos.x,
                                                        panelAtributos.y,
                                                        panelAtributos.width,
                                                        16);

    public MenuEquipo(final String nombreSeccion, final Rectangle etiquetaMenu, EstructuraMenu em) {
        super(nombreSeccion, etiquetaMenu, em);
    }

    public void actualizar() {

    }

    public void dibujar(final Graphics g, final SuperficieDibujo sd) {

        dibujarLimitePeso(g);

        DibujoDebug.dibujarRectangulo(g, panelObjetos, Constantes.ROJO_GLOBAL, false);

        DibujoDebug.dibujarRectangulo(g, tituloPanelObjetos, Constantes.ROJO_GLOBAL, true);

        DibujoDebug.dibujarRectangulo(g, panelEquipamiento, Constantes.ROJO_GLOBAL, false);

        DibujoDebug.dibujarRectangulo(g, tituloPanelEquipamiento, Constantes.ROJO_GLOBAL, true);

        DibujoDebug.dibujarRectangulo(g, panelAtributos, Constantes.ROJO_GLOBAL, false);

        DibujoDebug.dibujarRectangulo(g, tituloPanelAtributos, Constantes.ROJO_GLOBAL, true);

        if(sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloSuperior(barraPeso))) {
            GeneradorTooltip.dibujarTooltip(g, sd, pesoActual + "/" + limitePeso);
        }
    }
}