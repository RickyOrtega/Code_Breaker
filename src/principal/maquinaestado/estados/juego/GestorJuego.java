package principal.maquinaestado.estados.juego;

import principal.Constantes;
import principal.entes.Jugador;
import principal.interfazusuario.MenuInferior_R;
import principal.interfazusuario.MenuLateral_R;
import principal.mapas.Mapa;
import principal.maquinaestado.EstadoJuego;
import java.awt.*;

import static principal.maquinaestado.estados.menujuego.MenuInventario.inventario;

public class GestorJuego implements EstadoJuego {

    private static Mapa mapa;
    private static Jugador jugador;
    private MenuInferior_R menuInferior_r;
    private MenuLateral_R menuLateralR;

    public GestorJuego() {
        iniciarMapa(Constantes.RUTA_MAPA_1);
        iniciarJugador();
        menuInferior_r = new MenuInferior_R(jugador);
        menuLateralR = new MenuLateral_R();
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
        menuInferior_r.dibujar(g, jugador);
        menuLateralR.dibujar(g, mapa);
    }

    public static Mapa getMapa() {
        return mapa;
    }

    public static Jugador getJugador() {
        return jugador;
    }
}