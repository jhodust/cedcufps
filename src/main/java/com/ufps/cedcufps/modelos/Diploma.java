package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.poi.hpsf.Array;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufps.cedcufps.mapper.HashMapConverter;
import com.ufps.cedcufps.utils.Auditable;

@Entity
@Table(name="diplomas")
public class Diploma extends Auditable<String> implements Serializable {//1
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(columnDefinition = "LONGTEXT", name="structure_diploma")
	@Convert(converter = HashMapConverter.class)
    private Map<String, Object> estructuraDiploma;
	


	
	
	public Diploma() {
		
	}
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	public Map<String, Object> getEstructuraDiploma() {
		return estructuraDiploma;
	}
	public void setEstructuraDiploma(Map<String, Object> estructuraDiploma) {
		this.estructuraDiploma = estructuraDiploma;
	}
	
	
	
}
