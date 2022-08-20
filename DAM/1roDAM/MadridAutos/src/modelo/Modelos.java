package modelo;

/**
 * Datos de Modelos
 * @author diego
 *
 */
public class Modelos {
	private int id;
	private int id_marca;
	private String modelo;
	private float consumo;
	private float emisiones;
	private String c_energetica;
	
	/**
	 * Constructor por parámetros
	 * @param id
	 * @param id_marca
	 * @param modelo
	 * @param consumo
	 * @param emisiones
	 * @param c_energetica
	 */
	public Modelos(int id, int id_marca, String modelo, float consumo, float emisiones, String c_energetica) {
		this.id = id;
		this.id_marca = id_marca;
		this.modelo = modelo;
		this.consumo = consumo;
		this.emisiones = emisiones;
		this.c_energetica = c_energetica;
	}
	
	/**
	 * Constructor pro defecto
	 */
	public Modelos() {
		id = 0;
		id_marca = 0;
		modelo = null;
		consumo = 0.0f;
		emisiones = 0.0f;
		c_energetica = null;
	}

	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return id_marca
	 */
	public int getId_marca() {
		return id_marca;
	}

	/**
	 * 
	 * @param id_marca
	 */
	public void setId_marca(int id_marca) {
		this.id_marca = id_marca;
	}

	/**
	 * 
	 * @return modelo
	 */
	public String getModelo() {
		return modelo;
	}
	
	/**
	 * 
	 * @param modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * 
	 * @return consumo
	 */
	public float getConsumo() {
		return consumo;
	}

	/**
	 * 
	 * @param consumo
	 */
	public void setConsumo(float consumo) {
		this.consumo = consumo;
	}

	/**
	 * 
	 * @return emisiones
	 */
	public float getEmisiones() {
		return emisiones;
	}

	/**
	 * 
	 * @param emisiones
	 */
	public void setEmisiones(float emisiones) {
		this.emisiones = emisiones;
	}

	/**
	 * 
	 * @return c_energetica
	 */
	public String getC_energetica() {
		return c_energetica;
	}

	/**
	 * 
	 * @param c_energetica
	 */
	public void setC_energetica(String c_energetica) {
		this.c_energetica = c_energetica;
	}
	
	
	
}
