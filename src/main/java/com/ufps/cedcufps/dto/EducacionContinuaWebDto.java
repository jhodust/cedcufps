package com.ufps.cedcufps.dto;

import java.util.Date;
import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;


public class EducacionContinuaWebDto {

	private Long id;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaLimInscripcion;
	private String imagen;
	private String lugar;
	private String duracion;
	private String cantMaxParticipantes;
	private String infoAdicional;
	private String costoInscripcion;
	private Long idTipoEduContinua;
	private String tipoEduContinua;
	private List<JornadaAppDto> jornadas;
	private List<ParticipanteDto> participantes;
	private List<PonenteDto> ponentes;
	private Long idProgramaResp;
	private String programaResp;
	private Long idDocenteResp;
	private String nombreDocenteResp;
	private String codigoDocenteResp;
	private Long idClasificacion;
	private String clasificacion;
	private DiplomaDto diploma;
	private String estado;
	private int cantidadInscritos;
	private Long idFacultad;
	private String facultad;
	private String costoEducacionContinua;
	private List<TipoBeneficiarioDto> tipoBeneficiarios;
	private String porcentajeAsistencia;
	private String consecutivo;
	private boolean estadoOficialTipoEducacionContinua;
	
	public String getCostoEducacionContinua() {
		return costoEducacionContinua;
	}
	public void setCostoEducacionContinua(String costoEducacionContinua) {
		this.costoEducacionContinua = costoEducacionContinua;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Date getFechaLimInscripcion() {
		return fechaLimInscripcion;
	}
	public void setFechaLimInscripcion(Date fechaLimInscripcion) {
		this.fechaLimInscripcion = fechaLimInscripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	
	public String getCantMaxParticipantes() {
		return cantMaxParticipantes;
	}
	public void setCantMaxParticipantes(String cantMaxParticipantes) {
		this.cantMaxParticipantes = cantMaxParticipantes;
	}
	public String getInfoAdicional() {
		return infoAdicional;
	}
	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
	public String getCostoInscripcion() {
		return costoInscripcion;
	}
	public void setCostoInscripcion(String costoInscripcion) {
		this.costoInscripcion = costoInscripcion;
	}
	public Long getIdTipoEduContinua() {
		return idTipoEduContinua;
	}
	public void setIdTipoEduContinua(Long idTipoEduContinua) {
		this.idTipoEduContinua = idTipoEduContinua;
	}
	public String getTipoEduContinua() {
		return tipoEduContinua;
	}
	public void setTipoEduContinua(String tipoEduContinua) {
		this.tipoEduContinua = tipoEduContinua;
	}
	public List<JornadaAppDto> getJornadas() {
		return jornadas;
	}
	public void setJornadas(List<JornadaAppDto> jornadas) {
		this.jornadas = jornadas;
	}
	public List<ParticipanteDto> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(List<ParticipanteDto> participantes) {
		this.participantes = participantes;
	}
	public Long getIdProgramaResp() {
		return idProgramaResp;
	}
	public void setIdProgramaResp(Long idProgramaResp) {
		this.idProgramaResp = idProgramaResp;
	}
	public String getProgramaResp() {
		return programaResp;
	}
	public void setProgramaResp(String programaResp) {
		this.programaResp = programaResp;
	}
	public Long getIdDocenteResp() {
		return idDocenteResp;
	}
	public void setIdDocenteResp(Long idDocenteResp) {
		this.idDocenteResp = idDocenteResp;
	}
	public String getNombreDocenteResp() {
		return nombreDocenteResp;
	}
	public void setNombreDocenteResp(String nombreDocenteResp) {
		this.nombreDocenteResp = nombreDocenteResp;
	}
	public Long getIdClasificacion() {
		return idClasificacion;
	}
	public void setIdClasificacion(Long idClasificacion) {
		this.idClasificacion = idClasificacion;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getCantidadInscritos() {
		return cantidadInscritos;
	}
	public void setCantidadInscritos(int cantidadInscritos) {
		this.cantidadInscritos = cantidadInscritos;
	}
	public List<PonenteDto> getPonentes() {
		return ponentes;
	}
	public void setPonentes(List<PonenteDto> ponentes) {
		this.ponentes = ponentes;
	}
	public DiplomaDto getDiploma() {
		return diploma;
	}
	public void setDiploma(DiplomaDto diploma) {
		this.diploma = diploma;
	}
	public Long getIdFacultad() {
		return idFacultad;
	}
	public void setIdFacultad(Long idFacultad) {
		this.idFacultad = idFacultad;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	public List<TipoBeneficiarioDto> getTipoBeneficiarios() {
		return tipoBeneficiarios;
	}
	public void setTipoBeneficiarios(List<TipoBeneficiarioDto> tipoBeneficiarios) {
		this.tipoBeneficiarios = tipoBeneficiarios;
	}
	
	public void addTipoBeneficiarioDto(TipoBeneficiarioDto dto) {
		this.tipoBeneficiarios.add(dto);
	}
	public String getPorcentajeAsistencia() {
		return porcentajeAsistencia;
	}
	public void setPorcentajeAsistencia(String porcentajeAsistencia) {
		this.porcentajeAsistencia = porcentajeAsistencia;
	}
	public String getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}
	public boolean isEstadoOficialTipoEducacionContinua() {
		return estadoOficialTipoEducacionContinua;
	}
	public void setEstadoOficialTipoEducacionContinua(boolean estadoOficialTipoEducacionContinua) {
		this.estadoOficialTipoEducacionContinua = estadoOficialTipoEducacionContinua;
	}
	public String getCodigoDocenteResp() {
		return codigoDocenteResp;
	}
	public void setCodigoDocenteResp(String codigoDocenteResp) {
		this.codigoDocenteResp = codigoDocenteResp;
	}
	
	
}
