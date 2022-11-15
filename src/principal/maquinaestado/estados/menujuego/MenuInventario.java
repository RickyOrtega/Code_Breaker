package principal.maquinaestado.estados.menujuego;

import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoDebug;
import principal.herramientas.EscaladorElementos;
import principal.herramientas.GeneradorTooltip;
import principal.herramientas.MedidorString;
import principal.inventario.Inventario;

import java.awt.*;

public class MenuInventario extends SeccionMenu {

    private int limitePeso = 100;
    private int pesoActual = 20;

    private final int MARGEN_GENERAL = 8;
    private final Rectangle barraPeso;
    private final EstructuraMenu em;
    public static Inventario inventario;

    public MenuInventario(final String nombreSeccion, final Rectangle etiquetaMenu, EstructuraMenu em) {
        super(nombreSeccion, etiquetaMenu);

        int ancho = 100;
        int alto = 8;

        this.em = em;

        barraPeso = new Rectangle(Constantes.ANCHO_JUEGO - ancho - MARGEN_GENERAL, em.getBANNER_SUPERIOR().height + MARGEN_GENERAL, ancho, alto);

        inventario = new Inventario();
    }

    public void actualizar() {

    }

    public void dibujar(final Graphics g, final SuperficieDibujo sd) {
        dibujarLimitePeso(g);
        dibujarElementosInventario(g, em);
        dibujarPaginador(g, em);

        if(sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloSuperior(barraPeso))) {
            GeneradorTooltip.dibujarTooltip(g, sd, pesoActual + "/" + limitePeso);
        }
    }

    private void dibujarLimitePeso(final Graphics g) {
        final Rectangle contenidoBarra = new Rectangle(barraPeso.x + 1, barraPeso.y + 1, barraPeso.width / (limitePeso/pesoActual), barraPeso.height - 2);

        DibujoDebug.dibujarString(g, "Peso ", barraPeso.x - 30, barraPeso.y + 7, Color.BLACK);
        DibujoDebug.dibujarRectangulo(g, barraPeso, Color.GRAY,true);
        DibujoDebug.dibujarRectangulo(g, contenidoBarra, Constantes.ROJO_GLOBAL,true);
    }

    private void dibujarRejillaInventario(final Graphics g, EstructuraMenu em) {
        final Point puntoInicial = new Point(em.getFONDO().x + 20, barraPeso.y + barraPeso.height + MARGEN_GENERAL);

        g.setColor(Color.gray);

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 7; j++) {
                DibujoDebug.dibujarRectangulo(g, puntoInicial.x + i * (Constantes.LADO_SPRITE + MARGEN_GENERAL) , puntoInicial.y + j * (Constantes.LADO_SPRITE + MARGEN_GENERAL), Constantes.LADO_SPRITE, Constantes.LADO_SPRITE, Color.BLACK, true);
            }
        }
    }

    private void dibujarElementosInventario(final Graphics g, EstructuraMenu em) {
        final Point puntoInicial = new Point(em.getFONDO().x + 20, barraPeso.y + barraPeso.height + MARGEN_GENERAL);

        g.setColor(Color.gray);

        for (int i = 0; i < inventario.getObjetos().size(); i++) {
            DibujoDebug.dibujarImagen(g, inventario.getObjetos().get(i).getSprite().getImage(), puntoInicial.x + i * (Constantes.LADO_SPRITE + MARGEN_GENERAL) , puntoInicial.y);
            g.setColor(Color.BLACK);
            DibujoDebug.dibujarRectangulo(g, puntoInicial.x + i * (Constantes.LADO_SPRITE + MARGEN_GENERAL) +Constantes.LADO_SPRITE -12,
                                                puntoInicial.y + Constantes.LADO_SPRITE - (Constantes.LADO_SPRITE/4), 12, 8, new Color(0,0,0,150), true);

            String texto = "" + inventario.getObjetos().get(i).getCantidad();
            g.setFont(g.getFont().deriveFont(2f));
            g.setColor(Color.WHITE);
            DibujoDebug.dibujarString(g, texto, puntoInicial.x + i * (Constantes.LADO_SPRITE + MARGEN_GENERAL) + Constantes.LADO_SPRITE - (MedidorString.medirAnchoPixeles(g, texto)*4) - Constantes.LADO_SPRITE/16,
                                                puntoInicial.y + Constantes.LADO_SPRITE - 1);
        }
        g.setFont(Constantes.FUENTE_GLOBAL);
    }

    private void dibujarPaginador(final Graphics g, EstructuraMenu em){
        final int anchoBoton = 32;
        final int altoBoton = 16;

        final Rectangle anterior = new Rectangle((em.getFONDO().x + em.getFONDO().width)/2 + anchoBoton + MARGEN_GENERAL,
                                                 em.getFONDO().y + em.getFONDO().height - MARGEN_GENERAL*2 - altoBoton, anchoBoton, altoBoton);
        final Rectangle siguiente = new Rectangle(anterior.x + anterior.width + MARGEN_GENERAL, anterior.y, anchoBoton, altoBoton);

        DibujoDebug.dibujarRectangulo(g, anterior, Constantes.ROJO_GLOBAL, true);
        DibujoDebug.dibujarRectangulo(g, siguiente, Constantes.ROJO_GLOBAL, true);
    }

    public Inventario getInventario() {
        return inventario;
    }
}