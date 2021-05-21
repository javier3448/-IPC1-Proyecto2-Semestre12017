package Calculadora;

import Ventanas.Celda;
import calculoDeCeldas.LectorDeCeldas;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alvarez
 */
public class Calculadora {

public String operarParentesis(String operacion){
    
    if(!hasParentesis(operacion)){
        return operacion;
    }
    
    String sTemp=operacion;
    int parentesis1=-1;
    int parentesis2=operacion.length();
    boolean hasParentesisIzquierdo;
    
    while(hasParentesis(sTemp)){
        for(int i=0; i<sTemp.length(); i++){
            if(String.valueOf(sTemp.charAt(i)).equals("(")){
                parentesis1=i;
                hasParentesisIzquierdo=true;
            }
            if(String.valueOf(sTemp.charAt(i)).equals(")")){
                parentesis2=i;
                break;
            }
        }
        sTemp=insertarString(parentesis1, parentesis2, multiDivSumRest(sTemp.substring(parentesis1+1, parentesis2)), sTemp);
    }
    
    return sTemp;
}

 /**
     * Inserta un string en otro, todo lo que este entre inicio y fin (inclusivo)sera reemplazado por aInsertar
     * @param inicio
     * @param fin
     * @param aInsertar
     * @param string
     * @return 
     */
public String insertarString(int inicio, int fin, String aInsertar, String string){
    String temp = "";
      
    for(int i=0; i<inicio;i++){
        temp=temp+String.valueOf(string.charAt(i));
    }
    for(int i=0;i<aInsertar.length();i++){
        temp=temp+String.valueOf(aInsertar.charAt(i));
    }
    for(int i=fin+1;i<string.length();i++){
        temp=temp+String.valueOf(string.charAt(i));
    }
        
        return temp;
    }


/**
 * Revisa si un String tiene parentesis
 * @param operacion
 * @return true si tiene, false si no.
 */
public boolean hasParentesis(String operacion){
    for(int i=0;i<operacion.length();i++){
        if(String.valueOf(operacion.charAt(i)).equals("(")){
            return true;
        }
    }
    
    return false;
}
    
public String resolver (String operacion){
    
    String resultado=operarParentesis(operacion);
    resultado=multiDivSumRest(resultado);
    
    return resultado;
}

/**
 * Recive un String y opera todas las sumas restas multiplicaciones y divisiones, NO RECIVE PARENTESIS
 * @param operacion
 * @return 
 */
public String multiDivSumRest(String operacion){

     String operacion1 = operacion;
    String operacion1Ordenar="";
    
    double resultado;
    
    Calc calcu=new Calc();
    
    //multiplicacion y division
    
    for (int i = 0; i<operacion1.length(); i++){
        if (String.valueOf(operacion1.charAt(i)).equals("*")){
            resultado=calcu.getNumero1(operacion1, i+1)*calcu.getNumero2(operacion1, i+1);
            
            if ((calcu.getOperador1(operacion1, i-1)!=0)){
                for(int c=0; c<calcu.getOperador1(operacion1, i-1)+1; c++){
                    operacion1Ordenar=operacion1Ordenar+String.valueOf(operacion1.charAt(c));
                }
            }
            operacion1Ordenar=operacion1Ordenar+resultado;
            
            if(calcu.getOperador2(operacion1, i+1)!=operacion1.length()-1){
                for(int r=calcu.getOperador2(operacion1, i+1); r<operacion1.length(); r++){
                    operacion1Ordenar=operacion1Ordenar+String.valueOf(operacion1.charAt(r));
                }
            }
            operacion1=operacion1Ordenar;
            operacion1Ordenar="";
            
            i=0;
        }
        
        else if (String.valueOf(operacion1.charAt(i)).equals("/")){
            resultado=calcu.getNumero1(operacion1, i+1)/calcu.getNumero2(operacion1, i+1);
            
            if ((calcu.getOperador1(operacion1, i-1)!=0)){
                for(int c=0; c<calcu.getOperador1(operacion1, i-1)+1; c++){
                    operacion1Ordenar=operacion1Ordenar+String.valueOf(operacion1.charAt(c));
                }
            }
            operacion1Ordenar=operacion1Ordenar+resultado;
            
            if(calcu.getOperador2(operacion1, i+1)!=operacion1.length()-1){
                for(int r=calcu.getOperador2(operacion1, i+1); r<operacion1.length(); r++){
                    operacion1Ordenar=operacion1Ordenar+String.valueOf(operacion1.charAt(r));
                }
            }
            operacion1=operacion1Ordenar;
            operacion1Ordenar="";
            
            i=0;
        }
    }
    
    //Suma y resta
    
    for (int i=0; i<operacion1.length(); i++){
         if (String.valueOf(operacion1.charAt(i)).equals("+")){
            resultado=calcu.getNumero1(operacion1, i+1)+calcu.getNumero2(operacion1, i+1);
            
            operacion1Ordenar=String.valueOf(resultado);
            if(calcu.getOperador2(operacion1, i+1)!=operacion1.length()-1){
                for(int r=calcu.getOperador2(operacion1, i+1); r<operacion1.length(); r++){
                    operacion1Ordenar=operacion1Ordenar+String.valueOf(operacion1.charAt(r));
                }
            }
            operacion1=operacion1Ordenar;
            operacion1Ordenar="";
            
            i=0;
         }
         else if(String.valueOf(operacion1.charAt(i)).equals("-")){
            resultado=calcu.getNumero1(operacion1, i+1)-calcu.getNumero2(operacion1, i+1);
            
            operacion1Ordenar=String.valueOf(resultado);
            if(calcu.getOperador2(operacion1, i+1)!=operacion1.length()-1){
                for(int r=calcu.getOperador2(operacion1, i+1); r<operacion1.length(); r++){
                    operacion1Ordenar=operacion1Ordenar+String.valueOf(operacion1.charAt(r));
                }
            }
            operacion1=operacion1Ordenar;
            operacion1Ordenar="";
            i=0; 
         }
    }
    
    return operacion1;
    
}

public static void main(String args[]){
    Calculadora c=new Calculadora();
    System.out.println(c.operarParentesis("(5+(5+5+(5*25))+5)+(15-2)"));
}
}
