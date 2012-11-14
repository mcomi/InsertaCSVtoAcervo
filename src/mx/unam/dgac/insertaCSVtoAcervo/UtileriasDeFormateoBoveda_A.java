package mx.unam.dgac.insertaCSVtoAcervo;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import mx.unam.dgac.acervo.domain.Registro;
import mx.unam.dgac.acervo.sevicios.BovedasServiceImpl;
import mx.unam.dgac.insertaCSVtoAcervo.RegistroExcel;


public class UtileriasDeFormateoBoveda_A {
	private static final Logger log;
	static { 
		// ya inicializado el logger, asi se jala
		log = Logger.getLogger(UtileriasDeFormateoBoveda_A.class.getName());
		
		//inicializacion del mensaje que va en el campo acervo.observaciones
		SimpleDateFormat dtFormateador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		strMensajeEnCampoObservaciones = dtFormateador.format(new java.util.Date());
	}
	

	private static final int INDX_SECUENCIAL = 0;
	private static final int INDX_COLOCACION = 1;
	private static final int INDX_TITULO = 2;
	
	private static final int INF_BLOQUE_1 = -1;
	private static final int INF_BLOQUE_2 = 77;
	private static final int INF_BLOQUE_3 = 106;
	private static final int INF_BLOQUE_4 = 148;
	private static final int INF_BLOQUE_5 = 1302;
	private static final int INF_BLOQUE_6 = 1409;
	private final int[] fonterasPorRango ={ INF_BLOQUE_1, INF_BLOQUE_2, INF_BLOQUE_3, INF_BLOQUE_4, INF_BLOQUE_5, INF_BLOQUE_6, Integer.MAX_VALUE };
	
	public static String strMensajeEnCampoObservaciones; 

	private int cuentaB1, cuentaB2, cuentaB3, cuentaB4, cuentaB5, cuentaB6;
	private int cuentaErroresAlFundir, cuentaFundidos;

	public RegistroExcel parseaUnRenglonExcelDeBoveda_A(String strDeEntrada){
		String[] strDescompuestoPorComas = strDeEntrada.split(";");
		RegistroExcel reg = new RegistroExcel();
		for (int i = 0; i <= INDX_TITULO; i++) {
			switch(i) {
			case INDX_SECUENCIAL:
				reg.setiNumSecuencial(Integer.parseInt(strDescompuestoPorComas[i]));
				break;
			case INDX_COLOCACION:
				reg.setStrColocacion(strDescompuestoPorComas[i]);
				break;
			case INDX_TITULO:
				reg.setStrTitulo(strDescompuestoPorComas[i]);
				break;
			default:
					;
			}
		}
		return reg;
	}
	
	public void reportaCifrasDeControl() {
		log.info("Procesados para b�veda A en bloque 1:"+cuentaB1);		
		log.info("Procesados para b�veda A en bloque 2:"+cuentaB2);		
		log.info("Procesados para b�veda A en bloque 3:"+cuentaB3);		
		log.info("Procesados para b�veda A en bloque 4:"+cuentaB4+". De estos se fundieron "+cuentaFundidos);		
		log.info("Procesados para b�veda A en bloque 5:"+cuentaB5);		
		log.info("Procesados para b�veda A en bloque 6:"+cuentaB6);	
		int suma = cuentaB1+cuentaB2+cuentaB3+cuentaB4+cuentaB5+cuentaB6;
		log.info("Total de procesados sumando los bloques:"+suma);		
		log.info("Total de errores al intentar fundir dos registros en uno del 4o bloque:"+cuentaErroresAlFundir);		
	}
	
	public void dumpContenido(String strReg){
		System.out.println("REGISTRO DE ENTRADA:"+strReg);
	}
	
	
	public mx.unam.dgac.acervo.domain.Registro procesaRangoBoveda_A(RegistroExcel registroEnLaEntrada) {
		mx.unam.dgac.acervo.domain.Registro registro;
		registro = null;
		for (int indxInf = 0; indxInf < fonterasPorRango.length - 1; indxInf++) {
			if(fonterasPorRango[indxInf] < registroEnLaEntrada.getiNumSecuencial() && registroEnLaEntrada.getiNumSecuencial() <= fonterasPorRango[indxInf+1]) {
				switch(fonterasPorRango[indxInf]) {
				case INF_BLOQUE_1:
					cuentaB1++;
					registro = procesaBloques1y6(registroEnLaEntrada);
					break;
				case INF_BLOQUE_6:
					cuentaB6++;
					registro = procesaBloques1y6(registroEnLaEntrada);
					break;
				case INF_BLOQUE_2:
					cuentaB2++;
					registro = procesaBloque2y5(registroEnLaEntrada);
					break;
				case INF_BLOQUE_5:
					cuentaB5++;
					registro = procesaBloque2y5(registroEnLaEntrada);
					break;
				case INF_BLOQUE_3:
					cuentaB3++;
					registro = procesaBloque3(registroEnLaEntrada);
					break;
				case INF_BLOQUE_4:
					cuentaB4++;
					registro = procesaBloque4(registroEnLaEntrada);
					/*if(registro != null)
						dumpeaRegistro("FUNDIDOS", registro);
					else
						System.out.println("nulo en bloque 4");*/
					break;
				default:
					log.error("Registro con secuencial fuera de rango "+ registroEnLaEntrada.getiNumSecuencial());
				}
			}
		}		
		return registro;
	}

	private void dumpeaRegistro(String prefijo, Registro registro) {
		System.out.println(prefijo+":TITULO :"+registro.getTituloOriginal() + ":COLOCACION :"+registro.getColocacion() + ":Formato :"+registro.getFormato() + ":Observaciones :"+registro.getObservaciones());
	}
	
	private Registro procesaBloques1y6(RegistroExcel registroEnLaEntrada) {
		Registro reg = creaAndInicializaRegistro(registroEnLaEntrada);
		return reg;
	}

	private Registro procesaBloque2y5(RegistroExcel registroEnLaEntrada) {
		Registro reg = creaAndInicializaRegistro(registroEnLaEntrada);
		asignaDatosComunesBloques2al5(reg);
		reg.setEmulsion("P");
		reg.setTama("1 COMP");
		return reg;
	}
	
	private Registro procesaBloque3(RegistroExcel registroEnLaEntrada) {
		Registro reg = creaAndInicializaRegistro(registroEnLaEntrada);
		asignaDatosComunesBloques2al5(reg);
		reg.setEmulsion("NO");
		reg.setTama("1 IMAG 1 ST");
		return reg;
	}

	private Registro regAnterior = null;
	// iSecuenciaAnterior se utiliza para complementar los mensajes de error
	private int iSecuenciaAnterior;
	
	private Registro procesaBloque4(RegistroExcel registroEnLaEntrada) {
		Registro reg = creaAndInicializaRegistro(registroEnLaEntrada);
		asignaDatosComunesBloques2al5(reg);
		reg.setEmulsion("NO");
		reg.setTama("1 IMAG 1 ST");
		if(regAnterior != null) {
			try {
				comparaQuizasFundesRegConRegAnterior(reg, registroEnLaEntrada.getiNumSecuencial());
			} catch (Exception ex) {
				cuentaErroresAlFundir++;
				log.error("HAY NUEVO ERROR cuando el registro anterior con secuencia "+iSecuenciaAnterior+" se titula '"+regAnterior.getTituloOriginal()+"'");
				log.error(ex.getMessage());
				regAnterior = reg;
				iSecuenciaAnterior = registroEnLaEntrada.getiNumSecuencial();
				return null;
			}
			/* como pudimos fundir, declaramos regAnterior null dado que se asume que son pares de latas y nunca dos o mas o solo una lata para Cinescopio XX */
			regAnterior = null;
			iSecuenciaAnterior = -1;
		} else {
			regAnterior = reg;
			iSecuenciaAnterior = registroEnLaEntrada.getiNumSecuencial();
			return null;
		}
		return reg;
	}
	
	private void comparaQuizasFundesRegConRegAnterior(Registro reg, int iSecuencial) throws Exception {
		double numDeAnterior = FuncionVal.val(regAnterior.getTituloOriginal());
		double numDeEste = FuncionVal.val(reg.getTituloOriginal());
		/* primero critica si pueden fundirse */
		if(numDeAnterior != numDeEste || numDeAnterior == Double.NaN) {
			throw new Exception("imposible fundir registros. Para el registro con secuencia "+iSecuencial+ " titulado '"+reg.getTituloOriginal()+"' con el registro anterior porque no coincide el t��tulo.");
		}
		if(!(regAnterior.getTituloOriginal().trim().startsWith("Cinescopio") && reg.getTituloOriginal().trim().startsWith("Cinescopio"))) {
			throw new Exception("imposible fundir registros. Para el registro con secuencia "+iSecuencial+ " titulado '"+reg.getTituloOriginal()+"' con el registro anterior porque no pertenecen a la serie CINESCOPIO.");
		}
		/* funde los dos registros reg y regAnterior en uno solo ajustando la colocaci�n */
		String strColocacionDeReg = reg.getColocacion().trim();
		String nuevaColocacion = regAnterior.getColocacion().trim() + "-" + strColocacionDeReg.substring(strColocacionDeReg.length()-2);
		cuentaFundidos++;
		reg.setColocacion(nuevaColocacion);
	}

	/* los bloques 2 al 5 tienen los siguientes valores iguales */
	private void asignaDatosComunesBloques2al5(Registro reg) {
		reg.setFormato("35");
		reg.setCuadro("1:1.37");
		reg.setSoporte("A");
		reg.setColor("BN");
		reg.setAudio("N SPA");
		reg.setOrigen("Depósito de Manuel Barbachano Ponce");
	}

	/* crea el bean del registro y le asigna titulo original, colocacion y observaciones con los datos de un renglon de la entrada 
	 * se hace as� porque todos los bloques en estos campos se procesan de igual manera. */
	private Registro creaAndInicializaRegistro(RegistroExcel registroEnLaEntrada) {
		Registro reg = new Registro();
		reg.setObservaciones("REVISAR insertado por programa el "+UtileriasDeFormateoBoveda_A.strMensajeEnCampoObservaciones);
		reg.setTituloOriginal(registroEnLaEntrada.getStrTitulo());
		reg.setColocacion(registroEnLaEntrada.getStrColocacion());
		reg.setFechaDeCaptura(UtileriasDeFormateoBoveda_A.strMensajeEnCampoObservaciones);
		// reg.setNombreCaptura() se hace en BovedasServiceImpl
		return reg;
	}


}
