package principal.mapas;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import principal.herramientas.CargadorRecursos;

public class MapaTiled {
    public MapaTiled(final String ruta){
        String contenido = CargadorRecursos.leerArchivoJSON_R(ruta);

        //ANCHO, ALTO
    }

    private JSONObject getObjetoJSON(final String codigoJSON){
        JSONParser parser = new JSONParser();
        JSONObject objetoJSON = null;
        try{
            objetoJSON = (JSONObject) parser.parse(codigoJSON);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return objetoJSON;
    }
}