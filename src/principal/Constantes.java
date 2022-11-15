package principal;

import java.awt.*;

public class Constantes {

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Dimension screenSize = tk.getScreenSize();

    public static final int LADO_SPRITE = 32;
    public static int LADO_CURSOR = 0;
    public static int ANCHO_JUEGO = 640;
    public static int ALTO_JUEGO = 360;
    public static int ANCHO_PANTALLA_COMPLETA = screenSize.width;
    public static int ALTO_PANTALLA_COMPLETA = screenSize.height;
/*    public static int ANCHO_PANTALLA_COMPLETA = 1280;
    public static int ALTO_PANTALLA_COMPLETA = 720;*/

    public static double FACTOR_ESCALADO_X = (double) ANCHO_PANTALLA_COMPLETA / (double) ANCHO_JUEGO;
    public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA_COMPLETA / (double) ALTO_JUEGO;

    public static int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
    public static int CENTRO_VENTANA_Y = ALTO_JUEGO /  2;

    public static Font FUENTE_GLOBAL = new Font("Segoe UI", Font.PLAIN, 8);
    public static Color ROJO_GLOBAL = new Color(100,0,0);

    public static String RUTA_PERSONAJE = "/resources/imagenes/sheetPersonajes/c.png";
    public static final String RUTA_MAPA_1 = "resources/mapas/03.cbm";
    public static final String RUTA_MAPA_2 = "resources/mapas/02.cbm";
    public static final String RUTA_OBJETOS_INVENTARIO = "/imagenes/sheetObjetosInventario/01.png";
    public static final String RUTA_ROPA = "/imagenes/sheetObjetosInventario/ropa.png";
    public static final String RUTA_COFRES = "/resources/imagenes/sheetCofres/cobrecerrado.png";
    public static final String RUTA_ICONO_RATON = "/resources/imagenes/icons/iconcursor.png";
    public static final String RUTA_ICONO_VENTANA = "/resources/imagenes/icons/iconwindow.png";
}