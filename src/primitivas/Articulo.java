/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

/**
 *
 * @author cesar
 */
public class Articulo {
    private String titulo;
    private String resumen;
    private String[] autores;
    private String[] claves;

    public Articulo(String titulo, String resumen, String[] autores, String[] claves) {
        this.titulo = titulo;
        this.resumen = resumen;
        this.autores = autores;
        this.claves = claves;
    }
    
    public Articulo(String titulo, String resumen) {
        this.titulo = titulo;
        this.resumen = resumen;
        this.autores = null;
        this.claves = null;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String[] getAutores() {
        return autores;
    }

    public void setAutores(String[] autores) {
        this.autores = autores;
    }

    public String[] getClaves() {
        return claves;
    }

    public void setClaves(String[] claves) {
        this.claves = claves;
    }
    
    
}
