package controlador;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import modelo.Modelos;

/**
 * Clase para exportar la jtable de consultar a .xml y .sql
 * @author diego
 *
 */
public class Exportar {

	/**
	 * Metodo para exportar a .xml la consulta realizada en la ventana "Consultar"
	 * @param tb
	 */
	 public void exportarXML(JTable tb){
	 	try{
            String file = "madridAutos";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Datos");
            doc.appendChild(rootElement);
            
            int i=0;
            
            while (i<tb.getRowCount()){
                int j=0;
                Element rows = doc.createElement("Fila");
                rootElement.appendChild(rows);
                
                while (j<tb.getColumnCount()){
                    Element element = doc.createElement(tb.getTableHeader().getColumnModel().getColumn(j).getHeaderValue()+"");
                    element.appendChild(doc.createTextNode(tb.getModel().getValueAt(i,j)+""));
                    rows.appendChild(element);
                    j++;
                }
                i++;
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            DOMSource source = new DOMSource(doc);
            StreamResult result;
            
            try{
                FileOutputStream fileOutputStream = null;
                
                fileOutputStream = new FileOutputStream(new File(file+".xml"));
                
                result = new StreamResult(fileOutputStream);
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, result);
                try{
                    fileOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Fichero .xml creado correctamente");
        }catch (ParserConfigurationException pce){
        	JOptionPane.showMessageDialog(null, "Error al crear el fichero .xml");
            pce.printStackTrace();
        } catch (TransformerException te){
        	JOptionPane.showMessageDialog(null, "Error al crear el fichero .xml");
            te.printStackTrace();
        }
	 }
	 
	 /**
	  * Metodo para exportar a .sql la consulta realizada en la ventana "Consultar"
	  * @param tb
	  * @param modeloActual
	  */
	 public void exportarSQL(JTable tb,ArrayList<Modelos> modeloActual) {
		 File fichero = null;
	        PrintWriter pw = null;
	        try {
	            fichero = new File("MadridAutos.sql");
	            pw = new PrintWriter(fichero);
	            
	            pw.print("CREATE DATABASE IF NOT EXISTS 'madridautos';\n"
	            		+ "USE 'madridautos';\n\n");
	            
	            pw.print("CREATE TABLE 'modelos' (\n"
	            		+ "  ID int(6) NOT NULL,\r\n"
	            		+ "  ID_MARCA int(6) NOT NULL,\r\n"
	            		+ "  MODELO varchar(200) NOT NULL,\r\n"
	            		+ "  CONSUMO decimal(4,1) NOT NULL,\r\n"
	            		+ "  EMISIONES decimal(4,1) NOT NULL,\r\n"
	            		+ "  C_ENERGETICA varchar(2) NOT NULL\r\n"
	            		+ ");\n\n");
	            
	            pw.print("INSERT INTO modelos (ID, ID_MARCA, MODELO, CONSUMO, EMISIONES, C_ENERGETICA) VALUES\n");
	            for (int i = 0; i <= modeloActual.size(); i++) {
		            if(i==modeloActual.size()-1) {
	            	pw.print( "("
	            			+ modeloActual.get(i).getId() + ","
	            			+ modeloActual.get(i).getId_marca() + ",'"
        					+ modeloActual.get(i).getModelo() + "',"
							+ modeloActual.get(i).getConsumo() + ","
							+ modeloActual.get(i).getEmisiones() + ",'"
							+ modeloActual.get(i).getC_energetica() + "');");
            		
	            	}else if(i<modeloActual.size()){
	            		pw.print( "("
		            			+ modeloActual.get(i).getId() + ","
		            			+ modeloActual.get(i).getId_marca() + ",'"
	        					+ modeloActual.get(i).getModelo() + "',"
								+ modeloActual.get(i).getConsumo() + ","
								+ modeloActual.get(i).getEmisiones() + ",'"
								+ modeloActual.get(i).getC_energetica() + "'),\n");
	            	}
	            }
	            JOptionPane.showMessageDialog(null, "Fichero .sql creado correctamente");
	        } catch (FileNotFoundException e) {
	        	JOptionPane.showMessageDialog(null, "Error al crear el fichero .sql");
	        } finally {
	            if (pw != null) {
	                pw.close();
	            }
	        }
	 }
}
