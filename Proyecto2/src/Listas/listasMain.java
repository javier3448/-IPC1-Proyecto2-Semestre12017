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
public class listasMain {
    public static void main(String args[]){
        ListaDobleString lista1=new ListaDobleString();
        lista1.addFin("0");
        lista1.addFin("1");
        lista1.addFin("2");
        lista1.addFin("3");
        lista1.addFin("4");
        lista1.addFin("5");
        
        ListaDobleString lista2=new ListaDobleString();
        lista2.addFin("0");
        lista2.addFin("1");
        lista2.addFin("2");
        lista2.addFin("3");
        lista2.addFin("4");
        lista2.addFin("5");
        
        ListaDobleString lista3=new ListaDobleString();
        lista3.addFin("0");
        lista3.addFin("1");
        lista3.addFin("2");
        lista3.addFin("3");
        lista3.addFin("4");
        lista3.addFin("5");
        
        ListaDobleString lista4=new ListaDobleString();
        lista4.addFin("0");
        lista4.addFin("1");
        lista4.addFin("2");
        lista4.addFin("3");
        lista4.addFin("4");
        lista4.addFin("5");
        
        ListaDobleLista lista=new ListaDobleLista();
        
        lista.addFin(lista1);
        lista.addFin(lista2);
        lista.addFin(lista3);
        lista.addFin(lista4);

        lista.get(0).mostrarInicioAfin();
        lista.get(1).mostrarInicioAfin();
        lista.get(2).mostrarInicioAfin();
        lista.get(3).mostrarInicioAfin();
    }
}
