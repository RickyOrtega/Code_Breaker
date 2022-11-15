package principal.maquinaestado.estados.juego;

import principal.Constantes;
import principal.entes.Jugador;
import principal.interfazusuario.MenuInferior;
import principal.mapas.Mapa;
import principal.maquinaestado.EstadoJuego;
import java.awt.*;

import static principal.maquinaestado.estados.menujuego.MenuInventario.inventario;

public class GestorJuego implements EstadoJuego {

    private GestorMapa gm;

    private static Mapa mapa;
    private static Jugador jugador;
    private MenuInferior menuInferior;

    public GestorJuego() {
        iniciarMapa(Constantes.RUTA_MAPA_1);
        iniciarJugador();
        menuInferior = new MenuInferior(jugador);
    }

    private void recargarJuego() {

        final String ruta = "resources/mapas/" + mapa.getSiguienteMapa();

        iniciarMapa(ruta);
        iniciarJugador();
    }
    public void iniciarMapa(final String ruta) {
        mapa = new Mapa(ruta);
    }
    private void iniciarJugador() {
        jugador = new Jugador(mapa);
    }

    public void actualizar() {
        if(jugador.getLIMITE_ARRIBA().intersects(mapa.getZonaSalida())){
            recargarJuego();
        }

        jugador.actualizar();
        mapa.actualizar((int)jugador.getPosicionX(), (int)jugador.getPosicionY(), jugador, inventario);
    }

    public void dibujar(final Graphics g) {
        mapa.dibujar(g, (int)jugador.getPosicionX(), (int)jugador.getPosicionY());
        jugador.dibujar(g);
        menuInferior.dibujar(g, jugador);
    }

    public static Mapa getMapa() {
        return mapa;
    }

    public static Jugador getJugador() {
        return jugador;
    }
}