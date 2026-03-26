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
import clases.Impresion;
import primitivas.Lista;
import primitivas.MonticuloBinario;
/**
 *
 * @author miwindowspc
 */

public class LogicaInterfaz {

    private Graph graph;
    private Viewer viewer;
    private GuardadoCSV guardado = new GuardadoCSV();
    private InterfazGrafica ui;
    private Simulador motor; //SE AÑADIO ESTO PARA COMUNICARSE CON FUNCIONES DEL RELOJ Y DE AÑADIR USUARIOS
    private Graph graphColaS;
    private Graph graphColaArbol;
    private Node ultimoNodo;
    private String rutaBase = System.getProperty("user.dir");
    
    /**
     * El constructor de la logica de interfaz.
     *
     * @param ui Una InterfazGrafica que se desea conectar con la logica.
     * @param m Un motor (Proyecto2EDD) que se desea conectar para el reloj y
     * otras funciones.
     */
    public LogicaInterfaz(InterfazGrafica ui, Simulador m) {
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
        graphColaS = new SingleGraph("ColaViewSimple");
        graphColaArbol = new SingleGraph("ColaViewArbol");
    }
    
    /**
     * Un procedimiento que añade un usario a la UI.
     *
     * @param user Un usuario que se revisa si ya existe en la UI antes de
     * añadir.
     */
    public void AñadirUsuarioUI(Usuario user) {
        String username = user.getNombre();
        if (graph.getNode(username) == null) {
            var novo = graph.addNode(username);
            novo.setAttribute("ui.label", username + ", " + user.getPrioridad() + ", " + "Documentos: " + "0");
            novo.setAttribute("ui.class", user.getPrioridad());
        }

    }
    
    /**
     * Un procedimiento que muestra la ventana gráfica de los usuarios.
     */
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
    /**
     * Un procedimiento que muestra la ventana de la cola de impresión simple.
     */
    public void mostrarVentanaCola() {
        System.setProperty("org.graphstream.ui", "swing");
        Viewer viewer = graphColaS.display();
        viewer.disableAutoLayout();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }
    
    /**
     * Un procedimiento que muestra la ventana de la cola de impresión arbol.
     */
    public void mostrarVentanaColaArbol()
    {
       System.setProperty("org.graphstream.ui", "swing"); 
       Viewer viewer = graphColaArbol.display();
       viewer.disableAutoLayout();
       viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }

    
    /**
     * Un procedimiento que refresca la cola de UI.
     * @param colaDeImpresion La lista del monticulo binario convertido.
     */
    public void refrescarColaUI(Lista<Impresion> colaDeImpresion) {
       graphColaS.clear();
    
        String css = "node { shape: box; fill-color: #2ecc71; text-alignment: center; padding: 10px; size-mode: fit; stroke-mode: plain; stroke-color: black; } "
                   + "edge { fill-color: black; size: 2px; }";
        graphColaS.setAttribute("ui.stylesheet", css);

        MonticuloBinario copia = motor.getColaImpresion().clonar();

        int xSiguiente = 0;
        Node ultimo = null;

        while (!copia.esVacio()) {
            Impresion imp = copia.eliminarMin();
            String id = imp.toString();

            Node n = graphColaS.addNode(id);
            n.setAttribute("ui.label", id);
            n.setAttribute("xy", xSiguiente, 0); 

            if (ultimo != null) {
                graphColaS.addEdge(ultimo.getId() + "-" + n.getId(), ultimo, n);
            }

            ultimo = n;
            xSiguiente += 100; 
        }
    }
    
    public void refrescarColaArbolUI() {
        graphColaArbol.clear();

        String css = "node { "
                + "shape: box; "
                + "fill-color: #3498db; "
                + "text-alignment: center; "
                + "text-size: 14; "
                + "padding: 10px; "
                + "size-mode: fit; "
                + "stroke-mode: plain; "
                + "stroke-color: black; "
                + "} "
                + "edge { fill-color: black; size: 2px; }";

        graphColaArbol.setAttribute("ui.stylesheet", css);

        MonticuloBinario cola = motor.getColaImpresion().clonar();
        Impresion[] impresiones = cola.getImpresiones();
        int n = cola.getiN();

        if (n == 0) {
            return;
        }

        int maxNivel = (int) (Math.log(n) / Math.log(2));
        double separacionBaseX = 40.0;
        double separacionY = 50.0;

        for (int i = 1; i <= n; i++) {
            if (impresiones[i] == null) {
                continue;
            }

            String nodeId = "heap_" + i;
            Node nodo = graphColaArbol.addNode(nodeId);

            int nivel = (int) (Math.log(i) / Math.log(2));
            int inicioNivel = 1 << nivel;
            int posEnNivel = i - inicioNivel;
            int cantidadEnNivel = 1 << nivel;

            double anchoNivel = Math.pow(2, maxNivel - nivel) * separacionBaseX;
            double x = (posEnNivel - (cantidadEnNivel - 1) / 2.0) * anchoNivel;
            double y = -nivel * separacionY;

            nodo.setAttribute("xy", x, y);
            nodo.setAttribute("ui.label",
                    "Índice: " + i
                    + "\nID: " + impresiones[i].toString()
                    + "\nUser: " + impresiones[i].getNombreUsuario());
        }

        for (int i = 1; i <= n; i++) {
            if (impresiones[i] == null) {
                continue;
            }

            int hijoIzq = 2 * i;
            int hijoDer = 2 * i + 1;

            if (hijoIzq <= n && impresiones[hijoIzq] != null) {
                graphColaArbol.addEdge("edge_" + i + "_" + hijoIzq, "heap_" + i, "heap_" + hijoIzq);
            }

            if (hijoDer <= n && impresiones[hijoDer] != null) {
                graphColaArbol.addEdge("edge_" + i + "_" + hijoDer, "heap_" + i, "heap_" + hijoDer);
            }
        }
    }
    
     /**
     * Un procedimiento que recibe un nombre de usuario y un documento y lo
     * intenta añadir a aquel usuario, luego añadiendolo a la UI.
     *
     * @param username Un string que se desea buscar para ser el receptor del
     * nuevo documento.
     * @param doc Un documento que se desea otorgar a un usuario.
     */
    public void AnadirDocumentoUser(String username, Documento doc) {
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
    
    /**
     * Un procedimiento que recibe un usuario y lo intenta eliminar de la interfaz.
     * interfaz gráfica.
     *
     * @param user Un usuario que se desea eliminar.
     */
    public void eliminarUsuarioUI(Usuario user) {
        String username = user.getNombre();
        Documento[] docs = user.getDocumentos();

        Node usernode = graph.getNode(username);
        if (usernode != null) {
            for (Documento doc : docs) {
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
    
    /**
     * Un procedimiento que recibe una impresión y la añade a la UI de colas.
     * @param imp La impresión a añadir.
     */
    public void agregarAColaUI(Impresion imp) {
        String id = imp.toString();
        if (graphColaS.getNode(id) != null) {
            return;
        }

        Node nuevoNode = graphColaS.addNode(id);
        nuevoNode.setAttribute("ui.label", id);
        //int xPos = graphColaS.getNodeCount() * -50;
        //nuevoNode.setAttribute("xy", xPos, 0);

        if (ultimoNodo != null) {
            String edgeId = id + "_" + ultimoNodo.getId();
            graphColaS.addEdge(edgeId, nuevoNode, ultimoNodo);
        }

        ultimoNodo = nuevoNode;
    }

    /**
     * Un procedimiento que recibe un nombre de usuario y un documento para
     * eliminar de la interfaz gráfica. El botón de la interfaz gráfica ya a
     * este punto ha hecho el llamado para eliminar el documento del usuario.
     *
     * @param username Una string que es el nombre del usuario que se le desea
     * eliminar un documento.
     * @param doc El documento a eliminar.
     */
    public void eliminarDocumentoUI(String username, Documento doc) {
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
    
    
     /**
     * Una función que retorna el hashtable de los usuarios locales.
     *
     * @return
     */
    public HashTable<String, Usuario> getUsuariosLocal() {
        return motor.getUsuarios(); // 
    }
    
    /**
     * Una función que guarda los usuarios en el CSV y retorna si fue exitoso o
     * no.
     *
     * @return Un booleano que indica true si fue exitoso o false si no lo fue.
     */
    boolean guardarUsuariosEnCSV() {
        boolean exito = guardado.guardarUsuariosEnCSV(motor.getUsuarios(), rutaBase);
        if (exito)
        {
            ui.updateConsola("✓ Usuarios guardados exitosamente\n");
        }
        else{
            ui.updateConsola("✗ Error al guardar CSV\n");
        }
        return exito;
    }
    
    /**
     * Una función que intenta registrar a un usuario, recibiendo un nombre y
     * una prioridad, y retorna si fue exitoso o no.
     *
     * @param nombre Un string que es el nombre del usuario.
     * @param prioridad Un string que es la prioridad del usuario.
     * @return Un booleano que indica true si fue exitoso la adición del usuario
     * o false en lo contrario.
     */
    public boolean intentarRegistrarUsuario(String nombre, String prioridad) {
        return motor.agregarUsuario(nombre, prioridad);
    }
    

    /**
     * Una función que intenta cargar una CSV y retorna si fue exitoso o no.
     *
     * @return Un booleano que indica true si fue exitoso o false si no lo fue.
     */
    public boolean cargarUsuariosDesdeCSV() {
        graph.clear(); 
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

        boolean exito = guardado.cargarUsuariosDesdeCSV(motor.getUsuarios()); 

        if (exito) {
            HashTable<String, Usuario> listaCargada = motor.getUsuarios();

            for (int i = 0; i < listaCargada.getCapacidad(); i++) {
                var nodoHash = listaCargada.getTabla()[i];
                while (nodoHash != null) {
                    Usuario u = (Usuario) nodoHash.getValor();
                    AñadirUsuarioUI(u); 
                    nodoHash = nodoHash.getpNext();
                }
            }
            ui.updateConsola("✓ Usuarios cargados.\n");
        } else {
            ui.updateConsola("✗ Error al cargar CSV\n");
        }
        return exito;
    }

}
