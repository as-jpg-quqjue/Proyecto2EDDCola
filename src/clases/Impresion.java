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
    
    

    public String getTrabajoID() {
        return trabajoID;
    }

    public void setTrabajoID(String trabajoID) {
        this.trabajoID = trabajoID;
    }

    public String getNombreDoc() {
        return nombreDoc;
    }

    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public long getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(long prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isPrioritario() {
        return prioritario;
    }

    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    
    
}
