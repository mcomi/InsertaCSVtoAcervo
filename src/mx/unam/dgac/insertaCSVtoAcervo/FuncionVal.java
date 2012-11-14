package mx.unam.dgac.insertaCSVtoAcervo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author Gerardo Leon Lastra
 * El metodo val busca en la cadena de entrada un nœmero y lo entrega como un double.
 * Si no puede encontrar el patr—n de un nœmero, regresa Double.NaN - Not a Number.
 */
public class FuncionVal {
	private static String strQuizasSinParteEntera = "[+-]?\\d*\\.\\d+";
	private static String strQuizasSinParteFraccion = "[+-]?\\d+\\.\\d*";
	private static String strExponente = "[Ee][+-]?\\d+";
	private static String strEntero = "[+-]?\\d+";
	private static Pattern pEntero = Pattern.compile(strEntero);
	private static Pattern pFlotante = Pattern.compile(
			strQuizasSinParteEntera+"|"+
			strQuizasSinParteFraccion);
	private static Pattern pCientifica = Pattern.compile(
			strQuizasSinParteEntera+strExponente+"|"+
			strQuizasSinParteFraccion+strExponente);
	/**
	 * La entrada es un String, en este se busca el primer patron de cadenas formadas con
	 * los elementos {digitos,+,-,e,E} que pueda interpretarse como un numero y regresa
	 * este patron convertido a un numero en punto flotante de tipo 'double'.
	 * Se intentan tres patrones de busqueda en el siguiente orden:
	 * 1. primero en notacion cientifica,
	 * 2. despues como numero fraccionario y
	 * 3. por ultimo como entero.
	 * Note que el orden dado provoca que para la cadena "22-7.3" el numero que
	 * se entrega es '-7.3' y no '22'.
	 * 
	 * Cuando no se logra una tal interpretacion entrega Double.NaN , esto es 'Not a Number'.
	 * 
	 * Ademas es posible que regrese los valores Double.POSITIVE_INFINITY y Double.NEGATIVE_INFINITY.
	 * 
	 * Notas:
	 * 1. Exite el proyecto Eclipse de GLL llamado VALFunction para mejorar esta funcion. 
	 * 2. Para mayor eficiencia, debiera intentarse la funcion val() con "capturing groups" de 
	 *    las clases Matcher & Pattern. Pero no hay tiempo!
	 * @param str
	 * @return un double posiblemente NaN
	 */
	public static double val(String str) {
		double numero;
		String strNumero = extraeSubcadenaDeNumero(str);
		try {
			numero = Double.parseDouble(strNumero);
		} catch (NumberFormatException e) {
			return Double.NaN;
		}
		return numero;
	}
	/**
	 * La entrada es un String, en este se busca el primer patron de cadenas formadas con
	 * los elementos {digitos,+,-,e,E} que pueda interpretarse como un numero y regresa
	 * este patron o String habiendo probado que se puede interpretar como
	 * numero en punto flotante de tipo 'double'.
	 * 
	 * Cuando no se logra una tal interpretacion entrega la cadena " #".
	 * 
	 * Se intentan tres patrones de busqueda en el siguiente orden:
	 * 1. primero en notacion cientifica,
	 * 2. despues como numero fraccionario y
	 * 3. por ultimo como entero.
	 * Note que el orden dado provoca que para la cadena "22-7.3" la cadena que
	 * se entrega es '-7.3' y no '22'.
	 * @param str
	 * @return la primera subcadena que se puede interpretar como numero o "-" si no.
	 */
	public static String valComoString(String str) {
		String strNumero = extraeSubcadenaDeNumero(str);
		try {
			Double.parseDouble(strNumero);
		} catch (NumberFormatException e) {
			return " #";
		}
		return strNumero;
	}
	
	private static String extraeSubcadenaDeNumero(String str) {
		String strNumMatched = "";
		Matcher m = pCientifica.matcher(str);
		boolean hayMatch = m.find();
//		System.err.println("match cientifica: "+hayMatch);
		if(hayMatch) {
			strNumMatched = str.substring(m.start(),m.end());
		} else {
			m = pFlotante.matcher(str);
			hayMatch = m.find();
//			System.err.println("match fraccion: "+hayMatch);
			if(hayMatch){
				strNumMatched = str.substring(m.start(),m.end());
			} else {
				m = pEntero.matcher(str);
				hayMatch = m.find();
//				System.err.println("match entero: "+hayMatch);
				if(hayMatch){
					strNumMatched = str.substring(m.start(),m.end());				
				}
			}
		}
//		System.err.println("extraccion:"+strNumMatched);
		return strNumMatched;
	}

}
