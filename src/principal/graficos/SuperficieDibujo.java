package principal.graficos;

import principal.Constantes;
import principal.GestorPrincipal;
import principal.control.GestorControles;
import principal.control.Raton;
import principal.herramientas.DatosDebug_R;
import principal.herramientas.DibujoDebug_R;
import principal.herramientas.MedidorString;
import principal.mapas.Mapa;
import principal.maquinaestado.GestorEstados;
import principal.maquinaestado.estados.juego.GestorJuego;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class SuperficieDibujo extends Canvas {

    public static final long serialVersionUID = 1L;

    private final int WIDTH;
    private final int HEIGHT;

    private String mostrarTiempo;

    private Raton raton;

    public SuperficieDibujo(final int ancho, final int alto, final double mostrarTiempo) {
        this.WIDTH = ancho;
        this.HEIGHT = alto;

        this.raton = new Raton(this);

        this.mostrarTiempo = mostrarTiempo + " ms";

        setCursor(raton.getCursor());
        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(GestorControles.getTeclado());
        addMouseListener(raton);
        setFocusable(true);
        requestFocus();
    }

    public void dibujar(final GestorEstados ge) {

        DibujoDebug_R.reiniciarContadorObjetosDibujados();

        BufferStrategy buffer = getBufferStrategy();

        if (buffer == null) {
            createBufferStrategy(4);
            return;
        }

        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
        g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);

        if(Constantes.FACTOR_ESCALADO_X != 1 || Constantes.FACTOR_ESCALADO_Y != 1){
            g.scale(Constantes.FACTOR_ESCALADO_X, Constantes.FACTOR_ESCALADO_Y);
        }

        if(Mapa.isFinal.equals("null")){

            int x = Constantes.CENTRO_VENTANA_X - MedidorString.medirAnchoPixeles(g, "GAME OVER");
            int y = Constantes.CENTRO_VENTANA_Y - MedidorString.medirAltoPixeles(g, "GAME");
            g.setColor(Constantes.ROJO_GLOBAL);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME", x, y);
            g.drawString("OVER", x, y + 50);

            GestorPrincipal.enFuncionamiento = false;
        } else {
            ge.dibujar(g);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString(mostrarTiempo, 20, 20);

            mostrarDebug_R();
        }

        if (GestorControles.getTeclado().mostrandoMetricas){
            DatosDebug_R.dibujarDatos_R(g);
        } else {
            DatosDebug_R.limpiarDatos();
        }

        Toolkit.getDefaultToolkit().sync();

        g.dispose();

        buffer.show();
    }

    private void mostrarDebug_R(){
        DatosDebug_R.agregarDato("FPS: " + GestorPrincipal.getFps());
        DatosDebug_R.agregarDato("APS: " + GestorPrincipal.getAps());
        DatosDebug_R.agregarDato("OPF: " + DibujoDebug_R.getObjetosDibujados());
        DatosDebug_R.agregarDato("FACTOR ESCALADO: " + Constantes.FACTOR_ESCALADO_X + ", " + Constantes.FACTOR_ESCALADO_Y);
        DatosDebug_R.agregarDato("POSICION RATON: " + raton.getPosicion().x + ", " + raton.getPosicion().y);
        DatosDebug_R.agregarDato("Posición: " + GestorJuego.getJugador().getPosicionX() + ", " + GestorJuego.getJugador().getPosicionY());
        DatosDebug_R.agregarDato("Siguiente mapa: " +GestorJuego.getMapa().getSiguienteMapa());
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

    public long getMostrarTiempo() {
        return Long.parseLong(mostrarTiempo);
    }

    public void setMostrarTiempo(double mostrarTiempo) {
        this.mostrarTiempo = mostrarTiempo + " s";
    }
}