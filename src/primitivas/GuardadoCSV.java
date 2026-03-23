/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
 * to edit this template
 */
package primitivas;

import clases.Usuario;
import clases.documento;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase encargada de manejar la persistencia de datos mediante archivos CSV.
 * Permite guardar y cargar usuarios y sus documentos desde/archivo.
 * 
 * @author josep
 */
public class GuardadoCSV {

    private static final String DELIMITADOR = ",";
    private static final String EXTENSION = "csv";

    /**
     * Muestra un JFileChooser para seleccionar archivo CSV y carga los usuarios.
     * 
     * @param hashUsuarios se almacenarán los usuarios cargados
     * @return true si la carga fue exitosa, false en caso contrario.
     */
    public boolean cargarUsuariosDesdeCSV(HashTable<String, Usuario> hashUsuarios) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Cargar Usuarios desde CSV");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", EXTENSION));
        fileChooser.setAcceptAllFileFilterUsed(false);

        int opcion = fileChooser.showOpenDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            return procesarCarga(archivo, hashUsuarios);
        }
        return false;
    }

    /**
     * Procesa la lectura del archivo CSV y popula la tabla de usuarios.
     * 
     * @param archivo Archivo CSV a leer
     * @param hashUsuarios Tabla hash donde se almacenarán los usuarios
     * @return true si la carga fue exitosa
     */
    private boolean procesarCarga(File archivo, HashTable<String, Usuario> hashUsuarios) {
        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

            String linea;
            boolean primeraLinea = true;
            int usuariosCargados = 0;

            while ((linea = lector.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Saltar encabezado
                }

                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(DELIMITADOR);
                if (partes.length >= 2) {
                    String nombre = partes[0].trim();
                    String prioridad = partes[1].trim();

                    if (!nombre.isEmpty() && !prioridad.isEmpty()) {
                        Usuario usuario = new Usuario(nombre, prioridad);
                        hashUsuarios.put(nombre, usuario);
                        usuariosCargados++;
                    }
                }
            }

            System.out.println("✓ Usuarios cargados: " + usuariosCargados);
            return true;

        } catch (IOException e) {
            System.err.println("✗ Error al leer archivo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Muestra un JFileChooser para guardar usuarios en archivo CSV.
     * 
     * @param hashUsuarios Tabla hash con los usuarios a guardar
     * @return true si el guardado fue exitoso
     */
    public boolean guardarUsuariosEnCSV(HashTable<String, Usuario> hashUsuarios) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Usuarios en CSV");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", EXTENSION));
        fileChooser.setAcceptAllFileFilterUsed(false);

        int opcion = fileChooser.showSaveDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            // Asegurar extensión .csv
            if (!archivo.getName().toLowerCase().endsWith("." + EXTENSION)) {
                archivo = new File(archivo.getParentFile(), archivo.getName() + "." + EXTENSION);
            }
            return procesarGuardado(archivo, hashUsuarios);
        }
        return false;
    }

    /**
     * Procesa la escritura de usuarios en archivo CSV.
     * 
     * @param archivo Archivo donde se guardarán los datos
     * @param hashUsuarios Tabla hash con los usuarios
     * @return true si el guardado fue exitoso
     */
    private boolean procesarGuardado(File archivo, HashTable<String, Usuario> hashUsuarios) {
        try (BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {

            // Escribir encabezado
            escritor.write("usuario" + DELIMITADOR + "tipo");
            escritor.newLine();

            // Recorrer la tabla hash y escribir cada usuario
            Nodo[] tabla = hashUsuarios.getTabla();
            int count = 0;

            for (Nodo nodo : tabla) {
                while (nodo != null) {
                    Usuario usuario = (Usuario) nodo.getValor();
                    if (usuario != null) {
                        escritor.write(usuario.getNombre() + DELIMITADOR + usuario.getPrioridad());
                        escritor.newLine();
                        count++;
                    }
                    nodo = nodo.getpNext();
                }
            }

            System.out.println("✓ Usuarios guardados: " + count);
            return true;

        } catch (IOException e) {
            System.err.println("✗ Error al escribir archivo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Guarda los documentos de un usuario en un archivo CSV separado.
     * 
     * @param usuario Usuario cuyos documentos se guardarán
     * @param rutaDirectorio Directorio donde se guardará el archivo
     * @return true si el guardado fue exitoso
     */
    public boolean guardarDocumentosUsuario(Usuario usuario, String rutaDirectorio) {
        if (usuario == null || usuario.getCantidad() == 0) {
            return false;
        }

        String nombreArchivo = rutaDirectorio + File.separator + 
                              "docs_" + usuario.getNombre().replaceAll("[^a-zA-Z0-9]", "_") + ".csv";

        try (BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(nombreArchivo), StandardCharsets.UTF_8))) {

            escritor.write("documento" + DELIMITADOR + "paginas" + DELIMITADOR + 
                          "tipo" + DELIMITADOR + "encola");
            escritor.newLine();

            for (int i = 0; i < usuario.getCantidad(); i++) {
                documento doc = usuario.getDocumentos()[i];
                if (doc != null) {
                    escritor.write(doc.getNombre() + DELIMITADOR + 
                                  doc.getPaginas() + DELIMITADOR + 
                                  doc.getTipo() + DELIMITADOR + 
                                  doc.isEncola());
                    escritor.newLine();
                }
            }

            return true;

        } catch (IOException e) {
            System.err.println("✗ Error al guardar documentos: " + e.getMessage());
            return false;
        }
    }

    /**
     * Carga los documentos de un usuario desde un archivo CSV.
     * 
     * @param usuario Usuario al que se le asignarán los documentos
     * @param archivo Archivo CSV con los documentos
     * @return true si la carga fue exitosa
     */
    public boolean cargarDocumentosUsuario(Usuario usuario, File archivo) {
        if (usuario == null || !archivo.exists()) {
            return false;
        }

        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

            String linea;
            boolean primeraLinea = true;

            while ((linea = lector.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(DELIMITADOR);
                if (partes.length >= 4) {
                    String nombreDoc = partes[0].trim();
                    int paginas = Integer.parseInt(partes[1].trim());
                    String tipo = partes[2].trim();
                    boolean enCola = Boolean.parseBoolean(partes[3].trim());

                    documento doc = new documento(nombreDoc, paginas, tipo, enCola);
                    usuario.agregarDocumento(doc);
                }
            }

            return true;

        } catch (IOException | NumberFormatException e) {
            System.err.println("✗ Error al cargar documentos: " + e.getMessage());
            return false;
        }
    }
}