/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

/**
 *Es una lista primitiva simple con funciones de insertar y buscar posición.
 * @author cesar
 * @param <T> Un tipo de dato generico que se usa en lugar de un tipo de dato específico.
 */
public class Lista<T> {
    private NodoLista<T> pInicial;
    private NodoLista<T> pFinal;
    private int iN;

    public Lista() {
        this.iN = 0;
        this.pFinal = null;
        this.pInicial = null;
    }
    /**
     * Este procedimiento recibe un tipo de dato x, lo pone en un nodo y lo inserta a la lista, en el caso de que no hayan nodos en la lista, se le asigna como el primero, en el caso que no, se le asigna al siguiente del ultimo y a este nuevo se asigna como ultimo.
     * @param x El dato que queremos insertar al nodo para luego insertar a la lista.
     */
    public void insertar (T x){ 
        NodoLista pNew = new NodoLista(x);
        iN++;
        if (pFinal == null) {
            this.pFinal = this.pInicial = pNew;
        }
        else{
            this.pFinal.setpSig(pNew);
            this.pFinal = pNew;
        }
    }
    
    /**
     * Este procedimiento recibe un entero, lo busca en la lista y lo elimina.
     * @param iB La posición que queremos eliminar.
     */
    public void eliminar(int iB) {
        if (iB < 0 || iB >= iN) {
            System.out.println("Error: Índice fuera de rango.");
            return;
        }

        if (iB == 0) {
            pInicial = pInicial.getpSig();
            if (pInicial == null) {
                pFinal = null;
            }
        } else {
            NodoLista<T> aux = pInicial;
            for (int i = 0; i < iB - 1; i++) {
                aux = aux.getpSig();
            }

            NodoLista<T> aEliminar = aux.getpSig();

            aux.setpSig(aEliminar.getpSig());

            if (aEliminar == pFinal) {
                pFinal = aux;
            }
        }

        iN--;
    }
    
    /**
     * Busca un objeto x en la lista y lo elimina si lo encuentra.
     *
     * @param x El objeto que se desea eliminar.
     * @return true si el objeto fue encontrado y eliminado, false en caso
     * contrario.
     */
    public boolean remover(T x) {
        if (pInicial == null) {
            return false;
        }

        if (pInicial.getDato().equals(x)) {
            pInicial = pInicial.getpSig();
            if (pInicial == null) {
                pFinal = null;
            }
            iN--;
            return true;
        }

        NodoLista<T> anterior = pInicial;
        NodoLista<T> actual = pInicial.getpSig();

        while (actual != null) {
            if (actual.getDato().equals(x)) {

                anterior.setpSig(actual.getpSig());

                if (actual == pFinal) {
                    pFinal = anterior;
                }

                iN--;
                return true;
            }

            anterior = actual;
            actual = actual.getpSig();
        }

        return false;
    }
    
    /**
     * Esta función recibe un entero y luego va buscando hasta llegar a la posición del entero iB deseado, retornando el dato contenido en el nodo de esa dirección.
     * @param iB La posición que deseamos buscar en la lista, como precondición, no puede ser mayor a iN-1.
     * @return El dato que contiene la posición deseada.
     */
    public T buscarPosición (int iB){
        NodoLista aux =  pInicial;
        for (int i = 0; i < iB; i++) {
            aux = aux.pSig;
        }
        return (T) aux.dato;
    }
    
    /**
     * Esta función retorna el nodo inicial de la lista.
     * @return El nodo inicial de la lista.
     */
    public NodoLista<T> getpInicial() {
        return pInicial;
    }
    
    /**
     * Este procedimiento recibe un nodo de un tipo T y lo convierte en el pInicial de la lista.
     * @param pInicial Un nodo de tipo T que deseamos convertir en el primero de la lista.
     */
    public void setpInicial(NodoLista<T> pInicial) {
        this.pInicial = pInicial;
    }
    
    /**
     * Esta función retorna el nodo final de la lista.
     * @return El nodo final de la lista.
     */
    public NodoLista<T> getpFinal() {
        return pFinal;
    }
    
    /**
     * Este procedimiento recibe un nodo de tipo T y lo convierte en el pFinal de la lista.
     * @param pFinal Un nodo de tipo T que deseamos convertir en el ultimo de la lista.
     */
    public void setpFinal(NodoLista<T> pFinal) {
        this.pFinal = pFinal;
    }
    
    /**
     * Esta función retorna el tamaño de la lista.
     * @return El iN de la lista.
     */
    public int getiN() {
        return iN;
    }
    
    /**
     * Este procedimiento recibe un entero iN y lo convierte en el tamaño de la lista, se hace esto para no tocar la protección de iN directamente.
     * @param iN Un entero que queremos convertir al tamaño de la lista.
     */
    public void setiN(int iN) {
        this.iN = iN;
    }
}
