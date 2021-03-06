package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IParticipanteDao;
import com.ufps.cedcufps.dao.ITipoParticipanteDao;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.modelos.TipoParticipante;

@Service
public class ParticipanteService implements IParticipanteService{

	@Autowired
	private IParticipanteDao participanteDao;
	
	@Autowired
	private ITipoParticipanteDao tipoParticipanteDao;
	
	@Override
	public List<TipoParticipante> findAllTiposParticipante() {
		// TODO Auto-generated method stub
		return (List<TipoParticipante>) tipoParticipanteDao.findAll();
	}

	@Override
	public List<Participante> findAllParticipante() {
		// TODO Auto-generated method stub
		return (List<Participante>) participanteDao.findAll();
	}

	@Override
	public void save(Participante p) {
		// TODO Auto-generated method stub
		participanteDao.save(p);
	}

	@Override
	public TipoParticipante findByTipoParticipante(String tipoParticipante) {
		// TODO Auto-generated method stub
		return tipoParticipanteDao.findByTipoParticipante(tipoParticipante);
	}

	@Override
	public Optional<Participante> findOne(Long id) {
		// TODO Auto-generated method stub
		return participanteDao.findById(id);
	}

	@Override
	public Participante findByIdEducacionContinuaAndIdPersona(Long idEducacionContinua, Long idPersona) {
		// TODO Auto-generated method stub
		return participanteDao.findParticipanteByIdEducacionContinuaAndIdPersona(idEducacionContinua, idPersona);
	}

	@Override
	public void deleteParticipante(Participante p) {
		// TODO Auto-generated method stub
		participanteDao.delete(p);
	}

	@Override
	public List<Participante> findAllPonentesOfOneEducacionContinua(Long idEducacionContinua) {
		// TODO Auto-generated method stub
		return participanteDao.findAllPonentesOfOneEducacionContinua(idEducacionContinua);
	}

	@Override
	public Participante findParticipante(Long id) {
		// TODO Auto-generated method stub
		return participanteDao.findById(id).get();
	}

	@Override
	public void deleteParticipanteById(Long id) {
		// TODO Auto-generated method stub
		participanteDao.deleteById(id);
	}

	@Override
	public List<Participante> findAllParticipacionesActivasByParticipante(String numDocumento) {
		// TODO Auto-generated method stub
		return participanteDao.findAllParticipacionesActivasByParticipante(numDocumento);
	}

	@Override
	public List<Participante> findAllParticipantesByEducacionContinua(Long idEduContinua) {
		// TODO Auto-generated method stub
		return participanteDao.findAllParticipantesByEducacionContinua(idEduContinua);
	}

	@Override
	public TipoParticipante findTipoParticipanteById(Long id) {
		// TODO Auto-generated method stub
		return tipoParticipanteDao.findById(id).get();
	}

}
