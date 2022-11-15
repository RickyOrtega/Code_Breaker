package principal.graficos;

import principal.herramientas.CargadorRecursos;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Ventana extends JFrame {

    private static final long serialVersionUID = 1L;
    private final ImageIcon icono;

    private final String title;
    public Ventana(final String titulo, final SuperficieDibujo sd) {

        this.title = titulo;

        BufferedImage iconoBuffer = CargadorRecursos.cargarImagenCompatibleTranslucida_R("/resources/imagenes/icons/iconwindow.png");
        this.icono = new ImageIcon(iconoBuffer);
        
        configurarVentana_R(sd);
    }

    private void configurarVentana_R(final SuperficieDibujo sd) {
        setTitle(title);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(icono.getImage());
        setLayout(new BorderLayout());
        add(sd, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        //Windows Look And Feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}