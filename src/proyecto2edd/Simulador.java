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

    long reloj = 1;
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
    
    /**
     * Este procedimiento recibe un usuario, documento, tiempo de reloj y si es prioritario el documento para enviarlo a la cola.
     * @param u Un usuario que se desea imprimir los documentos.
     * @param d El documento qeu se desea imprimir.
     * @param tiempoReloj Un long del reloj.
     * @param esPrioritario Un booleano que marca si es prioritario el documento.
     */
    public Impresion enviarACola(Usuario u, Documento d, long tiempoReloj, boolean esPrioritario) {
        long prioridad = calcularEtiqueta(u, esPrioritario, tiempoReloj);
        Impresion nuevaImp = new Impresion(d, prioridad, u.getNombre());

        colaImpresion.insertar(nuevaImp);

        //Registrar en la HashTable para su acceso
        if (!impresiones.contiene(u.getNombre())) {
            impresiones.put(u.getNombre(), new Lista<>());
        }
        impresiones.get(u.getNombre()).insertar(nuevaImp);
        return nuevaImp;
    }
    
    /**
     * Esta función retorna un long que es la etiqueta calculada de tiempo que se tomara para imprimir.
     * @param user El usuario al que le pertenece los documentos.
     * @param esPrioritario Un booleano que marca si es prioritario o no.
     * @param tiempoReloj El tiempo en el reloj actual.
     * @return 
     */
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
    
    /**
     * Esta función recibe un nombre de usuario, y un documento para eliminar que este en la cola, retorna un booleano que es falso si no elimino o verdadero si logro eliminar.
     * @param nombreUsuario Un string que es el nombre del usuario.
     * @param docAEliminar El documento que se desea eliminar.
     * @return 
     */
    public boolean eliminarDocumentoDeCola(String nombreUsuario, Documento docAEliminar) {
        Lista<Impresion> listaEnCola = impresiones.get(nombreUsuario);
    if (listaEnCola == null) return false;

    Impresion impEncontrada = null;
    for (int i = 0; i < listaEnCola.getiN(); i++) {
        Impresion actual = listaEnCola.buscarPosición(i);
        if (actual.getDoc().getNombre().equals(docAEliminar.getNombre())) {
            impEncontrada = actual;
            break;
        }
    }

    if (impEncontrada != null && impEncontrada.getIndice() != -1) {
        int idx = impEncontrada.getIndice();
        
        // Verificación de seguridad extra
        if (idx > 0 && idx <= colaImpresion.getiN()) {
            // Forzamos que sea el mínimo absoluto para que suba a la raíz
            impEncontrada.setPrioridad(Long.MIN_VALUE); 
            colaImpresion.flotar(idx);
            
            // Ahora que es la raíz, eliminarMin lo sacará correctamente
            colaImpresion.eliminarMin(); 
            
            // Limpieza de referencias
            listaEnCola.remover(impEncontrada);
            docAEliminar.setEncola(false); 
            return true;
        }
    }
        return false;
    }
    
    /**
     * Esta función busca llamar para imprimir lo que esta primero en la cola, retornando el resultado de la impresión.
     * @return Una impresión que se libera de la cola.
     */
    public Impresion liberarImpresora() {
        if (colaImpresion.esVacio())return null;
        

        Impresion impresa = colaImpresion.eliminarMin();

        if (impresa != null) {
            impresa.getDoc().setEncola(false);
            Lista<Impresion> listaUser = impresiones.get(impresa.getNombreUsuario());
            if (listaUser != null) {
                listaUser.remover(impresa);
            }
        }
        return impresa;
    }
    
    /**
     * Una función que recibe un nombre y una prioridad y intenta agregarlos al motor, retornando un booleano que dice si fue exitosa la operación.
     * @param nombre Un string nombre del usuario.
     * @param prioridad Un string prioridad del usuario.
     * @return Un booleano que retorna verdadero si se logro con exito añadir o falso si ya existe.
     */
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
    
    /**
     * Una función que retorna la secuencia.
     * @return Un entero que sirve como secuencia.
     */
    public int getSecuencia() {
        return secuencia;
    }
    
     /**
     * Un procedimiento que recibe un entero y lo asigna como secuencia.
     * @param secuencia 
     */
    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }
    
    /**
     * Una función que retorna el hashtable con los usuarios registrados en el motor.
     * @return Un hashtable de los usuarios.
     */
    public HashTable<String, Usuario> getUsuarios()
    {
        return this.usuarios;
    }
    
    /**
     * 
     * @return 
     */
    public Lista<Impresion> getColaOrdenadaParaUI() {
        Lista<Impresion> listaParaUI = new Lista<>();
        MonticuloBinario copia = this.colaImpresion.clonar();
        while (!copia.esVacio()) {
            listaParaUI.insertar(copia.eliminarMin());
        }
        return listaParaUI;
    }
    
    /**
     * Esta función retorna la cola de impresión del simulador.
     * @return 
     */
    public MonticuloBinario getColaImpresion()
    {
        return this.colaImpresion;
    }
}
