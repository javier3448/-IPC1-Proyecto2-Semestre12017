/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Listas.ListaDobleString;
import calculoDeCeldas.Sorts;
import pdfGraficas.Graficas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 *
 * @author Javier
 */
public final class DashBoard extends JFrame{
    
    private JPanel clientesPane = new JPanel();

    public void clientesPane(JTable tablaDeDatos){
        clientesPane.setLayout(new GridBagLayout());
        GridBagConstraints gb=new GridBagConstraints();
        
        JLabel porTotal = new JLabel("Por total gastado");
        porTotal.setFont(new Font("DialogInput",Font.BOLD,20));
        gb.gridy=0;
        gb.gridx=0;
        gb.gridwidth=3;
        clientesPane.add(porTotal, gb);
        
        JLabel graficas=new JLabel("Graficas");
        graficas.setFont(new Font("DialogInput",Font.PLAIN,20));
        gb.gridy++;
        System.out.println(gb.gridy);
        clientesPane.add(graficas, gb);
        
        gb.gridwidth=1;
        
        JLabel pie = new JLabel(new ImageIcon("Grafica de pie del total gastado por cliente.jpeg"));
        gb.gridy++;
        clientesPane.add(pie, gb);
        
        JLabel barra = new JLabel(new ImageIcon("Grafica de barra del total gastado por cliente.jpeg"));
        gb.gridx++;
        clientesPane.add(barra, gb);
        
        JTable tabla=tabla1;
        JScrollPane sc1=new JScrollPane(tabla);
        sc1.setPreferredSize(new Dimension(350,350));
        gb.gridx++;
        clientesPane.add(sc1, gb);
        
        JLabel porCantidad = new JLabel("Por cantidad vendida");
        porCantidad.setFont(new Font("DialogInput",Font.BOLD,20));
        gb.gridx=0;
        gb.gridy++;
        gb.gridwidth=3;
        clientesPane.add(porCantidad, gb);
        
        JLabel graficas1=new JLabel("Graficas");
        graficas1.setFont(new Font("DialogInput",Font.PLAIN,20));
        gb.gridy++;
        clientesPane.add(graficas1, gb);
        
        gb.gridwidth=1;
        
        JLabel pie1 = new JLabel(new ImageIcon("Grafica de pie por cantidad de productos comprados por cliente.jpeg"));
        gb.gridy++;
        clientesPane.add(pie1, gb);
        
        JLabel barra1 = new JLabel(new ImageIcon("Grafica de barra por cantidad de productos comprados por cliente.jpeg"));
        gb.gridx++;
        clientesPane.add(barra1, gb);
        
        JTable tabl=tablaA;
        JScrollPane sc2=new JScrollPane(tabl);
        sc2.setPreferredSize(new Dimension(350,350));
        gb.gridx++;
        clientesPane.add(sc2, gb);
        
        JScrollPane sc = new JScrollPane(tablaDeDatos);
        sc.setPreferredSize(new Dimension(1050, 400));
        gb.gridy++;
        gb.gridx=0;
        gb.gridwidth=3;
        clientesPane.add(sc,gb);
    }
        
    //
    private JPanel productosPane = new JPanel();
    public void productosPane(JTable tablaDeDatos){
        
        Graficas g = new Graficas();
        
        productosPane.setLayout(new GridBagLayout());
        GridBagConstraints gb=new GridBagConstraints();
        
        JLabel porTotal = new JLabel("Por total gastado");
        porTotal.setFont(new Font("DialogInput",Font.BOLD,20));
        gb.gridy=0;
        gb.gridx=0;
        gb.gridwidth=3;
        productosPane.add(porTotal, gb);
        
        JLabel graficas=new JLabel("Graficas");
        graficas.setFont(new Font("DialogInput",Font.PLAIN,20));
        gb.gridy++;
        System.out.println(gb.gridy);
        productosPane.add(graficas, gb);
        
        gb.gridwidth=1;
        
        JLabel pie = new JLabel(new ImageIcon("Grafica de pie del total gastado por producto.jpeg"));
        gb.gridy++;
        productosPane.add(pie, gb);
        
        JLabel barra = new JLabel(new ImageIcon("Grafica de barras del total gastado por producto.jpeg"));
        gb.gridx++;
        productosPane.add(barra, gb);
        
        JTable tabla=tabla2;
        JScrollPane sc1=new JScrollPane(tabla);
        sc1.setPreferredSize(new Dimension(350,350));
        gb.gridx++;
        productosPane.add(sc1, gb);
        
        JLabel porCantidad = new JLabel("Por cantidad vendida");
        porCantidad.setFont(new Font("DialogInput",Font.BOLD,20));
        gb.gridx=0;
        gb.gridy++;
        gb.gridwidth=3;
        productosPane.add(porCantidad, gb);
        
        JLabel graficas1=new JLabel("Graficas");
        graficas1.setFont(new Font("DialogInput",Font.PLAIN,20));
        gb.gridy++;
        productosPane.add(graficas1, gb);
        
        gb.gridwidth=1;
        
        JLabel pie1 = new JLabel(new ImageIcon("Grafica de pie del numero de unidades por producto.jpeg"));
        gb.gridy++;
        productosPane.add(pie1, gb);
        
        JLabel barra1 = new JLabel(new ImageIcon("Grafica de barras del numero de unidades por producto.jpeg"));
        gb.gridx++;
        productosPane.add(barra1, gb);
        
        JTable tabl=tablaB;
        JScrollPane sc2=new JScrollPane(tabl);
        sc2.setPreferredSize(new Dimension(350,350));
        gb.gridx++;
        productosPane.add(sc2, gb);
        
        JScrollPane sc = new JScrollPane(tablaDeDatos);
        sc.setPreferredSize(new Dimension(1050, 400));
        gb.gridy++;
        gb.gridx=0;
        gb.gridwidth=3;
        productosPane.add(sc,gb);
    }
    
    //
    private JPanel formasDePagoPane = new JPanel();
    public void formasDePagoPane(JTable tablaDeDatos){
        formasDePagoPane.setLayout(new GridBagLayout());
        GridBagConstraints gb=new GridBagConstraints();
        
        JLabel porTotal = new JLabel("Por total gastado");
        porTotal.setFont(new Font("DialogInput",Font.BOLD,20));
        gb.gridy=0;
        gb.gridx=0;
        gb.gridwidth=3;
        formasDePagoPane.add(porTotal, gb);
        
        JLabel graficas=new JLabel("Graficas");
        graficas.setFont(new Font("DialogInput",Font.PLAIN,20));
        gb.gridy++;
        System.out.println(gb.gridy);
        formasDePagoPane.add(graficas, gb);
        
        gb.gridwidth=1;
        
        JLabel pie = new JLabel(new ImageIcon("Grafica de pie del total gastado por forma de pago.jpeg"));
        gb.gridy++;
        formasDePagoPane.add(pie, gb);
        
        JLabel barra = new JLabel(new ImageIcon("Grafica de barras del total gastado por forma de pago.jpeg"));
        gb.gridx++;
        formasDePagoPane.add(barra, gb);
        
        JTable tabla=tabla3;
        JScrollPane sc1=new JScrollPane(tabla);
        sc1.setPreferredSize(new Dimension(350,350));
        gb.gridx++;
        formasDePagoPane.add(sc1, gb);
        
        JLabel porCantidad = new JLabel("Por cantidad vendida");
        porCantidad.setFont(new Font("DialogInput",Font.BOLD,20));
        gb.gridx=0;
        gb.gridy++;
        gb.gridwidth=3;
        formasDePagoPane.add(porCantidad, gb);
        
        JLabel graficas1=new JLabel("Graficas");
        graficas1.setFont(new Font("DialogInput",Font.PLAIN,20));
        gb.gridy++;
        formasDePagoPane.add(graficas1, gb);
        
        gb.gridwidth=1;
        
        JLabel pie1 = new JLabel(new ImageIcon("Grafica de pie de la cantidad de veces que se utililzo cada forma de pago.jpeg"));
        gb.gridy++;
        formasDePagoPane.add(pie1, gb);
        
        JLabel barra1 = new JLabel(new ImageIcon("Grafica de barras de la cantidad de veces que se utililzo cada forma de pago.jpeg"));
        gb.gridx++;
        formasDePagoPane.add(barra1, gb);
        
        JTable tabl=tablaC;
        JScrollPane sc2=new JScrollPane(tabl);
        sc2.setPreferredSize(new Dimension(350,350));
        gb.gridx++;
        formasDePagoPane.add(sc2, gb);
        
        JScrollPane sc = new JScrollPane(tablaDeDatos);
        sc.setPreferredSize(new Dimension(1050, 400));
        gb.gridy++;
        gb.gridx=0;
        gb.gridwidth=3;
        formasDePagoPane.add(sc,gb);
    }
    
    //
    private JPanel tiempoPane =  new JPanel();
    public void tiempoPane(JTable tablaDeDatos, Object[][] data, String[] nombreDeColumnas, int fechaI, int fechaF){
        tiempoPane.setLayout(new GridBagLayout());
        GridBagConstraints gb=new GridBagConstraints();
        
        JLabel titulo = new JLabel("REPORTE POR PERIODO DE TIEMPO");
        titulo.setFont(new Font("DialogInput",Font.BOLD,40));
        gb.gridy=0;
        gb.gridx=0;
        gb.gridwidth=6;
        tiempoPane.add(titulo, gb);
        
        ListaDobleString[] lista= new ListaDobleString[9];
        
        Sorts s = new Sorts();
        
        lista=s.hacerListaDeTiempo(data, fechaI, fechaF);
        
        Object[][] dataNew = new Object[lista[0].getSize()][9];
        
        for(int i=0; i<dataNew.length;i++){
            dataNew[i][0]=lista[0].get(i);
            dataNew[i][1]=lista[1].get(i);
            dataNew[i][2]=lista[2].get(i);
            dataNew[i][3]=lista[3].get(i);
            dataNew[i][4]=lista[4].get(i);
            dataNew[i][5]=lista[5].get(i);
            dataNew[i][6]=lista[6].get(i);
            dataNew[i][7]=lista[7].get(i);
            dataNew[i][8]=lista[8].get(i);
            dataNew[i][9]=lista[9].get(i);
        }
        
        JScrollPane sc = new JScrollPane(new JTable(dataNew, nombreDeColumnas));
    }    
    
    public DashBoard(){}
    
    public void hacerImagenes(JTable tabla1, JTable tabla2, JTable tabla3, JTable tabla4,
                     JTable tablaA, JTable tablaB, JTable tablaC, JTable tablaD, Object[][] data, String[] nombreDeColumnas, int fechaI, int fechaF){
        this.tabla1=tabla1;
        this.tabla2=tabla2;
        this.tabla3=tabla3;
        this.tabla4=tabla4;
        
        this.tablaA=tablaA;
        this.tablaB=tablaB;
        this.tablaC=tablaC;
        this.tablaD=tablaD;
        
        Graficas g=new Graficas();
        
        clientesPane(g.hacerTabla(data, nombreDeColumnas));
        productosPane(g.hacerTabla(data, nombreDeColumnas));
        formasDePagoPane(g.hacerTabla(data, nombreDeColumnas));
        tiempoPane(g.hacerTabla(data, nombreDeColumnas), data, nombreDeColumnas, fechaI, fechaF);
    }
    
    public void hacerImagenes(JTable tabla1, JTable tabla2, JTable tabla3, JTable tabla4,
                     JTable tablaA, JTable tablaB, JTable tablaC, JTable tablaD, Object[][] data, String[] nombreDeColumnas){
        this.tabla1=tabla1;
        this.tabla2=tabla2;
        this.tabla3=tabla3;
        this.tabla4=tabla4;
        
        this.tablaA=tablaA;
        this.tablaB=tablaB;
        this.tablaC=tablaC;
        this.tablaD=tablaD;
        
        Graficas g=new Graficas();
        
        clientesPane(g.hacerTabla(data, nombreDeColumnas));
        productosPane(g.hacerTabla(data, nombreDeColumnas));
        formasDePagoPane(g.hacerTabla(data, nombreDeColumnas));
    }
    
    private JTabbedPane tbPane = new JTabbedPane();
    private JScrollPane clientesSc;
    private JScrollPane productosSc;
    private JScrollPane formasDePagoSc;
    private JScrollPane tiempoSc;
    
    private JTable tabla1;
    private JTable tabla2;
    private JTable tabla3;
    private JTable tabla4;
    
    private JTable tablaA;
    private JTable tablaB;
    private JTable tablaC;
    private JTable tablaD;

    public DashBoard(JTable tabla1, JTable tabla2, JTable tabla3, JTable tabla4,
                     JTable tablaA, JTable tablaB, JTable tablaC, JTable tablaD, Object[][] data, String[] nombreDeColumnas, int fechaI, int fechaF){
        
        hacerImagenes(tabla1, tabla2, tabla3, tabla4,
                      tablaA, tablaB, tablaC, tablaD, data , nombreDeColumnas, fechaI, fechaF);
        
        clientesSc=new JScrollPane(clientesPane);
        tbPane.addTab("Top Clientes", null, clientesSc, "En esta pestaña 10 clientes que mas compraron");
        
        productosSc=new JScrollPane(productosPane);
        tbPane.addTab("Top Productos", null, productosSc, "En esta pestaña 10 productos que mas compraron");
        
        formasDePagoSc=new JScrollPane(formasDePagoPane);
        tbPane.addTab("Top formas de pago", null, formasDePagoSc, "En esta pestaña 10 productos que mas compraron");
        
        tiempoSc=new JScrollPane(tiempoPane);
        tbPane.addTab("Reportes Por periodo de tiempo", null, tiempoSc, "En esta pestaña 10 productos que mas compraron");
        
        tbPane.setPreferredSize(new Dimension(1300,500));
        
        add(tbPane);
        setSize(1300,500);
        
        setVisible(true);
    }
}
