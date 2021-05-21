/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculoDeCeldas;

import Listas.ListaDobleString;
import Ventanas.Celda;
import static Ventanas.ventanaPrincipal.celdas;
import calculoDeCeldas.LectorDeDocumentos;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Javier
 */
public class Sorts {
    
   
    
    public ListaDobleString[] ordenar(int y, int x, int yFinal, int xFinal, int columna1, int columna2){
        ListaDobleString[] lista=new ListaDobleString[2];
        lista[0]=new ListaDobleString();
        lista[1]=new ListaDobleString();
        
        lista=hacerLista(y, x, yFinal, xFinal, columna1, columna2);
        
        lista=clientesYTotales(lista);
        
        lista=bubbleSortDeLista(lista);
        
         
        if(lista[0].getSize()>10){
            for(int i=0; i<lista[0].getSize()-10;i++){
                lista[0].removeFin();
                lista[1].removeFin();
            }
        }
        
        lista[0].addInicio(celdas[0][columna1].getText());
        lista[1].addInicio(celdas[0][columna2].getText());
        
        return lista;
    }
    
    public ListaDobleString[] hacerListaDeTiempo(Object[][] data, int fecha0, int fechaF){
        ListaDobleString[] ps =  new ListaDobleString[9];
        ps[0] =  new ListaDobleString();
        ps[1] =  new ListaDobleString();
        ps[2] =  new ListaDobleString();
        ps[3] =  new ListaDobleString();
        ps[4] =  new ListaDobleString();
        ps[5] =  new ListaDobleString();
        ps[6] =  new ListaDobleString();
        ps[8] =  new ListaDobleString();
        ps[9] =  new ListaDobleString();
        
        for(int i=0;i<data.length; i++){
            if ((Double.valueOf(String.valueOf(data[i][5]))*360+
                    (Double.valueOf(String.valueOf(data[i][4]))-1)*30+
                    Double.valueOf(String.valueOf(data[i][3]))<=fechaF)
                    &&
                    (Double.valueOf(String.valueOf(data[i][5]))*360+
                    (Double.valueOf(String.valueOf(data[i][4]))-1)*30+
                    Double.valueOf(String.valueOf(data[i][3])))>=fecha0
                    ) {
                ps[0].addFin(String.valueOf(data[i][0]));
                ps[1].addFin(String.valueOf(data[i][1]));
                ps[2].addFin(String.valueOf(data[i][2]));
                ps[3].addFin(String.valueOf(data[i][3]));
                ps[4].addFin(String.valueOf(data[i][4]));
                ps[5].addFin(String.valueOf(data[i][5]));
                ps[6].addFin(String.valueOf(data[i][6]));
                ps[7].addFin(String.valueOf(data[i][7]));
                ps[8].addFin(String.valueOf(data[i][8]));
                ps[9].addFin(String.valueOf(data[i][9]));
            }
        }
        
        return ps;
    }
    
    /**
     * @param y
     * @param x
     * @param yFinal
     * @param xFinal
     * @param fecha0
     * @param fechaF
     * @return 
     */
    public ListaDobleString[] hacerListaTiempo(int y, int x, int yFinal, int xFinal, int fecha0, int fechaF){
        ListaDobleString[] ps =  new ListaDobleString[9];
        ps[0] =  new ListaDobleString();
        ps[1] =  new ListaDobleString();
        ps[2] =  new ListaDobleString();
        ps[3] =  new ListaDobleString();
        ps[4] =  new ListaDobleString();
        ps[5] =  new ListaDobleString();
        ps[6] =  new ListaDobleString();
        ps[8] =  new ListaDobleString();
        ps[9] =  new ListaDobleString();
        
        for(int i=0;i<yFinal-y+1; i++){
            if ((Double.valueOf(celdas[y+i][5+x].getResultado())*360+
                    (Double.valueOf(celdas[y+i][4+x].getResultado())-1)*30+
                    Double.valueOf(celdas[y+i][3+x].getResultado()))<=fechaF
                    &&
                    (Double.valueOf(celdas[y+i][5+x].getResultado())*360+
                    (Double.valueOf(celdas[y+i][4+x].getResultado())-1)*30+
                    Double.valueOf(celdas[y+i][3+x].getResultado()))>=fecha0
                    ) {
                ps[0].addFin(celdas[y + i][0 + x].getResultado());
                ps[1].addFin(celdas[y + i][1 + x].getResultado());
                ps[2].addFin(celdas[y + i][2 + x].getResultado());
                ps[3].addFin(celdas[y + i][3 + x].getResultado());
                ps[4].addFin(celdas[y + i][4 + x].getResultado());
                ps[5].addFin(celdas[y + i][5 + x].getResultado());
                ps[6].addFin(celdas[y + i][6 + x].getResultado());
                ps[7].addFin(celdas[y + i][7 + x].getResultado());
                ps[8].addFin(celdas[y + i][8 + x].getResultado());
                ps[9].addFin(celdas[y + i][9 + x].getResultado());
            }
        }
        return ps;
    }
    
    /**
     * Hace las lista necesaria para hacer las imagenes de las graficas, las guarda como 
     * @param y
     * @param x
     * @param yFinal
     * @param xFinal
     * @param columna1
     * @param columna2 
     */
    public ListaDobleString[] hacerLista(int y, int x, int yFinal, int xFinal, int columna1, int columna2){
        ListaDobleString[] ps =  new ListaDobleString[2];
        ps[0] =  new ListaDobleString();
        ps[1] =  new ListaDobleString();
        
        for(int i=1;i<yFinal-y+1; i++){
            ps[0].addFin(celdas[y+i][columna1+x].getResultado());
            ps[1].addFin(celdas[y+i][columna2+x].getResultado());
        }
       
        return ps;
    }
    
    public ListaDobleString[] ordenarDocumento(int columnaObjetos, int columnaCantidad, int lineaInicial,String direccion, String separador) throws IOException{
        ListaDobleString[] lista=new ListaDobleString[2];
        lista[0]=new ListaDobleString();
        lista[1]=new ListaDobleString();
        
        lista=construirLista(columnaObjetos, columnaCantidad, lineaInicial, direccion, separador);
        
        lista=clientesYTotales(lista);
        
        lista=bubbleSortDeLista(lista);
        
        return lista;
    }
    
    /**
     * Ordena 2 listas contenidas en un array, la lista en la posicion [0] contiene el nombre de los objetos a ordenar, la lista en la posicion [1] contiene los valores de los objetos
     * @param lista tiene que ser un vector tipo lista de dos posiciones
     * @return 
     */
    public ListaDobleString[] bubbleSortDeLista(ListaDobleString[] lista){
        boolean swapped = true;
        int j = 0;
        String tmp;
        String tmp1;
    
        ListaDobleString[] arr=lista;
    
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < arr[0].getSize() - j; i++) {                                       
                if (Double.valueOf(arr[1].get(i))> Double.valueOf(arr[1].get(i+1))) {                          
                    tmp = arr[0].get(i);
                    tmp1 = arr[1].get(i);
                
                    arr[0].add(i, arr[0].get(i+1));
                    arr[0].remove(i+1);
                    arr[1].add(i, arr[1].get(i+1));
                    arr[1].remove(i+1);
                
                    arr[0].add(i+1, tmp);
                    arr[0].remove(i+2);
                    arr[1].add(i+1, tmp1);
                    arr[1].remove(i+2);
                    swapped = true;
                }
            }                
        }
        return arr;  
    }
    
    /**
     * Pone cada cliente diferente en un linea nueva y le suma todas sus cantidades
     * @param clientes clientes a "framentar"
     * @return 
     */
    public ListaDobleString[] clientesYTotales(ListaDobleString[] clientes){
        ListaDobleString[] clientesNuevo = new ListaDobleString[2];
        clientesNuevo[0] = new ListaDobleString();
        clientesNuevo[1] = new ListaDobleString();
        
        for(int i=0; i<clientes[0].getSize();i++){
            if(!contieneCliente(clientesNuevo[0],clientes[0].get(i))){
                clientesNuevo[0].add(i, clientes[0].get(i));
                clientesNuevo[1].add(i, "0");
            }
        }
        
        for(int i=0; i<clientes[1].getSize();i++){
            int num=queCliente(clientesNuevo[0], clientes[0].get(i));
            
            clientesNuevo[1].add(num, String.valueOf(
                    Double.valueOf(clientesNuevo[1].get(num))
                    +Double.valueOf(clientes[1].get(i)))
            );
            clientesNuevo[1].remove(num+1);
        }
        
        return clientesNuevo;
    }

    
    /**
     * Devuelve la posicion de la lista en la que se encuentra el cliente
     * @param clientes
     * @param cliente
     * @return 
     */
    public int queCliente(ListaDobleString clientes, String cliente){
        for(int i=0; i<clientes.getSize();i++){
            if(clientes.get(i).equals(cliente)){
                return i;
            }
        }
        return clientes.getSize();
    }
    
    public boolean contieneCliente(ListaDobleString clientes, String clienteNuevo){
        for(int i=0;i<clientes.getSize();i++){
            if(clientes.get(i).equals(clienteNuevo)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * devuelve un array de dimension 2 que contiene dos listas con los objectos a ordenar y sus cantidades empieza siempre una fila despues 
     * @param columnaObjetos que objetos se ordenaran 
     * @param columnaCantidad la cantidad de cada objeto
     * @param lineaInicial la primera linea de donde se empezaran a tomar los datos
     * @param direccion direccion del documento de texto donde se encuentran los datos
     * @return 
     */
    public ListaDobleString[] construirLista(int columnaObjetos, int columnaCantidad, int lineaInicial,String direccion, String separador) throws IOException{
        ListaDobleString[] lista = new ListaDobleString[2];
        lista[0] = new ListaDobleString();
        lista[1] = new ListaDobleString();
        
        LectorDeDocumentos lectorDeDocumentos=new LectorDeDocumentos();
        
        lectorDeDocumentos.LeerDocumento(direccion, separador);
        
        for(int i=lineaInicial; i<lectorDeDocumentos.lineas.getSize();i++){
            lista[0].addFin(lectorDeDocumentos.lineas.get(i).get(columnaObjetos));
            lista[1].addFin(lectorDeDocumentos.lineas.get(i).get(columnaCantidad));
        }
        
        return lista;
    }
    
    public ListaDobleString[] construirLista(int y, int yFinal,
            int columnaObjetos, int columnaCantidad,
            String direccion, String separador,Celda[][]celdas) 
    throws IOException{
        
        ListaDobleString[] lista = new ListaDobleString[2];
        lista[0] = new ListaDobleString();
        lista[1] = new ListaDobleString();
        
        for(int i=0; i<yFinal;i++){
            lista[0].addFin(celdas[i][columnaObjetos].getText());
            lista[1].addFin(celdas[i][columnaCantidad].getText());
        }
        
        return lista;
    }
    
    public static void main(String args[]) throws IOException{
        Sorts s=new Sorts();
        
        ListaDobleString[] productos=new ListaDobleString[2];
        
        productos[0]=new ListaDobleString();
        productos[1]=new ListaDobleString();
        
        Random r = new Random();
        
        for(int i=0; i<10;i++){
            productos[0].addFin("Producto: "+String.valueOf(i+1));
            productos[1].addFin(String.valueOf(10));
        }
        for(int i=10; i<20;i++){
            productos[0].addFin("Producto: "+String.valueOf(i+1-10));
            productos[1].addFin(String.valueOf(10));
        }
        
        for (int i = 0; i < s.ordenarDocumento(0,1,1,"C:\\Users\\javie\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto2\\res\\documento.txt",",")[0].getSize(); i++) {
            System.out.println(s.ordenarDocumento(0,1,1,"C:\\Users\\javie\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto2\\res\\documento.txt",",")[0].get(i) 
                    + "     " + 
                    s.ordenarDocumento(0,1,1,"C:\\Users\\javie\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto2\\res\\documento.txt",",")[1].get(i));
        }
    }
}
