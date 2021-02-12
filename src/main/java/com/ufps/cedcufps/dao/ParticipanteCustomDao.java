package com.ufps.cedcufps.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.mapper.EducacionContinuaMapper;
import com.ufps.cedcufps.modelos.Participante;

@Repository
public class ParticipanteCustomDao implements IParticipanteCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EducacionContinuaMapper educacionContinuaMapper;

	@Transactional
	@Override
	public void saveParticipante(ParticipanteDto p) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder().append("insert into participantes (codigoqr, fecha_registro, imagen_codigo_qr, educacion_continua_id, id_persona, id_tipo_participante, id_tipo_persona, token)")
				.append(" VALUES (?,?,?,?,?,?,?,?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, p.getCodigoQR());
				ps.setObject(2, p.getFechaInscripcion());
				ps.setString(3, p.getImagenQr());
				ps.setLong(4, p.getIdEducacionContinua());
				ps.setLong(5, p.getIdPersona());
				ps.setLong(6, p.getIdTipoParticipante());
				ps.setObject(7, p.getIdTipoPersona());
				ps.setObject(8, p.getToken());
				
				return ps;
			}

		}, keyHolder);
		
		p.setId(Long.parseLong(String.valueOf(keyHolder.getKey())));
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println(p.getId() );
		System.out.println(  keyHolder.getKeys().get("id"));

	}

	
	
	
	
	
	
}
