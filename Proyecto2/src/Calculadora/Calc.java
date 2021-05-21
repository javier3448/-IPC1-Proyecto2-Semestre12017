package Calculadora;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alvarez
 */
public class Calc {
    public int[] getNumeros(String operacion1){
        String numero1S="";
        String numero2S="";
        int[] numeros={};
    
        String a;
    
        int doMas=0;
    
        for (int i=0; i<operacion1.length(); i++){
            a=String.valueOf(operacion1.charAt(i));
            if(a.equals("+") || a.equals("-")|| a.equals("*")|| a.equals("/")){
                for(int c=0; c<i; c++){
                    numero1S=numero1S+String.valueOf(operacion1.charAt(c));
                }
                System.out.println(numero1S);
                for(int g=i+1; g<operacion1.length(); g++){
                    if (String.valueOf(operacion1.charAt(g)).equals("+") || g==operacion1.length()){
                    doMas=g;
                    }
                }                            
                for(int h=i+1; h<doMas; h++){
                    numero2S=numero2S+String.valueOf(operacion1.charAt(h));
                }
                System.out.println(numero2S);
            }
        }
    numeros[0]=Integer.parseInt(numero1S);
    numeros[1]=Integer.parseInt(numero2S);
    return numeros;
    } 
    
    public double getNumero1(String operacion1, int i){
        int d=0;
        int numero1=0;
        String numero1S="";
        for (int a=i; a>0; a--){
            if(a==1){
                break;
            }
            else if (String.valueOf(operacion1.charAt(a-2)).equals("+")||String.valueOf(operacion1.charAt(a-2)).equals("/")||String.valueOf(operacion1.charAt(a-2)).equals("*")||String.valueOf(operacion1.charAt(a-2)).equals("-")){
                d=a-2;
                break;
            }
            numero1S=String.valueOf(operacion1.charAt(a-2))+numero1S;
        }
        return Double.valueOf(numero1S);
    }
    
    public double getNumero2(String operacion1, int i){
        int d=0;
        int numero2=0;
        String numero2S="";
        String numero2SOrdenar="";
        
        for (int a=i; a<operacion1.length(); a++){
            if (String.valueOf(operacion1.charAt(a)).equals("+")||String.valueOf(operacion1.charAt(a)).equals("/")||String.valueOf(operacion1.charAt(a)).equals("*")||String.valueOf(operacion1.charAt(a)).equals("-")||operacion1.length()==a){
                d=a;
                break;
            }
            numero2S=String.valueOf(operacion1.charAt(a))+numero2S;
        }
        
        for (int c=0; c<numero2S.length(); c++){
            numero2SOrdenar=numero2SOrdenar+String.valueOf(numero2S.charAt(numero2S.length()-1-c));
        }
        numero2S=numero2SOrdenar;
        return Double.parseDouble(numero2S);
    }
    
    public int getOperador2(String operacion1, int i){
        int posicion=operacion1.length()-1;
        for (int a=i; a<operacion1.length(); a++){
            if (String.valueOf(operacion1.charAt(a)).equals("+")||String.valueOf(operacion1.charAt(a)).equals("-")||String.valueOf(operacion1.charAt(a)).equals("/")||String.valueOf(operacion1.charAt(a)).equals("*")){
                posicion = a;
                break;
            }
        }
        return posicion;
    }
    
    public int getOperador1(String operacion1, int i){
        int posicion=0;
        for (int a=i; a>0; a--){
            if(String.valueOf(operacion1.charAt(a)).equals("+")||String.valueOf(operacion1.charAt(a)).equals("-")||String.valueOf(operacion1.charAt(a)).equals("/")||String.valueOf(operacion1.charAt(a)).equals("*")){
                posicion = a;
                break;
            }
        }
        return posicion;
    }
}
