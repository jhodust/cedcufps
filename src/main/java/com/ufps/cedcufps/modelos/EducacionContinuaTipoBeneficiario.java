package com.ufps.cedcufps.modelos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "educacion_continua_tipo_beneficiario")
public class EducacionContinuaTipoBeneficiario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_educacion_continua", foreignKey=@ForeignKey(name = "FK_beneficiario_educontinua"))
	private EducacionContinua educacionContinua;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_beneficiario", foreignKey=@ForeignKey(name = "FK_tipo_beneficiario_educontinua"))
	private TipoBeneficiario tipoBeneficiario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EducacionContinua getEducacionContinua() {
		return educacionContinua;
	}

	public void setEducacionContinua(EducacionContinua educacionContinua) {
		this.educacionContinua = educacionContinua;
	}

	public TipoBeneficiario getTipoBeneficiario() {
		return tipoBeneficiario;
	}

	public void setTipoBeneficiario(TipoBeneficiario tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}

	
}
