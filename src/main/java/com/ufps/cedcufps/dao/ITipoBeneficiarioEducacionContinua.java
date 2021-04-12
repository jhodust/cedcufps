package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ufps.cedcufps.modelos.EducacionContinuaTipoBeneficiario;

public interface ITipoBeneficiarioEducacionContinua extends JpaRepository<EducacionContinuaTipoBeneficiario, Long> {

	@Query(value="select * from educacion_continua_tipo_beneficiario  where id_educacion_continua = ?1", nativeQuery=true)
	public List<EducacionContinuaTipoBeneficiario> findTiposBeneficiariosByIdEduContinua(Long idEduContinua);
}
