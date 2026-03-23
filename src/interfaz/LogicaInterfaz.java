package interfaz;


import clases.Usuario;

import clases.Usuario;
import clases.documento;
import javax.swing.JOptionPane;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import primitivas.GuardadoCSV;
import primitivas.HashTable;


/**
 *
 * @author miwindowspc
 */
public class LogicaInterfaz {
    private Graph graph;
    private Viewer viewer;
    private GuardadoCSV guardado = new GuardadoCSV();
    private HashTable<String, Usuario> usuarios = new HashTable<>(50);

    public LogicaInterfaz() {
        System.setProperty("org.graphstream.ui", "swing");
        graph = new SingleGraph("UsuariosView");
        String css = "node { shape: box; fill-color: green; text-alignment: center; padding: 10px; size-mode: fit; } "
                   + "node.prioridad_alta { fill-color: red; } "
                   + "node.prioridad_media { fill-color: yellow; }"
                + "node.documento { "
                + "   size: 10px; "
                + "   fill-color: gray; "
                + "   shape: circle; "
                + "   stroke-mode: plain; "
                + "   stroke-color: black; "
                + "   z-index: 0; "
                + "} "
                + "edge { fill-color: #CCC; size: 1px; }";
        graph.setAttribute("ui.stylesheet", css);
    }
    public void AñadirUsuarioUI(Usuario user){
        String username = user.getNombre();
        if (graph.getNode(username) == null){
            var novo = graph.addNode(username);
            novo.setAttribute("ui.label", username + ", " + user.getPrioridad() + ", " + "Documentos: " + "0");
            novo.setAttribute("ui.class", user.getPrioridad());
        }
        
    }
    
    public void mostrarVentanaUser(){
        if (viewer == null) {
            viewer = graph.display();
        }
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }
    
    public void mostrarVentanaCola(){
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("ArbolCola");
        String css = "node {"
                + "shape: box;"
                + "stroke-mode: plain;"
                + "stroke-color: #000000;"
                + "fill-color: green;"
                + "text-alignment: center;"
                + "padding: 10, 15px;"
                + "size: 20px, 20px;"
                + "size-mode: fit;"
                + "text-size: 14;"
                + "}";

        graph.setAttribute("ui.stylesheet", css);
        Node n1 = graph.addNode("A");
        n1.setAttribute("ui.label", "ID: 102, Paginas: 15, Prioridad: Alta");
        n1.setAttribute("xy", 0, 0);
        Node n2 = graph.addNode("B");
        n2.setAttribute("ui.label", "ID: 105, Paginas: 15, Prioridad: Alta");
        n2.setAttribute("xy", -1, -1);
        graph.addEdge("AB", "A", "B");
        Viewer viewer = graph.display();
        viewer.disableAutoLayout();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }
    
    
public void AnadirDocumentoUser(String username, documento doc) {
    Usuario usuario = logica.getUsuarios().get(username); 
    
    if (usuario != null) {
        usuario.agregarDocumento(doc);
        
        
        Node nodoUser = graph.getNode(username);
        if (nodoUser != null) {
            String docname = doc.getNombre();
            Node docNode = graph.addNode(username + "-" + docname);
            docNode.setAttribute("ui.label", docname);
            docNode.setAttribute("ui.class", "documento");
            graph.addEdge(username + "-" + docname, username, username + "-" + docname);
        }
        output.append("✓ Documento '" + doc.getNombre() + "' añadido a " + username + "\n");
    } else {
        JOptionPane.showMessageDialog(null,
            "El usuario '" + username + "' no existe en el sistema.",
            "ERROR",
            JOptionPane.ERROR_MESSAGE);
    }
}


public HashTable<String, Usuario> getUsuarios() {
    return usuarios; // 
}

    boolean guardarUsuariosEnCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean cargarUsuariosDesdeCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
