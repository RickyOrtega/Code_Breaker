package principal.maquinaestado.estados.menujuego;

import principal.Constantes;
import principal.entes.Jugador;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoDebug;
import principal.herramientas.EscaladorElementos;
import principal.herramientas.GeneradorTooltip;
import principal.herramientas.MedidorString;

import java.awt.*;
import java.util.Random;

import static principal.entes.Jugador.limitePeso;
import static principal.entes.Jugador.pesoActual;

public abstract class SeccionMenu {
    protected final String nombreSeccion;
    protected final Rectangle etiquetaMenu;
    protected final EstructuraMenu em;

    private final Random valorRGB;
    protected final Rectangle barraPeso;

    protected final int MARGEN_GENERAL = 8;


    public SeccionMenu(final String nombreSeccion, final Rectangle etiquetaMenu, EstructuraMenu em) {
        this.nombreSeccion = nombreSeccion;
        this.etiquetaMenu = etiquetaMenu;
        this.em = em;

        int ancho = 100;
        int alto = 8;

        barraPeso = new Rectangle(Constantes.ANCHO_JUEGO - ancho - MARGEN_GENERAL, em.getBANNER_SUPERIOR().height + MARGEN_GENERAL, limitePeso, alto);

        valorRGB = new Random();

    }

    public abstract void actualizar();

    public abstract void dibujar(Graphics g, final SuperficieDibujo sd);

    protected static void dibujarTooltip(final Graphics g, final SuperficieDibujo sd, final String texto){
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

    protected void dibujarLimitePeso(final Graphics g) {

        int ancho = 0;

        if (pesoActual > 0) {
            ancho = (int) (pesoActual/ limitePeso);
        }

        final Rectangle contenidoBarra = new Rectangle(barraPeso.x + 1, barraPeso.y + 1,  ancho,barraPeso.height - 2);

        DibujoDebug.dibujarString(g, "Peso ", barraPeso.x - 30, barraPeso.y + 7, Color.BLACK);
        DibujoDebug.dibujarRectangulo(g, barraPeso, Color.GRAY,true);
        DibujoDebug.dibujarRectangulo(g, contenidoBarra, Constantes.ROJO_GLOBAL,true);
    }

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