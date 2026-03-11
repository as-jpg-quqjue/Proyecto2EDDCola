/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2edd;

import primitivas.Arbol;
import primitivas.Articulo;

/**
 *
 * @author cesar
 */
public class Proyecto2EDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Arbol avl = new Arbol();
        Articulo a1 = new Articulo("gere", "trebte");
        Articulo a2 = new Articulo("art", "freqtea");
        Articulo a3 = new Articulo("per", "bdtsh");
        Articulo a4 = new Articulo("ant", "veda");
        avl.agregar("123", a1);
        avl.agregar("635", a2);
        avl.agregar("364", a3);
        avl.agregar("653", a4);
        avl.recorrer();
    }
    
}
