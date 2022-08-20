package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.CardLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;

/**
 * Ventana para consultar los diferentes modelos de coche en el Jtable
 * @author diego
 *
 */
public class FramePrincipal extends JFrame {

	private JPanel pnlPrincipal;

	/**
	 * Launch the application.
	 */
	public static void cargaFramePrincipal(FramePrincipal frame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FramePrincipal() {
		setFont(new Font("Impact", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FramePrincipal.class.getResource("/img/logotrabajo3rTrimestre2.png")));
		setTitle("MadridAutos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 550);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 172, 252));
		menuBar.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setBackground(new Color(255, 172, 252));
		mnArchivo.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		menuBar.add(mnArchivo);
		
		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mntmConsultar.setBackground(new Color(255, 172, 252));
		mntmConsultar.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		/**
		 * Cambiar a la ventana Consultar
		 */
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consultar co = new Consultar();
				cambiar(co);
			}
		});
		mnArchivo.add(mntmConsultar);
		
		JMenuItem mntmCrear = new JMenuItem("Crear");
		mntmCrear.setBackground(new Color(255, 172, 252));
		mntmCrear.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		/**
		 * Cambiar a la ventana Crear
		 */
		mntmCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Crear cr = new Crear();
				cambiar(cr);
			}
		});
		mnArchivo.add(mntmCrear);
		
		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mntmModificar.setBackground(new Color(255, 172, 252));
		mntmModificar.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		/**
		 * Cambiar a la ventana Modificar
		 */
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificar m = new Modificar();
				cambiar(m);
			}
		});
		mnArchivo.add(mntmModificar);
		
		JMenuItem mntmBorrar = new JMenuItem("Borrar");
		mntmBorrar.setBackground(new Color(255, 172, 252));
		mntmBorrar.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		/**
		 * Cambiar a la ventana Borrar
		 */
		mntmBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borrar bo = new Borrar();
				cambiar(bo);
			}
		});
		mnArchivo.add(mntmBorrar);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(new CardLayout(0, 0));
		
		Bienvenido b = new Bienvenido();
		pnlPrincipal.add(b);
	}
	/**
	 * Método para cambiar entre ventanas
	 * @param p
	 */
	public void cambiar(JPanel p) {
		pnlPrincipal.removeAll();
		pnlPrincipal.add(p);
		pnlPrincipal.revalidate();
		pnlPrincipal.repaint();
	}

}
