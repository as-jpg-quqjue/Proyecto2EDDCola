/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

/**
 *
 * @author cesar
 */
public class Nodo<k, v> {
    private k clave;
    private v valor;
    private Nodo<k, v> pNext;

    public Nodo(k clave, v valor) {
        this.clave = clave;
        this.valor = valor;
        this.pNext = null;
    }

    public k getClave() {
        return clave;
    }

    public void setClave(k clave) {
        this.clave = clave;
    }

    public v getValor() {
        return valor;
    }

    public void setValor(v valor) {
        this.valor = valor;
    }

    public Nodo getpNext() {
        return pNext;
    }

    public void setpNext(Nodo pNext) {
        this.pNext = pNext;
    }
    
}
