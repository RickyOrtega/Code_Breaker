package principal.herramientas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 *
 * Estaba teniendo problemas con algunos métodos del tutorial, así que decidí hacer algunos cambios menores.
 *
 * @author Ricky O.
 * @version 1.0
 * */

public class CargadorRecursos {
    public static BufferedImage cargarImagenCompatibleOpaca_R(final String ruta) {
        Image imagen = null;

        try{
            imagen = ImageIO.read(CargadorRecursos.class.getResource(ruta));

        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen: " + ruta);
        }

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), Transparency.OPAQUE);

        Graphics g = imagenAcelerada.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();

        return imagenAcelerada;
    }

    public static BufferedImage cargarImagenCompatibleTranslucida_R(final String ruta) {
        Image imagen = null;
        try {
            imagen = ImageIO.read(CargadorRecursos.class.getResource(ruta));
        } catch (IOException e){
            System.out.println("No se pudo cargar la imagen: " + ruta);
        }

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), Transparency.TRANSLUCENT);

        Graphics g = imagenAcelerada.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();

        return imagenAcelerada;
    }

    public static String leerArchivoTexto_R(String ruta){

        String contenido = "";

        BufferedReader lector = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(ruta)));

        String linea;

        try{
            while((linea = lector.readLine()) != null){
                contenido += linea;
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo: " + ruta);
        } finally {
            try {
                lector.close();
            } catch (IOException e) {
                System.out.println("No se pudo cerrar el archivo: " + ruta);
            }
        }
        return contenido;
    }

    public static Font cargarFuente(final String ruta){

        Font fuente = null;

        InputStream entradaBytes = ClassLoader.getSystemResourceAsStream(ruta);

        try {
            fuente = Font.createFont(Font.TRUETYPE_FONT, entradaBytes);
        } catch (FontFormatException e) {
            System.out.println("No se pudo cargar la fuente.");
        } catch (IOException e) {
            System.out.println("No se pudo cargar la fuente.");
        }

        fuente = fuente.deriveFont(12f);

        return fuente;
    }
}