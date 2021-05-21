/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Alvarez
 */
public class Dialogs {
    public static int[] twoInputDialog(String titulo){
        //Y va primero
        int[] coordenadas=new int[2];
        
        JFrame frame = new JFrame(titulo);
        
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbcF=new GridBagConstraints();
        
        gbcF.gridwidth=3;
        frame.add(new JLabel("Ingrese la celda inicial"),gbcF);
        gbcF.gridwidth=1;
        
        gbcF.gridy=1;
        frame.add(new JLabel("                 "),gbcF);
        
        gbcF.gridy=2;
        JTextField y=new JTextField();
        y.setPreferredSize(new Dimension(60,35));
        y.setToolTipText("Ingrese aqui la columna en la que se encuantra la celda");
        frame.add(y,gbcF);
        
        gbcF.gridx=2;
        JTextField x=new JTextField();
        x.setPreferredSize(new Dimension(60,35));
        x.setToolTipText("Ingrese aqui la fila en la que se encuantra la celda");
        frame.add(x,gbcF);
        
        gbcF.gridy=3;
        frame.add(new JLabel("                 "),gbcF);
        
        gbcF.gridy=4;
        gbcF.gridx=1;
        JButton b=new JButton("Aceptar");
        b.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    coordenadas[0]=Integer.valueOf(y.getText());
                    coordenadas[1]=Integer.valueOf(x.getText());
                }
                catch(Exception ex){
                    coordenadas[0]=0;
                    coordenadas[1]=0;
                }
                frame.setVisible(false);
            }
        });
        frame.add(b,gbcF);
        
        frame.setSize(300, 200);
        frame.setVisible(true);
        
        return coordenadas;
    }
    public static int[] fourInputDialog(String titulo){
        //Y va primero
        int[] coordenadas=new int[4];
        
        JFrame frame = new JFrame(titulo);
        
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbcF=new GridBagConstraints();
        
        gbcF.gridwidth=3;
        frame.add(new JLabel("Ingrese las coordenadas de las celdas"),gbcF);
        gbcF.gridwidth=1;
        
        gbcF.gridy=1;
        frame.add(new JLabel("                 "),gbcF);
        
        gbcF.gridy=2;
        JTextField y=new JTextField();
        y.setPreferredSize(new Dimension(60,35));
        y.setToolTipText("Ingrese aqui la columna en la que se encuantra la celda");
        frame.add(y,gbcF);
        
        gbcF.gridx=2;
        JTextField x=new JTextField();
        x.setPreferredSize(new Dimension(60,35));
        x.setToolTipText("Ingrese aqui la fila en la que se encuantra la celda");
        frame.add(x,gbcF);
        
        gbcF.gridy=3;
        frame.add(new JLabel("                 "),gbcF);
        
        gbcF.gridx=0;
        gbcF.gridy=4;
        JTextField y2=new JTextField();
        y2.setPreferredSize(new Dimension(60,35));
        y2.setToolTipText("Ingrese aqui la columna en la que se encuantra la ultima celda");
        frame.add(y2,gbcF);
        
        gbcF.gridx=2;
        JTextField x2=new JTextField();
        x2.setPreferredSize(new Dimension(60,35));
        x2.setToolTipText("Ingrese aqui la fila en la que se encuantra la ultima celda");
        frame.add(x2,gbcF);
        
        gbcF.gridy=5;
        frame.add(new JLabel("                 "),gbcF);
        
        gbcF.gridy=6;
        gbcF.gridx=1;
        JButton b=new JButton("Aceptar");
        b.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    coordenadas[0]=Integer.valueOf(y.getText());
                    coordenadas[1]=Integer.valueOf(x.getText());
                    coordenadas[2]=Integer.valueOf(y2.getText());
                    coordenadas[3]=Integer.valueOf(x2.getText());
                }
                catch(Exception ex){
                    coordenadas[0]=0;
                    coordenadas[1]=0;
                    coordenadas[2]=0;
                    coordenadas[3]=0;
                }
                frame.setVisible(false);
            }
        });
        frame.add(b,gbcF);
        
        frame.setSize(300, 200);
        frame.setVisible(true);
        
        return coordenadas;
    }
    
    
}
