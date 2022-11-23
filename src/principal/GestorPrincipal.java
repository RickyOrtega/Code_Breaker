package principal;

import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.mapas.Mapa;
import principal.maquinaestado.GestorEstados;
import principal.puntuaciones.Archivo;
import principal.puntuaciones.Puntuacion;

import javax.swing.*;
import java.util.ArrayList;

public class GestorPrincipal {

    private ArrayList<Puntuacion> listaPuntajes = new ArrayList<Puntuacion>();
    private Archivo archivoPuntajes;
    public static boolean enFuncionamiento = false;
    private String title;
    private int WIDTH;
    private int HEIGHT;

    private SuperficieDibujo sd;
    private Ventana ventana;
    private GestorEstados ge;

    private static int aps;
    private static int fps;

    private long inicio;
    private long fin;
    public static double tiempoTranscurrido;
    private String nombre = null;
    private int contador=0;

    public GestorPrincipal(final String titulo, final int ancho, final int alto){
        this.title = titulo;
        this.WIDTH = ancho;
        this.HEIGHT = alto;
    }

/*    public static void main(String[] args) {

        System.setProperty("sun.java2d.opengl", "true");

        GestorPrincipal gp = new GestorPrincipal("Code-Breaker", Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);
        gp.iniciarJuego();
        gp.iniciarBuclePrincipal();
    }*/
    public void iniciarJuego(){
        enFuncionamiento = true;
        inicializar();
    }

    public void iniciarBuclePrincipal(){

        int actualizacionesAcumuladas = 0;
        int framesAcumulados = 0;

        final int NS_PER_SECOND = 1000000000;
        final int APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_PER_SECOND / APS_OBJETIVO;

        long updateReference = System.nanoTime();
        long counterReference = System.nanoTime();

        inicio = System.currentTimeMillis();

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

            fin = System.currentTimeMillis();
            tiempoTranscurrido = (double)(fin - inicio)/1000;

            if(Mapa.isFinal.equals("null") && contador<=0){
                ventana.dispose();

                nombre = JOptionPane.showInputDialog("Introduce tu nombre: ");
                int puntuacion = (int) (5000 - tiempoTranscurrido);

                listaPuntajes.add(new Puntuacion(nombre, puntuacion, tiempoTranscurrido));

                System.out.println(listaPuntajes.get(0).toString());

                archivoPuntajes = new Archivo(Constantes.PUNTUACIONES, (ArrayList) listaPuntajes);
                archivoPuntajes.guardar();

                contador++;
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

        sd.setMostrarTiempo(tiempoTranscurrido);

        ge.actualizar();
        sd.actualizar();
    }

    private void inicializar(){
        sd = new SuperficieDibujo(this.WIDTH, this.HEIGHT, tiempoTranscurrido);
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