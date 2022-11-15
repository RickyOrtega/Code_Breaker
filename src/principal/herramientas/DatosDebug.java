package principal.herramientas;

import principal.Constantes;

import java.awt.*;
import java.util.ArrayList;

public class DatosDebug {
    private static ArrayList<String> datos = new ArrayList<String>();

    public static void agregarDato(final String dato){
        datos.add(dato);
    }

    public static void limpiarDatos(){
        datos.clear();
    }

    public static ArrayList<String> getDatos(){
        return datos;
    }

    public static void dibujarDatos(final Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(Constantes.FUENTE_GLOBAL);
        for(int i = 0; i < datos.size(); i++){
            DibujoDebug.dibujarString(g, datos.get(i), 10, 15 + (i * 15));
        }
        limpiarDatos();
    }
}