/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2edd;

import clases.Documento;
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
import primitivas.Lista;

/**
 *
 * @author cesar
 */
public class Simulador {

    long reloj;
    int secuencia;
    HashTable<String, Usuario> usuarios = new HashTable(10);
    HashTable<String, Lista<Impresion>> impresiones = new HashTable(10);
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
    
    public void enviarACola(Usuario u, Documento d, long tiempoReloj, boolean esPrioritario) {
        long prioridad = calcularEtiqueta(u, esPrioritario, tiempoReloj);
        Impresion nuevaImp = new Impresion(d, prioridad, u.getNombre());

        colaImpresion.insertar(nuevaImp);

        //Registrar en la HashTable para su acceso
        if (!impresiones.contiene(u.getNombre())) {
            impresiones.put(u.getNombre(), new Lista<>());
        }
        impresiones.get(u.getNombre()).insertar(nuevaImp);
    }
    
    public long calcularEtiqueta(Usuario user, boolean esPrioritario, long tiempoReloj) {
        if (!esPrioritario) {
            return tiempoReloj;
        }
        // Si es prioritario, reducimos el valor para que suba en el Min-Heap
        if (user.getPrioridad().equals("prioridad_alta")) {
            return tiempoReloj / 2;
        }
        if (user.getPrioridad().equals("prioridad_media")) {
            return (long) (tiempoReloj * 0.8);
        }
        return tiempoReloj;
    }
    
    public boolean eliminarDocumentoDeCola(String nombreUsuario, Documento docAEliminar) {
        Lista<Impresion> listaEnCola = impresiones.get(nombreUsuario);
        if (listaEnCola == null) {
            return false;
        }

        Impresion impEncontrada = null;

        for (int i = 0; i < listaEnCola.getiN(); i++) {
            Impresion actual = listaEnCola.buscarPosición(i);
            if (actual.getDoc().equals(docAEliminar)) {
                impEncontrada = actual;
                break;
            }
        }

        if (impEncontrada != null) {
            impEncontrada.setPrioridad(Long.MIN_VALUE);
            colaImpresion.flotar(impEncontrada.getIndice());

            colaImpresion.eliminarMin();

            listaEnCola.remover(impEncontrada);
            return true;
        }
        return false;
    }
    
    public Impresion liberarImpresora() {
        if (colaImpresion.esVacio())return null;
        

        Impresion impresa = colaImpresion.eliminarMin();

        if (impresa != null) {
            Lista<Impresion> listaUser = impresiones.get(impresa.getNombreUsuario());
            if (listaUser != null) {
                listaUser.remover(impresa);
            }
        }
        return impresa;
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
     *
     * @return Se retorna la impresión realizada.
     */
    public Impresion tic() {
        reloj++;
        return liberarImpresora();
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
