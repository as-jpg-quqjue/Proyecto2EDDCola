package interfaznew;

import clases.Usuario;
import proyecto2edd.Proyecto2EDD;
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
    private InterfazGrafica ui;
    private Proyecto2EDD motor; //SE AÑADIO ESTO PARA COMUNICARSE CON FUNCIONES DEL RELOJ Y DE AÑADIR USUARIOS

    public LogicaInterfaz(InterfazGrafica ui, Proyecto2EDD m) {
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
        this.ui = ui;
        this.motor = m;
    }

    public void AñadirUsuarioUI(Usuario user) {
        String username = user.getNombre();
        if (graph.getNode(username) == null) {
            var novo = graph.addNode(username);
            novo.setAttribute("ui.label", username + ", " + user.getPrioridad() + ", " + "Documentos: " + "0");
            novo.setAttribute("ui.class", user.getPrioridad());
        }

    }

    public void mostrarVentanaUser() {
    if (viewer == null) {
        viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    } else {
        // Acceso correcto a la vista en GraphStream 2.0+
        org.graphstream.ui.view.View view = viewer.getDefaultView();
        
        if (view instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) view).setVisible(true);
            ((javax.swing.JFrame) view).toFront();
        } else if (view instanceof javax.swing.JPanel) {
            // Si la vista está incrustada en un panel, busca el ancestro JFrame
            java.awt.Component parent = (java.awt.Component) view;
            while (parent != null && !(parent instanceof javax.swing.JFrame)) {
                parent = parent.getParent();
            }
            if (parent != null) {
                ((javax.swing.JFrame) parent).setVisible(true);
                ((javax.swing.JFrame) parent).toFront();
            }
        }
    }
}

    public void mostrarVentanaCola() {
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
        Usuario usuario = (Usuario) motor.getUsuarios().get(username);

        if (usuario != null) {
            usuario.agregarDocumento(doc);

            Node nodoUser = graph.getNode(username);
            if (nodoUser != null) {
                String docname = doc.getNombre();
                String docId = username + "-" + docname;
                //AÑADIR CHEQUEO POR SI EXISTE EL DOCUMENTO YA EN EL USUARIO
                if (graph.getNode(docId) == null) {
                    Node docNode = graph.addNode(docId);
                    docNode.setAttribute("ui.label", "Nombre: " + docname + ", Paginas: " + doc.getPaginas() + ", Tipo: " + doc.getTipo());
                    docNode.setAttribute("ui.class", "documento");
                    graph.addEdge(docId, username, docId);
                    int totalDocs = usuario.getCantidad();
                    nodoUser.setAttribute("ui.label", username + ", " + usuario.getPrioridad() + ", Docs: " + totalDocs);
                }
            }
            ui.updateConsola("✓ Documento '" + doc.getNombre() + "' añadido a " + username + "\n");
            JOptionPane.showMessageDialog(ui, "Usuario registrado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "El usuario '" + username + "' no existe en el sistema.",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            ui.updateConsola("✗ Error al guardar documento: El usuario no existe.");
        }
    }

    public void eliminarUsuarioUI(Usuario user) {
        String username = user.getNombre();
        documento[] docs = user.getDocumentos();

        Node usernode = graph.getNode(username);
        if (usernode != null) {
            for (documento doc : docs) {
                if (doc != null) {
                    String docId = username + "-" + doc.getNombre();
                    if (graph.getNode(docId) != null) {
                        graph.removeNode(docId);
                    }
                }
            }
            graph.removeNode(username);
            ui.updateConsola("✓ Usuario " + username + " eliminado.\n");
            JOptionPane.showMessageDialog(ui, "Usuario eliminado con éxito.");
        } else {
            ui.updateConsola("✗ Error: El nodo del usuario no existe.\n");
        }
    }

    public void eliminarDocumentoUI(String username, documento doc) {
        String docId = username + "-" + doc.getNombre();
        if (graph.getNode(docId) != null) {
            graph.removeNode(docId);

            Usuario usuario = (Usuario) motor.getUsuarios().get(username);
            Node nodoUser = graph.getNode(username);
            if (nodoUser != null && usuario != null) {
                nodoUser.setAttribute("ui.label", username + ", " + usuario.getPrioridad() + ", Docs: " + usuario.getCantidad());
            }
        }
    }
    
    

    public HashTable<String, Usuario> getUsuariosLocal() {
        return motor.getUsuarios(); // 
    }

    boolean guardarUsuariosEnCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean cargarUsuariosDesdeCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean intentarRegistrarUsuario(String nombre, String prioridad) {
        return motor.agregarUsuario(nombre, prioridad);
    }

}
