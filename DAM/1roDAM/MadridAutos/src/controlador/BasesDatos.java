package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Eficiencias;
import modelo.Marcas;
import modelo.Modelos;
/**
 * Clase para la realizacion de conexiones con la base de datos
 * @author diego
 *
 */
public class BasesDatos {
//	Checkear si la bbdd esta conectada
	
	/**
	 * Antes de iniciar el programa testea si esta conectado a la base de datos, si no es así, se sale del programa
	 */
	public void conexion() {                                                  
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/madridautos","madridautos","madridautos");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * from modelos");
			conexion.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al conectar con la Base de Datos");
			System.exit(0);
			
			e.printStackTrace();
		}
    }              
	
//	Cargar JComboBox
	
	/**
	 * Método para cargar el JComboBox
	 * @return arrMarcas
	 */
	public ArrayList<Marcas> cargaComboBox(){
		ArrayList<Marcas> arrMarcas = new ArrayList<>();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/madridautos","madridautos","madridautos");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * from marcas");
			
			while(registro.next()) {
				Marcas marca = new Marcas();
				marca.setIdMarca(registro.getInt("ID"));
				marca.setMarca(registro.getString("MARCA"));
				
				arrMarcas.add(marca);
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arrMarcas;
	}
	
//	Diferentes funciones
	
	/**
	 * Método para insertar en la base de datos un modelo
	 * @param modeloActual
	 */
	public void CrearModelos(Modelos modeloActual) {
		
		try {										
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/madridautos","madridautos","madridautos");
			
			Statement consulta = conexion.createStatement();
			int valor = consulta.executeUpdate("INSERT INTO modelos(ID_MARCA,MODELO,CONSUMO,EMISIONES,C_ENERGETICA) VALUES"
					+ "('"+ modeloActual.getId_marca() +"','" + modeloActual.getModelo() +"','" 
					+ modeloActual.getConsumo() +"','" + modeloActual.getEmisiones() 
					+ "','" + modeloActual.getC_energetica() +"')");
	
			JOptionPane.showMessageDialog(null, "Datos introducidos correctamente");
			conexion.close();
		} catch (SQLException e1){
			JOptionPane.showMessageDialog(null, "Error al crear el modelo");
			e1.printStackTrace();
		}	
	}
	
	/**
	 * Método para modificar de la base de datos un modelo
	 * @param modeloOld
	 * @param modeloNew
	 */
	public void Modificar(Modelos modeloOld, Modelos modeloNew) {
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/madridautos","madridautos","madridautos");
			Statement consulta = conexion.createStatement();
			
			int valor = consulta.executeUpdate(
					  "UPDATE modelos SET ID_MARCA="+ modeloNew.getId_marca() 
					  +",MODELO='"+ modeloNew.getModelo() 
					  +"',CONSUMO="+ modeloNew.getConsumo()
					  + ",EMISIONES="+ modeloNew.getEmisiones()
					  + ",C_ENERGETICA='"+modeloNew.getC_energetica()
					  
					  + "' WHERE ID="+ modeloOld.getId() +";");	
			
			JOptionPane.showMessageDialog(null, "Modelo modificado correctamente");
			conexion.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al modificar el modelo seleccionado");
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para borrar un modelo de la base de datos
	 * @param modeloactual
	 */
	public void Borrar(Modelos modeloactual) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/madridautos","madridautos","madridautos");
			Statement consulta = conexion.createStatement();
			
			int valor = consulta.executeUpdate("DELETE FROM modelos WHERE "
					+ "ID=" + modeloactual.getId() + ";");  
			
			JOptionPane.showMessageDialog(null, "Modelo borrado correctamente");
			conexion.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al borrar el modelo seleccionado");
			e.printStackTrace();
		}
		
		System.out.println( modeloactual.getId());
	}
	
	
//	Cargar los ArrayList para mostrar el Jtable
	
	/**
	 * Método para cargar el jtable
	 * @return arrModelos
	 */
	public ArrayList<Modelos> cargaModelos(){
		ArrayList<Modelos> arrModelos = new ArrayList<>();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/madridautos","madridautos","madridautos");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT modelos.*, marcas.*, \r\n"
					+ "eficiencias.*"
					+ "FROM marcas JOIN modelos ON  marcas.ID = modelos.ID_MARCA\r\n"
					+ "JOIN eficiencias ON eficiencias.C_ENERGETICA = modelos.C_ENERGETICA\r\n"
					+ "ORDER BY modelos.MODELO;");
			
			while(registro.next()) {
				Modelos modeloActual = new Modelos();
				
				modeloActual.setId(registro.getInt("modelos.ID"));
				modeloActual.setModelo(registro.getString("modelos.MODELO"));
				modeloActual.setConsumo(registro.getFloat("modelos.CONSUMO"));
				modeloActual.setEmisiones(registro.getFloat("modelos.EMISIONES"));
				modeloActual.setC_energetica(registro.getString("modelos.C_ENERGETICA"));
				modeloActual.setId_marca(registro.getInt("modelos.ID_MARCA"));
				
				arrModelos.add(modeloActual);
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arrModelos;
	}
	
	
//	Filtrar para consultar
	
	/**
	 * Usado para "filtrar" los diferentes Jcomponent de la jtable
	 * @param marca
	 * @return arrModelos
	 */
	public ArrayList<Modelos> ConsulMarca(String marca){
		ArrayList<Modelos> arrModelos = new ArrayList<>();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/madridautos","madridautos","madridautos");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM modelos "
					+ "WHERE ID_MARCA = (SELECT ID FROM marcas "
					+ "WHERE MARCA ='" + marca +"') "
					+ "ORDER BY ID_MARCA");

			
			while(registro.next()) {
				Modelos modeloActual = new Modelos();

				modeloActual.setId(registro.getInt("modelos.ID"));
				modeloActual.setModelo(registro.getString("modelos.MODELO"));
				modeloActual.setConsumo(registro.getFloat("modelos.CONSUMO"));
				modeloActual.setEmisiones(registro.getFloat("modelos.EMISIONES"));
				modeloActual.setC_energetica(registro.getString("modelos.C_ENERGETICA"));
				modeloActual.setId_marca(registro.getInt("modelos.ID_MARCA"));

				arrModelos.add(modeloActual);
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arrModelos;
	}
	
	/**
	 * Usado para "filtrar" los diferentes Jcomponent de la jtable
	 * @param consumo
	 * @return arrModelos
	 */
	public ArrayList<Modelos> ConsulConsumo(Double consumo){
		ArrayList<Modelos> arrModelos = new ArrayList<>();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/madridautos","madridautos","madridautos");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM modelos WHERE CONSUMO <= '"
					+ consumo + "' "
					+ "ORDER BY CONSUMO DESC");

			
			while(registro.next()) {
				Modelos modeloActual = new Modelos();

				modeloActual.setId(registro.getInt("ID"));
				modeloActual.setModelo(registro.getString("MODELO"));
				modeloActual.setConsumo(registro.getFloat("CONSUMO"));
				modeloActual.setEmisiones(registro.getFloat("EMISIONES"));
				modeloActual.setC_energetica(registro.getString("C_ENERGETICA"));
				modeloActual.setId_marca(registro.getInt("ID_MARCA"));

				arrModelos.add(modeloActual);
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arrModelos;
	}
	
	/**
	 * Usado para "filtrar" los diferentes Jcomponent de la jtable
	 * @param emision
	 * @return arrModelos
	 */
	public ArrayList<Modelos> ConsulEmision(Double emision){
		ArrayList<Modelos> arrModelos = new ArrayList<>();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/madridautos","madridautos","madridautos");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM modelos WHERE EMISIONES <= '"
					+ emision + "' "
					+ "ORDER BY EMISIONES DESC");

			
			while(registro.next()) {
				Modelos modeloActual = new Modelos();

				modeloActual.setId(registro.getInt("ID"));
				modeloActual.setModelo(registro.getString("MODELO"));
				modeloActual.setConsumo(registro.getFloat("CONSUMO"));
				modeloActual.setEmisiones(registro.getFloat("EMISIONES"));
				modeloActual.setC_energetica(registro.getString("C_ENERGETICA"));
				modeloActual.setId_marca(registro.getInt("ID_MARCA"));

				arrModelos.add(modeloActual);
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arrModelos;
	}
	
	/**
	 * Usado para "filtrar" los diferentes Jcomponent de la jtable
	 * @param clasif
	 * @return arrModelos
	 */
	public ArrayList<Modelos> ConsulClasif(String clasif){
		ArrayList<Modelos> arrModelos = new ArrayList<>();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/madridautos","madridautos","madridautos");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM modelos WHERE C_ENERGETICA = '"
					+ clasif + "' "
					+ "ORDER BY C_ENERGETICA");

			
			while(registro.next()) {
				Modelos modeloActual = new Modelos();

				modeloActual.setId(registro.getInt("ID"));
				modeloActual.setModelo(registro.getString("MODELO"));
				modeloActual.setConsumo(registro.getFloat("CONSUMO"));
				modeloActual.setEmisiones(registro.getFloat("EMISIONES"));
				modeloActual.setC_energetica(registro.getString("C_ENERGETICA"));
				modeloActual.setId_marca(registro.getInt("ID_MARCA"));

				arrModelos.add(modeloActual);
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arrModelos;
	}
	
}
