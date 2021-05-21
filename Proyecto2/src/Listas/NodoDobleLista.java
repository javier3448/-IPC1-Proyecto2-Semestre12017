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
public class NodoDobleLista {
    public ListaDobleString dato;
    NodoDobleLista siguiente, anterior;
    
    //para cuando no hay nodos
    public NodoDobleLista(ListaDobleString d){
        this(d, null, null);
        
    }

    //para cuando ya hay nodos
    public NodoDobleLista(ListaDobleString dato, NodoDobleLista siguiente, NodoDobleLista anterior){
        this.dato=dato;
        this.siguiente=siguiente;
        this.anterior=anterior;
    }
}
