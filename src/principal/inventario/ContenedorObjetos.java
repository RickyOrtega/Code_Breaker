package principal.inventario;

import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;

import java.awt.*;
import java.awt.image.BufferedImage;

import static principal.Constantes.RUTA_COFRES;

public class ContenedorObjetos {

    private static final BufferedImage sprite = CargadorRecursos.cargarImagenCompatibleTranslucida_R(RUTA_COFRES);

    private Point posicion;
    private ItemInventario[] objetos;
    private boolean abierto = false;

    public ContenedorObjetos(final Point posicion, final int[] objetos, final int[] cantidades) {
        this.posicion = posicion;
        this.objetos = new ItemInventario[objetos.length];

        for(int i = 0; i < objetos.length; i++) {

            try{
                this.objetos[i] = RegistroObjetos.getObjeto(objetos[i]);
                this.objetos[i].incrementarCantidad(cantidades[i]);
            } catch (Exception e) {
                System.out.println("Error al cargar el objeto: " + objetos[i]);
            }
        }
    }

    public void dibujar(final Graphics g, final Point posicion) {
        DibujoDebug.dibujarImagen(g, sprite, posicion.x, posicion.y);
    }

    public ItemInventario[] getObjetos() {
        return objetos;
    }

    public void abrir() {
        abierto = true;
    }

    public void cerrar() {
        abierto = false;
    }

    public boolean estaAbierto() {
        return abierto;
    }

    public Point getPosicion() {
        return posicion;
    }
}