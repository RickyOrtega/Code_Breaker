package principal.sprites;

import principal.herramientas.CargadorRecursos;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private final int anchoHoja_px;
    private final int altoHoja_px;

    private final int anchoHoja_enSprites;
    private final int altoHoja_enSprites;

    private final int anchoSprites;
    private final int altoSprites;

    private Sprite[] sprites;

    public SpriteSheet(final String ruta, final int tamanoSprites, final boolean hojaOpaca) {
        BufferedImage imagen;

        if (hojaOpaca) {
            imagen = CargadorRecursos.cargarImagenCompatibleOpaca_R(ruta);
        } else {
            imagen = CargadorRecursos.cargarImagenCompatibleTranslucida_R(ruta);
        }

        anchoHoja_px = imagen.getWidth();
        altoHoja_px = imagen.getHeight();

        anchoHoja_enSprites = anchoHoja_px / tamanoSprites;
        altoHoja_enSprites = altoHoja_px / tamanoSprites;

        anchoSprites = tamanoSprites;
        altoSprites = tamanoSprites;

        sprites = new Sprite[anchoHoja_enSprites * altoHoja_enSprites];

        extraerSpritesDesdeImagen(imagen);
    }

    public SpriteSheet(final String ruta, final int anchoSprites, final int altoSprites, final boolean hojaOpaca) {
        BufferedImage imagen;

        if (hojaOpaca) {
            imagen = CargadorRecursos.cargarImagenCompatibleOpaca_R(ruta);
        } else {
            imagen = CargadorRecursos.cargarImagenCompatibleTranslucida_R(ruta);
        }

        anchoHoja_px = imagen.getWidth();
        altoHoja_px = imagen.getHeight();

        anchoHoja_enSprites = anchoHoja_px / anchoSprites;
        altoHoja_enSprites = altoHoja_px / altoSprites;

        this.anchoSprites = anchoSprites;
        this.altoSprites = altoSprites;

        sprites = new Sprite[anchoHoja_enSprites * altoHoja_enSprites];

        extraerSpritesDesdeImagen(imagen);
    }

    private void extraerSpritesDesdeImagen(final BufferedImage imagen) {
        for (int y = 0; y < altoHoja_enSprites; y++) {
            for (int x = 0; x < anchoHoja_enSprites; x++) {
                final int posicionX = x * anchoSprites;
                final int posicionY = y * altoSprites;

                sprites[x + y * anchoHoja_enSprites] = new Sprite(imagen.getSubimage(posicionX, posicionY, anchoSprites, altoSprites));
            }
        }
    }

    public Sprite getSprite(final int x, final int y) {
        return sprites[x + y * anchoHoja_enSprites];
    }

    public Sprite getSprite(final int indice) {
        return sprites[indice];
    }
}