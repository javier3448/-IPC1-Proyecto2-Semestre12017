/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;


/**
 *
 * @author Alvarez
 */
import Enums.FORMATO;
import calculoDeCeldas.LectorDeCeldas;
import javax.swing.JOptionPane;
public class Celda extends JTextField{
    //posicion en la hoja de calculo
    private int posicionX;
    private int posicionY;

    private LectorDeCeldas l = new LectorDeCeldas();
    
    private JTextField celdaActualTxtF;
    private FORMATO formato=FORMATO.Texto;
    
    
    //lo que ve el usuario cuando la celda no esta seleccionada
    private String text="";
    //Despues de trabjar el valor
    private String resultado="";
    //Lo que se utiliza para obtener el valor (la formula)
    private String valor="";
    
    
    public FORMATO getFormato() {
        return formato;
    }


    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setFormato(FORMATO formato) {
        
        if(this.formato==formato){}
        else if(formato==FORMATO.Porcentaje){
            if (isNumber()) {
                text=text+"%";
                setText(text);
                this.formato = formato;
                setHorizontalAlignment(JTextField.RIGHT);
            }
            else{
                JOptionPane.showMessageDialog(null, "La celda que selecciono no es un valor numerico", "ERROR",0);
                requestFocus();
            }
        }
        else if(formato==FORMATO.Numero){
            if(isNumber()){
                this.formato=formato;
                setHorizontalAlignment(JTextField.RIGHT);
            }
            else{
                JOptionPane.showMessageDialog(null, "La celda que selecciono no es un valor numerico", "ERROR",0);
                requestFocus();
            }
        }
        else if(formato==FORMATO.Texto){
            this.formato=FORMATO.Texto;
            setHorizontalAlignment(JTextField.LEFT);
        }
    }
    
    public Celda(){
        super();
        
        this.addFocusListener(new focus());
        celdaActualTxtF=new JTextField();
    }
    
    public Celda(JTextField celdaActualTxtF){
        super();
        
        this.addFocusListener(new focus());
        this.celdaActualTxtF=celdaActualTxtF;
    }
    
    public JTextField getCeldaActual() {
        return celdaActualTxtF;
    }

    public void setCeldaActual(JTextField celdaActual) {
        this.celdaActualTxtF = celdaActual;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posiciony) {
        this.posicionY = posiciony;
    }
    
    
    private String operarValor(String valor){
        if(getText().length()==0){
            return "";
        }
        else if(String.valueOf(valor.charAt(0)).equals("=")){
            String temp = null;
//            try {
                temp = l.operarCelda(this);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "ERROR EN SINTAXIS");
//                requestFocus();
//            }
            return temp;
        }
        else{
            return valor;
        }
    }
    
    private class focus implements FocusListener{

        @Override
        public void focusGained(FocusEvent e) {
            ventanaPrincipal.posicionActualY=posicionY;
            ventanaPrincipal.posicionActualX=posicionX;
            
            //quitar
            celdaActualTxtF.setText(String.valueOf(posicionY+1)+"   "+String.valueOf(posicionX+1));
            
            setText(valor);
            
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(formato==FORMATO.Numero){
                if(isNumber(operarValor(getText()))){
                    valor=getText();
                    resultado=operarValor(valor);
                    text=resultado;
                }
                else{
                    JOptionPane.showMessageDialog(null, "No es un valor numerico", "ERROR", 0);
                    requestFocus();
                }
            }
            
            if(formato==FORMATO.Porcentaje){
                if(isNumber(operarValor(getText()))){
                    valor=getText();
                    resultado=operarValor(valor);
                    text=resultado;
                    text=text+"%";
                }
                else{
                    JOptionPane.showMessageDialog(null, "No es un valor numerico", "ERROR", 0);
                    requestFocus();
                }
            }
            
            if(formato==FORMATO.Texto){
                valor=getText();
                resultado=operarValor(valor);
                text=resultado;
            }
            
            setText(text);
            
            System.out.println("Valor: "+valor+" Resultado: "+resultado+" Texto: "+text);
        }
    }
    
    public void update(){
    
        
            if(formato==FORMATO.Numero){
                if(isNumber(operarValor(getText()))){
                    resultado=operarValor(valor);
                    text=resultado;
                }
                else{
                    JOptionPane.showMessageDialog(null, "No es un valor numerico", "ERROR", 0);
                    requestFocus();
                }
            }
            
            if(formato==FORMATO.Porcentaje){
                if(isNumber(operarValor(getText()))){
                    resultado=operarValor(valor);
                    text=resultado;
                    text=text+"%";
                }
                else{
                    JOptionPane.showMessageDialog(null, "No es un valor numerico", "ERROR", 0);
                    requestFocus();
                }
            }
            
            if(formato==FORMATO.Texto){
                resultado=operarValor(valor);
                text=resultado;
            }
            
            setText(text);
    
    }
    
    public boolean isNumber(){
        boolean temp = true;
        try{
            Double.valueOf(resultado);
        }catch(Exception e){
            temp=false;
        }
        if(resultado.equals(""))temp=true;
        return temp;
    }
    
    public boolean isNumber(String resultado){
        boolean temp = true;
        try{
            Double.valueOf(resultado);
        }catch(Exception e){
            temp=false;
        }
        if(resultado.equals(""))temp=true;
        return temp;
    }
    
}
