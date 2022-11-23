package principal.Menu;

import principal.Constantes;
import principal.GestorPrincipal;
import principal.herramientas.CargadorRecursos;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class MenuPrincipal extends JFrame {

    private Panel panel;
    private ImageIcon icono;
    public MenuPrincipal() {
        initComponents();
    }


    public static void main(String[] args) {


        System.setProperty("sun.java2d.opengl", "true");

        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);

        GestorPrincipal gp = new GestorPrincipal("Code-Breaker", Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);
        gp.iniciarJuego();
        gp.iniciarBuclePrincipal();
    }

    public static void actualizaTabla(){

    }

    private void initComponents(){
        setTitle("Code-Breaker");
        setResizable(false);
        setSize(new java.awt.Dimension(Constantes.ANCHO_JUEGO, Constantes.ALTO_JUEGO));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BufferedImage iconoBuffer = CargadorRecursos.cargarImagenCompatibleTranslucida_R("/resources/imagenes/icons/iconwindow.png");
        this.icono = new ImageIcon(iconoBuffer);
        setIconImage(icono.getImage());

        panel = new Panel();
        iniciarPanel();
    }

    private void iniciarPanel(){
        panel.setVisible(true);
        panel.setSize(new java.awt.Dimension(Constantes.ANCHO_JUEGO, Constantes.ALTO_JUEGO));
        panel.setLocation(0, 0);
        panel.setBackground(new java.awt.Color(190, 190, 190));
        panel.setLayout(null);

        add(panel);
    }
}