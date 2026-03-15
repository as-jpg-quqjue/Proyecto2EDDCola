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

    public documento(String nombre, int paginas, String tipo, boolean encola) {
        this.nombre = nombre;
        this.paginas = paginas;
        this.tipo = tipo;
        this.encola = encola;
    }
    
    
    
    public String toString (){
        String retornar = "Documento {" + "nombre: " + nombre + ", paginas: " +  paginas + ", tipo: " + tipo + ", encola: " + encola + "}";
        return retornar;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEncola() {
        return encola;
    }

    public void setEncola(boolean encola) {
        this.encola = encola;
    }
    
}
