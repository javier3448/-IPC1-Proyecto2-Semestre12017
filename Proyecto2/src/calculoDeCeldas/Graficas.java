/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculoDeCeldas;
import Listas.ListaDobleString;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;



/**
 *
 * @author Javier
 */
public class Graficas {

    /**
     * Hace una imagen de una grafica de pie de la lista de elementos y sus cantidades
     * El nombre de la grafica de pie es el parametro titulo +".jpeg"
     * @param titulo titulo de la grafica de pie 
     * @param ps array de productos
     * @throws IOException 
     */
    public void setImagenGraficaDePie(String titulo, ListaDobleString[] ps) throws IOException{
        DefaultPieDataset dataset=new DefaultPieDataset();
        for (int i=1;i<ps[0].getSize();i++){
            dataset.setValue(ps[0].get(i), Double.valueOf(ps[1].get(i)));
        }
        JFreeChart chart = ChartFactory.createPieChart(titulo, dataset, true, true, true);
        File pieChart=new File(titulo+".jpeg");
        ChartUtilities.saveChartAsJPEG(pieChart,chart,350,350);
    }
    /**
     * Hace una imagen de una grafica de barras de la lista de elementos y sus cantidades
     * El nombre de la grafica de pie es el parametro titulo +".jpeg"
     * @param titulo titulo de la grafica de pie 
     * @param ps array de productos
     * @throws IOException 
     */
    public void setImagenGraficaDeBarras(String titulo, ListaDobleString[] ps) throws IOException{
        DefaultCategoryDataset dataset=new DefaultCategoryDataset() ;
        
        for (int i=1;i<ps[0].getSize();i++){
            dataset.setValue(Double.valueOf(ps[1].get(i)), ps[1].get(0), ps[0].get(i));
        }
        JFreeChart chart = ChartFactory.createBarChart(titulo, titulo, "Ventas", dataset, PlotOrientation.VERTICAL, true, true, false);
        File barChart=new File(titulo+".jpeg");
        ChartUtilities.saveChartAsJPEG(barChart,chart,350,350);
    }
    
    public JTable getTable(ListaDobleString[] ps){
        
        Object[][] data=new Object[ps[0].getSize()-1][2];
        
        String[] nombreDeColumnas = new String [2];
        nombreDeColumnas[0]=ps[0].get(0);
        nombreDeColumnas[1]=ps[1].get(0);
        
        for (int i=1;i<ps[0].getSize();i++){
            data[i-1][0]=ps[0].get(i);
            data[i-1][1]=ps[1].get(i);
        }
        
        JTable tabla=new JTable(data, nombreDeColumnas);
        tabla.setFont(new Font("Arial",Font.BOLD, 20));
        tabla.setSize(350, 350);
        tabla.setPreferredSize(new Dimension(350,350));
        tabla.setEnabled(false);
        
        return tabla;
    }
    
    public JTable hacerTabla(Object[][] data, String[] nombreDeColumnas){
        
        JTable tabla = new JTable(data, nombreDeColumnas);
        tabla.setEnabled(false);
        
        return tabla;
    }
    
    public static void main(String args[]) throws IOException{
        System.out.println("Sis");
        Graficas g=new Graficas ();
        ListaDobleString[] ps=new ListaDobleString[2];
        
        ps[0]=new ListaDobleString();
        ps[0].addFin("J");
        ps[0].addFin("J1");
        ps[0].addFin("J2");
        ps[0].addFin("J3");
        ps[0].addFin("J");
        ps[0].addFin("J1");
        ps[0].addFin("J2");
        ps[0].addFin("J3");
        ps[0].addFin("J3");
        ps[0].addFin("J3");
        
        ps[1]=new ListaDobleString();
        ps[1].addFin("1");
        ps[1].addFin("2");
        ps[1].addFin("3");
        ps[1].addFin("4");
        ps[1].addFin("1");
        ps[1].addFin("2");
        ps[1].addFin("3");
        ps[1].addFin("4");
        ps[1].addFin("3");
        ps[1].addFin("4");
        
        
        g.setImagenGraficaDeBarras("JAvier", ps);
        
        JFrame f = new JFrame();
        g.getTable(ps);
        f.add(g.getTable(ps));
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        System.out.println("Sis");
    }
}
