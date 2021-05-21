/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

/**
 *
 * @author Alvarez
 */
public class NodoDoble {
    public Object dato;
    NodoDoble siguiente, anterior;
    
    //para cuando no hay nodos
    public NodoDoble(Object d){
        this(d, null, null);
        
    }

    //para cuando ya hay nodos
    public NodoDoble(Object dato, NodoDoble siguiente, NodoDoble anterior){
        this.dato=dato;
        this.siguiente=siguiente;
        this.anterior=anterior;
    }
    
    
    
}
