package mx.unam.dgac.insertaCSVtoAcervo;

import java.util.StringTokenizer;

import mx.unam.dgac.acervo.domain.Registro;

import org.apache.log4j.Logger;

public class UtileriasDeFormateoBoveda_F {
	private static final Logger log = Logger.getLogger(UtileriasDeFormateoBoveda_F.class.getName());
	

	private static final int INDX_COLOCACION = 0;
	private static final int INDX_TITULO = 1;
	private static final int INDX_NUM_ROLLOS = 2;
	
	private mx.unam.dgac.acervo.sevicios.BovedasServiceFace servicios;
	
	public RegistroExcel parseaUnRenglonExcelDeBoveda_F(String strDeEntrada){
		String[] strDescompuestoPorComas = strDeEntrada.split(";");
		RegistroExcel reg = new RegistroExcel();
		
		try {
			for (int i = 0; i <= INDX_NUM_ROLLOS; i++) {
				switch(i) {
				case INDX_COLOCACION:
					reg.setStrColocacion(strDescompuestoPorComas[i]);
					break;
				case INDX_TITULO:
					reg.setStrTitulo(strDescompuestoPorComas[i]);
					break;
				case INDX_NUM_ROLLOS:
					reg.setStrNumeroDeRollos(strDescompuestoPorComas[i]);
					break;
				default:
						;
				}
			}
		} catch (Exception e) {
			log.error("registro de entrada>>>"+strDeEntrada+"<<<", e);
			reg = null;
		}
		return reg;
	}
	
	public mx.unam.dgac.acervo.domain.Registro procesaRangoBoveda_F(RegistroExcel registroEnLaEntrada) {
		return null;
	}
	
	public void setReferenciaToServicios(mx.unam.dgac.acervo.sevicios.BovedasServiceFace s) {
		this.servicios = s;
	}
	
	public void dumpContenido(String strReg){
		System.out.println("REGISTRO DE ENTRADA:"+strReg);
	}
	
	public void dumpContenido(RegistroExcel r){
		System.out.println("titulo:"+r.getStrTitulo()+":colocacion:"+r.getStrColocacion()+":numRollos:"+r.getStrNumeroDeRollos());
	}
	
	private int cuentaRegistradosBoveda_F = 0;
	private int cuentaLosDeEmbajadaCanadaDeEntrada_F = 0;
	private int cuentaLosDeEmbajadaCanadaDeSalida_F = 0;
	private int cuentaErroresDeDB = 0;
	
	public void reportaCifrasDeControl() {
		log.info("Registrados en base de datos para BOVEDA F:"+cuentaRegistradosBoveda_F);
		log.info("Errores para BOVEDA F al intentar insertar en la base de datos:"+cuentaErroresDeDB);
		log.info("Registos de entrada para BOVEDA F como donaciones de Embajada CANADA:"+cuentaLosDeEmbajadaCanadaDeEntrada_F);
		log.info("Registos de salida para BOVEDA F como donaciones de Embajada CANADA:"+cuentaLosDeEmbajadaCanadaDeSalida_F);
	}

	public void procesaUnRegBoveda_F(RegistroExcel registroEnLaEntrada) {
		boolean bPrimeroDeVarios = true;
		Registro reg = creaAndInicializaRegistro(registroEnLaEntrada);
		if(reg.getTituloOriginal().startsWith("EmbCan")){//cuando se exportaba de Excel de Microsoft ("\"EmbCan")){
			/* de este registro puedens salir dos o mas */
			cuentaLosDeEmbajadaCanadaDeEntrada_F++;
			StringTokenizer tk = new StringTokenizer(reg.getTituloOriginal(),"\"",false);
			System.out.println("---------------------------------"+reg.getTituloOriginal());
			while(tk.hasMoreElements()){
				String nt = tk.nextToken().trim();
				if(!(nt.equals("EmbCan") || nt.equals(""))) {
					/* se crean varios registros con titulos diferentes y misma colocaci�n a partir de un rengl�n de EXCEL */
					if(!bPrimeroDeVarios) {
						reg = creaAndInicializaRegistro(registroEnLaEntrada);
					}
					bPrimeroDeVarios = false;
					reg.setTituloOriginal(nt);
					reg.setOrigen("DONADO POR LA EMBAJADA DE CANADA");
					insertaEnBaseDeDatos(reg, registroEnLaEntrada);
					cuentaLosDeEmbajadaCanadaDeSalida_F++;
				}
			}
		} else {
			insertaEnBaseDeDatos(reg, registroEnLaEntrada);
		}
	}

	private void insertaEnBaseDeDatos(Registro reg, RegistroExcel registroEnLaEntrada){
		try {
			servicios.altaDeNuevoRegistro("CNTI, DGAC", reg);		
			cuentaRegistradosBoveda_F++;
		} catch (Exception e) {
			log.error("registro de entrada, COLOCACION:"+registroEnLaEntrada.getStrColocacion()+", TITULO:"+registroEnLaEntrada.getStrTitulo(), e);
			cuentaErroresDeDB++;
		}
		//dumpLoQueGuardasEnDB(reg);
	}
	
	public void dumpLoQueGuardasEnDB(Registro reg){
		System.out.println("COLOCACION :"+reg.getColocacion()+":TITULO :"+reg.getTituloOriginal()+": fecha de captura:"+ reg.getFechaDeCaptura()+": origen:"+reg.getOrigen()+": capturado por:"+reg.getNombreCaptura());
	}
	/* crea el bean del registro y le asigna titulo original, colocacion y observaciones con los datos de un renglon de la entrada 
	 * se hace as� porque todos los bloques en estos campos se procesan de igual manera. */
	private Registro creaAndInicializaRegistro(RegistroExcel registroEnLaEntrada) {
		Registro reg = new Registro();
		reg.setObservaciones("REVISAR insertado por programa el "+UtileriasDeFormateoBoveda_A.strMensajeEnCampoObservaciones);
		reg.setTituloOriginal(registroEnLaEntrada.getStrTitulo());
		reg.setColocacion("F-"+registroEnLaEntrada.getStrColocacion());
		reg.setFechaDeCaptura(UtileriasDeFormateoBoveda_A.strMensajeEnCampoObservaciones);
		// reg.setNombreCaptura() se hace en BovedasServiceImpl
		reg.setTama(registroEnLaEntrada.getStrNumeroDeRollos()+"R");
		return reg;
	}

}
