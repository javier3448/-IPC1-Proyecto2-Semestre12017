/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculoDeCeldas;

import Listas.ListaDoble;
import Listas.ListaDobleLista;
import Listas.ListaDobleString;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Javier
 */
public class LectorDeDocumentos {
    public ListaDobleLista lineas=new ListaDobleLista();
    public String texto;
    StringTokenizer token;
    
    public BufferedReader bf;
    
    public LectorDeDocumentos(){
        
    }
    
    
    /**
     * Lee el documento y almacena cada elemento en una lista de listas (cada elemento de la lista madre sera una linea del documento)
     * @param direccion donde se encuentra el documento
     * @throws IOException 
     */
    public void LeerDocumento(String direccion, String separador) throws IOException{
        //carga el archivo y lo mete en un buffer
        try {
            bf=new BufferedReader (new FileReader(direccion));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LectorDeDocumentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String bfRead;

        while((bfRead=bf.readLine())!=null){
            lineas.addFin(separar(separador,bfRead));
        }
    }
    
    

//    /**
//    * Separa las lineas del documento utlizando a separador como token almacena los datos en una matris de Strings
//    * @param separador token para separar las lineas
//   */
//    public void separar(String separador){
//        for(int i=0;i<lineas.getSize();i++){
//            token=new StringTokenizer(String.valueOf(lineas.get(i)),separador);
//           
//            int e=0;
//           while(token.hasMoreElements()){
//                System.out.println("i: "+i+"e: "+e);
//                elementos.addFin(token.nextToken());
//                e++;
//            }
//        }
//    }
    
    /**
     * Recibe un string y lo separa, mete los elementos en una lista
     * @param separador
     * @param string 
     */
    public ListaDobleString separar(String separador,String string){
        ListaDobleString temp=new ListaDobleString();
        
        token=new StringTokenizer(string,separador);

        int e=0;
        while(token.hasMoreElements()){
            temp.addFin(token.nextToken());
            e++;
        }
        
        return temp;
    }
    
    /**
     * Rellena un valor en la matriz, NO CAMBIA EL DOCUMENTO SOLO EL VALOR MATRIZ EN MEMORIA
     * @param x posicion en x
     * @param y posicion en y
     * @param contenido contenido que reemplaza
     */
    public void llenar(int x, int y, String contenido){
        for(int i=0;i<lineas.getSize();i++){
            
        }
    }
    
    public static void main(String args[]){
        LectorDeDocumentos l =  new LectorDeDocumentos();
        
        l.separar(",","si,si,si").mostrarInicioAfin();
    }
}
