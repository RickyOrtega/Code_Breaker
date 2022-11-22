package principal.mapas;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import principal.herramientas.CargadorRecursos;

import java.awt.*;
import java.util.ArrayList;

public class MapaTiled {

    private int anchoMapaEnTiles;
    private int altoMapaEnTiles;
    private Point posicionInicial;

    private ArrayList<CapaSprites> capasSprites;
    private ArrayList<CapaColisiones> capasColisiones;
    private ArrayList<Rectangle> areasColisionOriginales;

    public MapaTiled(final String ruta) {
        String contenido = CargadorRecursos.leerArchivoJSON_R(ruta);

        //ANCHO, ALTO
        JSONObject globalJSON = getObjetoJSON(contenido);
        anchoMapaEnTiles = getIntJSON(globalJSON, "width");
        altoMapaEnTiles = getIntJSON(globalJSON, "height");

        JSONObject puntoInicial = getObjetoJSON(globalJSON.get("start").toString());
        posicionInicial = new Point(getIntJSON(puntoInicial, "x"), getIntJSON(puntoInicial, "y"));

        JSONArray capas = getArrayJSON(globalJSON.get("layers").toString());

        this.capasSprites = new ArrayList<>();
        this.capasColisiones = new ArrayList<>();


        for (int i = 0; i < capas.size(); i++) {
            JSONObject datosCapa = getObjetoJSON(capas.get(i).toString());

            long anchoCapa = getIntJSON(datosCapa, "width");
            long altoCapa = getIntJSON(datosCapa, "height");
            long xCapa = getIntJSON(datosCapa, "x");
            long yCapa = getIntJSON(datosCapa, "y");
            String tipo = datosCapa.get("type").toString();

            switch (tipo) {
                case "tilelayer" -> {
                    JSONArray sprites = getArrayJSON(datosCapa.get("data").toString());
                    long[] spritesCapa = new long[sprites.size()];
                    for (int j = 0; j < sprites.size(); j++) {
                        long codigoSprite = Long.parseLong(sprites.get(j).toString());
                        spritesCapa[j] = codigoSprite - 1;
                    }

                    CapaSprites capaSprites = new CapaSprites(anchoCapa, altoCapa, xCapa, yCapa, spritesCapa);
                    capasSprites.add(capaSprites);

                }
                case "objectgroup" -> {
                    JSONArray rectangulos = getArrayJSON(datosCapa.get("objects").toString());

                    Rectangle[] colisionesCapa = new Rectangle[rectangulos.size()];
                    for (int j = 0; j < rectangulos.size(); j++) {
                        JSONObject datosRectangulo = getObjetoJSON(rectangulos.get(j).toString());

                        int xRectangulo = getIntJSON(datosRectangulo, "x");
                        int yRectangulo = getIntJSON(datosRectangulo, "y");
                        int anchoRectangulo = getIntJSON(datosRectangulo, "width");
                        int altoRectangulo = getIntJSON(datosRectangulo, "height");

                        if(xRectangulo == 0){
                            xRectangulo = 1;
                        }
                        if(yRectangulo == 0){
                            yRectangulo = 1;
                        }
                        if(anchoRectangulo == 0){
                            anchoRectangulo = 1;
                        }
                        if(altoRectangulo == 0){
                            altoRectangulo = 1;
                        }

                        Rectangle rectangulo = new Rectangle(xRectangulo, yRectangulo, anchoRectangulo, altoRectangulo);
                        colisionesCapa[j] = rectangulo;
                    }

                    CapaColisiones capaColisiones = new CapaColisiones(anchoCapa, altoCapa, xCapa, yCapa, colisionesCapa);

                    this.capasColisiones.add(capaColisiones);
                }
            }
        }

        //COMBINAR COLISIONES

        areasColisionOriginales = new ArrayList<>();

        for(int i=0; i<capasColisiones.size(); i++){
            Rectangle[] colisiones = capasColisiones.get(i).getArrayColisiones();
            for (int j = 0; j < colisiones.length; j++) {
                areasColisionOriginales.add(colisiones[j]);
            }
        }

        //AVERIGUAR TOTAL DE SPRITES EXISTENTES EN TODAS LAS CAPAS
        JSONArray coleccionSprites = getArrayJSON(globalJSON.get("tilesets").toString());
        int totalSprites = 0;
        for(int i=0; i<coleccionSprites.size(); i++){
            JSONObject datosGrupo = getObjetoJSON(coleccionSprites.get(i).toString());
            totalSprites += getIntJSON(datosGrupo, "tilecount");
        }
    }

    private JSONObject getObjetoJSON(final String codigoJSON) {
        JSONParser parser = new JSONParser();
        JSONObject objetoJSON = null;
        try {
            objetoJSON = (JSONObject) parser.parse(codigoJSON);
        } catch (ParseException e) {
            System.out.println("Posicion: " + e.getPosition());
            System.out.println(e);
        }
        return objetoJSON;
    }

    private JSONArray getArrayJSON(final String codigoJSON) {
        JSONParser parser = new JSONParser();
        JSONArray arrayJSON = null;
        try {
            arrayJSON = (JSONArray) parser.parse(codigoJSON);
        } catch (ParseException e) {
            System.out.println("Posicion: " + e.getPosition());
            System.out.println(e);
        }

        return arrayJSON;
    }

    private int getIntJSON(final JSONObject objetoJSON, final String clave) {

        int numero = (int)Double.parseDouble(objetoJSON.get(clave).toString());

        return numero;
    }

    private long getLongJSON(final JSONObject objetoJSON, final String clave) {
        return Long.parseLong(objetoJSON.get(clave).toString());
    }

    public int getAnchoMapaEnTiles() {
        return anchoMapaEnTiles;
    }

    public int getAltoMapaEnTiles() {
        return altoMapaEnTiles;
    }


    public Point getPosicionInicial() {
        return posicionInicial;
    }
}