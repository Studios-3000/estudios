package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controlador.BasesDatos;
import modelo.Eficiencias;
import modelo.Marcas;
import modelo.Modelos;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ventana Modificar, donde se pueden cambiar datos de los modelos de coches del Jtable
 * @author diego
 *
 */
public class Modificar extends JPanel {
	private JTable jtable;
	private BasesDatos bd = new BasesDatos();
	private ArrayList<Modelos> arrModelos =  bd.cargaModelos();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtModeloModif;
	private Modelos modeloOld = new Modelos();
	private Modelos modeloNew = new Modelos();

	/**
	 * Create the panel.
	 */
	public Modificar() {
		setLayout(null);
		setBounds(0, 0, 950, 550);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(113, 34, 250));
		panel.setLayout(null);
		panel.setBounds(0, 0, 950, 550);
		add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 133, 925, 230);
		panel.add(scrollPane);
		
		jtable = new JTable(){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
		};
		jtable.setBackground(new Color(255, 172, 252));
		
		JComboBox cmbMarcaModif = new JComboBox();
		cmbMarcaModif.setBackground(new Color(255, 172, 252));
		cmbMarcaModif.setBounds(20, 399, 110, 22);
		panel.add(cmbMarcaModif);
		
		JSpinner spinConsumoModif = new JSpinner();
		spinConsumoModif.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(0.1)));
		spinConsumoModif.setBackground(new Color(86, 10, 134));
		spinConsumoModif.setBounds(172, 401, 98, 20);
		panel.add(spinConsumoModif);
		
		JSpinner spinEmisionesModif = new JSpinner();
		spinEmisionesModif.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(0.1)));
		spinEmisionesModif.setBackground(new Color(86, 10, 134));
		spinEmisionesModif.setBounds(331, 401, 110, 20);
		panel.add(spinEmisionesModif);
		
		JLabel lblA_1 = new JLabel("A");
		lblA_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblA_1.setForeground(Color.WHITE);
		lblA_1.setFont(new Font("Impact", Font.PLAIN, 15));
		lblA_1.setBounds(475, 444, 19, 20);
		panel.add(lblA_1);
		
		JLabel lblB_1 = new JLabel("B");
		lblB_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblB_1.setForeground(Color.WHITE);
		lblB_1.setFont(new Font("Impact", Font.PLAIN, 15));
		lblB_1.setBounds(511, 444, 19, 20);
		panel.add(lblB_1);
		
		JLabel lblC_1 = new JLabel("C");
		lblC_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblC_1.setForeground(Color.WHITE);
		lblC_1.setFont(new Font("Impact", Font.PLAIN, 15));
		lblC_1.setBounds(547, 444, 19, 20);
		panel.add(lblC_1);
		
		JLabel lblD_1 = new JLabel("D");
		lblD_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblD_1.setForeground(Color.WHITE);
		lblD_1.setFont(new Font("Impact", Font.PLAIN, 15));
		lblD_1.setBounds(582, 444, 19, 20);
		panel.add(lblD_1);
		
		JLabel lblE_1 = new JLabel("E");
		lblE_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblE_1.setForeground(Color.WHITE);
		lblE_1.setFont(new Font("Impact", Font.PLAIN, 15));
		lblE_1.setBounds(617, 444, 19, 20);
		panel.add(lblE_1);
		
		JLabel lblF_1 = new JLabel("F");
		lblF_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblF_1.setForeground(Color.WHITE);
		lblF_1.setFont(new Font("Impact", Font.PLAIN, 15));
		lblF_1.setBounds(652, 444, 19, 20);
		panel.add(lblF_1);
		
		JLabel lblG_1 = new JLabel("G");
		lblG_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblG_1.setForeground(Color.WHITE);
		lblG_1.setFont(new Font("Impact", Font.PLAIN, 15));
		lblG_1.setBounds(689, 444, 19, 20);
		panel.add(lblG_1);
		
		JLabel lblNa_1 = new JLabel("NA");
		lblNa_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNa_1.setForeground(Color.WHITE);
		lblNa_1.setFont(new Font("Impact", Font.PLAIN, 15));
		lblNa_1.setBounds(723, 444, 19, 20);
		panel.add(lblNa_1);
		
		JSlider slClasificacionModif = new JSlider(1, 8, 8);
		slClasificacionModif.setPaintTicks(true);
		slClasificacionModif.setMinorTickSpacing(8);
		slClasificacionModif.setMajorTickSpacing(1);
		slClasificacionModif.setBackground(new Color(113, 34, 250));
		slClasificacionModif.setBounds(478, 402, 261, 45);
		panel.add(slClasificacionModif);
		
		txtModeloModif = new JTextField();
		txtModeloModif.setBounds(205, 437, 185, 20);
		panel.add(txtModeloModif);
		txtModeloModif.setColumns(10);
		
		JLabel lblMarcaModif = new JLabel("Marca");
		lblMarcaModif.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarcaModif.setForeground(Color.WHITE);
		lblMarcaModif.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblMarcaModif.setBounds(10, 374, 75, 22);
		panel.add(lblMarcaModif);
		
		JLabel lblConsumoModif = new JLabel("Consumo");
		lblConsumoModif.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsumoModif.setForeground(Color.WHITE);
		lblConsumoModif.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblConsumoModif.setBounds(172, 375, 98, 22);
		panel.add(lblConsumoModif);
		
		JLabel lblEmisionesModif = new JLabel("Emisiones");
		lblEmisionesModif.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmisionesModif.setForeground(Color.WHITE);
		lblEmisionesModif.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblEmisionesModif.setBounds(331, 375, 110, 22);
		panel.add(lblEmisionesModif);
		
		JLabel lblModeloModif = new JLabel("Nombre Modelo");
		lblModeloModif.setHorizontalAlignment(SwingConstants.CENTER);
		lblModeloModif.setForeground(Color.WHITE);
		lblModeloModif.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblModeloModif.setBounds(20, 432, 175, 22);
		panel.add(lblModeloModif);
		
		JLabel lblClasificacionModif = new JLabel("Clasificacion");
		lblClasificacionModif.setHorizontalAlignment(SwingConstants.CENTER);
		lblClasificacionModif.setForeground(Color.WHITE);
		lblClasificacionModif.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblClasificacionModif.setBounds(528, 374, 143, 22);
		panel.add(lblClasificacionModif);
		
		JComboBox cmbMarca = new JComboBox();
		cmbMarca.setBackground(new Color(255, 172, 252));
		ArrayList<Marcas> arrMarcasCombo = bd.cargaComboBox();
		
		for (int i = 0; i < arrMarcasCombo.size(); i++) {
			cmbMarca.addItem(arrMarcasCombo.get(i).getMarca());
		}
		cmbMarca.setSelectedIndex(-1);
		
		ArrayList<Marcas> arrMarcasComboModif = bd.cargaComboBox();
		
		for (int i = 0; i < arrMarcasComboModif.size(); i++) {
			cmbMarcaModif.addItem(arrMarcasComboModif.get(i).getMarca());
		}
		cmbMarcaModif.setSelectedIndex(-1);
		
		jtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JTable table =(JTable) e.getSource();
		        Point point = e.getPoint();
		        int row = table.rowAtPoint(point);
		        if (e.getClickCount() == 1 && table.getSelectedColumn() != -1) {
		        	
					int id = (int)jtable.getModel().getValueAt(jtable.getSelectedRow(), 0);
					int id_marca = (int)jtable.getModel().getValueAt(jtable.getSelectedRow(), 1);
					id_marca--;
					if(id_marca>=2) {
						id_marca--;
					}
					
					String modeloModif = jtable.getModel().getValueAt(jtable.getSelectedRow(), 2).toString();
					Float consumoModif = (Float)jtable.getModel().getValueAt(jtable.getSelectedRow(), 3);
					Float emisionesModif = (Float)jtable.getModel().getValueAt(jtable.getSelectedRow(), 4);
					String clasifModif = jtable.getModel().getValueAt(jtable.getSelectedRow(), 5).toString();
					
					int clasifInt = 8;
					
					if (clasifModif.equals("A")){
						clasifInt = 1;
					}else if(clasifModif.equals("B")) {
						clasifInt = 2;
					}else if(clasifModif.equals("C")) {
						clasifInt = 3;
					}else if(clasifModif.equals("D")) {
						clasifInt = 4;
					}else if(clasifModif.equals("E")) {
						clasifInt = 5;
					}else if(clasifModif.equals("F")) {
						clasifInt = 6;
					}else if(clasifModif.equals("G")) {
						clasifInt = 7;
					}else if(clasifModif.equals("NA")) {
						clasifInt = 8;
					}
					
					System.out.println(id_marca);
					
//					Trasladar lo seleccionado del Jtable a los Jcomponentes
					
					cmbMarcaModif.setSelectedIndex(id_marca);
					txtModeloModif.setText(modeloModif);
					spinConsumoModif.setValue(consumoModif);
					spinEmisionesModif.setValue(emisionesModif);
					slClasificacionModif.setValue(clasifInt);
					
					modeloOld.setId(id);
					
					cargaTabla();
		        }
			}
		});
		scrollPane.setViewportView(jtable);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			/**
			 * Al presionar el botón Modificar se modificará lo seleccionado del Jtable a los nuevos datos introducidos
			 */
			public void actionPerformed(ActionEvent e) {
				if(txtModeloModif.getText().equals("") || cmbMarcaModif.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione algún modelo!");
				}else{
					int valor = JOptionPane.showConfirmDialog(null, "¿Desea modificar el modelo seleccionado?");

		        	int id_marca = (int)cmbMarcaModif.getSelectedIndex();
					id_marca++;
					if(id_marca>=2) {
						id_marca++;
					}
		        	
					String modeloModif = txtModeloModif.getText();
					Float consumoModif = (Float)spinConsumoModif.getValue();
					Float emisionesModif = (Float)spinEmisionesModif.getValue();
					int clasifInt = slClasificacionModif.getValue();
					
					String clasifModif =null;
					
					if (clasifInt == 1){
						clasifModif = "A";
					}else if(clasifInt == 2) {
						clasifModif = "B";
					}else if(clasifInt == 3) {
						clasifModif = "C";
					}else if(clasifInt == 4) {
						clasifModif = "D";
					}else if(clasifInt == 5) {
						clasifModif = "E";
					}else if(clasifInt == 6) {
						clasifModif = "F";
					}else if(clasifInt == 7) {
						clasifModif = "G";
					}else if(clasifInt == 8) {
						clasifModif = "NA";
					}
					
					System.out.println(id_marca);
					
//					Trasladar lo seleccionado del Jcomponentes al BBDD	
					
					modeloNew.setId_marca(id_marca);
					modeloNew.setModelo(modeloModif);
					modeloNew.setConsumo(consumoModif);
					modeloNew.setEmisiones(emisionesModif);
					modeloNew.setC_energetica(clasifModif);
					
					if (JOptionPane.OK_OPTION == valor) {
						bd.Modificar(modeloOld,modeloNew);

					}else if(JOptionPane.NO_OPTION == valor) {
						JOptionPane.showMessageDialog(null, "Operación cancelada");
					}
					
					txtModeloModif.setText("");
					cmbMarcaModif.setSelectedIndex(-1);
					spinConsumoModif.setValue(0);
					spinEmisionesModif.setValue(0);
					slClasificacionModif.setValue(8);
				}
			}
		});
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(new Color(86, 10, 134));
		btnModificar.setFont(new Font("Segoe UI Black", Font.PLAIN, 16));
		btnModificar.setBounds(794, 391, 121, 73);
		panel.add(btnModificar);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setForeground(Color.WHITE);
		lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarca.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblMarca.setBounds(47, 24, 75, 22);
		panel.add(lblMarca);
		
		JLabel lblClasificacion = new JLabel("Clasificacion");
		lblClasificacion.setForeground(Color.WHITE);
		lblClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblClasificacion.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblClasificacion.setBounds(711, 24, 143, 30);
		panel.add(lblClasificacion);
		
		JLabel lblA = new JLabel("A");
		lblA.setForeground(Color.WHITE);
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setFont(new Font("Impact", Font.PLAIN, 15));
		lblA.setBounds(648, 102, 19, 20);
		panel.add(lblA);
		
		JLabel lblB = new JLabel("B");
		lblB.setForeground(Color.WHITE);
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		lblB.setFont(new Font("Impact", Font.PLAIN, 15));
		lblB.setBounds(684, 102, 19, 20);
		panel.add(lblB);
		
		JLabel lblC = new JLabel("C");
		lblC.setForeground(Color.WHITE);
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setFont(new Font("Impact", Font.PLAIN, 15));
		lblC.setBounds(720, 102, 19, 20);
		panel.add(lblC);
		
		JLabel lblD = new JLabel("D");
		lblD.setForeground(Color.WHITE);
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		lblD.setFont(new Font("Impact", Font.PLAIN, 15));
		lblD.setBounds(755, 102, 19, 20);
		panel.add(lblD);
		
		JLabel lblE = new JLabel("E");
		lblE.setForeground(Color.WHITE);
		lblE.setHorizontalAlignment(SwingConstants.CENTER);
		lblE.setFont(new Font("Impact", Font.PLAIN, 15));
		lblE.setBounds(790, 102, 19, 20);
		panel.add(lblE);
		
		JLabel lblF = new JLabel("F");
		lblF.setForeground(Color.WHITE);
		lblF.setHorizontalAlignment(SwingConstants.CENTER);
		lblF.setFont(new Font("Impact", Font.PLAIN, 15));
		lblF.setBounds(825, 102, 19, 20);
		panel.add(lblF);
		
		JLabel lblG = new JLabel("G");
		lblG.setForeground(Color.WHITE);
		lblG.setHorizontalAlignment(SwingConstants.CENTER);
		lblG.setFont(new Font("Impact", Font.PLAIN, 15));
		lblG.setBounds(862, 102, 19, 20);
		panel.add(lblG);
		
		JLabel lblNa = new JLabel("NA");
		lblNa.setForeground(Color.WHITE);
		lblNa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNa.setFont(new Font("Impact", Font.PLAIN, 15));
		lblNa.setBounds(896, 102, 19, 20);
		panel.add(lblNa);
		
		JRadioButton rdbtnMarca = new JRadioButton("");
		rdbtnMarca.setBackground(new Color(113, 34, 250));
		buttonGroup.add(rdbtnMarca);
		rdbtnMarca.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnMarca.setBounds(20, 25, 21, 23);
		panel.add(rdbtnMarca);
		
		JRadioButton rdbtnClasif = new JRadioButton("");
		rdbtnClasif.setBackground(new Color(113, 34, 250));
		buttonGroup.add(rdbtnClasif);
		rdbtnClasif.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnClasif.setBounds(684, 30, 21, 23);
		panel.add(rdbtnClasif);
		
		JRadioButton rdbtnEmision = new JRadioButton("");
		rdbtnEmision.setBackground(new Color(113, 34, 250));
		buttonGroup.add(rdbtnEmision);
		rdbtnEmision.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnEmision.setBounds(448, 25, 21, 23);
		panel.add(rdbtnEmision);
		
		JRadioButton rdbtnConsumo = new JRadioButton("");
		rdbtnConsumo.setBackground(new Color(113, 34, 250));
		buttonGroup.add(rdbtnConsumo);
		rdbtnConsumo.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnConsumo.setBounds(234, 25, 21, 23);
		panel.add(rdbtnConsumo);
		
		cmbMarca.addItemListener(new ItemListener() {
			/**
			 * Filtro para consultar por el JComboBox
			 */
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
		cmbMarca.setBounds(20, 60, 171, 22);
		panel.add(cmbMarca);
		
		JLabel lblConsumo = new JLabel("Consumo");
		lblConsumo.setForeground(Color.WHITE);
		lblConsumo.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsumo.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblConsumo.setBounds(261, 23, 98, 25);
		panel.add(lblConsumo);
		
		JLabel lblEmisiones = new JLabel("Emisiones");
		lblEmisiones.setForeground(Color.WHITE);
		lblEmisiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmisiones.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblEmisiones.setBounds(475, 23, 110, 24);
		panel.add(lblEmisiones);
		
		JSlider slClasificacion = new JSlider(1, 8, 8);
		slClasificacion.setBackground(new Color(113, 34, 250));
		slClasificacion.addChangeListener(new ChangeListener() {
			/**
			 * Filtro para consultar por el JSlider
			 */
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
		slClasificacion.setBounds(651, 60, 261, 45);
		slClasificacion.setPaintTicks(true);
		panel.add(slClasificacion);
		
		JSpinner spinConsumo = new JSpinner();
		spinConsumo.setBackground(new Color(86, 10, 134));
		spinConsumo.addChangeListener(new ChangeListener() {
			/**
			 * Filtro para consultar por el JSpinner
			 */
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
		spinConsumo.setBounds(234, 62, 171, 20);
		panel.add(spinConsumo);
		
		JSpinner spinEmisiones = new JSpinner();
		spinEmisiones.setBackground(new Color(86, 10, 134));
		spinEmisiones.addChangeListener(new ChangeListener() {
			/**
			 * Filtro para consultar por el JSpinner
			 */
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
		spinEmisiones.setBounds(448, 62, 171, 20);
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
			jtable.setValueAt(arrModelos.get(i).getId_marca(), i, 1);
			jtable.setValueAt(arrModelos.get(i).getModelo(), i, 2);
			jtable.setValueAt(arrModelos.get(i).getConsumo(), i, 3);
			jtable.setValueAt(arrModelos.get(i).getEmisiones(), i, 4);
			jtable.setValueAt(arrModelos.get(i).getC_energetica(), i, 5);
		}
	}
}
