package mx.unam.dgac.insertaCSVtoAcervo;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import mx.unam.dgac.insertaCSVtoAcervo.UtileriasDeFormateoBoveda_A;
import mx.unam.dgac.insertaCSVtoAcervo.RegistroExcel;
import mx.unam.dgac.acervo.domain.Registro;
import mx.unam.dgac.acervo.sevicios.BovedasServiceFace;

import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Este c—digo groovy se ejecutar‡ como Java Application y no
 * como Groovy script o Groovy Console. Es mas,
 * tendr‡ que codificarse como java aunque sea .groovy.
 */
class AplicacionDeLecturaDeCsv {
//	public static final String ARCHIVO_CSV_DE_BOVEDA_A = '/Users/ger/pro/miworkspace/InsertaCSVtoAcervo/inputData/bovedaA.csv'
	public static final String ARCHIVO_CSV_DE_BOVEDA_A = '/Users/ger/pro/miworkspace/InsertaCSVtoAcervo/inputData/FALTANTES DE CAPTURA BOVEDA A 1a PTE.csv'
	
	public static final String ARCHIVO_CSV_DE_BOVEDA_F = '/Users/ger/pro/miworkspace/InsertaCSVtoAcervo/inputData/FALTANTES DE CAPTURA BOVEDA F.csv'


	public static void main(String[] args) {
		/*  */
		ClaseDelLoopDeProceso instanciaDeObjeto = new ClaseDelLoopDeProceso();
		try {
			instanciaDeObjeto.procesoParaBoveda_A();
			instanciaDeObjeto.procesoParaBoveda_F();
		} catch(Exception e) {
			println(e.getMessage());
		} finally {
			instanciaDeObjeto.reportaCifrasDeControl();
		}
		
	}

}

class ClaseDelLoopDeProceso{
	static final Logger log;
	static { // Esta inicialización de logger log4j se hace idéntico si fuese un archivo java.
		PropertyConfigurator.configure("/Users/ger/pro/miworkspace/InsertaCSVtoAcervo/src/mx/unam/dgac/propiedadesDelLog4j.properties");
		log = Logger.getLogger(ClaseDelLoopDeProceso.class.getName());
	}
	private UtileriasDeFormateoBoveda_A utileriaDeFormateo_A;
	private UtileriasDeFormateoBoveda_F utileriaDeFormateo_F;
	private mx.unam.dgac.acervo.sevicios.BovedasServiceFace servicios;
	private ApplicationContext ctxPadre = null;
	
	private int cuentaLeidosBoveda_A = 0;
	private int cuentaLeidosBoveda_F = 0;
	private int cuentaRegistradosBoveda_A = 0;
	private int cuentaErroresDeDBBoveda_A = 0;
	
	
	

	public ClaseDelLoopDeProceso() {
		utileriaDeFormateo_A = new UtileriasDeFormateoBoveda_A();
		utileriaDeFormateo_F = new UtileriasDeFormateoBoveda_F();
		log.info("Este archivo se genera automáticamente para reportar problemas y cifras del proceso de ");
		log.info("inserción de registros al acervo provenientes de hojas de Excel.");
		
		String[] astrDeclaraciones = new String[1];
		astrDeclaraciones[0] = "mx/unam/dgac/insertaCSVtoAcervo/ConfigDeSpring.xml";
		ctxPadre = new ClassPathXmlApplicationContext(astrDeclaraciones);
		servicios = (BovedasServiceFace)ctxPadre.getBean("serviciosBovedas");
		utileriaDeFormateo_F.setReferenciaToServicios(servicios);
		/* durante desarrollo, para checar mis configuraciones basicas de Spring, primero intento esto que es m‡s simple que la salida a la base de datos */
		System.out.println("Desde el constructor de ClaseDelLoopDeProceso, consulto el nœmero de registros de acervo: "+servicios.leeTotalDeRegistrosAcervo());
	}



	RegistroExcel registroEnLaEntrada;
	public void procesoParaBoveda_A() {
		/* aqui NO inicializo Spring porque tambien voy emplear los beans en procesoParaBovedaF.
		 * o lo que sea...
		 * y el c—digo del loop que lee no necesariamente tiene que estar
		 * aqu’. Su œnica restricci—n es que estŽ en un archivo .groovy */
		File file = new File(AplicacionDeLecturaDeCsv.ARCHIVO_CSV_DE_BOVEDA_A);
		file.each { stringDeEntrada ->
			// println "procesa linea en contexto de instancia de clase (objeto) : "+stringDeEntrada;
			try{
				registroEnLaEntrada = utileriaDeFormateo_A.parseaUnRenglonExcelDeBoveda_A(stringDeEntrada.toString());
				cuentaLeidosBoveda_A++;
				Registro regAcervo = utileriaDeFormateo_A.procesaRangoBoveda_A(registroEnLaEntrada);
				if(regAcervo != null) {
					//utileriaDeFormateo_F.dumpLoQueGuardasEnDB(regAcervo);
					insertaEnBaseDeDatos(regAcervo, registroEnLaEntrada);
				}
			} catch(Exception t) {
				log.error(t);
				utileriaDeFormateo_A.dumpContenido(stringDeEntrada);
			}
			
		}
	}
	

	public void procesoParaBoveda_F() {
		File file = new File(AplicacionDeLecturaDeCsv.ARCHIVO_CSV_DE_BOVEDA_F);
		file.each { stringDeEntrada ->
			cuentaLeidosBoveda_F++;
			try{
				registroEnLaEntrada = utileriaDeFormateo_F.parseaUnRenglonExcelDeBoveda_F(stringDeEntrada.toString());
				if(registroEnLaEntrada != null) {
					utileriaDeFormateo_F.procesaUnRegBoveda_F(registroEnLaEntrada);
				}
			} catch(Exception t) {
				t.printStackTrace();
				utileriaDeFormateo_F.dumpContenido(stringDeEntrada);
			}
			
		}
	}
	
	private void insertaEnBaseDeDatos(Registro reg, RegistroExcel registroEnLaEntrada){
		try {
			servicios.altaDeNuevoRegistro("CNTI, DGAC", reg);
			cuentaRegistradosBoveda_A++;
		} catch (Exception e) {
			log.error("registro de entrada, colocación:"+registroEnLaEntrada.getStrColocacion()+", título:"+registroEnLaEntrada.getStrTitulo(), e);
			cuentaErroresDeDBBoveda_A++;
		}
	}
	
	public void reportaCifrasDeControl() {
		log.info("Leidos de archivo Excel de bóveda A:"+cuentaLeidosBoveda_A);
		log.info("Registrados en base de datos para bóveda A:"+cuentaRegistradosBoveda_A);
		log.info("Errores para bóveda A al intentar insertar en la base de datos:"+cuentaErroresDeDBBoveda_A);
		utileriaDeFormateo_A.reportaCifrasDeControl();
		
		log.info("Leidos de archivo Excel de bóveda F:"+cuentaLeidosBoveda_F);
		utileriaDeFormateo_F.reportaCifrasDeControl();
	}
}
