/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2edd;

import clases.Impresion;
import clases.Usuario;
import interfaznew.InterfazGrafica;
import javax.swing.JFrame;
import primitivas.HashTable;
import primitivas.MonticuloBinario;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.spriteManager.*;

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
        System.setProperty("org.graphstream.ui", "swing");
        java.awt.EventQueue.invokeLater(() -> {
            new InterfazGrafica().setVisible(true);
        });

    }

    public boolean agregarUsuario(String nombre, String prioridad) {
        if (!usuarios.contiene(nombre)) {
            usuarios.put(nombre, new Usuario(nombre, prioridad));
            return true; //el usuario se añadio de forma exitosa
        }
        return false;
    }
    
    /**
     * Una función que recibe el nombre de un usuario y retorna si se eliminó de forma exitosa o no.
     * @param nombre Un string que es el nombre del usuario.
     * @return Un booleano si se eliminó o no el usuario.
     */
    public boolean eliminarUsuario(String nombre) {
        Usuario u = usuarios.get(nombre);
        if (u != null) {
            return usuarios.remover(nombre);
        }
        return false;
    }
    
    /**
     * Un procedimiento que avanza el tiempo del reloj.
     */
    public void tic() {
        reloj++;
    }
    
    /**
     * Una función que retorna el reloj.
     * @return Un long reloj.
     */
    public long getReloj() {
        return reloj;
    }
    
    /**
     * Este procedimiento recibe un long y lo convierte en el valor reloj del sistema.
     * @param reloj Un long que se desea convertir en el valor del reloj.
     */
    public void setReloj(long reloj) {
        this.reloj = reloj;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }
    
    public HashTable<String, Usuario> getUsuarios()
    {
        return this.usuarios;
    }
}
