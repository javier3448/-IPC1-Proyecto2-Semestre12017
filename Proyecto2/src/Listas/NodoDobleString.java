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
public class NodoDobleString {
    public String dato;
    NodoDobleString siguiente, anterior;
    
    //para cuando no hay nodos
    public NodoDobleString(String d){
        this(d, null, null);
        
    }

    //para cuando ya hay nodos
    public NodoDobleString(String dato, NodoDobleString siguiente, NodoDobleString anterior){
        this.dato=dato;
        this.siguiente=siguiente;
        this.anterior=anterior;
    }
    
    
}
