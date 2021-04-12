package com.ufps.cedcufps.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IDiplomaCustomDao;

@Repository
public class DiplomaCustomDaoImpl implements IDiplomaCustomDao {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	@Modifying
	@Override
	public Long createDiploma(String imagen) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder().append("insert into diplomas (imagen_plantilla)")
				.append(" VALUES (?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,imagen);
				
				return ps;
			}

		}, keyHolder);
		
		return Long.parseLong(String.valueOf(keyHolder.getKey()));
	}

	
	@Transactional
	@Modifying
	@Override
	public void saveImagenDiploma(Long idDiploma,  String rutaImagen, int x, int y) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		 em.createNativeQuery("insert into imagenes_diploma(id_diploma,x,y,ruta) values "
				+ "(?1,?2,?3, ?4)")
		.setParameter(1, idDiploma)
		.setParameter(2, x)
		.setParameter(3, y)
		.setParameter(4, rutaImagen)
		.executeUpdate();
	}

	@Transactional
	@Modifying
	@Override
	public void saveTexto(Long idDiploma, String texto, String categoria, int x, int y) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		 em.createNativeQuery("insert into textos_diploma(id_diploma,x,y,categoria, texto) values "
				+ "(?1,?2,?3, ?4, ?5)")
		.setParameter(1, idDiploma)
		.setParameter(2, x)
		.setParameter(3, y)
		.setParameter(4, categoria)
		.setParameter(5, texto)
		.executeUpdate();
	}

	@Transactional
	@Modifying
	@Override
	public void saveFirma(Long idDiploma, String cargo, int xCargo, int yCargo, String nombre,
			int xNombre, int yNombre, String rutaImagen, int x, int y) {
		// TODO Auto-generated method stub
		
		
		
		StringBuilder query = new StringBuilder();
		 em.createNativeQuery("insert into firmas_diploma(id_diploma,x,y,cargo,x_cargo,y_cargo, nombre, x_nombre, y_nombre,imagen_firma_digital) values "
				+ "(?1,?2,?3, ?4, ?5,?6,?7,?8,?9,?10)")
		.setParameter(1, idDiploma)
		.setParameter(2, x)
		.setParameter(3, y)
		.setParameter(4, cargo)
		.setParameter(5, xCargo)
		.setParameter(6, yCargo)
		.setParameter(7, nombre)
		.setParameter(8, xNombre)
		.setParameter(9, yNombre)
		.setParameter(10, rutaImagen)
		.executeUpdate();
	}


	@Transactional
	@Modifying
	@Override
	public void updateDiplomaEduContinua(Long idDiploma, Long idEduContinua) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		 em.createNativeQuery(" update educacion_continua set id_diploma = ?1 where id = ?2")
		.setParameter(1, idDiploma)
		.setParameter(2, idEduContinua)
		.executeUpdate();
	}

	@Transactional
	@Modifying
	@Override
	public void deleteElementsDiploma(Long idDiploma) {
		// TODO Auto-generated method stub
		
		 em.createNativeQuery("delete from imagenes_diploma where id_diploma= ?1")
		.setParameter(1, idDiploma)
		.executeUpdate();
		 
		 em.createNativeQuery("delete from textos_diploma where id_diploma= ?1")
			.setParameter(1, idDiploma)
			.executeUpdate();
		 
		 em.createNativeQuery("delete from firmas_diploma where id_diploma= ?1")
			.setParameter(1, idDiploma)
			.executeUpdate();
	}
	
}
