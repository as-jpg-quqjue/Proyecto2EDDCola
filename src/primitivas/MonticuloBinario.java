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

    public MonticuloBinario() {
        this.impresiones = new Impresion[10];
        this.iN = 0;
    }
    
    public void insertar(Impresion imp){
        if (iN+1 == impresiones.length) {
            redimensionar();
        }
        iN++;
        impresiones[iN] = imp;
        imp.setIndice(iN);
        flotar(iN);
    }
    
    public void eliminarDocumentoEspecifico(Impresion imp) {
        int indexEnMonticulo = imp.getIndice();

        imp.setPrioridad(Long.MIN_VALUE);

        flotar(indexEnMonticulo);

        eliminarMin();
    }
    
    public void imprimirArbol(int i, int nivel) {
        if (i > iN) {
            return;
        }
        imprimirArbol(2 * i + 1, nivel + 1); // Derecha
        System.out.println("    ".repeat(nivel) + "[T:" + impresiones[i].getPrioridad() + "]");
        imprimirArbol(2 * i, nivel + 1);     // Izquierda
    }
    
    public Impresion eliminarMin (){
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
    
    public Impresion verMin(){
        if (iN != 0) {
            return impresiones[1];
        }
        return null;
    }
    
    public void flotar(int i){
        while(i>1 && impresiones[i].getPrioridad() < impresiones[i/2].getPrioridad()){
            intercambiar(i, i/2);
            i = i/2;
        }
    }
    
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
    
    private void hundir (int i){
        while(2*i <= iN){
            int hijo = 2*i;
            if (hijo+1 <= iN && impresiones[hijo+1].getPrioridad() < impresiones[hijo].getPrioridad()) {
                hijo++;
            }
            if (impresiones[i].getPrioridad() <= impresiones[hijo].getPrioridad()) {
                break;
            }
            intercambiar(i, hijo);
            i = hijo;
        }
    }
    
    public void redimensionar(){
        Impresion[] nuevo = new Impresion[impresiones.length*2];
        for (int i = 0; i < impresiones.length; i++) {
            nuevo[i] = impresiones[i];
        }
        
        impresiones = nuevo;
    }
    
    public boolean esVacio (){
        if (iN == 0) {
            return true;
        }
        return false;
    }

    public Impresion[] getImpresiones() {
        return impresiones;
    }

    public void setImpresiones(Impresion[] impresiones) {
        this.impresiones = impresiones;
    }

    public int getiN() {
        return iN;
    }

    public void setiN(int iN) {
        this.iN = iN;
    }
}
