/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import javax.swing.JTable;

/**
 *
 * @author Alvarez
 */
public class Proyecto2 {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) {
        ventanaPrincipal v = new ventanaPrincipal();
    }
    
    public static int entre(int var, int min, int max){
        if(var>=max)
            return max;
        if(var<=min)
            return min;
        else 
            return var;
    }
}
