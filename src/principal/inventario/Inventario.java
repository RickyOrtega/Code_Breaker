package principal.inventario;

import principal.inventario.prendas.Prenda;
import principal.inventario.trofeos.Trofeo;

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

    public ArrayList<ItemInventario> getTrofeos() {
        ArrayList<ItemInventario> trofeos = new ArrayList<ItemInventario>();

        for (ItemInventario i : objetos) {
            if(i instanceof Trofeo){
                trofeos.add(i);
            }
        }
        return trofeos;
    }

    public ArrayList<ItemInventario> getPrendas() {
        ArrayList<ItemInventario> prendas = new ArrayList<ItemInventario>();

        for (ItemInventario i : objetos) {
            if(i instanceof Prenda){
                prendas.add(i);
            }
        }
        return prendas;
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

    public ItemInventario getItem(final int id){
        for(ItemInventario i : objetos){
            if(i.getId() == id){
                return i;
            }
        }
        return null;
    }

    public boolean incrementarObjeto(final ItemInventario objeto, final int cantidad){

        boolean incrementado = false;

        for (ItemInventario itemInventario : objetos) {
            if (itemInventario.getId() == objeto.getId()) {

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