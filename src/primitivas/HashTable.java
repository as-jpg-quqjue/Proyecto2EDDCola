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

    public HashTable(int capacidad) {
        this.tabla = new Nodo[capacidad];
        this.capacidad = capacidad;
        this.cantidad = 0;
    }
    
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
    
    public boolean contiene(k clave){
        return get(clave) != null;
    }
    
    private int hash (k clave){
        return Math.abs(clave.hashCode())%capacidad;
    }
    
    public Nodo[] getTabla() {
        return tabla;
    }

    public void setTabla(Nodo[] tabla) {
        this.tabla = tabla;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
