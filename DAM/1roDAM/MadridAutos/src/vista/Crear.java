package vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import controlador.BasesDatos;
import modelo.Eficiencias;
import modelo.Marcas;
import modelo.Modelos;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * Ventana para Crear modelos de coches e insertarlos en la base de datos
 * @author diego
 *
 */
public class Crear extends JPanel {
	private JComboBox cmbMarca;
	private BasesDatos bd = new BasesDatos();
	private JTextField txtModelo;

	/**
	 * Create the panel.
	 */
	public Crear() {
		
		setBounds(0, 0, 950, 550);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(113, 34, 250));
		panel.setBounds(0, 0, 950, 550);
		add(panel);
		panel.setLayout(null);
		
		ArrayList<Marcas> arrMarcas = bd.cargaComboBox();
		
		cmbMarca = new JComboBox();
		cmbMarca.setBackground(new Color(255, 172, 252));
		cmbMarca.setBounds(379, 163, 200, 22);
		
		for (int i = 0; i < arrMarcas.size(); i++) {
			cmbMarca.addItem(arrMarcas.get(i).getMarca());
		}
		
		cmbMarca.setSelectedIndex(-1);
		
		panel.add(cmbMarca);
		txtModelo = new JTextField();
		txtModelo.setBackground(new Color(255, 172, 252));
		txtModelo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(txtModelo.getText().equals("Nombre del Modelo")) {
					txtModelo.setText("");
				}
			}
		});
		
		txtModelo.setText("Nombre del Modelo");
		txtModelo.setBounds(379, 95, 200, 20);
		panel.add(txtModelo);
		txtModelo.setColumns(10);
		
		JSpinner spinConsumo = new JSpinner();
		spinConsumo.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(0.1)));
		spinConsumo.setBackground(new Color(86, 10, 134));
		spinConsumo.setBounds(379, 242, 261, 20);
		panel.add(spinConsumo);
		
		JSpinner spinEmisiones = new JSpinner();
		spinEmisiones.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(0.1)));
		spinEmisiones.setBackground(new Color(86, 10, 134));
		spinEmisiones.setBounds(379, 326, 261, 20);
		panel.add(spinEmisiones);
		
		JSlider slClasificacion = new JSlider(1,8,8);
		slClasificacion.setBackground(new Color(113, 34, 250));
		slClasificacion.setBounds(379, 385, 200, 45);
		slClasificacion.setMajorTickSpacing(1);
		slClasificacion.setMinorTickSpacing(8);
		slClasificacion.setPaintTicks(true);
		panel.add(slClasificacion);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setBackground(new Color(86, 10, 134));
		btnCrear.setFont(new Font("Segoe UI Black", Font.PLAIN, 35));
		/**
		 * Boton para insertar los datos del modelo cread oa la base de datos
		 */
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtModelo.getText().equals("") || cmbMarca.getSelectedIndex() == -1
						|| txtModelo.getText().equals("Nombre del Modelo")) {
					JOptionPane.showMessageDialog(null, "Introduzca todos los datos!");
				}else {
					Modelos modeloActual = new Modelos();
					
					String modelo = txtModelo.getText();
					int marca = cmbMarca.getSelectedIndex();
					marca++;
					if(marca>=2) {
						marca++;
					}
					Float consumo =  (Float)spinConsumo.getValue();
					Float emisiones = (Float)spinEmisiones.getValue();
					String clasif= null;
					
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
					
					modeloActual.setModelo(modelo);
					modeloActual.setId_marca(marca);
					modeloActual.setConsumo(consumo);
					modeloActual.setEmisiones(emisiones);
					modeloActual.setC_energetica(clasif);
					
					System.out.println(modelo);
					System.out.println(marca);
					System.out.println(consumo);
					System.out.println(emisiones);
					System.out.println(clasif);
					
					bd.CrearModelos(modeloActual);
					
					txtModelo.setText("");
					cmbMarca.setSelectedIndex(-1);
					spinConsumo.setValue(0);
					spinEmisiones.setValue(0);
					slClasificacion.setValue(8);
				}
			}
		});
		btnCrear.setBounds(614, 385, 181, 72);
		panel.add(btnCrear);
		
		JLabel lbltitulo = new JLabel("Agregar Modelo");
		lbltitulo.setForeground(Color.WHITE);
		lbltitulo.setFont(new Font("Impact", Font.PLAIN, 35));
		lbltitulo.setBounds(348, 0, 233, 68);
		panel.add(lbltitulo);
		
		JLabel lblModelo = new JLabel("Nombre del Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblModelo.setBounds(159, 79, 200, 54);
		panel.add(lblModelo);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMarca.setForeground(Color.WHITE);
		lblMarca.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblMarca.setBounds(159, 148, 200, 54);
		panel.add(lblMarca);
		
		JLabel lblConsumo = new JLabel("Consumo");
		lblConsumo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConsumo.setForeground(Color.WHITE);
		lblConsumo.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblConsumo.setBounds(159, 221, 200, 54);
		panel.add(lblConsumo);
		
		JLabel lblEmisiones = new JLabel("Emisiones");
		lblEmisiones.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmisiones.setForeground(Color.WHITE);
		lblEmisiones.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblEmisiones.setBounds(159, 305, 200, 54);
		panel.add(lblEmisiones);
		
		JLabel lblClasificacion = new JLabel("Clasificacion");
		lblClasificacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClasificacion.setForeground(Color.WHITE);
		lblClasificacion.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblClasificacion.setBounds(159, 385, 200, 54);
		panel.add(lblClasificacion);
		
		JLabel lblA = new JLabel("A");
		lblA.setForeground(Color.WHITE);
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setFont(new Font("Impact", Font.PLAIN, 15));
		lblA.setBounds(377, 426, 19, 20);
		panel.add(lblA);
		
		JLabel lblB = new JLabel("B");
		lblB.setForeground(Color.WHITE);
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		lblB.setFont(new Font("Impact", Font.PLAIN, 15));
		lblB.setBounds(404, 426, 19, 20);
		panel.add(lblB);
		
		JLabel lblC = new JLabel("C");
		lblC.setForeground(Color.WHITE);
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setFont(new Font("Impact", Font.PLAIN, 15));
		lblC.setBounds(430, 426, 19, 20);
		panel.add(lblC);
		
		JLabel lblD = new JLabel("D");
		lblD.setForeground(Color.WHITE);
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		lblD.setFont(new Font("Impact", Font.PLAIN, 15));
		lblD.setBounds(457, 426, 19, 20);
		panel.add(lblD);
		
		JLabel lblE = new JLabel("E");
		lblE.setForeground(Color.WHITE);
		lblE.setHorizontalAlignment(SwingConstants.CENTER);
		lblE.setFont(new Font("Impact", Font.PLAIN, 15));
		lblE.setBounds(483, 426, 19, 20);
		panel.add(lblE);
		
		JLabel lblF = new JLabel("F");
		lblF.setForeground(Color.WHITE);
		lblF.setHorizontalAlignment(SwingConstants.CENTER);
		lblF.setFont(new Font("Impact", Font.PLAIN, 15));
		lblF.setBounds(510, 426, 19, 20);
		panel.add(lblF);
		
		JLabel lblG = new JLabel("G");
		lblG.setForeground(Color.WHITE);
		lblG.setHorizontalAlignment(SwingConstants.CENTER);
		lblG.setFont(new Font("Impact", Font.PLAIN, 15));
		lblG.setBounds(536, 426, 19, 20);
		panel.add(lblG);
		
		JLabel lblNa = new JLabel("NA");
		lblNa.setForeground(Color.WHITE);
		lblNa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNa.setFont(new Font("Impact", Font.PLAIN, 15));
		lblNa.setBounds(560, 426, 19, 20);
		panel.add(lblNa);
		
		JLabel lblIZ = new JLabel("");
		lblIZ.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIZ.setIcon(new ImageIcon(Crear.class.getResource("/img/fondo.png")));
		lblIZ.setBounds(0, 0, 145, 550);
		panel.add(lblIZ);
		
		JLabel lblDER = new JLabel("");
		lblDER.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDER.setIcon(new ImageIcon(Crear.class.getResource("/img/fondo.png")));
		lblDER.setBounds(805, 0, 145, 550);
		panel.add(lblDER);
		
	}
}
