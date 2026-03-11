/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

/**
 *
 * @author cesar
 */
public class Nodo<T> {
    private T dato;
    private int altura;
    private Lista<Articulo> articulos;
    private Nodo izquierdo;
    private Nodo derecho;
    private String hash;

    public Nodo(T dato, String hash) {
        this.dato = dato;
        this.derecho = null;
        this.izquierdo = null;
        this.altura = 1;
        this.articulos = new Lista();
        this.hash = hash;
    }

    public Nodo(String hash) {
        this.hash = hash;
        this.derecho = null;
        this.izquierdo = null;
        this.altura = 1;
        this.articulos = new Lista();
    }
    
    

    public Lista<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(Lista<Articulo> articulos) {
        this.articulos = articulos;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    
}
