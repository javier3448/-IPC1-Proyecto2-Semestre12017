/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculoDeCeldas;



import Calculadora.Calculadora;
import Enums.FORMATO;
import Enums.FORMULAS;
import Listas.ListaDoble;
import Ventanas.Celda;
import Ventanas.ventanaPrincipal;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Alvarez
 */
public class LectorDeCeldas {
    
    private ListaDoble celdasConError =new ListaDoble();
    private Celda[][] celdas;
    private Calculadora calc=new Calculadora();
    
    public LectorDeCeldas(Celda[][] celdas){
        this.celdas = celdas;
    }
    
    /**
     * Se utiliza este constructo si no se deseen utilizar los metodos realacionados con la matriz de celdas
     */
    public LectorDeCeldas(){
        this.celdas=ventanaPrincipal.celdas;
    }
    
    /**
     * Revisa si todos los valores dados en el rango de celdas son numeros
     * @param celdaInicial
     * @param celdaFinal
     * @return 
     */
    public boolean isNumero(Celda celdaInicial, Celda celdaFinal) {
        boolean temp=true;
        System.out.println("Celdas con problema:");
        
        for(int i=celdaInicial.getPosicionY(); i<celdaFinal.getPosicionY()+1; i++){
            for(int e=celdaInicial.getPosicionX()+1; e<celdaFinal.getPosicionX()+1;e++){
                if(!celdas[i][e].isNumber()){
                    celdasConError.addFin("Columna: "+i+1+", Fila: "+e+1);
                    temp=false;
                }
            }
        }
        celdasConError.mostrarInicioAfin();
        return temp;
    }
    /**
     * Revisa si todos los valores en el rango son numero, si es asi. Los mete en una lista de valores
     * @param y
     * @param x
     * @param yFinal
     * @param xFinal
     * @return 
     */
    public ListaDoble valores (Celda celdaInicial, Celda celdaFinal){
        
        ListaDoble valores=new ListaDoble();
        
        if(isNumero(celdaInicial, celdaFinal)){
            for(int i=celdaInicial.getPosicionY(); i<celdaFinal.getPosicionY()+1; i++){
                for(int e=celdaInicial.getPosicionX(); e<celdaFinal.getPosicionX()+1;e++){
                    if(!celdas[i][e].isNumber()){
                        valores.addFin(Double.valueOf(celdas[i][e].getResultado()));
                    }
                }
            }
        }
        return valores;
    }
    
    public boolean isNumero(int y, int x, int yFinal, int xFinal) {
        boolean temp=true;
        System.out.println("Celdas con problema:");
        
        for(int i=y; i<yFinal+1; i++){
            for(int e=x; e<xFinal+1;e++){
                if(!celdas[i][e].isNumber()){
                    celdasConError.addFin("Columna: "+String.valueOf(i+1)+", Fila: "+String.valueOf(e+1));
                    temp=false;
                }
            }
        }
        celdasConError.mostrarInicioAfin();
        return temp;
    }
    /**
     * Revisa si todos los valores en el rango son numero, si es asi. Los mete en una lista de valores
     * @param y
     * @param x
     * @param yFinal
     * @param xFinal
     * @return 
     */
    public ListaDoble valores (int y, int x, int yFinal, int xFinal){
        
        ListaDoble valores=new ListaDoble();
        
        if(isNumero(y,x,yFinal,xFinal)){
            for(int i=y; i<yFinal+1; i++){
                for(int e=x; e<xFinal+1;e++){
                    valores.addFin(Double.valueOf(celdas[i][e].getResultado()));
                }
            }
        }
        return valores;
    }
    
    public double suma(ListaDoble valores){
        double resultado=0;
        
        for(int i=0; i<valores.getSize();i++){
            resultado=resultado+(double)valores.get(i);
        }
        
        return resultado;
    }
    
    public double suma(int y, int x, int yFinal, int xFinal){
        ListaDoble valores=valores(y,x,yFinal,xFinal);
        
        return suma(valores);
    }
    
    public double resta(ListaDoble valores){
        double resultado=0;
        
        for(int i=0; i<valores.getSize();i++){
            resultado=resultado-(double)valores.get(i);
        }
        
        return resultado;
    }
    
    public double resta(int y, int x, int yFinal, int xFinal){
        ListaDoble valores=valores(y,x,yFinal,xFinal);
        
        return resta(valores);
    }
    
    public double promedio(ListaDoble valores){
        double resultado=0;
        
        for(int i=0; i<valores.getSize();i++){
            resultado=resultado+(double)valores.get(i);
        }
        
        resultado=resultado/valores.getSize();
        
        return resultado;
    }
    
    public double promedio(int y, int x, int yFinal, int xFinal){
        ListaDoble valores=valores(y,x,yFinal,xFinal);
        
        return promedio(valores);
    }
    
    public double min(int y, int x, int yFinal, int xFinal){
        ListaDoble valores=valores(y,x,yFinal,xFinal);
        
        double min=(double)valores.get(0);
        
        for(int i=0; i<valores.getSize();i++){
            if((double)valores.get(i)<min){
                min=(double)valores.get(i);
            }
        }
        
        return min;
    }
    
    public double max(int y, int x, int yFinal, int xFinal){
        ListaDoble valores=valores(y,x,yFinal,xFinal);
        
        double max=(double)valores.get(0);
        
        for(int i=0; i<valores.getSize();i++){
            if((double)valores.get(i)>max){
                max=(double)valores.get(i);
            }
        }
        
        return max;
    }
    
    public double multiplicacion(ListaDoble valores){
        double resultado=0;
        
        for(int i=0; i<valores.getSize();i++){
            resultado=resultado*(double)valores.get(i);
        }
        
        return resultado;
    }
    
    public double multiplicacion(int y, int x, int yFinal, int xFinal){
        ListaDoble valores=valores(y,x,yFinal,xFinal);
        
        return suma(valores);
    }
    
    public double division(ListaDoble valores){
        double resultado=0;
        
        for(int i=0; i<valores.getSize();i++){
            resultado=resultado/(double)valores.get(i);
        }
        
        return resultado;
    }
    
    public double division(int y, int x, int yFinal, int xFinal){
        ListaDoble valores=valores(y,x,yFinal,xFinal);
        
        return suma(valores);
    }
    
    DecimalFormat df = new DecimalFormat("#.##");
    
    /**
     * 
     * @return 
     */
    public String operarCelda(Celda celda){
        String temp = celda.getValor();
        if(temp.length()==0){}
        else if(String.valueOf(temp.charAt(0)).equals("=")){
            temp=temp.substring(1);
            
            if(temp==""){
                temp="0";
            }
            else if(isFormula(temp)==FORMULAS.No){
                temp=convertirRefs(temp);
                temp=calc.resolver(temp);
            }
            else if(isFormula(temp).equals(FORMULAS.Suma)){
                ListaDoble posiciones = getPosiciones(temp, ":");
                String sTemp=temp;
                
                if((posiciones.getSize()%2)!=0){
                    JOptionPane.showMessageDialog(null, "ERROR EN LA SINTAXIS");
                    celdas[ventanaPrincipal.posicionActualY][ventanaPrincipal.posicionActualX].requestFocus();
                }
                else{
                    int [] coordenadas1;
                    coordenadas1=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(0)))+1, Integer.valueOf(String.valueOf(posiciones.get(1)))));                    
                    int [] coordenadas2;
                    coordenadas2=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(2)))+1, Integer.valueOf(String.valueOf(posiciones.get(3)))));
                    return String.valueOf(suma(coordenadas1[0],coordenadas1[1],coordenadas2[0],coordenadas2[1]));
                }
            }
            else if(isFormula(temp).equals(FORMULAS.Resta)){
                ListaDoble posiciones = getPosiciones(temp, ":");
                String sTemp=temp;
                
                if((posiciones.getSize()%2)!=0){
                    JOptionPane.showMessageDialog(null, "ERROR EN LA SINTAXIS");
                    celdas[ventanaPrincipal.posicionActualY][ventanaPrincipal.posicionActualX].requestFocus();
                }
                else{
                    int [] coordenadas1;
                    coordenadas1=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(0)))+1, Integer.valueOf(String.valueOf(posiciones.get(1)))));                    
                    int [] coordenadas2;
                    coordenadas2=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(2)))+1, Integer.valueOf(String.valueOf(posiciones.get(3)))));
                    return String.valueOf(resta(coordenadas1[0],coordenadas1[1],coordenadas2[0],coordenadas2[1]));
                }
            }
            else if(isFormula(temp).equals(FORMULAS.Promedio)){
                ListaDoble posiciones = getPosiciones(temp, ":");
                String sTemp=temp;
                
                if((posiciones.getSize()%2)!=0){
                    JOptionPane.showMessageDialog(null, "ERROR EN LA SINTAXIS");
                    celdas[ventanaPrincipal.posicionActualY][ventanaPrincipal.posicionActualX].requestFocus();
                }
                else{
                    int [] coordenadas1;
                    coordenadas1=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(0)))+1, Integer.valueOf(String.valueOf(posiciones.get(1)))));                    
                    int [] coordenadas2;
                    coordenadas2=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(2)))+1, Integer.valueOf(String.valueOf(posiciones.get(3)))));
                    return df.format(promedio(coordenadas1[0],coordenadas1[1],coordenadas2[0],coordenadas2[1]));
                }
            }
            else if(isFormula(temp).equals(FORMULAS.Multiplicacion)){
                ListaDoble posiciones = getPosiciones(temp, ":");
                String sTemp=temp;
                
                if((posiciones.getSize()%2)!=0){
                    JOptionPane.showMessageDialog(null, "ERROR EN LA SINTAXIS");
                    celdas[ventanaPrincipal.posicionActualY][ventanaPrincipal.posicionActualX].requestFocus();
                }
                else{
                    int [] coordenadas1;
                    coordenadas1=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(0)))+1, Integer.valueOf(String.valueOf(posiciones.get(1)))));                    
                    int [] coordenadas2;
                    coordenadas2=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(2)))+1, Integer.valueOf(String.valueOf(posiciones.get(3)))));
                    return df.format(multiplicacion(coordenadas1[0],coordenadas1[1],coordenadas2[0],coordenadas2[1]));
                }
            }
            else if(isFormula(temp).equals(FORMULAS.Division)){
                ListaDoble posiciones = getPosiciones(temp, ":");
                String sTemp=temp;
                
                if((posiciones.getSize()%2)!=0){
                    JOptionPane.showMessageDialog(null, "ERROR EN LA SINTAXIS");
                    celdas[ventanaPrincipal.posicionActualY][ventanaPrincipal.posicionActualX].requestFocus();
                }
                else{
                    int [] coordenadas1;
                    coordenadas1=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(0)))+1, Integer.valueOf(String.valueOf(posiciones.get(1)))));                    
                    int [] coordenadas2;
                    coordenadas2=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(2)))+1, Integer.valueOf(String.valueOf(posiciones.get(3)))));
                    return df.format(division(coordenadas1[0],coordenadas1[1],coordenadas2[0],coordenadas2[1]));
                }
            }
            else if(isFormula(temp).equals(FORMULAS.Min)){
                ListaDoble posiciones = getPosiciones(temp, ":");
                String sTemp=temp;
                
                if((posiciones.getSize()%2)!=0){
                    JOptionPane.showMessageDialog(null, "ERROR EN LA SINTAXIS");
                    celdas[ventanaPrincipal.posicionActualY][ventanaPrincipal.posicionActualX].requestFocus();
                }
                else{
                    int [] coordenadas1;
                    coordenadas1=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(0)))+1, Integer.valueOf(String.valueOf(posiciones.get(1)))));                    
                    int [] coordenadas2;
                    coordenadas2=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(2)))+1, Integer.valueOf(String.valueOf(posiciones.get(3)))));
                    return String.valueOf(min(coordenadas1[0],coordenadas1[1],coordenadas2[0],coordenadas2[1]));
                }
            }
            else if(isFormula(temp).equals(FORMULAS.Max)){
                ListaDoble posiciones = getPosiciones(temp, ":");
                String sTemp=temp;
                
                if((posiciones.getSize()%2)!=0){
                    JOptionPane.showMessageDialog(null, "ERROR EN LA SINTAXIS");
                    celdas[ventanaPrincipal.posicionActualY][ventanaPrincipal.posicionActualX].requestFocus();
                }
                else{
                    int [] coordenadas1;
                    coordenadas1=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(0)))+1, Integer.valueOf(String.valueOf(posiciones.get(1)))));                    
                    int [] coordenadas2;
                    coordenadas2=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(2)))+1, Integer.valueOf(String.valueOf(posiciones.get(3)))));
                    return String.valueOf(max(coordenadas1[0],coordenadas1[1],coordenadas2[0],coordenadas2[1]));
                }
            }
        }
        return temp;
    }
    
    /**
     * Revisa si un String empieza con SUMA RESTA MULTIPLICACION DIVISION MAX MIN o ninguno
     * @return el valor formula correspondiente
     */
    public FORMULAS isFormula(String string){
        String temp=string.toUpperCase();
        try {
            temp = temp.substring(0, 3);
        } catch (Exception e) {
            return FORMULAS.No;
        }
        
        System.out.println(temp);
        
        if (null == temp) {
            return FORMULAS.No;
        } else switch (temp) {
            case "SUM":
                return FORMULAS.Suma;
            case "RES":
                return FORMULAS.Resta;
            case "PRO":
                return FORMULAS.Promedio;
            case "MUL":
                return FORMULAS.Multiplicacion;
            case "DIV":
                return FORMULAS.Division;
            case "MAX":
                return FORMULAS.Max;
            case "MIN":
                return FORMULAS.Min;
            default:
                return FORMULAS.No;
        }

    }
    
    /**
     * Convierte todas las referencias de celdas a sus resultados NO INCLUIR EL = EN EL PARAMETRO
     * @param operacion
     * @return 
     */
    public String convertirRefs(String operacion){
        ListaDoble posiciones = getPosiciones(operacion, ":");
        String sTemp=operacion;
        
        if((posiciones.getSize()%2)!=0){
            JOptionPane.showMessageDialog(null, "ERROR EN LA SINTAXIS");
            celdas[ventanaPrincipal.posicionActualY][ventanaPrincipal.posicionActualX].requestFocus();
        }
        else{
            int [] temp;
            posiciones.mostrarInicioAfin();
            while(posiciones.getSize()>=2){
                temp=stringACoordenada(sTemp.substring(Integer.valueOf(String.valueOf(posiciones.get(0)))+1, Integer.valueOf(String.valueOf(posiciones.get(1)))));
                
                int a=Integer.valueOf(String.valueOf(posiciones.get(0)));
                int b=Integer.valueOf(String.valueOf(posiciones.get(1)));
                String c=celdas[temp[0]][temp[1]].getResultado();
                
                sTemp=insertarString(a,b,c,sTemp);
                
                posiciones=getPosiciones(sTemp, ":");
                
                int i=1;
                System.out.println("ciclo numero: "+i);
            }
        }
        return sTemp;
    }
    
    /**
     * Consigue las posiciones de todos los : del estring dado
     * @param string solo puede ser de un caracter
     * @return 
     */
    public ListaDoble getPosiciones(String string, String separador){
        ListaDoble posiciones = new ListaDoble();
        
        for(int i=0; i<string.length();i++){
            if(String.valueOf(string.charAt(i)).equals(separador)){
                posiciones.addFin(i);
            }
        }
        
        return posiciones;
    }
    
    /**
     * Recive un string que tiene dos enteros separados por una coma. Devuelve el valor de cada uno de los enteros -1
     * @param string
     * @return 
     */
    public int[] stringACoordenada(String string){
        int[] temp=new int[2];
        
        StringTokenizer token=new StringTokenizer(string,",");

        int e=0;
        while(token.hasMoreElements()){
            temp[e]=Integer.valueOf(token.nextToken())-1;
            e++;
        }
        
        return temp;
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
    
    public static void main(String args[]){
        LectorDeCeldas l = new LectorDeCeldas();
        
        System.out.println(l.isFormula("SUMA565656565656"));
    }
}
