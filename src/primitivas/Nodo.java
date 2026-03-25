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
    
    /**
     * El constructor del Nodo del hashtable.
     * @param clave Un tipo de dato k que sirve de clave.
     * @param valor Un tipo de dato v que sirve de valor.
     */
    public Nodo(k clave, v valor) {
        this.clave = clave;
        this.valor = valor;
        this.pNext = null;
    }
    
    /**
     * Esta función retorna la llave del nodo.
     * @return La llave guardada en el nodo.
     */
    public k getClave() {
        return clave;
    }
    
    /**
     * Este procedimiento recibe un tipo de dato k y lo asigna como su llave.
     * @param clave Un tipo de dato k que se desea convertir en la llave del nodo.
     */
    public void setClave(k clave) {
        this.clave = clave;
    }
    
    /**
     * Esta función retorna el valor del nodo.
     * @return Un dato tipo v del valor del nodo.
     */
    public v getValor() {
        return valor;
    }
    
    /**
     * Este procedimiento recibe un tipo de dato v y lo asigna como el valor guardado en este nodo.
     * @param valor Un dato generico que se desea guardar en el nodo.
     */
    public void setValor(v valor) {
        this.valor = valor;
    }
    
    /**
     * Esta función retorna el siguiente nodo que se apunte.
     * @return El nodo que sigue.
     */
    public Nodo getpNext() {
        return pNext;
    }
    
    /**
     * Este procedimiento recibe un Nodo y lo asigna como el siguiente en la lista.
     * @param pNext 
     */
    public void setpNext(Nodo pNext) {
        this.pNext = pNext;
    }
    
}
