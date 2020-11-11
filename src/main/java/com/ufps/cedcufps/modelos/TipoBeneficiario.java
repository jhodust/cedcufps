package com.ufps.cedcufps.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tipos_beneficiarios")
public class TipoBeneficiario implements Serializable {//1
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "tipo_beneficiario")
		private String tipoBeneficiario;
		
		@JsonIgnore
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="id_tipo_persona", foreignKey=@ForeignKey(name = "FK_beneficiary_type_people"))
		private TipoPersona tipoPersona;
		
		private String homologacion;
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTipoBeneficiario() {
			return tipoBeneficiario;
		}

		public void setTipoBeneficiario(String tipoBeneficiario) {
			this.tipoBeneficiario = tipoBeneficiario;
		}

		public TipoPersona getTipoPersona() {
			return tipoPersona;
		}

		public void setTipoPersona(TipoPersona tipoPersona) {
			this.tipoPersona = tipoPersona;
		}

		public String getHomologacion() {
			return homologacion;
		}

		public void setHomologacion(String homologacion) {
			this.homologacion = homologacion;
		}
		
		
		
}
