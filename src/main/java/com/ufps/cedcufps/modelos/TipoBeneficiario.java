package com.ufps.cedcufps.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
		
		
		
}
