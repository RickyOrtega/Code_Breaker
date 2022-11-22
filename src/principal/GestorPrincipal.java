package principal;

import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.mapas.MapaTiled;
import principal.maquinaestado.GestorEstados;

public class GestorPrincipal {
    private boolean enFuncionamiento = false;
    private String title;
    private int WIDTH;
    private int HEIGHT;

    private SuperficieDibujo sd;
    private Ventana ventana;
    private GestorEstados ge;

    private static int aps;
    private static int fps;

    private GestorPrincipal(final String titulo, final int ancho, final int alto){
        this.title = titulo;
        this.WIDTH = ancho;
        this.HEIGHT = alto;
    }

    public static void main(String[] args) {

        System.setProperty("sun.java2d.opengl", "true");

        GestorPrincipal gp = new GestorPrincipal("Code-Breaker", Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);
        gp.iniciarJuego();
        gp.iniciarBuclePrincipal();
    }
    private void iniciarJuego(){
        enFuncionamiento = true;
        inicializar();
    }

    private void iniciarBuclePrincipal(){

        int actualizacionesAcumuladas = 0;
        int framesAcumulados = 0;

        final int NS_PER_SECOND = 1000000000;
        final int APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_PER_SECOND / APS_OBJETIVO;

        long updateReference = System.nanoTime();
        long counterReference = System.nanoTime();

        double elapsedTime;
        double delta = 0;

        while(enFuncionamiento){
            final long startLoop = System.nanoTime();

            elapsedTime = startLoop - updateReference;
            updateReference = startLoop;

            delta += elapsedTime / NS_POR_ACTUALIZACION;

            while(delta >= 1){
                actualizar();
                actualizacionesAcumuladas++;
                delta--;
            }

            dibujar();
            framesAcumulados++;

            if (System.nanoTime() - counterReference > NS_PER_SECOND){

                fps = framesAcumulados;
                aps = actualizacionesAcumuladas;

                actualizacionesAcumuladas = 0;
                framesAcumulados = 0;

                counterReference = System.nanoTime();
            }
        }
    }

    private void dibujar() {
        sd.dibujar(ge);
    }

    private void actualizar(){
        if(GestorControles.getTeclado().inventarioActivo){
            ge.setEstadoActual(1);
        }else{
            ge.setEstadoActual(0);
        }

        ge.actualizar();
        sd.actualizar();
    }

    private void inicializar(){
        sd = new SuperficieDibujo(this.WIDTH, this.HEIGHT);
        ventana = new Ventana(title, sd);
        ge = new GestorEstados(sd);
    }

    public static int getAps() {
        return aps;
    }

    public static int getFps() {
        return fps;
    }

    public SuperficieDibujo getSuperficieDibujo(){
        return sd;
    }

    private boolean isEnFuncionamiento(){
        return enFuncionamiento;
    }
}