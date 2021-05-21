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
public class ListaDobleLista {
    private NodoDobleLista inicio;
    private NodoDobleLista fin;
    private int size=0;
    
    public ListaDobleLista(){
        inicio=null;
        fin=null;
    }
    
    /**
     * Regresa si esta vacia o no
     * @return true si esta vacia false si no lo esta
     */
    public boolean isVacia(){
        return inicio==null;
    }
    
    public void addFin(ListaDobleString d){
        if(!isVacia()){
            fin=new NodoDobleLista(d, null, fin);
            fin.anterior.siguiente=fin;
        }
        else{
            inicio=fin=new NodoDobleLista(d);
        }
        size++;
    }
    
    public void addInicio(ListaDobleString d){
        if(!isVacia()){
            inicio=new NodoDobleLista(d, inicio, null);
            inicio.siguiente.anterior=inicio;
        }
        else{
            inicio=fin=new NodoDobleLista(d);
        }
        size++;
    }
    
    public void mostrarInicioAfin(){
        if(!isVacia()){
            NodoDobleLista auxiliar=inicio;
            while(auxiliar!=null){
                System.out.println(auxiliar.dato);
                auxiliar=auxiliar.siguiente;
            }
        }
    }
    
    public void mostrarFinAInicio(){
        if(!isVacia()){
            NodoDobleLista auxiliar=fin;
            while(auxiliar!=null){
                System.out.println(auxiliar.dato);
                auxiliar=auxiliar.anterior;
            }
        }
    }
    
    public void add(int index, ListaDobleString d){
        if(index==0){
            addInicio(d);
            return;
        }
        else if(index==size){
            addFin(d);
            return;
        }
        NodoDobleLista auxiliar=inicio;
        for(int i=0; i<index; i++){
            auxiliar=auxiliar.siguiente;
        }
        NodoDobleLista nuevo=new NodoDobleLista(d, auxiliar, auxiliar.anterior);
        auxiliar.anterior.siguiente=nuevo;
        auxiliar.anterior=nuevo;
        size++;
    }
    
    public ListaDobleString get(int index){
        if(index==0){
            return inicio.dato;
        }
        else{    
            NodoDobleLista auxiliar=inicio;
            for(int i=0; i<index; i++){
                auxiliar=auxiliar.siguiente;
            }
            return auxiliar.dato;
        }
    
    }
    
    public void removeInicio(){
        if(size==1){
            inicio=null;
            return;
        }
        inicio.siguiente.anterior=null;
        NodoDobleLista aux=inicio.siguiente;
        inicio.siguiente=null;
        inicio=aux;
        inicio.anterior=null;
        size--;
    }
    
    public void removeFin(){
        if(size==1){
            inicio=null;
            return;
        }
        fin.anterior.siguiente=null;
        NodoDobleLista aux=fin.anterior;
        fin.anterior=null;
        fin=aux;
        size--;
    }
    
    public void remove(int index){
        if(index==0){
            removeInicio();
        }
        else if(index==size-1){
            removeFin();
        }
        else{
            
            NodoDobleLista auxiliar = inicio;
            for (int i = 0; i < index; i++) {
                auxiliar = auxiliar.siguiente;
            }
            auxiliar.anterior.siguiente = auxiliar.siguiente;
            auxiliar.siguiente.anterior = auxiliar.anterior;
            
            auxiliar.siguiente = null;
            auxiliar.anterior = null;
            
            size--;
        }
    }
    
    public void clear(){
        inicio=null;
        fin=null;
        size=0;
    }
    
    public int getSize() {
        return size;
    }
}
