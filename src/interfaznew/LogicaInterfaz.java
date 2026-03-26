package interfaznew;

import clases.Usuario;
import proyecto2edd.Simulador;
import clases.Usuario;
import clases.Documento;
import javax.swing.JOptionPane;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import primitivas.GuardadoCSV;
import primitivas.HashTable;
import primitivas.Nodo;

/**
 *
 * @author miwindowspc
     */
    public class LogicaInterfaz {
    private GuardadoCSV guardado = new GuardadoCSV();
    private InterfazGrafica ui;
    private Simulador motor;

    LogicaInterfaz(InterfazGrafica aThis, Simulador motor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
* Guarda usuarios y documentos en CSV
*/
public boolean guardarUsuariosEnCSV() {
    String rutaBase = System.getProperty("user.dir");
    boolean exito = guardado.guardarUsuariosEnCSV(motor.getUsuarios(), rutaBase);
    if (exito) {
        ui.updateConsola("✓ Usuarios y documentos guardados exitosamente\n");
    } else {
        ui.updateConsola("✗ Error al guardar CSV\n");
    }
    return exito;
}
    
    /**
* Carga usuarios y documentos desde CSV
*/
public boolean cargarUsuariosDesdeCSV() {
    boolean exito = guardado.cargarUsuariosDesdeCSV(motor.getUsuarios());
    if (exito) {
        ui.updateConsola("✓ Usuarios cargados exitosamente\n");
        actualizarUIUsuarios(); // ✅ Actualizar la interfaz gráfica
    } else {
        ui.updateConsola("✗ Error al cargar CSV\n");
    }
    return exito;
}

    
    // ✅ Método helper para actualizar UI después de cargar
public void actualizarUIUsuarios() {
    HashTable<String, Usuario> usuarios = motor.getUsuariosLocal();
    for (int i = 0; i < usuarios.getCapacidad(); i++) {
        Nodo<String, Usuario> nodo = usuarios.getTabla()[i];
        while (nodo != null) {
            Usuario user = nodo.getValor();
            if (user != null) {
                AñadirUsuarioUI(user);
                // Añadir documentos a la UI
                for (int j = 0; j < user.getCantidad(); j++) {
                    Documento doc = user.getDocumentos()[j];
                    if (doc != null) {
                        AnadirDocumentoUser(user.getNombre(), doc);
                    }
                }
            }
            nodo = nodo.getpNext();
        }
    }
}

    void AñadirUsuarioUI(Usuario user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void AnadirDocumentoUser(String nombre, Documento doc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void eliminarDocumentoUI(String username, Documento doc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void mostrarVentanaCola() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean intentarRegistrarUsuario(String nombre, String prioridad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void mostrarVentanaUser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    void eliminarUsuarioUI(Usuario usuarioExistente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}