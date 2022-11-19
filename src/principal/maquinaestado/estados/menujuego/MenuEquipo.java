package principal.maquinaestado.estados.menujuego;

import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoDebug_R;
import principal.herramientas.EscaladorElementos;
import principal.herramientas.GeneradorTooltip;
import principal.herramientas.MedidorString;
import principal.inventario.Inventario;
import principal.inventario.ItemInventario;

import java.awt.*;
import java.util.Random;

import static principal.entes.Jugador.limitePeso;
import static principal.entes.Jugador.pesoActual;

public class MenuEquipo extends SeccionMenu {

    final Rectangle panelObjetos = new Rectangle(em.getFONDO().x + MARGEN_GENERAL,
            barraPeso.y + barraPeso.height + MARGEN_GENERAL,
            248,
            (Constantes.ALTO_JUEGO - barraPeso.y + barraPeso.height + MARGEN_GENERAL * 2) - 60);

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

    final Rectangle etiquetaOutfit;

    final Rectangle contenedorOutfit;

    private double numero = 0;
    private boolean bajando = false;
    private Inventario inventario;

    public MenuEquipo(final String nombreSeccion, final Rectangle etiquetaMenu, EstructuraMenu em, SuperficieDibujo sd, final Inventario inventario) {
        super(nombreSeccion, etiquetaMenu, em);

        etiquetaOutfit = new Rectangle(tituloPanelEquipamiento.x + MARGEN_GENERAL,
                tituloPanelEquipamiento.y + tituloPanelEquipamiento.height + MARGEN_GENERAL,
                tituloPanelEquipamiento.width - MARGEN_GENERAL * 2,
                MARGEN_GENERAL * 2 + MedidorString.medirAltoPixeles(sd.getGraphics(), "Outfit"));

        contenedorOutfit = new Rectangle(etiquetaOutfit.x + 1,
                etiquetaOutfit.y + etiquetaOutfit.height - 1,
                etiquetaOutfit.width - 2,
                Constantes.LADO_SPRITE);

        this.inventario = inventario;
    }

    public void actualizar() {

    }

    public void dibujar(final Graphics g, final SuperficieDibujo sd) {

        dibujarLimitePeso(g);
        dibujarPaneles(g);

        if (sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloSuperior(barraPeso))) {
            GeneradorTooltip.dibujarTooltip(g, sd, pesoActual + "/" + limitePeso);
        }
    }

    private void dibujarPanel(final Graphics g, final Rectangle panel, final Rectangle titulo, final String nombrePanel) {
        DibujoDebug_R.dibujarRectangulo(g, panel, Constantes.ROJO_GLOBAL, false);
        DibujoDebug_R.dibujarRectangulo(g, titulo, Constantes.ROJO_GLOBAL, true);
        DibujoDebug_R.dibujarString(g, nombrePanel,
                titulo.x + titulo.width / 2 - MedidorString.medirAnchoPixeles(g, nombrePanel) / 2,
                titulo.y + titulo.height - MedidorString.medirAltoPixeles(g, nombrePanel) / 2, Color.WHITE);
    }

    private void dibujarPaneles(final Graphics g) {
        dibujarPanelObjetos(g, panelObjetos, tituloPanelObjetos, "Equipables");
        dibujarPanelEquipo(g, panelEquipamiento, tituloPanelEquipamiento, "Equipamiento");
        dibujarPanelAtributos(g, panelAtributos, tituloPanelAtributos, "Atributos");

        Graphics2D g2 = (Graphics2D) g;

        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void dibujarPanelObjetos(final Graphics g, final Rectangle panel, final Rectangle titulo, final String nombrePanel) {
        dibujarPanel(g, panel, titulo, nombrePanel);
        dibujarElementosEquipables(g, panel, titulo);
    }

    private void dibujarElementosEquipables(final Graphics g, final Rectangle panel, final Rectangle titulo) {
        if(inventario.getPrendas().isEmpty()){
            return;
        }

        final Point puntoInicial = new Point(titulo.x + MARGEN_GENERAL, titulo.y + MARGEN_GENERAL + titulo.height);

        for (int i = 0; i < inventario.getPrendas().size(); i++) {
            int idActual = inventario.getPrendas().get(i).getId();

            ItemInventario itemActual = inventario.getPrendas().get(i);

            DibujoDebug_R.dibujarImagen(g, itemActual.getSprite().getImage(), puntoInicial.x, puntoInicial.y + i * Constantes.LADO_SPRITE);
        }
    }

    private void dibujarPanelEquipo(final Graphics g, final Rectangle panel, final Rectangle titulo, final String nombrePanel) {

        dibujarPanel(g, panel, titulo, nombrePanel);


        Random random = new Random();


        Color color = new Color((int) numero, 0, 0);
        DibujoDebug_R.dibujarRectangulo(g, etiquetaOutfit, color, true);
        DibujoDebug_R.dibujarRectangulo(g, contenedorOutfit, color, false);

        g.setColor(Color.WHITE);
        DibujoDebug_R.dibujarString(g, "Outfit", new Point(etiquetaOutfit.x + etiquetaOutfit.width / 2 - MedidorString.medirAnchoPixeles(g, "Outfit") / 2,
                etiquetaOutfit.y + etiquetaOutfit.height - MedidorString.medirAltoPixeles(g, "Outfit") / 2));

        if(numero < 150 && !bajando) {
            numero += 0.05;
        } else {
            bajando = true;
            numero -= 0.05;
        }

        if(numero < 0) {
            bajando = false;
        }
    }

    private void dibujarPanelAtributos(final Graphics g, final Rectangle panel, final Rectangle titulo, final String nombrePanel) {
        dibujarPanel(g, panel, titulo, nombrePanel);
    }
}