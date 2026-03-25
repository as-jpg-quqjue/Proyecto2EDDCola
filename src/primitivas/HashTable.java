/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

/**
 *
 * @author cesar
 */
public class HashTable<k, v> {
    private Nodo[] tabla;
    int capacidad;
    int cantidad;
    
    /**
     * El constructor del HashTable.
     * @param capacidad El tamaño del hashtable maximo al iniciar.
     */
    public HashTable(int capacidad) {
        this.tabla = new Nodo[capacidad];
        this.capacidad = capacidad;
        this.cantidad = 0;
    }
    
    /**
     * Un procedimiento que coloca un valor y su clave a la tabla.
     * @param clave Un dato generico que sirve de clave.
     * @param valor Un dato generico que sirve de valor.
     */
    public void put(k clave, v valor){
        int i = hash(clave);
        Nodo aux = tabla[i];
        while(aux != null){
            if (aux.getClave().equals(clave)) {
                aux.setValor(valor);
                return;
            }
            aux = aux.getpNext();
            
        }
        Nodo aux2 = new Nodo(clave, valor);
        aux2.setpNext(tabla[i]);
        tabla[i] = aux2;
        cantidad++;
    }
    
    /**
     * Una función que retorna el valor segun una clave.
     * @param clave La clave del objeto deseado.
     * @return El objeto que se busca.
     */
    public v get(k clave){
        int i = hash(clave);
        Nodo aux = tabla[i];
        while(aux != null){
            if (aux.getClave().equals(clave)) {
                return (v) aux.getValor();
            }
        }
        return null;
        
    }
    
    /**
     * Una función que recibe una clave y quita el valor y la clave de la tabla.
     * @param clave La clave del objeto que se desea eliminar.
     * @return Un booleano que dice si fue exitosa la eliminación fue exitosa o no.
     */
    public boolean remover(k clave){
        int i = hash(clave);
        Nodo aux1 = tabla[i];
        Nodo aux2 = null;
        while(aux1 != null){
            if (aux1.getClave().equals(clave)) {
                if (aux2 == null) {
                    tabla[i] = aux1.getpNext();
                }else{
                    aux2.setpNext(aux1.getpNext());
                }
                cantidad--;
                return true;
            }
            aux2 = aux1;
            aux1 = aux1.getpNext();
            
        }
        return false;
    }
    
    /**
     * Una función que retorna si existe algo en la tabla.
     * @param clave La clave del objeto que se busca.
     * @return Un booleano que dice si ya existe el objeto en la tabla.
     */
    public boolean contiene(k clave){
        return get(clave) != null;
    }
    
    /**
     * Una función que toma una clave y lo convierte en el valor absoluto del hashcode de si mismo.
     * @param clave Una clave que se desea convertir en hash.
     * @return Un entero positivo hash.
     */
    private int hash (k clave){
        return Math.abs(clave.hashCode())%capacidad;
    }
    
    /**
     * Una función que retorna la tabla local del hashtable.
     * @return Un array de nodos que es la tabla local.
     */
    public Nodo[] getTabla() {
        return tabla;
    }
    
    /**
     * Un procedimiento que recibe un array de nodos y lo convierte en la tabla.
     * @param tabla Un array de nodos que se desea convertir en la tabla.
     */
    public void setTabla(Nodo[] tabla) {
        this.tabla = tabla;
    }
    
    /**
     * Una función que retorna la capacidad.
     * @return Un entero que dice la capacidad del array de la tabla.
     */
    public int getCapacidad() {
        return capacidad;
    }
    
    /**
     * Un procedimiento que define la capacidad del HashTable.
     * @param capacidad Un enteor que se desea definir como capacidad.
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    /**
     * Una función que retorna la cantidad de objetos en la tabla.
     * @return Un entero que es la cantidad de objetos en la tabla.
     */
    public int getCantidad() {
        return cantidad;
    }
    
    /**
     * Un procedimiento que recibe un entero y lo define como la cantidad de cosas en la tabla.
     * @param cantidad Un entero que se desea definir com la cantidad de cosas en la tabla.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
