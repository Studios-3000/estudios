package modelo;

/**
 * Datos de Marcas
 * @author diego
 *
 */
public class Marcas{
	private int idMarca;
	private String  marca;
	
	/**
	 * Constructor por parámetros
	 * @param idMarca
	 * @param marca
	 */
	public Marcas(int idMarca, String marca) {
		this.idMarca = idMarca;
		this.marca = marca;
	}
	
	/**
	 * Constructor pro defecto
	 */
	public Marcas() {
		idMarca = 0;
		marca = null;
	}
	
	/**
	 * 
	 * @return idMarca
	 */
	public int getIdMarca() {
		return idMarca;
	}

	/**
	 * 
	 * @param idMarca
	 */
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	/**
	 * 
	 * @return marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * 
	 * @param marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	
	
}
