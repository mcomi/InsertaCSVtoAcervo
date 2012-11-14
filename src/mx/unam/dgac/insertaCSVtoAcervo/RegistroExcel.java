package mx.unam.dgac.insertaCSVtoAcervo;

/** Bean utilizado para almacena datos de cada registro proveniente de hoja de MS-Excell ya interpretados. */

public class RegistroExcel {
	private int iNumSecuencial;
	private String strColocacion;
	private String strTitulo;
	private String strNumeroDeRollos;
	
	
	public int getiNumSecuencial() {
		return iNumSecuencial;
	}
	public void setiNumSecuencial(int iNumSecuencial) {
		this.iNumSecuencial = iNumSecuencial;
	}
	public String getStrColocacion() {
		return strColocacion;
	}
	public void setStrColocacion(String strColocacion) {
		this.strColocacion = strColocacion;
	}
	public String getStrTitulo() {
		return strTitulo;
	}
	public void setStrTitulo(String strTitulo) {
		this.strTitulo = strTitulo;
	}
	public String getStrNumeroDeRollos() {
		return strNumeroDeRollos;
	}
	public void setStrNumeroDeRollos(String strNumeroDeRollos) {
		this.strNumeroDeRollos = strNumeroDeRollos;
	}
	
}
