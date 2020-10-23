package com.ufps.cedcufps.modelos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="asistentes")
@PrimaryKeyJoinColumn(name="id_participante")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Asistente extends Participante{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
