/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Enums.FORMATO;
import Listas.ListaDobleString;
import pdfGraficas.Graficas;
import calculoDeCeldas.LectorDeCeldas;
import calculoDeCeldas.LectorDeDocumentos;
import calculoDeCeldas.Sorts;
import com.itextpdf.text.DocumentException;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.table.TableModel;
import pdfGraficas.Pdf;

/**
 *
 * @author Alvarez
 */
public class ventanaPrincipal extends JFrame{
    public static int posicionActualY=0;
    public static int posicionActualX=0;
    
    LectorDeCeldas lectorDeCeldas;
    LectorDeDocumentos lectorDeDocumentos=new LectorDeDocumentos();
    
    public static Celda[][] celdas=new Celda[50][50];
    
    JTextField celdaActualTxtF=new JTextField();
    
    GridBagConstraints gbFrame=new GridBagConstraints();
    
    GridBagConstraints gbPanelCeldas=new GridBagConstraints();
    JPanel panelCeldas=new JPanel();
    
    GridBagConstraints gbReglaH=new GridBagConstraints();
    JPanel reglaH=new JPanel();
    
    GridBagConstraints gbReglaV=new GridBagConstraints();
    JPanel reglaV=new JPanel();
    
    JScrollPane scrollPaneCeldas=new JScrollPane();
    
    JViewport viewportH=new JViewport();
    JViewport viewportV=new JViewport();
    
    public ventanaPrincipal(){
        super("Proyecto 2");
        
        
        //Look and feel
        try{
            UIManager.setLookAndFeel(new MotifLookAndFeel());
        }
        catch(Exception e){}
        
        setSize(1200,720);
        setLayout(new GridBagLayout());
        
        gbFrame.gridx=0;
        gbFrame.gridy=1;
        celdaActualTxtF.setPreferredSize(new Dimension(65,25));
        add(celdaActualTxtF, gbFrame);
        
        panelCeldas.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        
        panelCeldas = new JPanel();//
        panelCeldas.setLayout(new GridBagLayout());
        gbPanelCeldas = new GridBagConstraints();
        gbPanelCeldas.gridy=0;
        gbPanelCeldas.gridx=0;
        
        for(int i=0;i<50; i++){
            gbPanelCeldas.gridy++;
            for(int e=0;e<50;e++){
                gbPanelCeldas.gridx++;

                celdas[i][e]=new Celda(celdaActualTxtF);
                celdas[i][e].setPreferredSize(new Dimension(90, 25));
                celdas[i][e].setPosicionY(i);
                celdas[i][e].setPosicionX(e);
                
                
                celdas[i][e].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        if(posicionActualY<50-1){
                            posicionActualY++;
                            celdas[posicionActualY][posicionActualX].requestFocus();
                        }
                    }
                });
                
                
                panelCeldas.add(celdas[i][e],gbPanelCeldas);
            }
            gbPanelCeldas.gridx=0;
        }
        
        //Para la Regla horizontal
        reglaH.setPreferredSize(new Dimension(4500,17));
        reglaH.setLayout(new GridBagLayout());
        for(int i=0; i<50;i++){
            gbReglaH.gridx=i;
            gbReglaH.fill=GridBagConstraints.HORIZONTAL;
            gbReglaH.weightx=1;
            JLabel tempLbl=new JLabel();
            tempLbl.setFont(new Font("Courier New",Font.BOLD,15));
            
            if(String.valueOf(i+1).length()==1){
                tempLbl.setText("0"+String.valueOf(i+1));
                reglaH.add(tempLbl, gbReglaH);
            }
            else{
                tempLbl.setText(String.valueOf(i+1));
                reglaH.add(tempLbl, gbReglaH);
            }
        }
        //Para la regla vertical
        reglaV.setPreferredSize(new Dimension(17,1250));
        reglaV.setLayout(new GridBagLayout());
        for(int i=0; i<50;i++){
            gbReglaV.gridy=i;
            gbReglaV.fill=GridBagConstraints.HORIZONTAL;
            gbReglaV.weighty=1;
            JLabel tempLbl=new JLabel();
            tempLbl.setFont(new Font("Courier New",Font.BOLD,15));
            
            if(String.valueOf(i+1).length()==1){
                tempLbl.setText("0"+String.valueOf(i+1));
                reglaV.add(tempLbl, gbReglaV);
            }
            else{
                tempLbl.setText(String.valueOf(i+1));
                reglaV.add(tempLbl, gbReglaV);
            }
            
        }      
        
        scrollPaneCeldas=new JScrollPane(panelCeldas);
        scrollPaneCeldas.setPreferredSize(new Dimension(1000,500));
        
        viewportH=new JViewport();
        viewportH.add(reglaH);
        scrollPaneCeldas.setColumnHeader(viewportH);
        
        viewportV=new JViewport();
        viewportV.add(reglaV);
        scrollPaneCeldas.setRowHeader(viewportV);
        
        gbFrame.gridx=1;
        gbFrame.gridy=1;
        add(scrollPaneCeldas, gbFrame);
        
        lectorDeCeldas=new LectorDeCeldas(celdas);
        
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tabbedPane();
    }
    
    JTabbedPane menu = new JTabbedPane();
    //
    JPanel inicioPane = new JPanel();
    GridBagConstraints gbInicio=new GridBagConstraints();
    
    JLabel edicionLbl=new JLabel("Edicion");
    JButton negritaBtn=new JButton("Negrita");
    JButton subrayadaBtn=new JButton("Subrayada");
    JButton cursivaBtn=new JButton("Cursiva");
    
    JLabel formatoDeTextoLbl=new JLabel("Formato de texto");
    JButton numeroBtn=new JButton("Numero");
    JButton porcentajeBtn=new JButton("Porcentaje");
    JButton textoBtn=new JButton("Texto");
    JButton generalBtn=new JButton("General");
    //
    //
            
    JPanel insertaPane=new JPanel();
    GridBagConstraints gbInserta=new GridBagConstraints();
        
    JButton documentoBtn=new JButton("Documento");
    JButton formulaBtn=new JButton("Formula");
    JButton referenciaACeldaBtn=new JButton("Referencia a celda");
    //
    //
    
    JPanel formulasPane = new JPanel();
    GridBagConstraints gbFormulas=new GridBagConstraints();
    
    JButton sumaBtn=new JButton("Suma");
    JButton restaBtn=new JButton("Resta");
    JButton promedioBtn=new JButton("Promedio");
    
    JButton multiplicacionBtn=new JButton("Multiplicacion");
    JButton divisionBtn=new JButton("Division");
    
    JButton maxBtn=new JButton("Max");
    JButton minBtn=new JButton("Min");
    //
    //
    JPanel reportesPane = new JPanel();
    GridBagConstraints gbReportes=new GridBagConstraints();
    
    JButton vistaPreviaBtn=new JButton("Vista Previa");
    JButton reportePdfBtn=new JButton("Generar PDF");
    JButton guardar=new JButton("Guardar");
    //
    //
    JButton updateBtn=new JButton("UPDATE");
    
    private void tabbedPane(){
        //Inicio
        inicioPane.setLayout(new GridBagLayout());
        
        gbInicio.gridx=0;
        gbInicio.gridy=0;
        inicioPane.add(edicionLbl, gbInicio);
        
        gbInicio.gridx=0;
        gbInicio.gridy=1;
        negritaBtn.setPreferredSize(new Dimension(110, 25));
        negritaBtn.setMinimumSize(new Dimension(110, 25));
        negritaBtn.setMaximumSize(new Dimension(110, 25));
        inicioPane.add(negritaBtn, gbInicio);
        
        gbInicio.gridx=0;
        gbInicio.gridy=2;
        cursivaBtn.setPreferredSize(new Dimension(110, 25));
        cursivaBtn.setMinimumSize(new Dimension(110, 25));
        cursivaBtn.setMaximumSize(new Dimension(110, 25));
        inicioPane.add(cursivaBtn, gbInicio);
        
        gbInicio.gridx=0;
        gbInicio.gridy=3;
        subrayadaBtn.setPreferredSize(new Dimension(110, 25));
        subrayadaBtn.setMinimumSize(new Dimension(110, 25));
        subrayadaBtn.setMaximumSize(new Dimension(110, 25));
        subrayadaBtn.setSize(75, 25);
        inicioPane.add(subrayadaBtn, gbInicio);
        //
        gbInicio.gridx=1;
        gbInicio.gridy=0;
        inicioPane.add(new JLabel("     "), gbInicio);
        //
        gbInicio.gridx=2;
        gbInicio.gridy=0;
        inicioPane.add(formatoDeTextoLbl, gbInicio);
        
        gbInicio.gridx=2;
        gbInicio.gridy=1;
        numeroBtn.setPreferredSize(new Dimension(110, 25));
        numeroBtn.setMinimumSize(new Dimension(110, 25));
        numeroBtn.setMaximumSize(new Dimension(110, 25));
        inicioPane.add(numeroBtn, gbInicio);
        
        gbInicio.gridx=2;
        gbInicio.gridy=2;
        porcentajeBtn.setPreferredSize(new Dimension(110, 25));
        porcentajeBtn.setMinimumSize(new Dimension(110, 25));
        porcentajeBtn.setMaximumSize(new Dimension(110, 25));
        inicioPane.add(porcentajeBtn, gbInicio);
        
        gbInicio.gridx=3;
        gbInicio.gridy=1;
        textoBtn.setPreferredSize(new Dimension(110, 25));
        textoBtn.setMinimumSize(new Dimension(110, 25));
        textoBtn.setMaximumSize(new Dimension(110, 25));
        inicioPane.add(textoBtn, gbInicio);
        
        gbInicio.gridx=3;
        gbInicio.gridy=2;
        generalBtn.setPreferredSize(new Dimension(110, 25));
        generalBtn.setMinimumSize(new Dimension(110, 25));
        generalBtn.setMaximumSize(new Dimension(110, 25));
        inicioPane.add(generalBtn, gbInicio);
        
        gbInicio.gridx=4;
        gbInicio.gridy=0;
        inicioPane.add(new JLabel("                                        "), gbInicio);
        
        gbInicio.gridx=5;
        gbInicio.gridy=1;
        gbInicio.gridheight=2;
        updateBtn.setPreferredSize(new Dimension(110, 50));
        updateBtn.setMinimumSize(new Dimension(110, 50));
        updateBtn.setMaximumSize(new Dimension(110, 50));
        updateBtn.setToolTipText("Actualiza el valor de todas las celdas");
        inicioPane.add(updateBtn, gbInicio);
        
        
        menu.addTab("Inicio", null, inicioPane, "En esta pestaña estan todas opciones de las celdas");
        
        //Insertar
        
        insertaPane.setLayout(new GridBagLayout());
        
        gbInserta.gridx=0;
        gbInserta.gridy=0;
        documentoBtn.setPreferredSize(new Dimension(150, 55));
        documentoBtn.setMinimumSize(new Dimension(150, 55));
        documentoBtn.setMaximumSize(new Dimension(150, 55));
        insertaPane.add(documentoBtn, gbInserta);
        
        gbInserta.gridx=1;
        gbInserta.gridy=0;
        insertaPane.add(new JLabel("     "), gbInserta);
        
        gbInserta.gridx=2;
        gbInserta.gridy=0;
        formulaBtn.setPreferredSize(new Dimension(150, 55));
        formulaBtn.setMinimumSize(new Dimension(150, 55));
        formulaBtn.setMaximumSize(new Dimension(150, 55));
        insertaPane.add(formulaBtn, gbInserta);
        
        gbInserta.gridx=3;
        gbInserta.gridy=0;
        insertaPane.add(new JLabel("     "), gbInserta);
        
        gbInserta.gridx=4;
        gbInserta.gridy=0;
        referenciaACeldaBtn.setPreferredSize(new Dimension(150, 55));
        referenciaACeldaBtn.setMinimumSize(new Dimension(150, 55));
        referenciaACeldaBtn.setMaximumSize(new Dimension(150, 55));
        insertaPane.add(referenciaACeldaBtn, gbInserta);
        
        menu.addTab("Insertar", null, insertaPane, "Para insertar elementos");
        
        //FORMULAS
        formulasPane.setLayout(new GridBagLayout());
        
        gbFormulas.gridx=0;
        gbFormulas.gridy=0;
        sumaBtn.setPreferredSize(new Dimension(110, 25));
        sumaBtn.setMinimumSize(new Dimension(110, 25));
        sumaBtn.setMaximumSize(new Dimension(110, 25));
        formulasPane.add(sumaBtn, gbFormulas);
        
        gbFormulas.gridx=0;
        gbFormulas.gridy=1;
        restaBtn.setPreferredSize(new Dimension(110, 25));
        restaBtn.setMinimumSize(new Dimension(110, 25));
        restaBtn.setMaximumSize(new Dimension(110, 25));
        restaBtn.setSize(75, 25);
        formulasPane.add(restaBtn, gbFormulas);
        
        gbFormulas.gridx=0;
        gbFormulas.gridy=2;
        promedioBtn.setPreferredSize(new Dimension(110, 25));
        promedioBtn.setMinimumSize(new Dimension(110, 25));
        promedioBtn.setMaximumSize(new Dimension(110, 25));
        formulasPane.add(promedioBtn, gbFormulas);
        
        gbFormulas.gridx=1;
        gbFormulas.gridy=0;
        formulasPane.add(new JLabel("     "), gbFormulas);
        
        gbFormulas.gridx=2;
        gbFormulas.gridy=0;
        multiplicacionBtn.setPreferredSize(new Dimension(110, 25));
        multiplicacionBtn.setMinimumSize(new Dimension(110, 25));
        multiplicacionBtn.setMaximumSize(new Dimension(110, 25));
        formulasPane.add(multiplicacionBtn, gbFormulas);
        
        gbFormulas.gridx=2;
        gbFormulas.gridy=1;
        divisionBtn.setPreferredSize(new Dimension(110, 25));
        divisionBtn.setMinimumSize(new Dimension(110, 25));
        divisionBtn.setMaximumSize(new Dimension(110, 25));
        formulasPane.add(divisionBtn, gbFormulas);
        
        gbFormulas.gridx=3;
        gbFormulas.gridy=0;
        formulasPane.add(new JLabel("     "), gbFormulas);
        
        gbFormulas.gridx=4;
        gbFormulas.gridy=0;
        maxBtn.setPreferredSize(new Dimension(110, 25));
        maxBtn.setMinimumSize(new Dimension(110, 25));
        maxBtn.setMaximumSize(new Dimension(110, 25));
        formulasPane.add(maxBtn, gbFormulas);
        
        gbFormulas.gridx=4;
        gbFormulas.gridy=1;
        minBtn.setPreferredSize(new Dimension(110, 25));
        minBtn.setMinimumSize(new Dimension(110, 25));
        minBtn.setMaximumSize(new Dimension(110, 25));
        formulasPane.add(minBtn, gbFormulas);
        
        
        menu.addTab("Formulas", null, formulasPane, "En esta pestaña estan todas las formulas disponibles");

        //REPORTES
        reportesPane.setLayout(new GridBagLayout());
        
        gbReportes.gridx=0;
        gbReportes.gridy=0;
        reportePdfBtn.setPreferredSize(new Dimension(150, 55));
        reportePdfBtn.setMinimumSize(new Dimension(150, 55));
        reportePdfBtn.setMaximumSize(new Dimension(150, 55));
        reportesPane.add(reportePdfBtn, gbReportes);
        
        gbReportes.gridx=1;
        gbReportes.gridy=0;
        reportesPane.add(new JLabel("     "), gbReportes);
        
        gbReportes.gridx=2;
        gbReportes.gridy=0;
        vistaPreviaBtn.setPreferredSize(new Dimension(150, 55));
        vistaPreviaBtn.setMinimumSize(new Dimension(150, 55));
        vistaPreviaBtn.setMaximumSize(new Dimension(150, 55));
        reportesPane.add(vistaPreviaBtn, gbReportes);
        
        gbReportes.gridx=3;
        gbReportes.gridy=0;
        reportesPane.add(new JLabel("     "), gbReportes);
        
        gbReportes.gridx=4;
        gbReportes.gridy=0;
        guardar.setPreferredSize(new Dimension(150, 55));
        guardar.setMinimumSize(new Dimension(150, 55));
        guardar.setMaximumSize(new Dimension(150, 55));
        reportesPane.add(guardar, gbReportes);
        
        menu.addTab("Reportes", null, reportesPane, "En esta pestaña es donde se generan los reportes");
        menu.setPreferredSize(new Dimension(1000,120));
        
        //fin
        gbFrame.gridx=1;
        gbFrame.gridy=0;
        
        add(menu,gbFrame);
        
        agregarEventos();
    }
    
    Graficas g = new Graficas();
    
    private void agregarEventos(){
    
        negritaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(celdas[posicionActualY][posicionActualX].getFont().isBold()){
                    if(celdas[posicionActualY][posicionActualX].getFont().isItalic()){
                        celdas[posicionActualY][posicionActualX].setFont(new Font("Dialog", Font.PLAIN,12));
                        celdas[posicionActualY][posicionActualX].setFont(new Font("Dialog", Font.ITALIC,12));
                    }
                    else{
                        celdas[posicionActualY][posicionActualX].setFont(new Font("Dialog", Font.PLAIN,12));
                    }
                }
                else{
                    if(celdas[posicionActualY][posicionActualX].getFont().isItalic()){
                        celdas[posicionActualY][posicionActualX].setFont(new Font("Dialog", Font.ITALIC+Font.BOLD,12));
                    }
                    else{
                        celdas[posicionActualY][posicionActualX].setFont(new Font("Dialog", Font.BOLD,12));
                    }
                }
            }
        });
    
        cursivaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(celdas[posicionActualY][posicionActualX].getFont().isItalic()){
                    if(celdas[posicionActualY][posicionActualX].getFont().isBold()){
                        celdas[posicionActualY][posicionActualX].setFont(new Font("Dialog", Font.PLAIN,12));
                        celdas[posicionActualY][posicionActualX].setFont(new Font("Dialog", Font.BOLD,12));
                    }
                    else{
                        celdas[posicionActualY][posicionActualX].setFont(new Font("Dialog", Font.PLAIN,12));
                    }
                }
                else{
                    if(celdas[posicionActualY][posicionActualX].getFont().isBold()){
                        celdas[posicionActualY][posicionActualX].setFont(new Font("Dialog", Font.ITALIC+Font.BOLD,12));
                    }
                    else{
                        celdas[posicionActualY][posicionActualX].setFont(new Font("Dialog", Font.ITALIC,12));
                    }
                }
            }
        });
    
        subrayadaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    
        numeroBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                celdas[posicionActualY][posicionActualX].setFormato(FORMATO.Numero);
                celdas[posicionActualY][posicionActualX].requestFocus();
            }
        });
    
        porcentajeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                celdas[posicionActualY][posicionActualX].setFormato(FORMATO.Porcentaje);
                celdas[posicionActualY][posicionActualX].requestFocus();
            }
        });
        
    
        textoBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                celdas[posicionActualY][posicionActualX].setFormato(FORMATO.Texto);
                celdas[posicionActualY][posicionActualX].requestFocus();
            }
        });
    
        generalBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        updateBtn.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent ev) {
                for (int i=0; i<celdas.length;i++){
                    for (int e=0; e<celdas[i].length;e++){
                        celdas[i][e].update();
                    }
                }
            }
        });
        
        //
        
        documentoBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    lectorDeDocumentos.LeerDocumento("C:\\Users\\Alvarez\\Documents\\NetBeansProjects\\Proyecto2\\documento.txt", ",");
                    
                    for(int i=0; i<lectorDeDocumentos.lineas.getSize();i++){
                        for(int e=0;e<lectorDeDocumentos.lineas.get(i).getSize();e++){
                            celdas[i][e].setText(lectorDeDocumentos.lineas.get(i).get(e));
                            celdas[i][e].setValor(lectorDeDocumentos.lineas.get(i).get(e));
                        }
                    }
                    
                    lectorDeDocumentos=new LectorDeDocumentos();
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i=0; i<celdas.length;i++){
                    for (int e=0; e<celdas[i].length;e++){
                        celdas[i][e].update();
                    }
                }
            }
        });
        
        formulaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev) {
                String[] opciones = new String [7];
                
                opciones[0]="=SUM(";
                opciones[1]="=RES(";
                opciones[2]="=PRO(";
                opciones[3]="=MUL(";
                opciones[4]="=DIV(";
                opciones[5]="=MIN(";
                opciones[6]="=MAX(";
                
                celdas[posicionActualY][posicionActualX].setText(String.valueOf(JOptionPane.showInputDialog(null, "Que formula desea insertar", 
                        "Insercion de formula", 
                        JOptionPane.QUESTION_MESSAGE,
                        null,opciones,opciones[0])));
                celdas[posicionActualY][posicionActualX].setValor(celdas[posicionActualY][posicionActualX].getText());
                celdas[posicionActualY][posicionActualX].requestFocus();
            }
        });
        
        referenciaACeldaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] opciones = new String [celdas.length];
                
                for(int i=0;i<celdas.length;i++){
                    opciones[i]=String.valueOf(i+1);
                }
                
                celdas[posicionActualY][posicionActualX].setText(celdas[posicionActualY][posicionActualX].getText()+":"+
                        String.valueOf(JOptionPane.showInputDialog(null, "Coordenada en Y", 
                        "Insercion de celda", 
                        JOptionPane.QUESTION_MESSAGE,
                        null,opciones,opciones[0]))+","+
                        String.valueOf(JOptionPane.showInputDialog(null, "Coordenada en X", 
                        "Insercion de celda", 
                        JOptionPane.QUESTION_MESSAGE,
                        null,opciones,opciones[0]))+":");
                celdas[posicionActualY][posicionActualX].setValor(celdas[posicionActualY][posicionActualX].getText());
                celdas[posicionActualY][posicionActualX].requestFocus();
            }
        });
        
        //
        
        sumaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int yInicial=0;
                int xInicial=0;
                int yFinal=0;
                int xFinal=0;

                
                try{
                    yInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la primera celda"))-1;
                    xInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la primera celda"))-1;
                    
                    yFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la ultima celda"))-1;
                    xFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la ultima celda"))-1;
                }
                catch(Exception ex){
                    yInicial=0;
                    xInicial=0;
                    yFinal=0;
                    xFinal=0;
                }
                
                JOptionPane.showMessageDialog(null, lectorDeCeldas.suma(yInicial, xInicial, yFinal, xFinal));
                
            }
        });
    
        restaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int yInicial=0;
                int xInicial=0;
                int yFinal=0;
                int xFinal=0;

                
                try{
                    yInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la primera celda"))-1;
                    xInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la primera celda"))-1;
                    
                    yFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la ultima celda"))-1;
                    xFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la ultima celda"))-1;
                }
                catch(Exception ex){
                    yInicial=0;
                    xInicial=0;
                    yFinal=0;
                    xFinal=0;
                }
                
                JOptionPane.showMessageDialog(null, lectorDeCeldas.resta(yInicial, xInicial, yFinal, xFinal));
                
            }
        });
    
        promedioBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int yInicial=0;
                int xInicial=0;
                int yFinal=0;
                int xFinal=0;

                
                try{
                    yInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la primera celda"))-1;
                    xInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la primera celda"))-1;
                    
                    yFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la ultima celda"))-1;
                    xFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la ultima celda"))-1;
                }
                catch(Exception ex){
                    yInicial=0;
                    xInicial=0;
                    yFinal=0;
                    xFinal=0;
                }
                
                JOptionPane.showMessageDialog(null, lectorDeCeldas.promedio(yInicial, xInicial, yFinal, xFinal));
            }
        });
    
        multiplicacionBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int yInicial=0;
                int xInicial=0;
                int yFinal=0;
                int xFinal=0;

                
                try{
                    yInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la primera celda"))-1;
                    xInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la primera celda"))-1;
                    
                    yFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la ultima celda"))-1;
                    xFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la ultima celda"))-1;
                }
                catch(Exception ex){
                    yInicial=0;
                    xInicial=0;
                    yFinal=0;
                    xFinal=0;
                }
                
                JOptionPane.showMessageDialog(null, lectorDeCeldas.multiplicacion(yInicial, xInicial, yFinal, xFinal));
            }
        });
    
        divisionBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int yInicial=0;
                int xInicial=0;
                int yFinal=0;
                int xFinal=0;

                
                try{
                    yInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la primera celda"))-1;
                    xInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la primera celda"))-1;
                    
                    yFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la ultima celda"))-1;
                    xFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la ultima celda"))-1;
                }
                catch(Exception ex){
                    yInicial=0;
                    xInicial=0;
                    yFinal=0;
                    xFinal=0;
                }
                
                JOptionPane.showMessageDialog(null, lectorDeCeldas.division(yInicial, xInicial, yFinal, xFinal));
            }
        });
    
        maxBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int yInicial=0;
                int xInicial=0;
                int yFinal=0;
                int xFinal=0;

                
                try{
                    yInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la primera celda"))-1;
                    xInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la primera celda"))-1;
                    
                    yFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la ultima celda"))-1;
                    xFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la ultima celda"))-1;
                }
                catch(Exception ex){
                    yInicial=0;
                    xInicial=0;
                    yFinal=0;
                    xFinal=0;
                }
                
                JOptionPane.showMessageDialog(null, lectorDeCeldas.max(yInicial, xInicial, yFinal, xFinal));
            }
        });
    
        minBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int yInicial=0;
                int xInicial=0;
                int yFinal=0;
                int xFinal=0;

                
                try{
                    yInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la primera celda"))-1;
                    xInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la primera celda"))-1;
                    
                    yFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la ultima celda"))-1;
                    xFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la ultima celda"))-1;
                }
                catch(Exception ex){
                    yInicial=0;
                    xInicial=0;
                    yFinal=0;
                    xFinal=0;
                }
                
                JOptionPane.showMessageDialog(null, lectorDeCeldas.min(yInicial, xInicial, yFinal, xFinal));
            }
        });
    
        reportePdfBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev) {
                
                DashBoard d;
                
                int yInicial=0;
                int xInicial=0;
                int yFinal=0;
                int xFinal=0;
                
                
                try{
                    yInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la primera celda"))-1;
                    xInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la primera celda"))-1;
                    
                    yFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la ultima celda"))-1;
                    xFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la ultima celda"))-1;
                }
                catch(Exception ex){
                    yInicial=1;
                    xInicial=0;
                    yFinal=1;
                    xFinal=0;
                }
                

                //PARA CLIENTES POR TOTAL
                ListaDobleString[] clientesTotal =hacerLista(yInicial, xInicial, yFinal, xFinal, 6,8);
                JTable tabla1=g.getTable(clientesTotal);
                try {
                    g.setImagenGraficaDePie("Grafica de pie del total gastado por cliente", clientesTotal);
                    g.setImagenGraficaDeBarras("Grafica de barra del total gastado por cliente", clientesTotal);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("ERROR");
                }
                
                //PARA CLIENTES POR CANTIDAD
                ListaDobleString[] clientesCantidad =hacerLista(yInicial, xInicial, yFinal, xFinal, 6,1);
                JTable tablaA=g.getTable(clientesCantidad);
                try {
                    g.setImagenGraficaDePie("Grafica de pie por cantidad de productos comprados por cliente", clientesCantidad);
                    g.setImagenGraficaDeBarras("Grafica de barra por cantidad de productos comprados por cliente", clientesCantidad);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("ERROR");
                }
                
                //
                //
                
                //PARA PRODUCTOS TOTAL
                ListaDobleString[] productosTotal =hacerLista(yInicial, xInicial, yFinal, xFinal, 0,8);
                JTable tabla2=g.getTable(productosTotal);
                try {
                    g.setImagenGraficaDePie("Grafica de pie del total gastado por producto", productosTotal);
                    g.setImagenGraficaDeBarras("Grafica de barras del total gastado por producto", productosTotal);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //PARA PRODUCTOS POR CANTIDAD
                ListaDobleString[] productosCantidad =hacerLista(yInicial, xInicial, yFinal, xFinal, 0,1);
                JTable tablaB=g.getTable(productosCantidad);
                try {
                    g.setImagenGraficaDePie("Grafica de pie del numero de unidades por producto", productosCantidad);
                    g.setImagenGraficaDeBarras("Grafica de barras del numero de unidades por producto", productosCantidad);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //
                //
                
                //PARA FORMAS DE PAGO TOTAL
                ListaDobleString[] formasDePagoTotal =hacerLista(yInicial, xInicial, yFinal, xFinal, 7,8);
                JTable tabla3=g.getTable(formasDePagoTotal);
                try {
                    g.setImagenGraficaDePie("Grafica de pie del total gastado por forma de pago", formasDePagoTotal);
                    g.setImagenGraficaDeBarras("Grafica de barras del total gastado por forma de pago", formasDePagoTotal);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //PARA PRODUCTOS POR CANTIDAD
                ListaDobleString[] formasDePagoCantidad =hacerLista(yInicial, xInicial, yFinal, xFinal, 7,1);
                JTable tablaC=g.getTable(formasDePagoCantidad);
                try {
                    g.setImagenGraficaDePie("Grafica de pie de la cantidad de veces que se utililzo cada forma de pago", formasDePagoCantidad);
                    g.setImagenGraficaDeBarras("Grafica de barras de la cantidad de veces que se utililzo cada forma de pago", formasDePagoCantidad);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //PARA REPORTE DE TIEMPO
                JTable tabla4=new JTable();
                JTable tablaD=new JTable();
                
                Object[][] data=new Object[yFinal-yInicial][xFinal-xInicial+1];
        
                String[] nombreDeColumnas = new String [xFinal-xInicial+1];
                for(int i=0; i<xFinal-xInicial+1;i++){
                    nombreDeColumnas[i]=celdas[yInicial][xInicial+i].getText();
                    System.out.println(nombreDeColumnas[i]);
                }
        
                for (int i=0;i<yFinal-yInicial;i++){
                    for(int e=0;e<xFinal-xInicial+1; e++){
                        data[i][e]=String.valueOf(celdas[i+yInicial+1][e+xInicial].getValor());
                    }
                }
                
                JTable tablaDeDatos = g.hacerTabla(data, nombreDeColumnas);
                
                JFrame frame=new JFrame();
                frame.add(tablaDeDatos);
                frame.pack();
                frame.setVisible(true);
                
                d = new DashBoard();
                
                d.hacerImagenes(tabla1, tabla2, tabla3, tabla4, tablaA, tablaB, tablaC, tablaD, data, nombreDeColumnas);
                
                String[] s ={
                    "Grafica de pie del total gastado por cliente.jpeg", 
                    "Grafica de barra del total gastado por cliente.jpeg", 
                    "Grafica de pie por cantidad de productos comprados por cliente.jpeg", 
                    "Grafica de barra por cantidad de productos comprados por cliente.jpeg", 
                    "Grafica de pie del total gastado por producto.jpeg", 
                    "Grafica de barras del total gastado por producto.jpeg", 
                    "Grafica de pie del numero de unidades por producto.jpeg",
                    "Grafica de barras del numero de unidades por producto.jpeg",
                    "Grafica de pie del total gastado por forma de pago.jpeg",
                    "Grafica de barras del total gastado por forma de pago.jpeg",
                    "Grafica de pie de la cantidad de veces que se utililzo cada forma de pago.jpeg",
                    "Grafica de barras de la cantidad de veces que se utililzo cada forma de pago.jpeg"
                };
                
                ListaDobleString lista = new ListaDobleString();
                for(int i=0; i<s.length; i++){
                    lista.addFin(s[i]);
                }
                
                Pdf pdf = new Pdf();
                try {
                    pdf.hacerReporte("ReportePDF", lista);
                } catch (DocumentException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }      
            }
        });
    
        vistaPreviaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev) {
                
                DashBoard d;
                
                int yInicial=0;
                int xInicial=0;
                int yFinal=0;
                int xFinal=0;
                
                
                try{
                    yInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la primera celda"))-1;
                    xInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la primera celda"))-1;
                    
                    yFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la ultima celda"))-1;
                    xFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la ultima celda"))-1;
                }
                catch(Exception ex){
                    yInicial=1;
                    xInicial=0;
                    yFinal=1;
                    xFinal=0;
                }
                

                //PARA CLIENTES POR TOTAL
                ListaDobleString[] clientesTotal =hacerLista(yInicial, xInicial, yFinal, xFinal, 6,8);
                JTable tabla1=g.getTable(clientesTotal);
                try {
                    g.setImagenGraficaDePie("Grafica de pie del total gastado por cliente", clientesTotal);
                    g.setImagenGraficaDeBarras("Grafica de barra del total gastado por cliente", clientesTotal);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("ERROR");
                }
                
                //PARA CLIENTES POR CANTIDAD
                ListaDobleString[] clientesCantidad =hacerLista(yInicial, xInicial, yFinal, xFinal, 6,1);
                JTable tablaA=g.getTable(clientesCantidad);
                try {
                    g.setImagenGraficaDePie("Grafica de pie por cantidad de productos comprados por cliente", clientesCantidad);
                    g.setImagenGraficaDeBarras("Grafica de barra por cantidad de productos comprados por cliente", clientesCantidad);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("ERROR");
                }
                
                //
                //
                
                //PARA PRODUCTOS TOTAL
                ListaDobleString[] productosTotal =hacerLista(yInicial, xInicial, yFinal, xFinal, 0,8);
                JTable tabla2=g.getTable(productosTotal);
                try {
                    g.setImagenGraficaDePie("Grafica de pie del total gastado por producto", productosTotal);
                    g.setImagenGraficaDeBarras("Grafica de barras del total gastado por producto", productosTotal);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //PARA PRODUCTOS POR CANTIDAD
                ListaDobleString[] productosCantidad =hacerLista(yInicial, xInicial, yFinal, xFinal, 0,1);
                JTable tablaB=g.getTable(productosCantidad);
                try {
                    g.setImagenGraficaDePie("Grafica de pie del numero de unidades por producto", productosCantidad);
                    g.setImagenGraficaDeBarras("Grafica de barras del numero de unidades por producto", productosCantidad);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //
                //
                
                //PARA FORMAS DE PAGO TOTAL
                ListaDobleString[] formasDePagoTotal =hacerLista(yInicial, xInicial, yFinal, xFinal, 7,8);
                JTable tabla3=g.getTable(formasDePagoTotal);
                try {
                    g.setImagenGraficaDePie("Grafica de pie del total gastado por forma de pago", formasDePagoTotal);
                    g.setImagenGraficaDeBarras("Grafica de barras del total gastado por forma de pago", formasDePagoTotal);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //PARA PRODUCTOS POR CANTIDAD
                ListaDobleString[] formasDePagoCantidad =hacerLista(yInicial, xInicial, yFinal, xFinal, 7,1);
                JTable tablaC=g.getTable(formasDePagoCantidad);
                try {
                    g.setImagenGraficaDePie("Grafica de pie de la cantidad de veces que se utililzo cada forma de pago", formasDePagoCantidad);
                    g.setImagenGraficaDeBarras("Grafica de barras de la cantidad de veces que se utililzo cada forma de pago", formasDePagoCantidad);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //PARA REPORTE DE TIEMPO
                JTable tabla4=new JTable();
                JTable tablaD=new JTable();
                
                Object[][] data=new Object[yFinal-yInicial][xFinal-xInicial+1];
        
                String[] nombreDeColumnas = new String [xFinal-xInicial+1];
                for(int i=0; i<xFinal-xInicial+1;i++){
                    nombreDeColumnas[i]=celdas[yInicial][xInicial+i].getText();
                    System.out.println(nombreDeColumnas[i]);
                }
        
                for (int i=0;i<yFinal-yInicial;i++){
                    for(int e=0;e<xFinal-xInicial+1; e++){
                        data[i][e]=String.valueOf(celdas[i+yInicial+1][e+xInicial].getValor());
                    }
                }
                
                JTable tablaDeDatos = g.hacerTabla(data, nombreDeColumnas);
                
                JFrame frame=new JFrame();
                frame.add(tablaDeDatos);
                frame.pack();
                frame.setVisible(true);
                
                String[] dias = new String[30];
                for(int i=0; i< dias.length;i++){
                    dias[i]=String.valueOf(i+1);
                }
                
                String[] meses = new String[12];
                for(int i=0; i< meses.length;i++){
                    meses[i]=String.valueOf(i+1);
                }
                
                String[] años = new String[18];
                for(int i=0; i< dias.length;i++){
                    dias[i]=String.valueOf(i+2000);
                }
                
                int diaI=1;
                int mesI=1;
                int añoI=1;
                
                int FechaI=diaI+mesI*30+añoI*360;
                
                
                
                int diaF=30;
                int mesF=12;
                int añoF=3000;
                
                int FechaF=diaF+mesF*30+añoF*360;
                
                d = new DashBoard(tabla1, tabla2, tabla3, tabla4, tablaA, tablaB, tablaC,tablaD, data, nombreDeColumnas, FechaI, FechaF);
            }
        });
        guardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev) {
                int yInicial=0;
                int xInicial=0;
                int yFinal=0;
                int xFinal=0;
                
                
                try{
                    yInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la primera celda"))-1;
                    xInicial=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la primera celda"))-1;
                    
                    yFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la columna de la ultima celda"))-1;
                    xFinal=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la fila de la ultima celda"))-1;
                }
                catch(Exception ex){
                    yInicial=1;
                    xInicial=0;
                    yFinal=1;
                    xFinal=0;
                }
                
                File archivo;
                FileWriter w;
                BufferedWriter bw;
                PrintWriter wr;
                
                try {
                    archivo=new File("archivoNuevo");
                    w=new FileWriter(archivo);
                    bw=new BufferedWriter(w);
                    wr = new PrintWriter(bw);
                    
                    wr.write("");
                    
                    for (int i=yInicial;i<yFinal+1;i++){
                        for(int e=xInicial;e<xFinal+1; e++){
                            wr.append(String.valueOf(celdas[i][e].getValor())+",");
                        }
                        wr.println();
                    }
                    
                    wr.close();
                    bw.close();
                } catch (Exception ex) {
                    Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                JOptionPane.showMessageDialog(null, "SE GUARDO EL ARCHIVO");
                
            }
        });
        
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
        
        Sorts s = new Sorts();
        
        ListaDobleString[] ps =  s.ordenar(y, x, yFinal, xFinal, columna1, columna2);
        return ps;
    }
    
}
