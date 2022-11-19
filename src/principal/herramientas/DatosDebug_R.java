package principal.herramientas;

import principal.Constantes;

import java.awt.*;
import java.util.ArrayList;

public class DatosDebug_R {
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

    public static void dibujarDatos_R(final Graphics g){
        g.setFont(Constantes.FUENTE_GLOBAL);
        for(int i = 0; i < datos.size(); i++){

            DibujoDebug_R.dibujarRectangulo(g, 10, 15/2 + (i * 15),MedidorString.medirAnchoPixeles(g, datos.get(i)) ,MedidorString.medirAltoPixeles(g, datos.get(i)),new Color(0,255,0,100), true);

            g.setColor(Color.BLACK);

            DibujoDebug_R.dibujarString(g, datos.get(i), 10, 15 + (i * 15));
        }
        limpiarDatos();
    }
}