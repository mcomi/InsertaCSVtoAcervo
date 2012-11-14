package mx.unam.dgac.acervo.sevicios;

import java.util.Collection;
import java.util.StringTokenizer;

import org.apache.log4j.*;

import mx.unam.dgac.acervo.dao.BovedasDaoFace;
import mx.unam.dgac.acervo.domain.Registro;
/* Ningun servicio es transaccional.
 */
public class BovedasServiceImpl implements BovedasServiceFace {
	private static final Logger log = Logger.getLogger(BovedasServiceImpl.class.getName());
//	ArrayList<Registro> testList = new ArrayList<Registro>();
	public BovedasServiceImpl() {
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	public long leeTotalDeRegistrosAcervo() {
		try {
			return bovedasDaoImpl.leeTotalDeRegistrosAcervo();
		} catch (RuntimeException e) {
			log.error(e);
			throw e;
		}		
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	public void altaDeNuevoRegistro(String userName, Registro registro) {
		try {
			registro.setFechaHoraDeInsercion(System.currentTimeMillis());
			registro.setNombreCaptura("Coordinación de Nuevas Tecnologías");
			registro.setNuevaColocacionBoveda("");
			/* bovedasDaoImpl.altaDeNuevoRegistro afecta su argumento registro.setIdReg(valor) */
			bovedasDaoImpl.altaDeNuevoRegistro(userName, registro);
		} catch (RuntimeException e) {
			log.error(e);
			throw e;
		}
//		log.error("regresando de serviceImpl altaDeNuevoRegistro");
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	public Registro leeRegistroPorId(long idReg){
		try {
			return bovedasDaoImpl.leeRegistroPorId(idReg);
		} catch (RuntimeException e) {
			log.error(e);
			throw e;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean actualizaUnRegistro(String usuario, Registro modificado) {
		try {
			return bovedasDaoImpl.actualizaUnRegistro(modificado);
		} catch (RuntimeException e) {
			log.error(e);
			throw e;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	/** La baja de un registro implica eliminar cualquier referencia al idReg desde la tabla "bovedaPropone".
	 *  y eliminar el registro de la tabla "Acervo".
	 *    
	 * NOTESE QUE LA operacion o nombre del metodo para el log de movimientos (2o argumento de logueaTransaccion)
	 * en este caso no es "bajaDeUnRegistro" sino "b:"+motivo en donde el sufijo 'motivo' distingue la razon de la baja.
	 *    */
	public boolean bajaDeUnRegistro(String usuario, Registro registro, String motivo) {
		try {
			return bovedasDaoImpl.bajaDeUnRegistro(usuario, registro);
		} catch (RuntimeException e) {
			log.error(e);
			throw e;
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////trabajo sobre la tabla bovedaAcervo /////////////////////////////////
//////////////////////////trabajo sobre la tabla bovedaAcervo /////////////////////////////////
//////////////////////////trabajo sobre la tabla bovedaAcervo /////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////// propiedades Spring beans /////////////////////////////
	private BovedasDaoFace bovedasDaoImpl;
	public void setAcervoDao(BovedasDaoFace acervoDaoImpl) {
		this.bovedasDaoImpl = acervoDaoImpl;
	}
}
