package principal.sprites;

import java.awt.image.BufferedImage;

public class Sprite {
    private final BufferedImage imagen;

    private final int WIDTH;
    private final int HEIGHT;

    public Sprite(final BufferedImage imagen) {
        this.imagen = imagen;
        this.WIDTH = imagen.getWidth();
        this.HEIGHT = imagen.getHeight();
    }

    public BufferedImage getImage() {
        return imagen;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}