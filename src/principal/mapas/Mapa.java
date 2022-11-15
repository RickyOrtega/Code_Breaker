package principal.mapas;

import principal.Constantes;
import principal.control.GestorControles;
import principal.entes.Jugador;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.inventario.ContenedorObjetos;
import principal.inventario.Inventario;
import principal.sprites.Sprite;
import principal.sprites.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mapa {

    private String[] contenidoPartes;
    private final int ancho;
    private final int alto;
    private final Point posicionInicial;
    private final Point puntoSalida;
    private Rectangle zonaSalida;
    private String siguienteMapa;
    private final Sprite[] paletaSprites;

    private final int[] sprites;
    private final boolean[] colisiones;
    private ArrayList<Rectangle> zonasColision = new ArrayList<Rectangle>();
    private ArrayList<ContenedorObjetos> objetosMapa;

    private final int MargenX = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
    private final int MargenY = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;

    public Mapa(final String ruta) {
        String contenido = CargadorRecursos.leerArchivoTexto_R(ruta);
        contenidoPartes = contenido.split("\\*");

        ancho = Integer.parseInt(contenidoPartes[0]);
        alto = Integer.parseInt(contenidoPartes[1]);

        String usedSheets = contenidoPartes[2];
        String[] sheets = usedSheets.split(",");

        //Cargamos la paleta de sprites
        String paleta = contenidoPartes[3];
        String[] paletaPartes = paleta.split("#");

        paletaSprites = asignarSprite(paletaPartes, sheets);

        String colisionesEnteras = contenidoPartes[4];
        colisiones = extraerColisiones(colisionesEnteras);

        String spritesEnteros = contenidoPartes[5];
        String[] cadenasSprites = spritesEnteros.split(" ");

        sprites = extraerSprites(cadenasSprites);

        String posicion = contenidoPartes[6];
        String[] posiciones = posicion.split("-");

        posicionInicial = new Point();
        posicionInicial.x = Integer.parseInt(posiciones[0]) * Constantes.LADO_SPRITE;
        posicionInicial.y = Integer.parseInt(posiciones[1]) * Constantes.LADO_SPRITE;

        String salida = contenidoPartes[7];
        String[] datosSalida = salida.split("-");

        puntoSalida = new Point();
        puntoSalida.x = Integer.parseInt(datosSalida[0]);
        puntoSalida.y = Integer.parseInt(datosSalida[1]);

        siguienteMapa = datosSalida[2];

        zonaSalida = new Rectangle();

        if(contenidoPartes.length > 8) {
            String informacionObjetos = contenidoPartes[8];
            objetosMapa = asignarObjetos(informacionObjetos);
        }
    }

    private ArrayList<ContenedorObjetos> asignarObjetos(String informacionObjetos) {
        ArrayList<ContenedorObjetos> objetos = new ArrayList<ContenedorObjetos>();

        String[] contenedoresObjetos = informacionObjetos.split("#");

        for (String contenedoresIndividuales : contenedoresObjetos) {
            final ArrayList<Integer> idObjetos = new ArrayList<Integer>();
            final ArrayList<Integer> cantidades = new ArrayList<Integer>();

            final String[] divisionInformacionObjetos = contenedoresIndividuales.split(":");
            final String[] coordenadas = divisionInformacionObjetos[0].split(",");
            final Point posicionC = new Point(Integer.parseInt(coordenadas[0]),
                    Integer.parseInt(coordenadas[1]));

            final String[] objetosCantidades = divisionInformacionObjetos[1].split("/");

            for (String objetoCantidad : objetosCantidades) {
                final String[] datosObjetoActual = objetoCantidad.split("-");
                idObjetos.add(Integer.parseInt(datosObjetoActual[0]));
                cantidades.add(Integer.parseInt(datosObjetoActual[1]));
            }

            final int[] idObjetosArray = new int[idObjetos.size()];
            final int[] cantidadesArray = new int[cantidades.size()];

            for (int i = 0; i < idObjetosArray.length; i++) {
                idObjetosArray[i] = idObjetos.get(i);
                cantidadesArray[i] = cantidades.get(i);
            }

            final ContenedorObjetos contenedor = new ContenedorObjetos(posicionC, idObjetosArray, cantidadesArray);

            objetos.add(contenedor);
        }
        return objetos;
    }

    private Sprite[] asignarSprite(final String[] paletaPartes, final String[] hojasSeparadas) {

        Sprite[] paleta = new Sprite[paletaPartes.length];

        SpriteSheet ss = new SpriteSheet("/resources/imagenes/sheetTexturas/" + hojasSeparadas[0] + ".png", 32, false);

        for (int i = 0; i < paletaPartes.length; i++) {
            String spriteActual = paletaPartes[i];
            String[] partesSprite = spriteActual.split("-");

            int indicePaleta = Integer.parseInt(partesSprite[0]);
            int indiceSpriteSheet = Integer.parseInt(partesSprite[2]);

            paleta[indicePaleta] = ss.getSprite(indiceSpriteSheet);
        }
        return paleta;
    }

    private int[] extraerSprites(final String[] cadenaSprites) {

        ArrayList<Integer> spritesMapa = new ArrayList<Integer>();

        for (int i = 0; i < cadenaSprites.length; i++) {
            if (cadenaSprites[i].length() == 2) {
                spritesMapa.add(Integer.parseInt(cadenaSprites[i]));
            } else {
                String uno = "";
                String dos = "";

                String error = cadenaSprites[i];

                uno = error.substring(0, 2);
                dos = error.substring(2, 4);

                spritesMapa.add(Integer.parseInt(uno));
                spritesMapa.add(Integer.parseInt(dos));
            }
        }

        int[] vectorSprites = new int[spritesMapa.size()];

        for (int i = 0; i < spritesMapa.size(); i++) {
            vectorSprites[i] = spritesMapa.get(i);
        }
        return vectorSprites;
    }

    private boolean[] extraerColisiones(final String colisiones) {
        boolean[] colisionesMapa = new boolean[colisiones.length()];

        for (int i = 0; i < colisiones.length(); i++) {
            if (colisiones.charAt(i) == '0') {
                colisionesMapa[i] = false;
            } else {
                colisionesMapa[i] = true;
            }
        }
        return colisionesMapa;
    }

    public Rectangle getBordes(final int posicionX, final int posicionY, final int anchoJugador, final int altoJugador) {

        int x = MargenX - posicionX + anchoJugador;
        int y = MargenY - posicionY + altoJugador;
        int ancho = this.ancho * Constantes.LADO_SPRITE - anchoJugador * 2;
        int alto = this.alto * Constantes.LADO_SPRITE - altoJugador * 2;

        return new Rectangle(x, y, ancho, alto);
    }

    public void actualizar(final int posicionX, final int posicionY, final Jugador jugador, final Inventario inventario) {
        actualizarZonasColision(posicionX, posicionY);
        actualizarZonaSalida(posicionX, posicionY);
        actualizarRecogidaObjetos_R(jugador, inventario);
    }

    private void actualizarZonasColision(final int posicionX, final int posicionY) {
        if (!zonasColision.isEmpty()) {
            zonasColision.clear();
        }
        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                if (this.colisiones[x + y * this.ancho]) {
                    int puntoX = x * Constantes.LADO_SPRITE - posicionX + this.MargenX;
                    int puntoY = y * Constantes.LADO_SPRITE - posicionY + this.MargenY;

                    if (colisiones[x + y * this.ancho]) {

                        final Rectangle zona = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

                        zonasColision.add(zona);
                    }
                }
            }
        }
    }

    private void actualizarRecogidaObjetos_R(final Jugador jugador, final Inventario inventario) {
        if(!objetosMapa.isEmpty()){
            final Rectangle areaJugador = new Rectangle((int) jugador.getPosicionX(), (int) jugador.getPosicionY(), Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

            for(int i = 0; i < objetosMapa.size(); i++){
                final ContenedorObjetos conObj = objetosMapa.get(i);

                final Rectangle areaObjeto = new Rectangle((int) conObj.getPosicion().getX() * Constantes.LADO_SPRITE,
                                                           (int) conObj.getPosicion().getY() * Constantes.LADO_SPRITE,
                                                              Constantes.LADO_SPRITE,
                                                              Constantes.LADO_SPRITE);
                if(areaJugador.intersects(areaObjeto) && GestorControles.getTeclado().recoger){
                    inventario.recogerObjetos(conObj);
                    objetosMapa.remove(i);
                }
            }
        }
    }

    private void actualizarZonaSalida(final int posicionX, final int posicionY) {
        int puntoX = ((int) getPuntoSalida().getX()) * Constantes.LADO_SPRITE - posicionX + this.MargenX;
        int puntoY = ((int) getPuntoSalida().getY()) * Constantes.LADO_SPRITE - posicionY + this.MargenY;

        zonaSalida = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
    }

    public void dibujar(Graphics g, int posicionX, int posicionY) {
        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                BufferedImage spriteActual = paletaSprites[sprites[x + y * this.ancho]].getImage();

                int puntoX = x * Constantes.LADO_SPRITE - posicionX + MargenX;
                int puntoY = y * Constantes.LADO_SPRITE - posicionY + MargenY;

                DibujoDebug.dibujarImagen(g, spriteActual, puntoX, puntoY);
            }
        }

        if(!objetosMapa.isEmpty()){
            for(ContenedorObjetos objeto : objetosMapa){

                final int puntoX = objeto.getPosicion().x * Constantes.LADO_SPRITE - posicionX + MargenX;
                final int puntoY = objeto.getPosicion().y * Constantes.LADO_SPRITE - posicionY + MargenY;

                objeto.dibujar(g, new Point(puntoX, puntoY));
            }
        }
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public String getContenido() {
        return contenidoPartes[1];
    }

    public Sprite getSprite(final int indice) {
        return paletaSprites[indice];
    }

    public Sprite getSprite(final int x, final int y) {
        return paletaSprites[sprites[x + y * this.ancho]];
    }

    public Sprite[] getPaletaSprites() {
        return paletaSprites;
    }

    public Point getPosicionInicial() {
        return posicionInicial;
    }

    public Point getPuntoSalida() {
        return puntoSalida;
    }

    public String getSiguienteMapa() {
        return siguienteMapa;
    }

    public Rectangle getZonaSalida() {
        return zonaSalida;
    }

    public ArrayList<Rectangle> getZonasColision() {
        return zonasColision;
    }

    public Rectangle getZonaColision(final int indice) {
        return zonasColision.get(indice);
    }
}