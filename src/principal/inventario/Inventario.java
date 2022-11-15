package principal.inventario;

import java.util.ArrayList;

public class Inventario {
    private final ArrayList<ItemInventario> objetos;

    public Inventario() {
        objetos = new ArrayList<>();
    }

    public void recogerObjetos(final ContenedorObjetos co){
        for(ItemInventario i : co.getObjetos()){
            if(verificarExistenciaInventario(i)){

                incrementarObjeto(i, i.getCantidad());
            } else {
                objetos.add(i);
            }
        }
    }

    public boolean verificarExistenciaInventario(final ItemInventario item){
        boolean existe = false;

        for(ItemInventario i : objetos){
            if(i.getId() == item.getId()){
                existe = true;
                break;
            }
        }

        return existe;
    }

    public boolean incrementarObjeto(final ItemInventario objeto, final int cantidad){

        boolean incrementado = false;

        for (ItemInventario itemInventario : objetos) {
            if (itemInventario.getId() == objeto.getId()) {

                System.out.println("Cantidad actual: " + itemInventario.getCantidad());

                System.out.println("Cantidad a incrementar: " + cantidad);

                itemInventario.incrementarCantidad(cantidad);
                incrementado = true;
                break;
            }
        }
        return incrementado;
    }

    public ArrayList<ItemInventario> getObjetos() {
        return objetos;
    }

    public void agregarObjeto(final ItemInventario objeto) {
        objetos.add(objeto);
    }

    public void eliminarObjeto(final ItemInventario objeto) {
        objetos.remove(objeto);
    }

    public void eliminarObjeto(final int posicion) {
        objetos.remove(posicion);
    }

    public void vaciarInventario() {
        objetos.clear();
    }
}