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
    private documento[] documentos;
    private int cantidad;
    
    /**
     * Constructor del usuario.
     * @param nombre Un string que es el nombre que se le desea dar al usuario.
     * @param prioridad Un string que es la prioridad que se le desea dar al usuario.
     */
    public Usuario(String nombre, String prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.documentos = new documento[10];
        this.cantidad = 0;
    }
    
    /**
     * Un procedimiento que agranda el array de los documentos.
     */
    public void redimensionar(){
        documento[] nuevo = new documento[documentos.length*2];
        for (int i = 0; i < documentos.length; i++) {
            nuevo[i] = documentos[i];
        }
        
        documentos = nuevo;
    }
    
    /**
     * Una función que retorna el nombre del usuario.
     * @return Un string que es el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Un procedimiento que recibe un nombre y lo asigna como nombre del usuario.
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Una función que retorna la prioridad.
     * @return Un string que es la prioridad del usuario.
     */
    public String getPrioridad() {
        return prioridad;
    }
    
    /**
     * Un procedimiento que recibe un string y lo asigna como la prioridad.
     * @param prioridad Un string que se desea convertir en la prioridad del usuario.
     */
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    
    /**
     * Una función que retorna los documentos asignados al usuario.
     * @return Un array que son los documentos del usuario.
     */
    public documento[] getDocumentos() {
        return documentos;
    }
    
    /**
     * Un procedimiento que agrega un documento al usuario, agrandando el array de documentos si es necesario.
     * @param doc Un documento que se desea agregar.
     */
    public void agregarDocumento(documento doc){
        if (cantidad == documentos.length) {
            redimensionar();
        }
        documentos[cantidad++] = doc;
    }
    
    /**
     * Una función que recibe un nombre y lo busca en los documentos actuales del usuario, si existe lo elimina y retorna un booleano de su estado de exito.
     * @param nombre Un string nombre de un documento.
     * @return 
     */
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
    
    /**
     * Una función que busca si un documento existe en el array del usuario.
     * @param nombre Un string nombre del documento que se desea buscar.
     * @return Un documento si existe, si no existe se retorna null.
     */
    public documento buscar(String nombre){
        for (int i = 0; i < cantidad; i++) {
            if (documentos[i].getNombre().equals(nombre)) {
                return documentos[i];
            }
        }
        return null;
    }
    
    /**
     * Un procedimiento que recibe un array de documentos y los asigna como los documentos del usuario.
     * @param documentos 
     */
    public void setDocumentos(documento[] documentos) {
        this.documentos = documentos;
    }
    
    /**
     * Una función que retorna la cantidad de documentos que se tienen.
     * @return Un entero que es la cantidad de documentos que tiene el usuario.
     */
    public int getCantidad() {
        return cantidad;
    }
    
    /**
     * Un procedimiento que recibe un entero y lo asigna como el contador de documentos.
     * @param cantidad Un entero que se desea convertir en el contador de documentos del usuario.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
