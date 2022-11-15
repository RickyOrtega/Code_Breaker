package principal.inventario;

import principal.inventario.prendas.Desarmardo;
import principal.inventario.prendas.OutfitOficina;
import principal.inventario.prendas.Prenda;
import principal.inventario.trofeos.Trofeos;

public class RegistroObjetos {
    public static ItemInventario getObjeto(final int id) {

        ItemInventario objeto = null;

        switch (id){
            //0 - 99: Recompensas
            case 0:
                objeto = new Trofeos(0, "Fibonacci", "Premio por completar tareas", 0);
                break;
            case 1:
                objeto = new Trofeos(1, "Bombilógica", "Premio por completar tareas", 0);
                break;
            // 100 - 199: Ropa
            case 100:
                objeto = new Desarmardo(100, "Prenda principal", "Buzo azul turquí. Super Hacker el bro.", 0, 0);
                break;
            case 101:
                objeto = new OutfitOficina(101, "Oficinista", "El propio asalariado.", 0, 0);
                break;

        }

        return objeto;
    }
}