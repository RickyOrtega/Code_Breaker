package principal.control;

public class GestorControles {
    private static final Teclado teclado = new Teclado();

    public static Teclado getTeclado(){
        return teclado;
    }

    public static Raton getRaton(){
        return null;
    }
}