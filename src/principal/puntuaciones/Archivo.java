package principal.puntuaciones;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Archivo implements Serializable {

    private String nombreArchivo;
    private ArrayList<Object> listaObjetos;

    public Archivo(String nombreArchivo, ArrayList<Object> listaObjetos) {
        this.listaObjetos = listaObjetos;
        this.nombreArchivo = nombreArchivo;
    }

    public Archivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void guardar() {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(this.nombreArchivo));
            salida.writeObject(this.listaObjetos);
            salida.close();
        } catch (EOFException e){
            return;
        } catch (FileNotFoundException e) {
            System.err.println("Error, " + e);
        } catch (IOException e){
            System.err.println("Error, " + e);
        }
    }

    public ArrayList<Object> obtener(String nombreArchivo) {
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombreArchivo));
            this.listaObjetos = (ArrayList<Object>) entrada.readObject();
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error, " + e);
        } catch (IOException e){
            System.err.println("Error, " + e);
        } catch (ClassNotFoundException e){
            System.err.println("Error, " + e);
        }

        return this.listaObjetos;
    }

}