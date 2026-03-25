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
    private Documento documento;   // El objeto con nombre, tamaño, etc.
    private long prioridad;        // La etiqueta de tiempo (calculada con el reloj)
    private int indice;            // SU POSICIÓN ACTUAL en el arreglo del Montículo
    private String nombreUsuario;  // Referencia al dueño (útil para limpieza en la HashTable)

    public Impresion(Documento documento, long prioridad, String nombreUsuario) {
        this.documento = documento;
        this.prioridad = prioridad;
        this.nombreUsuario = nombreUsuario;
        this.indice = -1; // -1 indica que aún no está en el montículo
    }
    
    // Getters y Setters
    public Documento getDoc() { return documento; }
    
    public long getPrioridad() { return prioridad; }
    public void setPrioridad(long prioridad) { this.prioridad = prioridad; }

    public int getIndice() { return indice; }
    public void setIndice(int indice) { this.indice = indice; }

    public String getNombreUsuario() { return nombreUsuario; }

    @Override
    public String toString() {
        return "[" + documento.getNombre() + " | T:" + prioridad + "]";
    }
}
