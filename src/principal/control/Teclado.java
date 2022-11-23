package principal.control;

import principal.GestorPrincipal;
import principal.graficos.Ventana;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    public Tecla up = new Tecla();
    public Tecla down = new Tecla();
    public Tecla left = new Tecla();
    public Tecla right = new Tecla();

    public boolean corriendo = false;
    public boolean mostrandoMetricas = false;
    public boolean inventarioActivo = false;
    public boolean recoger = false;

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            // Movimiento
            case KeyEvent.VK_W:
                up.teclaPulsada();
                break;
            case KeyEvent.VK_S:
                down.teclaPulsada();
                break;
            case KeyEvent.VK_A:
                left.teclaPulsada();
                break;
            case KeyEvent.VK_D:
                right.teclaPulsada();
                break;
            case KeyEvent.VK_SHIFT:
                corriendo = true;
                break;

            // Teclas especiales
            case KeyEvent.VK_ESCAPE:
                inventarioActivo = !inventarioActivo;
                break;
            case KeyEvent.VK_X:
                recoger = true;
                break;

            // Menú
            case KeyEvent.VK_I:

                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea salir del juego?", "Salir", JOptionPane.YES_NO_OPTION);

                GestorPrincipal.enFuncionamiento = false;

                if(opcion == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
                break;
            case KeyEvent.VK_K:
                mostrandoMetricas = !mostrandoMetricas;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                up.teclaLiberada();
                break;
            case KeyEvent.VK_S:
                down.teclaLiberada();
                break;
            case KeyEvent.VK_A:
                left.teclaLiberada();
                break;
            case KeyEvent.VK_D:
                right.teclaLiberada();
                break;
            case KeyEvent.VK_SHIFT:
                corriendo = false;
                break;
            case KeyEvent.VK_X:
                recoger = false;
                break;
        }
    }

    public void keyTyped(KeyEvent e) {

    }
}