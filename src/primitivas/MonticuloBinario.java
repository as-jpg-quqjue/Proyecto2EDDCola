/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

import clases.Impresion;

/**
 *
 * @author cesar
 */
public class MonticuloBinario {

    private Impresion[] impresiones;
    private int iN;

    /**
     * Constructor del monticulo.
     */
    public MonticuloBinario() {
        this.impresiones = new Impresion[10];
        this.iN = 0;
    }

    /**
     * Un procedimiento que inserta una impresión a la cola, redimensionando si
     * es necesario.
     *
     * @param imp
     */
    public void insertar(Impresion imp) {
        if (iN + 1 == impresiones.length) {
            redimensionar();
        }
        iN++;
        impresiones[iN] = imp;
        imp.setIndice(iN);
        flotar(iN);
    }

    /**
     * Este procedimiento recibe una impresión especifica y le pone un valor
     * minimo de prioridad, lo flota hasta el tope y lo elimina sin imprimir.
     *
     * @param imp Una impresión que se desea eliminar.
     */
    public void eliminarDocumentoEspecifico(Impresion imp) {
        int indexEnMonticulo = imp.getIndice();

        imp.setPrioridad(Long.MIN_VALUE);

        flotar(indexEnMonticulo);

        eliminarMin();
    }

    /**
     * Un procedimiento que imprime el arbol.
     *
     * @param i Un entero que es el índice del nodo actual en el arreglo (basado
     * en 1).
     * @param nivel Un entero que es el nivel de profundidad actual.
     */
    public void imprimirArbol(int i, int nivel) {
        if (i > iN) {
            return;
        }
        imprimirArbol(2 * i + 1, nivel + 1); // Derecha
        System.out.println("    ".repeat(nivel) + "[T:" + impresiones[i].getPrioridad() + "]");
        imprimirArbol(2 * i, nivel + 1);     // Izquierda
    }

    /**
     * Una función que elimina la impresión con la minima prioridad, manteniendo
     * el orden intercambiendo el ultimo elemento con el primero asi no se
     * elimina la raiz.
     *
     * @return La impresión eliminada.
     */
    public Impresion eliminarMin() {
        if (iN != 0) {
            Impresion min = impresiones[1];
            intercambiar(1, iN);
            impresiones[iN] = null;
            iN--;
            if (iN > 0) {
                hundir(1);
            }
            min.setIndice(-1);
            return min;
        }
        return null;
    }

    /**
     * Una función que retorna la impresión con la menor prioridad (menor
     * prioridad significa primero en el heap).
     *
     * @return La impresión con la menor prioridad, o si no existe un null.
     */
    public Impresion verMin() {
        if (iN != 0) {
            return impresiones[1];
        }
        return null;
    }

    /**
     * Un procedimiento que 'flota' un numero al tope del monticulo.
     *
     * @param i El entero que es el indice que se desea flotar hasta el tope.
     */
    public void flotar(int i) {
        while (i > 1 && impresiones[i].getPrioridad() < impresiones[i / 2].getPrioridad()) {
            intercambiar(i, i / 2);
            i = i / 2;
        }
    }

    /**
     * Un procedimiento que intercambia las posiciones de i y j.
     *
     * @param i Un entero que es el indice de uno de los elementos que se desea
     * intercambiar.
     * @param j Un entero que es el indice de uno de los elementos que se desea
     * intercambiar.
     */
    private void intercambiar(int i, int j) {
        Impresion aux = impresiones[i];
        impresiones[i] = impresiones[j];
        impresiones[j] = aux;

        if (impresiones[i] != null) {
            impresiones[i].setIndice(i);
        }
        if (impresiones[j] != null) {
            impresiones[j].setIndice(j);
        }
    }

    /**
     * Un procedimiento que 'hunde' un elemento de indice i hasta que sus hijos
     * no sean menores o hasta que el elemento sea menor o igual que sus hijos.
     *
     * @param i Un entero que marca el indice del elemento que se desea hundir.
     */
    private void hundir(int i) {
        while (2 * i <= iN) {
            int hijo = 2 * i;
            if (hijo + 1 <= iN && impresiones[hijo + 1].getPrioridad() < impresiones[hijo].getPrioridad()) {
                hijo++;
            }
            if (impresiones[i].getPrioridad() <= impresiones[hijo].getPrioridad()) {
                break;
            }
            intercambiar(i, hijo);
            i = hijo;
        }
    }

    /**
     * Un procedimiento que redimensiona el array de impresiones.
     */
    public void redimensionar() {
        Impresion[] nuevo = new Impresion[impresiones.length * 2];
        for (int i = 0; i < impresiones.length; i++) {
            nuevo[i] = impresiones[i];
        }

        impresiones = nuevo;
    }

    /**
     * Una función que retorna si el monticulo esta vacio o no.
     *
     * @return Un booleano que es verdadero si no tiene un elemento adentro, o
     * falso si hay almenos uno.
     */
    public boolean esVacio() {
        if (iN == 0) {
            return true;
        }
        return false;
    }

    /**
     * Una función que retorna las impresiones en el monticulo.
     *
     * @return
     */
    public Impresion[] getImpresiones() {
        return impresiones;
    }

    /**
     * Un procedimiento que recibe un array de impresiones y las inserta en el
     * monticulo.
     *
     * @param impresiones Las impresiones que se les desea asignar al monticulo.
     */
    public void setImpresiones(Impresion[] impresiones) {
        this.impresiones = impresiones;
    }

    /**
     * Una función que retorna el tamaño del monticulo.
     *
     * @return Un entero que es el tamaño del monticulo.
     */
    public int getiN() {
        return iN;
    }

    /**
     * Un procedimiento que recibe un entero y lo asigna como el contador de
     * tamaño del monticulo.
     *
     * @param iN
     */
    public void setiN(int iN) {
        this.iN = iN;
    }
    
    /**
     * Una función que retorna una copia de si misma.
     * @return Un monticulo binario identico a este.
     */
    public MonticuloBinario clonar() {
        MonticuloBinario copia = new MonticuloBinario();
        copia.impresiones = new Impresion[this.impresiones.length];
        copia.iN = this.iN;

        for (int i = 1; i <= iN; i++) {
            copia.impresiones[i] = this.impresiones[i];
        }
        return copia;
    }
}
