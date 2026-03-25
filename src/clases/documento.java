/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author cesar
 */
public class documento {
    private String nombre;
    private int paginas;
    private String tipo;
    private boolean encola;
    
    /**
     * Constructor del documento.
     * @param nombre Un string que es el nombre del documento.
     * @param paginas Un entero que es la cantidad de paginas que tiene.
     * @param tipo Un string que es el tipo de documento que es.
     * @param encola Un booleano que indica si esta en cola de impresión o no.
     */
    public documento(String nombre, int paginas, String tipo, boolean encola) {
        this.nombre = nombre;
        this.paginas = paginas;
        this.tipo = tipo;
        this.encola = encola;
    }
    
    /**
     * Una función que retorna el documento como un string.
     * @return Un string que representa el documento.
     */
    public String toString (){
        String retornar = "Documento {" + "nombre: " + nombre + ", paginas: " +  paginas + ", tipo: " + tipo + ", encola: " + encola + "}";
        return retornar;
    }
    
    /**
     * Una función que retorna el nombre del documento.
     * @return El string nombre del documento.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Un procedimiento que recibe un string y lo asigna como nombre del documento.
     * @param nombre El string que se desea asignar como nombre del documento.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Una función que retorna la cantidad de paginas que tiene el documento.
     * @return El entero contador de paginas que tiene el documento.
     */
    public int getPaginas() {
        return paginas;
    }
    
    /**
     * Un procedimiento que recibe un entero y lo convierte en la cantidad de paginas del documento.
     * @param paginas Un entero que se desea asignar como las paginas.
     */
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
    
    /**
     * Una función que retorna el tipo de documento.
     * @return Un string que es el tipo de documento.
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Un procedimiento que recibe un string y lo asigna como el tipo de documento.
     * @param tipo Un string que se desea asignar como el tipo de documento.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Una función que retorna si el documento esta en cola o no.
     * @return Un booleano que indica el estado de cola del documento.
     */
    public boolean isEncola() {
        return encola;
    }
    
    /**
     * Un procedimiento que recibe un booleano y lo asigna si el documento esta en cola o no.
     * @param encola Un booleano que indicara el estado de cola del documento.
     */
    public void setEncola(boolean encola) {
        this.encola = encola;
    }
    
}
