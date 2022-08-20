package vista;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * Ventana Bienvenida, aparece al abrir el programa
 * @author diego
 *
 */
public class Bienvenido extends JPanel {

	/**
	 * Create the panel.
	 */
	public Bienvenido() {
		setLayout(null);
		setBounds(0, 0, 950, 550);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBackground(Color.CYAN);
		lblLogo.setIcon(new ImageIcon(Bienvenido.class.getResource("/img/logotrabajo3rTrimestre4.png")));
		lblLogo.setBounds(355, 156, 241, 258);
		add(lblLogo);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(Bienvenido.class.getResource("/img/fondo.png")));
		lblFondo.setBounds(0, 0, 950, 550);
		add(lblFondo);

	}
}
