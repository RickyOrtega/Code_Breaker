package principal.maquinaestado.estados.menujuego;

import principal.graficos.SuperficieDibujo;
import principal.maquinaestado.EstadoJuego;

import java.awt.*;

public class GestorMenu implements EstadoJuego {

    private final EstructuraMenu em;
    private final SeccionMenu[] seccionesMenu;
    private SeccionMenu seccionActual;
    private final SuperficieDibujo sd;

    public GestorMenu(final SuperficieDibujo sd) {
        em = new EstructuraMenu();
        seccionesMenu = new SeccionMenu[2];
        final Rectangle etiquetaInventario = new Rectangle(em.getBANNER_LATERAL().x + em.getMARGEN_HORIZONTAL(),
                                                           em.getBANNER_LATERAL().y + em.getMARGEN_VERTICAL(),
                                                              em.getANCHO_ETIQUETAS(),
                                                              em.getALTO_ETIQUETAS());

        seccionesMenu[0] = new MenuInventario("Inventario", etiquetaInventario, em);

        final Rectangle etiquetaEquipo= new Rectangle(em.getBANNER_LATERAL().x + em.getMARGEN_HORIZONTAL(),
                                                      etiquetaInventario.y + etiquetaInventario.height + em.getMARGEN_VERTICAL(),
                                                        em.getANCHO_ETIQUETAS(),
                                                        em.getALTO_ETIQUETAS());
        seccionesMenu[1] = new MenuEquipo("Equipo", etiquetaEquipo);
        seccionActual = seccionesMenu[0];

        this.sd = sd;
    }

    public void actualizar() {
        for (int i = 0; i < seccionesMenu.length; i++) {
            if(sd.getRaton().getClic() && sd.getRaton().getRectanguloPosicion().intersects(seccionesMenu[i].getEtiquetaMenuReescalada())){
                seccionActual = seccionesMenu[i];
            }
        }
        sd.getRaton().resetClic();
    }

    public void dibujar(final Graphics g) {
        em.dibujar(g);
        for(int i=0; i< seccionesMenu.length; i++){
            if(seccionActual == seccionesMenu[i]){
                if(sd.getRaton().getRectanguloPosicion().intersects(seccionesMenu[i].getEtiquetaMenuReescalada())){
                    seccionesMenu[i].dibujarEtiquetaActivaResaltada(g);
                } else {
                    seccionesMenu[i].dibujarEtiquetaActiva(g);
                }
            } else {
                if(sd.getRaton().getRectanguloPosicion().intersects(seccionesMenu[i].getEtiquetaMenuReescalada())){
                    seccionesMenu[i].dibujarEtiquetaInactivaResaltada(g);
                } else {
                    seccionesMenu[i].dibujarEtiquetaInactiva(g);
                }
            }
        }

        seccionActual.dibujar(g, sd);
    }

    public SeccionMenu[] getSeccionesMenu() {
        return seccionesMenu;
    }
}