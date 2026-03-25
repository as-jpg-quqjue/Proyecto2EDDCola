/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Objects;

/**
 *
 * @author cesar
 */
public class Impresion {
    private Documento documento;   
    private long prioridad;        
    private int indice;            
    private String nombreUsuario; 
    
    /**
     * El constructor de una impresión.
     * @param documento Un documento que se desea añadir a la impresión.
     * @param prioridad La prioridad del documento, la etiqueta del tiempo.
     * @param nombreUsuario Un string que es el nombre del usuario.
     */
    public Impresion(Documento documento, long prioridad, String nombreUsuario) {
        this.documento = documento;
        this.prioridad = prioridad;
        this.nombreUsuario = nombreUsuario;
        this.indice = -1; // -1 indica que aún no está en el montículo
    }
    
    /**
     * Una función que retorna el documento de la impresión.
     * @return El documento de la impresión.
     */
    public Documento getDoc() { return documento; }
    
    /**
     * Una función que retorna la etiqueta de tiempo de la impresión.
     * @return Un long que es la etiqueta de impresión.
     */
    public long getPrioridad() { return prioridad; }
    
    /**
     * Un procedimiento que recibe un long y lo asigna como la prioridad.
     * @param prioridad Un long que se convertira en la etiqueta de tiempo.
     */
    public void setPrioridad(long prioridad) { this.prioridad = prioridad; }
    
    /**
     * Una función que retorna el indice en el monticulo binario de esta impresión.
     * @return El entero que es el indice local de la impresión.
     */
    public int getIndice() { return indice; }
    
    /**
     * Un procedimiento que recibe un entero y lo asigna como el indice local.
     * @param indice Un entero que se desea asignar como indice.
     */
    public void setIndice(int indice) { this.indice = indice; }
    
    /**
     * Esta función retorna el nombre de usuario que solicitó la impresión.
     * @return Un string nombre del usuario de la impresión.
     */
    public String getNombreUsuario() { return nombreUsuario; }
    
    /**
     * Una función que retorna la impresión en forma de un string.
     * @return La impresión en forma de string.
     */
    @Override
    public String toString() {
        return "[" + documento.getNombre() + " | T:" + prioridad + "]";
    }
}
