package principal.graficos;

import principal.Constantes;
import principal.GestorPrincipal;
import principal.control.GestorControles;
import principal.control.Raton;
import principal.herramientas.DatosDebug;
import principal.herramientas.DibujoDebug;
import principal.maquinaestado.GestorEstados;
import principal.maquinaestado.estados.juego.GestorJuego;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class SuperficieDibujo extends Canvas {

    public static final long serialVersionUID = 1L;

    private final int WIDTH;
    private final int HEIGHT;

    private Raton raton;

    public SuperficieDibujo(final int ancho, final int alto) {
        this.WIDTH = ancho;
        this.HEIGHT = alto;

        this.raton = new Raton(this);

        setCursor(raton.getCursor());
        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(GestorControles.getTeclado());
        addMouseListener(raton);
        setFocusable(true);
        requestFocus();
    }

    public void dibujar(final GestorEstados ge) {

        DibujoDebug.reiniciarContadorObjetosDibujados();

        BufferStrategy buffer = getBufferStrategy();

        if (buffer == null) {
            createBufferStrategy(4);
            return;
        }

        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);

        if(Constantes.FACTOR_ESCALADO_X != 1 || Constantes.FACTOR_ESCALADO_Y != 1){
            g.scale(Constantes.FACTOR_ESCALADO_X, Constantes.FACTOR_ESCALADO_Y);
        }

        ge.dibujar(g);

        mostrarDebug_R();

        if (GestorControles.getTeclado().mostrandoMetricas){
            DatosDebug.dibujarDatos(g);
        } else {
            DatosDebug.limpiarDatos();
        }

        Toolkit.getDefaultToolkit().sync();

        g.dispose();

        buffer.show();
    }

    private void mostrarDebug_R(){
        DatosDebug.agregarDato("FPS: " + GestorPrincipal.getFps());
        DatosDebug.agregarDato("APS: " + GestorPrincipal.getAps());
        DatosDebug.agregarDato("OPF: " + DibujoDebug.getObjetosDibujados());
        DatosDebug.agregarDato("FACTOR ESCALADO: " + Constantes.FACTOR_ESCALADO_X + ", " + Constantes.FACTOR_ESCALADO_Y);
        DatosDebug.agregarDato("POSICION RATON: " + raton.getPosicion().x + ", " + raton.getPosicion().y);
        DatosDebug.agregarDato("Posición: " + GestorJuego.getJugador().getPosicionX() + ", " + GestorJuego.getJugador().getPosicionY());
        DatosDebug.agregarDato("Siguiente mapa: " +GestorJuego.getMapa().getSiguienteMapa());
    }

    public void actualizar() {
        raton.actualizar(this);
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public Raton getRaton() {
        return raton;
    }
}