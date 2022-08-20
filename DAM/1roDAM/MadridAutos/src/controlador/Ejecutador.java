package controlador;

import vista.FramePrincipal;

public class Ejecutador {

	/**
	 * El main principal del programa
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//realizacion de test de conexion con base de datos
		BasesDatos bd = new BasesDatos();
		bd.conexion();
		
		//llamada al frame principal del programa para empezar a mostrar las ventanas
		FramePrincipal miFrame = new FramePrincipal();
		miFrame.cargaFramePrincipal(miFrame);
	}

}
