package mx.unam.dgac.acervo.sevicios;

import java.util.Collection;

import mx.unam.dgac.acervo.domain.Registro;
/** 
 * @author Gerardo Leon Lastra
 *
 *
 * NINGUN SERVICIO TIENE interceptor para hacerlo transaccional...m‡s "avanzado2.
 */

public interface BovedasServiceFace {
	public long leeTotalDeRegistrosAcervo();
	/* afecta su argumento r.setIdReg(valor) */
	public void altaDeNuevoRegistro(String strUsuario, Registro registro);
	public Registro leeRegistroPorId(long idReg);
	public boolean actualizaUnRegistro(String strUsuario, Registro modificado);
	public boolean bajaDeUnRegistro(String strUsuario, Registro registro, String motivo);
}
