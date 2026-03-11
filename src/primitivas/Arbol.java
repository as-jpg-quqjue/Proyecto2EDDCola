/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

/**
 *
 * @author cesar
 */
public class Arbol {
    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }
    
    public void insertar(String hash){
        raiz = insertar(raiz, hash);
    }
    
    private Nodo insertar(Nodo nodo, String hash) {

        if (nodo == null) {
            return new Nodo(hash);
        }

        if (hash.compareTo(nodo.getHash()) < 0) {
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), hash));
        } else if (hash.compareTo(nodo.getHash()) > 0) {
            nodo.setDerecho(insertar(nodo.getDerecho(), hash));
        } else {
            return nodo; // No duplicados
        }

        // La altura es 1 + el máximo de las alturas de sus hijos
        nodo.setAltura(1 + Math.max(getAlturaSegura(nodo.getIzquierdo()),
                getAlturaSegura(nodo.getDerecho())));

        int balance = getFactorBalance(nodo);

        // Caso Izquierda-Izquierda
        if (balance > 1 && hash.compareTo(nodo.getIzquierdo().getHash()) < 0) {
            return rotarDerecha(nodo);
        }

        // Caso Derecha-Derecha
        if (balance < -1 && hash.compareTo(nodo.getDerecho().getHash()) > 0) {
            return rotarIzquierda(nodo);
        }

        // Caso Izquierda-Derecha
        if (balance > 1 && hash.compareTo(nodo.getIzquierdo().getHash()) > 0) {
            nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
            return rotarDerecha(nodo);
        }

        // Caso Derecha-Izquierda
        if (balance < -1 && hash.compareTo(nodo.getDerecho().getHash()) < 0) {
            nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

// MÉTODOS AUXILIARES PARA EVITAR NULL POINTER EXCEPTION
    private int getAlturaSegura(Nodo n) {
        return (n == null) ? -1 : n.getAltura(); // Usamos -1 para que las hojas tengan altura 0
    }

    private int getFactorBalance(Nodo n) {
        if (n == null) {
            return 0;
        }
        return getAlturaSegura(n.getIzquierdo()) - getAlturaSegura(n.getDerecho());
    }
    
    public void agregar(String hash, Articulo articulo){
        Nodo aux = buscar(hash);
        if (aux == null) {
            insertar(hash);
            aux = buscar(hash);
        }
 
        if (aux.getArticulos().getpInicial() == null) {
            aux.getArticulos().insertar(articulo);
        }
        for (int i = 0; i < aux.getArticulos().getiN(); i++) {
            System.out.println(aux.getArticulos().buscarPosición(i));
        }
        
    }
    
    private Nodo buscar(String hash, Nodo nodo){
        if (nodo == null) {
            return null;
        }
        if (hash.equals(nodo.getHash())) {
            return nodo;
        }
        if (hash.compareTo(nodo.getHash())<0) {
            return buscar(hash, nodo.getIzquierdo());
        }else{
            return buscar(hash, nodo.getDerecho());
        }
    }
    
    public Nodo buscar (String hash){
        return buscar(hash, raiz);
    }
    
    private void recorrer(Nodo nodo){
        if (nodo != null) {
            recorrer(nodo.getIzquierdo());
            System.out.println(nodo.getHash());
            recorrer(nodo.getDerecho());
        }
    }
    
    public void recorrer(){
        recorrer(raiz);
    }
    
    public void mostrar(String hash){
        Nodo aux = buscar(hash);
        if (aux == null) {
            System.out.println("NO");
        }
        Lista articulos = aux.getArticulos();

        for (int i = 0; i < articulos.getiN(); i++) {
            System.out.println(articulos.buscarPosición(i));
        }
        
    }
    
    private int altura(Nodo nodo){
        if (nodo == null) {
            return 0;
        }
        return nodo.getAltura();
    }
    
    private int balance(Nodo nodo){
        if (nodo == null) {
            return 0;
        }
        if (nodo.getDerecho() == null && nodo.getIzquierdo() == null) {
            return 0;
        }
        if (nodo.getDerecho() ==null) {
            return nodo.getIzquierdo().getAltura();
        }
        if (nodo.getIzquierdo() ==null) {
            return nodo.getDerecho().getAltura();
        }
        
        return nodo.getDerecho().getAltura()-nodo.getIzquierdo().getAltura();
    }
    
    private Nodo rotarDerecha(Nodo Y) {

        Nodo X = Y.getIzquierdo();
        Nodo aux = X.getDerecho();

        X.setDerecho(Y);
        Y.setIzquierdo(aux);

        Y.setAltura(1 + Math.max(altura(Y.getIzquierdo()), altura(Y.getDerecho())));
        X.setAltura(1 + Math.max(altura(X.getIzquierdo()), altura(X.getDerecho())));

        return X;
    }
    
    private Nodo rotarIzquierda(Nodo X) {

        Nodo Y = X.getDerecho();
        Nodo aux = Y.getIzquierdo();

        Y.setIzquierdo(X);
        X.setDerecho(aux);

        X.setAltura(1 + Math.max(altura(X.getIzquierdo()), altura(X.getDerecho())));
        Y.setAltura(1 + Math.max(altura(Y.getIzquierdo()), altura(Y.getDerecho())));

        return Y;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
}
