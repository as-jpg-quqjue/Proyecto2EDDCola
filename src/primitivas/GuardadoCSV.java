/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
 * to edit this template
 */
package primitivas;

import clases.Usuario;
import clases.Documento;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase encargada de manejar la persistencia de datos mediante archivos CSV.
 * Permite guardar y cargar usuarios y sus documentos desde/archivo.
 * 
 * @author josep
 */
public class GuardadoCSV {
    private static final String DELIMITADOR = ",";
    private static final String EXTENSION = "csv";
    private static final String CODIFICACION = "UTF-8";
    
    /**
     * Guarda la información de los usuarios y sus documentos en un CSV.
     * Crea una carpeta de documentos en la ruta base y recorre la tabla hash 
     * para exportar cada usuario.
     *
     * @param archivo Destino del archivo CSV.
     * @param hashUsuarios Tabla con los datos de usuarios.
     * @param rutaBase Directorio donde se guardarán los archivos.
     * @return true si se guardó todo bien, false si hubo un error de escritura.
     */
    private boolean procesarGuardado(File archivo, HashTable<String, Usuario> hashUsuarios, String rutaBase) {
    try (BufferedWriter escritor = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
        
        escritor.write("usuario" + DELIMITADOR + "prioridad" + DELIMITADOR + "cantidad_documentos");
        escritor.newLine();
        
        Nodo[] tabla = hashUsuarios.getTabla();
        int count = 0;
        File carpetaDocs = new File(rutaBase + File.separator + "documentos");
        carpetaDocs.mkdirs();
        
        for (Nodo nodo : tabla) {
            while (nodo != null) {
                Usuario usuario = (Usuario) nodo.getValor();
                if (usuario != null) {
                    escritor.write(usuario.getNombre() + DELIMITADOR + 
                                 usuario.getPrioridad() + DELIMITADOR + 
                                 usuario.getCantidad());
                    escritor.newLine();
                    guardarDocumentosUsuario(usuario, carpetaDocs.getAbsolutePath());
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
     * Muestra un JFileChooser para guardar usuarios en archivo CSV.
     * 
     * @param hashUsuarios Tabla hash con los usuarios a guardar
     * @param rutaBase La ruta base donde se buscara guardar el documento.
     * @return Un booleano que indica si el guardado fue exitoso.
     */
    public boolean guardarUsuariosEnCSV(HashTable<String, Usuario> hashUsuarios, String rutaBase) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Usuarios en CSV");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", EXTENSION));
        
        int opcion = fileChooser.showSaveDialog(null);
        if (opcion != JFileChooser.APPROVE_OPTION) return false;
        
        File archivoUsuarios = fileChooser.getSelectedFile();
        if (!archivoUsuarios.getName().toLowerCase().endsWith("." + EXTENSION)) {
            archivoUsuarios = new File(archivoUsuarios.getParentFile(), 
                                       archivoUsuarios.getName() + "." + EXTENSION);
        }
        
        File carpetaDocs = new File(rutaBase + File.separator + "documentos");
        carpetaDocs.mkdirs();
        
        try {
            try (BufferedWriter escritor = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(archivoUsuarios), 
                                          StandardCharsets.UTF_8))) {
                escritor.write("usuario" + DELIMITADOR + "prioridad" + DELIMITADOR + "cantidad_documentos");
                escritor.newLine();
                for (int i = 0; i < hashUsuarios.getCapacidad(); i++) {
                    Nodo<String, Usuario> nodo = hashUsuarios.getTabla()[i];
                    while (nodo != null) {
                        Usuario usuario = nodo.getValor();
                        if (usuario != null) {
                            String nombreEscapado = escaparCSV(usuario.getNombre());
                            escritor.write(nombreEscapado + DELIMITADOR + 
                                         usuario.getPrioridad() + DELIMITADOR + 
                                         usuario.getCantidad());
                            escritor.newLine();
                            
                            guardarDocumentosUsuario(usuario, carpetaDocs.getAbsolutePath());
                        }
                        nodo = nodo.getpNext();
                    }
                }
            }
            return true;
        } catch (IOException e) {
            System.err.println("✗ Error al guardar: " + e.getMessage());
            return false;
        }
    }
    
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
        
        int opcion = fileChooser.showOpenDialog(null);
        if (opcion != JFileChooser.APPROVE_OPTION) return false;
        
        File archivo = fileChooser.getSelectedFile();
        File carpetaDocs = new File(archivo.getParentFile().getAbsolutePath() + File.separator + "documentos");
        
        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            
            String linea;
            boolean primeraLinea = true;
            int usuariosCargados = 0;
            
            while ((linea = lector.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                
                String[] partes = parsearLineaCSV(linea);
                if (partes.length >= 3) {
                    String nombre = desescaparCSV(partes[0].trim());
                    String prioridad = partes[1].trim();
                    int cantidadDocs = Integer.parseInt(partes[2].trim());
                    
                    Usuario usuario = new Usuario(nombre, prioridad);
                    hashUsuarios.put(nombre, usuario);
                    
                    File archivoDocs = new File(carpetaDocs, 
                        "docs_" + nombre.replaceAll("[^a-zA-Z0-9]", "_") + ".csv");
                    if (archivoDocs.exists()) {
                        cargarDocumentosUsuario(usuario, archivoDocs);
                    }
                    usuariosCargados++;
                }
            }
            System.out.println("✓ Usuarios cargados: " + usuariosCargados);
            return true;
        } catch (Exception e) {
            System.err.println("✗ Error al cargar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Prepara una cadena de texto para ser almacenada de forma segura en un archivo CSV.
     *
     * @param texto La cadena de texto original que se desea procesar.
     * @return El texto procesado y formateado según el estándar RFC 4180 para CSV.
     */
    private String escaparCSV(String texto) {
        if (texto.contains(",") || texto.contains("\"") || texto.contains("\n")) {
            return "\"" + texto.replace("\"", "\"\"") + "\"";
        }
        return texto;
    }

    /**
     * Revierte el formato de escape de una celda proveniente de un archivo CSV.
     *
     * @param texto El fragmento de texto extraído del CSV con posible formato de escape.
     * @return El texto original decodificado sin las marcas de formato CSV.
     */
    private String desescaparCSV(String texto) {
        if (texto.startsWith("\"") && texto.endsWith("\"")) {
            return texto.substring(1, texto.length() - 1).replace("\"\"", "\"");
        }
        return texto;
    }
    
    /**
     * Una función que recibe un string de linea y las parsea para que se conviertan en un array de distintas lineas.
     * @param linea La linea completa con todo el contenido.
     * @return Un array de las lineas separadas.
     */
    private String[] parsearLineaCSV(String linea) {
        List<String> partes = new ArrayList<>();
        StringBuilder actual = new StringBuilder();
        boolean entreComillas = false;
        
        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);
            if (c == '"') {
                entreComillas = !entreComillas;
            } else if (c == DELIMITADOR.charAt(0) && !entreComillas) {
                partes.add(actual.toString());
                actual = new StringBuilder();
            } else {
                actual.append(c);
            }
        }
        partes.add(actual.toString());
        return partes.toArray(new String[0]);
    }
    
    /**
     * Guarda los documentos de un usuario en un archivo CSV separado.
     * 
     * @param usuario Usuario cuyos documentos se guardarán
     * @param rutaDirectorio Directorio donde se guardará el archivo
     * @return true si el guardado fue exitoso
     */
    public boolean guardarDocumentosUsuario(Usuario usuario, String rutaDirectorio) {
        if (usuario == null || usuario.getCantidad() == 0) return false;
        
        String nombreArchivo = rutaDirectorio + File.separator +
            "docs_" + usuario.getNombre().replaceAll("[^a-zA-Z0-9]", "_") + ".csv";
        
        try (BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(nombreArchivo), StandardCharsets.UTF_8))) {
            
            escritor.write("documento" + DELIMITADOR + "paginas" + DELIMITADOR +
                          "tipo" + DELIMITADOR + "encola");
            escritor.newLine();
            
            for (int i = 0; i < usuario.getCantidad(); i++) {
                Documento doc = usuario.getDocumentos()[i];
                if (doc != null) {
                    if (doc.isEncola()) {
                        System.out.println("... Documento en cola: " + doc.getNombre());
                    }
                    escritor.write(escaparCSV(doc.getNombre()) + DELIMITADOR +
                                 doc.getPaginas() + DELIMITADOR +
                                 escaparCSV(doc.getTipo()) + DELIMITADOR +
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
        if (usuario == null || !archivo.exists()) return false;
        
        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            
            String linea;
            boolean primeraLinea = true;
            int docsCargados = 0;
            
            while ((linea = lector.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                
                String[] partes = parsearLineaCSV(linea);
                if (partes.length >= 4) {
                    String nombreDoc = desescaparCSV(partes[0].trim());
                    int paginas = Integer.parseInt(partes[1].trim());
                    String tipo = desescaparCSV(partes[2].trim());
                    boolean enCola = Boolean.parseBoolean(partes[3].trim());
                    if (paginas <= 0) {
                        System.out.println("! Páginas inválidas para: " + nombreDoc);
                        continue;
                    }
                    Documento doc = new Documento(nombreDoc, paginas, tipo, false);
                    usuario.agregarDocumento(doc);
                    docsCargados++;
                }
            }
            System.out.println("✓ Documentos cargados: " + docsCargados);
            return true;
        } catch (Exception e) {
            System.err.println("✗ Error al cargar documentos: " + e.getMessage());
            return false;
        }
    }
}