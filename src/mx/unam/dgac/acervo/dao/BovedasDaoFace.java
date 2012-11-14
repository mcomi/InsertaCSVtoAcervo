package mx.unam.dgac.acervo.dao;

import java.util.Collection;

import mx.unam.dgac.acervo.domain.Registro;

public interface BovedasDaoFace {
	public static final String TABLA_ACERVO = "Acervo";
	public static final String TABLA_LOG_MOVIMIENTOS = "log"+TABLA_ACERVO;
	public static final String TABLA_BOVEDAS = "bovedaPropone";
	public static final String TABLA_LOG_PROPUESTAS = "logBovedaPropone";
	
	public long leeTotalDeRegistrosAcervo();

	/* afecta su argumento r.setIdReg(valor) */
	public void altaDeNuevoRegistro(String userName, Registro registro);
	public Registro leeRegistroPorId(long idReg);
	public boolean actualizaUnRegistro(Registro modificado);
	public boolean bajaDeUnRegistro(String userName, Registro registro);
	
}
