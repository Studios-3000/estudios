package vista;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import controlador.BasesDatos;
import controlador.Exportar;
import modelo.Marcas;
import modelo.Modelos;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ItemEvent;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.Component;

/**
 * Ventana para consultar los diferentes modelos de coche en el Jtable
 * @author diego
 *
 */
public class Consultar extends JPanel {
	private JTable jtable;
	private BasesDatos bd = new BasesDatos();
	private Exportar ex = new Exportar();
	private ArrayList<Modelos> arrModelos =  bd.cargaModelos();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public Consultar() {
		setBounds(0, 0, 950, 550);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(113, 34, 250));
		panel.setLayout(null);
		panel.setBounds(0, 0, 950, 550);
		add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 149, 925, 251);
		panel.add(scrollPane);
		
		jtable = new JTable(){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
		};
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jtable.setBackground(new Color(255, 172, 252));
		scrollPane.setViewportView(jtable);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(new Color(86, 10, 134));
		toolBar.setBounds(0, 411, 940, 59);
		panel.add(toolBar);
		toolBar.setFloatable(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Consultar.class.getResource("/img/consultar.png")));
		toolBar.add(lblNewLabel);
		
		JButton btnXML = new JButton("");
		btnXML.setIcon(new ImageIcon(Consultar.class.getResource("/img/xml.png")));
		/**
		 * Botón para exportar el Jtable a .xml
		 */
		btnXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ex.exportarXML(jtable);
			}
		});
		btnXML.setForeground(Color.WHITE);
		btnXML.setBackground(new Color(86, 10, 134));
		toolBar.add(btnXML);
		
		JButton btnSQL = new JButton("");
		/**
		 * Botón para exportar el Jtable a .sql
		 */
		btnSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ex.exportarSQL(jtable,arrModelos);
			}
		});
		btnSQL.setIcon(new ImageIcon(Consultar.class.getResource("/img/sql.png")));
		btnSQL.setForeground(Color.WHITE);
		btnSQL.setBackground(new Color(86, 10, 134));
		toolBar.add(btnSQL);
		
		JButton btnXLS = new JButton("");
		btnXLS.setIcon(new ImageIcon(Consultar.class.getResource("/img/excel.png")));
		/**
		 * Botón para exportar el Jtable a archivo excel
		 */
		btnXLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileOutputStream excelFOU = null;
				BufferedOutputStream excelBOU = null;
				XSSFWorkbook excelJTableExporter = null;
				
				JFileChooser excelFileChooser = new JFileChooser();

				excelFileChooser.setDialogTitle("Guardar como..");
				

				FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls","xlsx","xlsm");
				excelFileChooser.setFileFilter(fnef);
				int excelChooser = excelFileChooser.showSaveDialog(null);
				

				if(excelChooser == JFileChooser.APPROVE_OPTION) {

					try {
						excelJTableExporter = new XSSFWorkbook();
						XSSFSheet excelSheet = excelJTableExporter.createSheet("Jtable Sheet");
						
						for(int i=0; i < jtable.getRowCount(); i++) {
							XSSFRow excelRow = excelSheet.createRow(i);
							for(int j=0; j < jtable.getColumnCount(); j++) {
								XSSFCell excelCell = excelRow.createCell(j);
								
								excelCell.setCellValue(jtable.getValueAt(i, j).toString());
							}
						}

							excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile()+ ".xls");
							excelBOU = new BufferedOutputStream(excelFOU);
							excelJTableExporter.write(excelBOU);
							JOptionPane.showMessageDialog(null, "Exportado correctamente");
						
					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					} finally {
						try {
							if(excelBOU != null) {
								excelBOU.close();
							}
							if(excelFOU != null) {
								excelFOU.close();
							}
							if(excelJTableExporter != null) {
								excelJTableExporter.close();
							}
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}	
				}		
			}
		});
		btnXLS.setForeground(Color.WHITE);
		btnXLS.setBackground(new Color(86, 10, 134));
		toolBar.add(btnXLS);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setForeground(Color.WHITE);
		lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarca.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblMarca.setBounds(66, 44, 75, 22);
		panel.add(lblMarca);
		
		JLabel lblClasificacion = new JLabel("Clasificacion");
		lblClasificacion.setForeground(Color.WHITE);
		lblClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblClasificacion.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblClasificacion.setBounds(711, 40, 143, 30);
		panel.add(lblClasificacion);
		
		JLabel lblA = new JLabel("A");
		lblA.setForeground(Color.WHITE);
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setFont(new Font("Impact", Font.PLAIN, 15));
		lblA.setBounds(648, 118, 19, 20);
		panel.add(lblA);
		
		JLabel lblB = new JLabel("B");
		lblB.setForeground(Color.WHITE);
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		lblB.setFont(new Font("Impact", Font.PLAIN, 15));
		lblB.setBounds(684, 118, 19, 20);
		panel.add(lblB);
		
		JLabel lblC = new JLabel("C");
		lblC.setForeground(Color.WHITE);
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setFont(new Font("Impact", Font.PLAIN, 15));
		lblC.setBounds(720, 118, 19, 20);
		panel.add(lblC);
		
		JLabel lblD = new JLabel("D");
		lblD.setForeground(Color.WHITE);
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		lblD.setFont(new Font("Impact", Font.PLAIN, 15));
		lblD.setBounds(755, 118, 19, 20);
		panel.add(lblD);
		
		JLabel lblE = new JLabel("E");
		lblE.setForeground(Color.WHITE);
		lblE.setHorizontalAlignment(SwingConstants.CENTER);
		lblE.setFont(new Font("Impact", Font.PLAIN, 15));
		lblE.setBounds(790, 118, 19, 20);
		panel.add(lblE);
		
		JLabel lblF = new JLabel("F");
		lblF.setForeground(Color.WHITE);
		lblF.setHorizontalAlignment(SwingConstants.CENTER);
		lblF.setFont(new Font("Impact", Font.PLAIN, 15));
		lblF.setBounds(825, 118, 19, 20);
		panel.add(lblF);
		
		JLabel lblG = new JLabel("G");
		lblG.setForeground(Color.WHITE);
		lblG.setHorizontalAlignment(SwingConstants.CENTER);
		lblG.setFont(new Font("Impact", Font.PLAIN, 15));
		lblG.setBounds(862, 118, 19, 20);
		panel.add(lblG);
		
		JLabel lblNa = new JLabel("NA");
		lblNa.setForeground(Color.WHITE);
		lblNa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNa.setFont(new Font("Impact", Font.PLAIN, 15));
		lblNa.setBounds(896, 118, 19, 20);
		panel.add(lblNa);
		
		JRadioButton rdbtnMarca = new JRadioButton("");
		rdbtnMarca.setBackground(new Color(113, 34, 250));
		buttonGroup.add(rdbtnMarca);
		rdbtnMarca.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnMarca.setBounds(39, 45, 21, 23);
		panel.add(rdbtnMarca);
		
		JRadioButton rdbtnClasif = new JRadioButton("");
		rdbtnClasif.setBackground(new Color(113, 34, 250));
		buttonGroup.add(rdbtnClasif);
		rdbtnClasif.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnClasif.setBounds(684, 45, 21, 23);
		panel.add(rdbtnClasif);
		
		JRadioButton rdbtnEmision = new JRadioButton("");
		rdbtnEmision.setBackground(new Color(113, 34, 250));
		buttonGroup.add(rdbtnEmision);
		rdbtnEmision.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnEmision.setBounds(467, 45, 21, 23);
		panel.add(rdbtnEmision);
		
		JRadioButton rdbtnConsumo = new JRadioButton("");
		rdbtnConsumo.setBackground(new Color(113, 34, 250));
		buttonGroup.add(rdbtnConsumo);
		rdbtnConsumo.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnConsumo.setBounds(253, 45, 21, 23);
		panel.add(rdbtnConsumo);
		
		
		JComboBox cmbMarca = new JComboBox();
		cmbMarca.setBackground(new Color(255, 172, 252));
		ArrayList<Marcas> arrMarcasCombo = bd.cargaComboBox();
		
		for (int i = 0; i < arrMarcasCombo.size(); i++) {
			cmbMarca.addItem(arrMarcasCombo.get(i).getMarca());
		}
		
		cmbMarca.setSelectedIndex(-1);
		
		/**
		 * Filtro para consultar por el JComboBox
		 */
		cmbMarca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String marca = cmbMarca.getSelectedItem().toString();
				System.out.println(marca);
				if(rdbtnMarca.isSelected()) {
					arrModelos = bd.ConsulMarca(marca);
					System.out.println(arrModelos.size());
					
					cargaTabla();
				}
			}
		});
		cmbMarca.setBounds(39, 80, 171, 22);
		panel.add(cmbMarca);
		
		JLabel lblConsumo = new JLabel("Consumo");
		lblConsumo.setForeground(Color.WHITE);
		lblConsumo.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsumo.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblConsumo.setBounds(280, 43, 98, 25);
		panel.add(lblConsumo);
		
		JLabel lblEmisiones = new JLabel("Emisiones");
		lblEmisiones.setForeground(Color.WHITE);
		lblEmisiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmisiones.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblEmisiones.setBounds(494, 43, 110, 24);
		panel.add(lblEmisiones);
		
		JSlider slClasificacion = new JSlider(1, 8, 8);
		slClasificacion.setBackground(new Color(113, 34, 250));
		/**
		 * Filtro para consultar por el JSlider
		 */
		slClasificacion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				String clasif=null;
				switch(slClasificacion.getValue()) {
				case 1:
					 clasif = "A";
					break;
				case 2:
					 clasif = "B";
					break;
				case 3:
					 clasif = "C";
					break;
				case 4:
					 clasif = "D";
					break;
				case 5:
					 clasif = "E";
					break;
				case 6:
					 clasif = "F";
					break;
				case 7:
					 clasif = "G";
					break;
				case 8:
					 clasif = "NA";
					break;
				}
				
				System.out.println(clasif);
				if(rdbtnClasif.isSelected()) {
					arrModelos = bd.ConsulClasif(clasif);
					System.out.println(arrModelos.size());
					
					cargaTabla();
				}
			}
		});
		slClasificacion.setMinorTickSpacing(8);
		slClasificacion.setMajorTickSpacing(1);
		slClasificacion.setBounds(651, 76, 261, 45);
		slClasificacion.setPaintTicks(true);
		panel.add(slClasificacion);
		
		JSpinner spinConsumo = new JSpinner();
		spinConsumo.setBackground(new Color(86, 10, 134));
		
		/**
		 * Filtro para consultar por el JSpinner
		 */
		spinConsumo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Double consumo =  (Double)spinConsumo.getValue();
				System.out.println(consumo);
				if(rdbtnConsumo.isSelected()) {
					arrModelos = bd.ConsulConsumo(consumo);
					System.out.println(arrModelos.size());

					cargaTabla();
					
				}
			}
		});
		spinConsumo.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(0.1)));
		spinConsumo.setBounds(253, 82, 171, 20);
		panel.add(spinConsumo);
		
		JSpinner spinEmisiones = new JSpinner();
		spinEmisiones.setBackground(new Color(86, 10, 134));
		/**
		 * Filtro para consultar por el JSpinner
		 */
		spinEmisiones.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Double emision =  (Double)spinEmisiones.getValue();
				System.out.println(emision);
				if(rdbtnEmision.isSelected()) {
					arrModelos = bd.ConsulEmision(emision);
					System.out.println(arrModelos.size());

					cargaTabla();
				}
			}
		});
		spinEmisiones.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(0.1)));
		spinEmisiones.setBounds(467, 82, 171, 20);
		panel.add(spinEmisiones);
	}
	
	/**
	 * Método para cargar el Jtable
	 */
	public void cargaTabla() {
		Vector vNombres = new Vector();
		vNombres.add("ID");
		vNombres.add("Marca");
		vNombres.add("Modelo");
		vNombres.add("Consumo");
		vNombres.add("Emisiones");
		vNombres.add("C_Energetica");	
		
		
		DefaultTableModel model = new DefaultTableModel(vNombres,arrModelos.size());
		
		jtable.setModel(model);
		
//      Hacer invisible columna id de modelo
		
        jtable.getColumnModel().getColumn(0).setMinWidth(0);
        jtable.getColumnModel().getColumn(0).setMaxWidth(0);
        jtable.getColumnModel().getColumn(0).setWidth(0);
		
//       Imagenes
        
        jtable.getColumnModel().getColumn(1).setCellRenderer(new imagenMarca());
        jtable.getColumnModel().getColumn(5).setCellRenderer(new imagenClasif());

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jtable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jtable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        jtable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        jtable.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        
//		Cambiar tamaño celdas
		
		int[] columnsWidth = {
                0,50,700,75,75,100
        };
		
		int j = 0;
        for (int width : columnsWidth) {
            TableColumn column = jtable.getColumnModel().getColumn(j++);
//            column.setMinWidth(width);
//            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
		
		for (int i = 0; i < arrModelos.size(); i++) {
			jtable.setValueAt(arrModelos.get(i).getId(), i, 0);
			jtable.setValueAt(arrModelos.get(i).getId_marca()+".gif", i, 1);
			jtable.setValueAt(arrModelos.get(i).getModelo(), i, 2);
			jtable.setValueAt(arrModelos.get(i).getConsumo(), i, 3);
			jtable.setValueAt(arrModelos.get(i).getEmisiones(), i, 4);
			jtable.setValueAt(arrModelos.get(i).getC_energetica()+".gif", i, 5);
		}
	}
	
	/**
	 * Se utiliza para poder visualizar imagenes en el jtable
	 * @author diego
	 *
	 */
	private class imagenMarca extends DefaultTableCellRenderer{

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		boolean hasFocus, int row, int column) {

		String nombreFoto = value.toString();
		ImageIcon imagenIcono = new ImageIcon(
		new ImageIcon("src/img/marcas/" + nombreFoto).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));

		return new JLabel(imagenIcono);
		}

	}
	
	/**
	 * Se utiliza para poder visualizar imagenes en el jtable
	 * @author diego
	 *
	 */
	private class imagenClasif extends DefaultTableCellRenderer{

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		boolean hasFocus, int row, int column) {

		String nombreFoto = value.toString();
		ImageIcon imagenIcono = new ImageIcon(
		new ImageIcon("src/img/clasif/" + nombreFoto).getImage().getScaledInstance(90, 13, Image.SCALE_DEFAULT));

		return new JLabel(imagenIcono);
		}

	}
		
}

