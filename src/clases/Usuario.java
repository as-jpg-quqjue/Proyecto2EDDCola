/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author cesar
 */
public class Usuario {
    private String nombre;
    private String prioridad;
    private Documento[] documentos;
    private int cantidad;

    public Usuario(String nombre, String prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.documentos = new Documento[10];
        this.cantidad = 0;
    }
    
    public void redimensionar(){
        Documento[] nuevo = new Documento[documentos.length*2];
        for (int i = 0; i < documentos.length; i++) {
            nuevo[i] = documentos[i];
        }
        
        documentos = nuevo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Documento[] getDocumentos() {
        return documentos;
    }
    
    public void agregarDocumento(Documento doc){
        if (cantidad == documentos.length) {
            redimensionar();
        }
        documentos[cantidad++] = doc;
    }
    
    public boolean eliminarDocumento(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (documentos[i].getNombre().equals(nombre)) {
                if (documentos[i].isEncola()) {
                    return false;
                }
                for (int j = i; j < cantidad-1; j++) {
                    documentos[j] = documentos[j+1];
                }
                documentos[cantidad-1]=null;
                cantidad--;
                return true;
            }
        }
        return false;
    }
    
    public Documento buscar(String nombre){
        for (int i = 0; i < cantidad; i++) {
            if (documentos[i].getNombre().equals(nombre)) {
                return documentos[i];
            }
        }
        return null;
    }

    public void setDocumentos(Documento[] documentos) {
        this.documentos = documentos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
