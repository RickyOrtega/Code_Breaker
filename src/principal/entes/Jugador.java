package principal.entes;

import principal.Constantes;
import principal.control.GestorControles;
import principal.herramientas.DibujoDebug_R;
import principal.mapas.Mapa;
import principal.sprites.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jugador {
    private static final int ANCHO_JUGADOR = 16;
    private static final int ALTO_JUGADOR = 16;
    private double posicionX;
    private double posicionY;
    private int direccion;
    private boolean enMovimiento;
    private SpriteSheet spriteSheet;
    private BufferedImage imagenActual;
    private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X-8, Constantes.CENTRO_VENTANA_Y, 16, 1);
    private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X-8, Constantes.CENTRO_VENTANA_Y+16, 16, 1);
    private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X-8, Constantes.CENTRO_VENTANA_Y, 1, 16);
    private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X+8, Constantes.CENTRO_VENTANA_Y, 1, 16);
    private int animacion;
    private int estado;
    private int velocidad = 1;
    private Mapa mapa;

    //Referentes a la resistencia. Reevaluar.
    private int resistencia = 600;
    private int recuperacion = 0;
    private final int RECUPERACION_MAXIMA = 100;
    private boolean recuperado = true;
    public static int limitePeso = 100;
    public static int pesoActual = 0;

    public Jugador(Mapa mapa) {
        posicionX = mapa.getPosicionInicial().getX();
        posicionY = mapa.getPosicionInicial().getY();
        this.mapa = mapa;
        direccion = 0;
        enMovimiento = false;

        spriteSheet = new SpriteSheet(Constantes.RUTA_PERSONAJE, Constantes.LADO_SPRITE, false);

        imagenActual = spriteSheet.getSprite(0).getImage();

        animacion = 0;
        estado = 0;
    }

    public void actualizar() {
        gestionarVelocidadYResistencia();
        cambiarAnimacionYEstado_R();
        enMovimiento = false;
        determinarDireccion();
        animar();
    }

    private void gestionarVelocidadYResistencia(){
        if(GestorControles.getTeclado().corriendo && resistencia > 0){
            velocidad = 2;
            recuperacion = 0;
            recuperado = false;
        } else {
            velocidad = 1;
            if(!recuperado && recuperacion < RECUPERACION_MAXIMA){
                recuperacion++;
            }
            if(recuperacion == RECUPERACION_MAXIMA && resistencia < RECUPERACION_MAXIMA*6){
                resistencia++;
            }
        }
    }

    private void determinarDireccion() {
        final int velocidadX = evaluarVelocidadX();
        final int velocidadY = evaluarVelocidadY();

        if (velocidadX == 0 && velocidadY == 0) {
            return;
        }

        if (velocidadX == 0 || velocidadY == 0) {
            mover(velocidadX, velocidadY);
        } else {
            if(velocidadX == -1 && velocidadY == -1){
                //izquierda arriba
                if(GestorControles.getTeclado().left.getUltimaPulsacion() > GestorControles.getTeclado().up.getUltimaPulsacion()){
                    mover(velocidadX, 0);
                } else{
                    mover(0, velocidadY);
                }

                //izquierda abajo
                if (GestorControles.getTeclado().left.getUltimaPulsacion() > GestorControles.getTeclado().down.getUltimaPulsacion()) {
                    mover(velocidadX, 0);
                } else {
                    mover(0, velocidadY);
                }

                //derecha arriba
                if (GestorControles.getTeclado().right.getUltimaPulsacion() > GestorControles.getTeclado().up.getUltimaPulsacion()) {
                    mover(velocidadX, 0);
                } else {
                    mover(0, velocidadY);
                }

                //derecha abajo
                if (GestorControles.getTeclado().right.getUltimaPulsacion() > GestorControles.getTeclado().down.getUltimaPulsacion()) {
                    mover(velocidadX, 0);
                } else {
                    mover(0, velocidadY);
                }
            }
        }
    }

    private void cambiarAnimacionYEstado_R() {

        if(animacion < 60){
            animacion++;
        } else{
            animacion = 0;
        }

        if(animacion < 15){
            estado = 1;
        } else if(animacion < 30){
            estado = 0;
        } else if(animacion < 45){
            estado = 2;
        } else if(animacion < 60){
            estado = 0;
        }
    }

    private int evaluarVelocidadX() {
        int velocidadX = 0;
        if (GestorControles.getTeclado().left.isPulsada() && !GestorControles.getTeclado().right.isPulsada() && !GestorControles.getTeclado().up.isPulsada() && !GestorControles.getTeclado().down.isPulsada()) {
            velocidadX = -1;
        }
        if (GestorControles.getTeclado().right.isPulsada() && !GestorControles.getTeclado().left.isPulsada() && !GestorControles.getTeclado().up.isPulsada() && !GestorControles.getTeclado().down.isPulsada()) {
            velocidadX = 1;
        }
        return velocidadX;
    }

    private int evaluarVelocidadY() {
        int velocidadY = 0;
        if (GestorControles.getTeclado().up.isPulsada() && !GestorControles.getTeclado().down.isPulsada() && !GestorControles.getTeclado().left.isPulsada() && !GestorControles.getTeclado().right.isPulsada()) {
            velocidadY = -1;
        } else if (GestorControles.getTeclado().down.isPulsada() && !GestorControles.getTeclado().up.isPulsada() && !GestorControles.getTeclado().left.isPulsada() && !GestorControles.getTeclado().right.isPulsada()) {
            velocidadY = 1;
        }
        return velocidadY;
    }

    private void mover(final int velocidadX, final int velocidadY){
        enMovimiento = true;

        cambiarDireccion(velocidadX, velocidadY);

        if (!fueraMapa(velocidadX, velocidadY)) {
            if (velocidadX == -1 && !enColisionIzquierda(velocidadX)) {
                posicionX += velocidadX * velocidad;
                restarResistencia();
                return;
            }
            if (velocidadX == 1 && !enColisionDerecha(velocidadX)) {
                posicionX += velocidadX * velocidad;
                restarResistencia();
                return;
            }
            if (velocidadY == -1 && !enColisionArriba(velocidadY)) {
                posicionY += velocidadY * velocidad;
                restarResistencia();
                return;
            }
            if (velocidadY == 1 && !enColisionAbajo(velocidadY)) {
                posicionY += velocidadY * velocidad;
                restarResistencia();
                return;
            }
        }
    }

    private void restarResistencia(){
        if(GestorControles.getTeclado().corriendo && resistencia > 0){
            resistencia--;
        }
    }

    private boolean enColisionArriba(final int velocidadY){
        for(int r = 0; r < mapa.getZonasColision().size(); r++){
            final Rectangle area = mapa.getZonasColision().get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * velocidad + 3 * velocidad;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

            if (LIMITE_ARRIBA.intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    private boolean enColisionAbajo(final int velocidadY){
        for(int r = 0; r < mapa.getZonasColision().size(); r++){
            final Rectangle area = mapa.getZonasColision().get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * velocidad - 3 * velocidad;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

            if (LIMITE_ABAJO.intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    private boolean enColisionIzquierda(final int velocidadX){
        for(int r = 0; r < mapa.getZonasColision().size(); r++){
            final Rectangle area = mapa.getZonasColision().get(r);

            int origenX = area.x + velocidadX * velocidad + 3 * velocidad;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

            if (LIMITE_IZQUIERDA.intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    private boolean enColisionDerecha(final int velocidadX){
        for(int r = 0; r < mapa.getZonasColision().size(); r++){
            final Rectangle area = mapa.getZonasColision().get(r);

            int origenX = area.x + velocidadX * velocidad - 3 * velocidad;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

            if (LIMITE_DERECHA.intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    private boolean fueraMapa(final int velocidadX, final int velocidadY){

        int posicionFuturaX = (int) posicionX + velocidadX * (int) velocidad;
        int posicionFuturaY = (int) posicionY + velocidadY * (int) velocidad;

        final Rectangle bordesMapa = mapa.getBordes(posicionFuturaX, posicionFuturaY, ANCHO_JUGADOR, ALTO_JUGADOR);

        final boolean fuera;

        if(LIMITE_ARRIBA.intersects(bordesMapa) ||
                LIMITE_ABAJO.intersects(bordesMapa) ||
                LIMITE_IZQUIERDA.intersects(bordesMapa) ||
                LIMITE_DERECHA.intersects(bordesMapa)){
            fuera = false;
        }else{
            fuera = true;
        }

        return fuera;
    }

    private void cambiarDireccion(final int velocidadX, final int velocidadY) {
        if(velocidadX == -1){
            direccion = 3;
        } else if(velocidadX == 1){
            direccion = 2;
        }

        if(velocidadY == -1){
            direccion = 1;
        } else if(velocidadY == 1){
            direccion = 0;
        }
    }
    private void animar(){
        if (!enMovimiento){
            estado = 0;
            animacion = 0;
        }
        imagenActual = spriteSheet.getSprite(direccion, estado).getImage();
    }
    public void dibujar(final Graphics g) {
        final int puntoX = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;

        final int puntoY = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;

        DibujoDebug_R.dibujarImagen(g, imagenActual, puntoX, puntoY);
    }
    public double getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(double posicionX) {
        this.posicionX = posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(double posicionY) {
        this.posicionY = posicionY;
    }

    public int getResistencia() {
        return resistencia;
    }

    public Rectangle getLIMITE_ARRIBA() {
        return LIMITE_ARRIBA;
    }

    public Rectangle getLIMITE_ABAJO() {
        return LIMITE_ABAJO;
    }

    public Rectangle getLIMITE_IZQUIERDA() {
        return LIMITE_IZQUIERDA;
    }

    public Rectangle getLIMITE_DERECHA() {
        return LIMITE_DERECHA;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }
}