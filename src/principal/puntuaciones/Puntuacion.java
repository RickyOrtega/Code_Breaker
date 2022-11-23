package principal.puntuaciones;

import java.io.Serializable;

public class Puntuacion implements Serializable {
    private String nombre="";
    private int puntuacion=0;
    private double tiempo=0;

    public Puntuacion(String nombre, int puntuacion, double tiempo){
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
    }

    public String toString(){
        return nombre + " || " + puntuacion + " || " + tiempo;
    }


}