package com.ufps.cedcufps.modelos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="asistentes")
@PrimaryKeyJoinColumn(name="id_participante")
public class Asistente extends Participante{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
