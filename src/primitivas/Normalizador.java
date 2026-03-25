/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

/**
 *
 * @author miwindowspc
 */

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Esta clase se encarga de normalizar Strings recibidos por una función.
 * @author milaptop
 */
public class Normalizador {
    /**
     * Esto compila todos los caracteres comos ´, ~, etc, en un regex. 
     */
    private static Pattern diacritos = Pattern.compile("\\p{InCombiningDiacriticalMarks}+"); 
    
    /**
     * Esta función estática recibe un String inputS, en caso de que no este vació, se utiliza el estático diacritos y lo compara, separando primero los símbolos diacríticos y luego reemplazandolos usando el regex con nulo, finalmente cambiando todas las letras a uppercase y retornando un inputF.
     * @param inputS El string que deseamos normalizar.
     * @return Un string normalizado sin carácteres especiales (acentos, diéresis).
     */
    public static String NormalizarTexto(String inputS){
     if (inputS != null){ //añadi este chequeo de null para evitar errores de isBlank quejandose de un objeto null   
        if (!inputS.isBlank()){ //revisa si esta vacio el string mandado
            String inputN = Normalizer.normalize(inputS, Normalizer.Form.NFD); //separa todos los caracteres especiales en dos
            String inputF = diacritos.matcher(inputN).replaceAll(""); //reemplaza todo lo que encuentra en el regex con espacios blancos
            return inputF.toUpperCase();
        }
        else{
            return null;
        }
     }
     else{
        return null;
        }
    }
}
