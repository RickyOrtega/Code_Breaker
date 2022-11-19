package principal.inventario;

import principal.Constantes;
import principal.sprites.Sprite;
import principal.sprites.SpriteSheet;

import java.awt.*;

public abstract class ItemInventario {
    protected final int id;
    protected final String nombre;
    protected final String descripcion;
    protected int cantidad;
    protected int cantidadMaxima;

    public ItemInventario(final int id, final String nombre, final String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;

        cantidad = 0;
        cantidadMaxima = 99;
    }

    public ItemInventario(final int id, final String nombre, final String descripcion, final int cantidad) {
        this(id, nombre, descripcion);

        if(cantidad <= cantidadMaxima) {
            this.cantidad = cantidad;
        }
    }

    public boolean incrementarCantidad(final int incremento) {
        boolean incrementado = false;

        if(cantidad + incremento <= cantidadMaxima){
            cantidad += incremento;
            incrementado = true;
        }

        return incrementado;
    }

    public boolean decrementarCantidad(final int decremento) {
        boolean decrementado = false;

        if(cantidad - decremento >= 0){
            cantidad -= decremento;
            decrementado = true;
        }

        return decrementado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public abstract Sprite getSprite();

    public int getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidad(final int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCantidadMaxima(final int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }
}