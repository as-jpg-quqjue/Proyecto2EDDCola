/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

/**
 *Es un nodo simple con dato y un puntero.
 * @author cesar
 * @param <T>
 */
public class NodoLista<T> {
    protected T dato;
    protected NodoLista<T> pSig;

    public NodoLista(T dato) {
        this.dato = dato;
        this.pSig = null;
    }
    
    /**
     * Esta función retorna el dato guardado dentro del nodo.
     * @return 
     */
    
    public T getDato() { //esta funcion retorna el dato guardado dentro del nodo
        return dato;
    }
    
    /**
     * Este procedimiento recibe un dato tipo T y lo asigna como el dato del nodo.
     * @param dato El dato que se desea asignar como el .dato del nodo.
     */
    public void setDato(T dato) { //este procedimiento reemplaza el dato dentro del nodo
        this.dato = dato;
    }
    
    /**
     * Esta función retorna el siguiente nodo que tiene asignado este mismo nodo.
     * @return El siguiente nodo según el puntero de este nodo.
     */
    
    public NodoLista<T> getpSig() { //esta funcion retorna el psiguiente de un nodo de cualquier tipo
        return pSig;
    }
    
    
    /**
     * Este procedimiento recibe un nodo y lo asigna como el que le sigue a este nodo.
     * @param pSig Un dato tipo Nodo que se desea asignar como el siguiente en la lista.
     */
    public void setpSig(NodoLista<T> pSig) { //este procedimiento asigna un nodo de cualquier tipo como el psiguiente
        this.pSig = pSig;
    }
    
}
