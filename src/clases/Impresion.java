/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author cesar
 */
public class Impresion {
    private String trabajoID;
    private String nombreDoc;
    private int paginas;
    private String tipoDoc;
    private long tiempo;
    private long prioridad;
    private boolean prioritario;
    private int indice;
    
    /**
     * Constructor de impresiones.
     * @param trabajoID La string ID del trabajo.
     * @param nombreDoc La string que es el nombre del documento.
     * @param paginas Un entero que es la cantidad de paginas.
     * @param tipoDoc Un string que es el tipo de documento.
     * @param tiempo Un long que es el tiempo que tardara en imprimir(?).
     * @param prioridad Un long que es la prioridad que se tiene.
     * @param prioritario Un booleano que dice si es prioritario o no.
     */
    public Impresion(String trabajoID, String nombreDoc, int paginas, String tipoDoc, long tiempo, long prioridad, boolean prioritario) {
        this.trabajoID = trabajoID;
        this.nombreDoc = nombreDoc;
        this.paginas = paginas;
        this.tipoDoc = tipoDoc;
        this.tiempo = tiempo;
        this.prioridad = prioridad;
        this.prioritario = prioritario;
        this.indice = -1;
    }
    
    
    /**
     * Una función que retorna el ID de trabajo.
     * @return Una string que es la ID del trabajo.
     */
    public String getTrabajoID() {
        return trabajoID;
    }
    
    /**
     * Un procedimiento que recibe un string y lo asigna como la ID de trabajo.
     * @param trabajoID Una string que se desea asignar como la ID del trabajo.
     */
    public void setTrabajoID(String trabajoID) {
        this.trabajoID = trabajoID;
    }
    
    /**
     * Una función que retorna el nombre del documento.
     * @return Un string que es el nombre del documento.
     */
    public String getNombreDoc() {
        return nombreDoc;
    }
    
    /**
     * Un procedimiento que recibe un string y lo asigna como el nombre del documento.
     * @param nombreDoc Un string que se desea asignar como el nombre del documento.
     */
    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    /**
     * Una función que retorna la cantidad de páginas.
     * @return Un entero que es la cantidad de páginas.
     */
    public int getPaginas() {
        return paginas;
    }

    /**
     * Un procedimiento que recibe un entero y lo asigna como la cantidad de páginas.
     * @param paginas Un entero que se desea asignar como la cantidad de páginas.
     */
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    /**
     * Una función que retorna el tipo de documento.
     * @return Un string que es el tipo de documento.
     */
    public String getTipoDoc() {
        return tipoDoc;
    }

    /**
     * Un procedimiento que recibe un string y lo asigna como el tipo de documento.
     * @param tipoDoc Un string que se desea asignar como el tipo de documento.
     */
    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    /**
     * Una función que retorna el tiempo que tardará en imprimir.
     * @return Un long que es el tiempo de impresión.
     */
    public long getTiempo() {
        return tiempo;
    }

    /**
     * Un procedimiento que recibe un long y lo asigna como el tiempo de impresión.
     * @param tiempo Un long que se desea asignar como el tiempo de impresión.
     */
    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * Una función que retorna la prioridad que se tiene.
     * @return Un long que es la prioridad.
     */
    public long getPrioridad() {
        return prioridad;
    }

    /**
     * Un procedimiento que recibe un long y lo asigna como la prioridad.
     * @param prioridad Un long que se desea asignar como la prioridad.
     */
    public void setPrioridad(long prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Una función que retorna si el trabajo es prioritario o no.
     * @return Un booleano que dice si es prioritario.
     */
    public boolean isPrioritario() {
        return prioritario;
    }

    /**
     * Un procedimiento que recibe un booleano y lo asigna al estado prioritario.
     * @param prioritario Un booleano que se desea asignar como estado prioritario.
     */
    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }

    /**
     * Una función que retorna el valor del índice.
     * @return Un entero que es el índice.
     */
    public int getIndice() {
        return indice;
    }

    /**
     * Un procedimiento que recibe un entero y lo asigna como el índice.
     * @param indice Un entero que se desea asignar como el índice.
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }
}