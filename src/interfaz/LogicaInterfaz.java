package interfaz;


import clases.Usuario;

import clases.Usuario;
import clases.documento;
import javax.swing.JOptionPane;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;


/**
 *
 * @author miwindowspc
 */
public class LogicaInterfaz {
    private Graph graph;
    private Viewer viewer;

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
    
    public void AnadirDocumentoUser(Usuario user, documento doc){
        Node nodoUser = graph.getNode(user.getNombre());
        String docname = doc.getNombre();
        if (nodoUser != null){
            Node docNode = graph.addNode(docname);
            docNode.setAttribute("ui.label", docname);
            docNode.setAttribute("ui.class", "documento"); // Aplicamos el CSS pequeño
            
            // 3. Los conectamos con un Edge (una línea)
            graph.addEdge(user.getNombre() + "-" + docname, (user.getNombre()), docname);
        }
        else {
            javax.swing.JOptionPane.showMessageDialog(null,
                "El usuario " + user.getNombre() + " no existe en el sistema.",
                "ERROR",
                javax.swing.JOptionPane.ERROR_MESSAGE);;
        }
    }
}
