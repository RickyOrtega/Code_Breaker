package principal.Menu;

import javax.swing.*;
import java.awt.*;

public class Panel extends javax.swing.JPanel {

    private final int MARGEN_HORIZONTAL = 20;
    private final int MARGEN_VERTICAL = 20;

    private Rectangle rectanguloJugar;

    private Point posicion;
    public Panel() {
        initComponents();
    }

    private void initComponents() {

        setLayout(null);
    }

    @Override
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        rectanguloJugar = new Rectangle(this.MARGEN_HORIZONTAL,
                this.MARGEN_VERTICAL,
                (this.getWidth() - 3*MARGEN_HORIZONTAL)/2,
                this.getHeight() - 2*MARGEN_VERTICAL);

        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRoundRect(rectanguloJugar.x, rectanguloJugar.y, rectanguloJugar.width, rectanguloJugar.height, 10, 10);
    }

    public void iniciarJuego(){

    }

    private void manejoRaton(){
        getRectanguloPosicion();
    }

    private Rectangle getRectanguloPosicion(){
        return new Rectangle(posicion.x, posicion.y, 1, 1);
    }

    private void actualizarPosicion(){
        final Point posicionRaton = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(posicionRaton, this);
        posicion.setLocation(posicionRaton.getX(), posicionRaton.getY());
    }
}