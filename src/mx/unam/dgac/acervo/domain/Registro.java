package mx.unam.dgac.acervo.domain;
/** bin que representa un registro del catalogo: Acervo Filmico en CU. */
public class Registro {
	/* La propiedad idParaNavegacion se utiliza para darle feedback al usuario cuando esta viendo
	 * detalles de un registro (presentaDetalles.jsp) y se calcula en DetallesController.java.
	 * NO se usa para otra cosa. No es un dato del registro en la base de datos.
	 */
	private String idParaNavegacion;
	
	public String getIdParaNavegacion() {
		return idParaNavegacion;
	}
	public void setIdParaNavegacion(String idParaNavegacion) {
		this.idParaNavegacion = idParaNavegacion;
	}

	/* NOTA IMPORTANTE, la propiedad motivoDeBaja se asigna al procecar una baja,
	 * ver FormaEliminacionDeUnRegistroController.java y formaEliminacionDeRegistro.jsp	 */
	private String motivoDeBaja;
	public void setMotivoDeBaja(String motivo) {
		this.motivoDeBaja = motivo;
	}
	public String getMotivoDeBaja() {
		return this.motivoDeBaja;
	}

	public Registro() {
	}
		
	/* campos del registro introducidos para el nuevo sistema y que no existian en ISIS */
	private long idReg;//identificador interno o "surrogate key" que no era parte original de DB en ISIS.
	private long fechaHoraDeInsercion;//interno no original de ISIS
	private String nuevaColocacionBoveda;//nuevo para desligar ubicacion de numero de inventario
	public long getFechaHoraDeInsercion() {
		return fechaHoraDeInsercion;
	}
	public void setFechaHoraDeInsercion(long fechaHoraDeInsercion) {
		this.fechaHoraDeInsercion = fechaHoraDeInsercion;
	}
	public String getNuevaColocacionBoveda() {
		return nuevaColocacionBoveda;
	}
	public void setNuevaColocacionBoveda(String colocacionBoveda) {
		this.nuevaColocacionBoveda = colocacionBoveda;
	}

	/* campo utilizado exclusivamente en las busquedas para especificar una cadena que se busca en
	 * todos los campos que no son explisitos en la forma de especificacion de la busqueda formaDeBusquedaGenerica.jsp */
	private String losOtrosCampos = "";
	public String getLosOtrosCampos() {
		return losOtrosCampos;
	}
	public void setLosOtrosCampos(String losOtrosCampos) {
		this.losOtrosCampos = losOtrosCampos;
	}

	/* campos transportados de BUDA desde ISIS */
	private String tituloOriginal="";
	private String tituloEnEspaniol="";
	private String realizador="";
	private String paisDeRealizacion="";
	private String anioDeProduccion="";//en la DB se llama "A"
	private String formato="";
	private String soporte="";
	private String emulsion="";
	private String color="";
	private String audio="";
	private String tama="";//sus valores son del tipo "1/1" o "1/2,2/2" etc.
	private String metraje="";
	private String da="";//un valor dice "el cassete 49 al poner play avanza, no se puede"
	private String estadoFisico="";
	private String norma="";
	private String cuadro="";
	private String duracion="";
	private String region="";
	private String pantalla="";
	private String fuente="";
	private String origen="";
	private String fechaDeGrabacion="";
	private String fechaDeRevision="";
	private String fechaDeIngreso="";
	private String fechaDeAcidez="";
	private String fechaDeResultado="";
	private String fechaDeCaptura="";
	private String fechaDeBaja="";
	private String origenDos="";//en la DB se llama "ORIGEN500" porque es campo repetido en ISIS
	private String colocacion="";
	private String distribucion="";
	private String programacion="";
	private String biblioteca="";
//inconsistencia con MAC	private String videoClub="";
	private String metrajeOriginal="";
	private String incomImg="";
	private String st="";
	private String otros="";
	private String reparaciones="";
	private String observaciones="";
	private String notasHistoricas="";
	private String prestamo="";
	private String institucionPrestamo="";
	private String conductoPrestamo="";
	private String fechaDeSalida="";
	private String fechaDeRetorno="";
	private String noCedulaRecibo="";
	private String nombreRevision="";
	private String nombreCaptura="";
	private String posibilidadDeExhibicion="";
	private String posibilidadDePrestamo="";
	private String restricciones="";
	private String extras="";
	private String statusAcervo="";
	private String statusDistribucion="";
	private String statusProgramacion="";
	private String statusBiblioteca="";
//	public String getParametroNoEsDelReg() {
//		return parametroNoEsDelReg;
//	}
//	public void setParametroNoEsDelReg(String parametroNoEsDelReg) {
//		this.parametroNoEsDelReg = parametroNoEsDelReg;
//	}

	public void setIdReg(long idReg) {
		this.idReg = idReg;
	}

	public void setTituloOriginal(String tituloOriginal) {
		this.tituloOriginal = tituloOriginal;
	}

	public void setTituloEnEspaniol(String tituloEnEspaniol) {
		this.tituloEnEspaniol = tituloEnEspaniol;
	}

	public void setRealizador(String realizador) {
		this.realizador = realizador;
	}

	public void setPaisDeRealizacion(String paisDeRealizacion) {
		this.paisDeRealizacion = paisDeRealizacion;
	}

	public void setAnioDeProduccion(String anioDeProduccion) {
		this.anioDeProduccion = anioDeProduccion;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public void setSoporte(String soporte) {
		this.soporte = soporte;
	}

	public void setEmulsion(String emulsion) {
		this.emulsion = emulsion;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public void setTama(String tama) {
		this.tama = tama;
	}

	public void setMetraje(String metraje) {
		this.metraje = metraje;
	}

	public void setDa(String da) {
		this.da = da;
	}

	public void setEstadoFisico(String estadoFisico) {
		this.estadoFisico = estadoFisico;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public void setCuadro(String cuadro) {
		this.cuadro = cuadro;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setPantalla(String pantalla) {
		this.pantalla = pantalla;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public void setFechaDeGrabacion(String fechaDeGrabacion) {
		this.fechaDeGrabacion = fechaDeGrabacion;
	}

	public void setFechaDeRevision(String fechaDeRevision) {
		this.fechaDeRevision = fechaDeRevision;
	}

	public void setFechaDeIngreso(String fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}

	public void setFechaDeAcidez(String fechaDeAcidez) {
		this.fechaDeAcidez = fechaDeAcidez;
	}

	public void setFechaDeResultado(String fechaDeResultado) {
		this.fechaDeResultado = fechaDeResultado;
	}

	public void setFechaDeCaptura(String fechaDeCaptura) {
		this.fechaDeCaptura = fechaDeCaptura;
	}

	public void setFechaDeBaja(String fechaDeBaja) {
		this.fechaDeBaja = fechaDeBaja;
	}

	public void setOrigenDos(String origenDos) {
		this.origenDos = origenDos;
	}

	public void setColocacion(String colocacion) {
		this.colocacion = colocacion;
	}

	public void setDistribucion(String distribucion) {
		this.distribucion = distribucion;
	}

	public void setProgramacion(String programacion) {
		this.programacion = programacion;
	}

	public void setBiblioteca(String biblioteca) {
		this.biblioteca = biblioteca;
	}

	public void setMetrajeOriginal(String metrajeOriginal) {
		this.metrajeOriginal = metrajeOriginal;
	}

	public void setIncomImg(String incomImg) {
		this.incomImg = incomImg;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public void setReparaciones(String reparaciones) {
		this.reparaciones = reparaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public void setNotasHistoricas(String notasHistoricas) {
		this.notasHistoricas = notasHistoricas;
	}

	public void setPrestamo(String prestamo) {
		this.prestamo = prestamo;
	}

	public void setInstitucionPrestamo(String institucionPrestamo) {
		this.institucionPrestamo = institucionPrestamo;
	}

	public void setConductoPrestamo(String conductoPrestamo) {
		this.conductoPrestamo = conductoPrestamo;
	}

	public void setFechaDeSalida(String fechaDeSalida) {
		this.fechaDeSalida = fechaDeSalida;
	}

	public void setFechaDeRetorno(String fechaDeRetorno) {
		this.fechaDeRetorno = fechaDeRetorno;
	}

	public void setNoCedulaRecibo(String noCedulaRecibo) {
		this.noCedulaRecibo = noCedulaRecibo;
	}

	public void setNombreRevision(String nombreRevision) {
		this.nombreRevision = nombreRevision;
	}

	public void setNombreCaptura(String nombreCaptura) {
		this.nombreCaptura = nombreCaptura;
	}

	public void setPosibilidadDeExhibicion(String posibilidadDeExhibicion) {
		this.posibilidadDeExhibicion = posibilidadDeExhibicion;
	}

	public void setPosibilidadDePrestamo(String posibilidadDePrestamo) {
		this.posibilidadDePrestamo = posibilidadDePrestamo;
	}

	public void setRestricciones(String restricciones) {
		this.restricciones = restricciones;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

	public void setStatusAcervo(String statusAcervo) {
		this.statusAcervo = statusAcervo;
	}

	public void setStatusDistribucion(String statusDistribucion) {
		this.statusDistribucion = statusDistribucion;
	}

	public void setStatusProgramacion(String statusProgramacion) {
		this.statusProgramacion = statusProgramacion;
	}

	public void setStatusBiblioteca(String statusBiblioteca) {
		this.statusBiblioteca = statusBiblioteca;
	}

	public long getIdReg() {
		return idReg;
	}

	public String getTituloOriginal() {
		return tituloOriginal;
	}

	public String getTituloEnEspaniol() {
		return tituloEnEspaniol;
	}

	public String getRealizador() {
		return realizador;
	}

	public String getPaisDeRealizacion() {
		return paisDeRealizacion;
	}

	public String getAnioDeProduccion() {
		return anioDeProduccion;
	}

	public String getFormato() {
		return formato;
	}

	public String getSoporte() {
		return soporte;
	}

	public String getEmulsion() {
		return emulsion;
	}

	public String getColor() {
		return color;
	}

	public String getAudio() {
		return audio;
	}

	public String getTama() {
		return tama;
	}

	public String getMetraje() {
		return metraje;
	}

	public String getDa() {
		return da;
	}

	public String getEstadoFisico() {
		return estadoFisico;
	}

	public String getNorma() {
		return norma;
	}

	public String getCuadro() {
		return cuadro;
	}

	public String getDuracion() {
		return duracion;
	}

	public String getRegion() {
		return region;
	}

	public String getPantalla() {
		return pantalla;
	}

	public String getFuente() {
		return fuente;
	}

	public String getOrigen() {
		return origen;
	}

	public String getFechaDeGrabacion() {
		return fechaDeGrabacion;
	}

	public String getFechaDeRevision() {
		return fechaDeRevision;
	}

	public String getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	public String getFechaDeAcidez() {
		return fechaDeAcidez;
	}

	public String getFechaDeResultado() {
		return fechaDeResultado;
	}

	public String getFechaDeCaptura() {
		return fechaDeCaptura;
	}

	public String getFechaDeBaja() {
		return fechaDeBaja;
	}

	public String getOrigenDos() {
		return origenDos;
	}

	public String getColocacion() {
		return colocacion;
	}

	public String getDistribucion() {
		return distribucion;
	}

	public String getProgramacion() {
		return programacion;
	}

	public String getBiblioteca() {
		return biblioteca;
	}

	public String getMetrajeOriginal() {
		return metrajeOriginal;
	}

	public String getIncomImg() {
		return incomImg;
	}

	public String getSt() {
		return st;
	}

	public String getOtros() {
		return otros;
	}

	public String getReparaciones() {
		return reparaciones;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public String getNotasHistoricas() {
		return notasHistoricas;
	}

	public String getPrestamo() {
		return prestamo;
	}

	public String getInstitucionPrestamo() {
		return institucionPrestamo;
	}

	public String getConductoPrestamo() {
		return conductoPrestamo;
	}

	public String getFechaDeSalida() {
		return fechaDeSalida;
	}

	public String getFechaDeRetorno() {
		return fechaDeRetorno;
	}

	public String getNoCedulaRecibo() {
		return noCedulaRecibo;
	}

	public String getNombreRevision() {
		return nombreRevision;
	}

	public String getNombreCaptura() {
		return nombreCaptura;
	}

	public String getPosibilidadDeExhibicion() {
		return posibilidadDeExhibicion;
	}

	public String getPosibilidadDePrestamo() {
		return posibilidadDePrestamo;
	}

	public String getRestricciones() {
		return restricciones;
	}

	public String getExtras() {
		return extras;
	}

	public String getStatusAcervo() {
		return statusAcervo;
	}

	public String getStatusDistribucion() {
		return statusDistribucion;
	}

	public String getStatusProgramacion() {
		return statusProgramacion;
	}

	public String getStatusBiblioteca() {
		return statusBiblioteca;
	}
	
	
	
}
