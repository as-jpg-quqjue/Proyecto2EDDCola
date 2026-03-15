/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2edd;

import clases.Impresion;
import clases.Usuario;
import primitivas.HashTable;
import primitivas.MonticuloBinario;


/**
 *
 * @author cesar
 */
public class Proyecto2EDD {

    long reloj;
    int secuencia;
    HashTable<String, Usuario> usuarios = new HashTable(10);
        HashTable<String, Impresion> impresiones = new HashTable(10);
        MonticuloBinario colaImpresion = new MonticuloBinario();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        
    }
    
    public boolean agregarUsuario (String nombre, String prioridad){
        if (usuarios.contiene(nombre)) {
            usuarios.put(nombre, new Usuario(nombre, prioridad));
            return true;
        }
        return false;
    }
    
    public boolean eliminarUsuario (String nombre){
        Usuario u = usuarios.get(nombre);
        if (u != null) {
            return usuarios.remover(nombre);
        }
        return false;
    }
    
    public void tic(){
        reloj++;
    }

    public long getReloj() {
        return reloj;
    }

    public void setReloj(long reloj) {
        this.reloj = reloj;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }
    
    
    
}
