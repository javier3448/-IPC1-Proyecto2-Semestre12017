/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfGraficas;

import Listas.ListaDobleString;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static javax.swing.text.StyleConstants.FontFamily;

/**
 *
 * @author Alvarez
 */
public class Pdf {
    public void hacerReporte(String titulo, ListaDobleString nombreDeImagenes) throws FileNotFoundException, DocumentException, BadElementException, IOException{
        Document reporte= new Document();
        
        PdfWriter.getInstance(reporte, new FileOutputStream(titulo+".pdf"));
        reporte.open();
        
        Image[] imagenes = new Image[nombreDeImagenes.getSize()];
        Paragraph[] parrafos = new Paragraph[nombreDeImagenes.getSize()];
        
        for(int i=0;i<nombreDeImagenes.getSize();i++){
            parrafos[i]=new Paragraph(nombreDeImagenes.get(i)
                    .substring(0, nombreDeImagenes.get(i).length()-5));
            parrafos[i].setAlignment(1);
            imagenes[i]=Image.getInstance(nombreDeImagenes.get(i));
            imagenes[i].setAlignment(1);
            reporte.add(parrafos[i]);
            reporte.add(imagenes[i]);
        }
        
        reporte.close();
    }
    
    public static void main(String args[]) throws DocumentException, BadElementException, IOException{
        Pdf pdf = new Pdf();
        ListaDobleString s = new ListaDobleString();
        s.addFin("barrasClientesC.jpeg");
        s.addFin("barrasClientesT.jpeg");
        s.addFin("barrasProductosC.jpeg");
        s.addFin("barrasProductosT.jpeg");
        pdf.hacerReporte("Titulo",s);
    }
}
