package modelo;

/**
 * Datos de Eficiencias
 * @author diego
 *
 */
public class Eficiencias{
	private String c_energeticaModelos;
	private String descripcion;
	private String icono;
	
	/**
	 * Constructor por parámetros
	 * @param c_energeticaModelos
	 * @param descripcion
	 * @param icono
	 */
	public Eficiencias(String c_energeticaModelos, String descripcion, String icono) {
		this.c_energeticaModelos = c_energeticaModelos;
		this.descripcion = descripcion;
		this.icono = icono;
	}

	/**
	 * Constructor pro defecto
	 */
	public Eficiencias() {
		c_energeticaModelos = null;
		descripcion = null;
		icono = null;
	}
	
	/**
	 * 
	 * @return c_energeticaModelos
	 */
	public String getC_energeticaModelos() {
		return c_energeticaModelos;
	}

	/**
	 * 
	 * @param c_energeticaModelos
	 */
	public void setC_energeticaModelos(String c_energeticaModelos) {
		this.c_energeticaModelos = c_energeticaModelos;
	}

	/**
	 * 
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * @return icono
	 */
	public String getIcono() {
		return icono;
	}

	/**
	 * 
	 * @param icono
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}
	
	

}
