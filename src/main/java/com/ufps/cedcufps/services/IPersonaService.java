package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.ufps.cedcufps.dto.DocenteDto;
import com.ufps.cedcufps.dto.PerfilRolUsuarioDto;
import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.dto.PersonaDtoLogueada;
import com.ufps.cedcufps.dto.UsuarioAppDto;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.EstadoCivil;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Genero;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.PersonaRol;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.TipoDocumento;
import com.ufps.cedcufps.modelos.TipoPersona;

public interface IPersonaService {

	public List<Persona> findAllPersonas();
	
	public List<Estudiante> findAllEstudiantes();
	
	public List<DocenteDto> findAllDocentes() ;
	
	public List<Administrativo> findAllAdministrativos();
	
	public List<Externo> findAllExternos();
	
	public List<EstadoCivil> findAllEstadosCiviles();
	
	public List<Genero> findAllGeneros();
	
	public List<TipoDocumento> findAllTiposDocumento();
	
	public List<TipoPersona> findAllTiposPersona();
	
	public List<Programa> findAllProgramas();
	
	public List<Departamento> findAllDepartamentos();
	
	public TipoPersona findByTipoPersona(String tipoPersona);
	
	public void save(Persona p);
	
	public Persona findPersonaLogueada();
	
	public String findEmailPersonaLogueada();
	
	public UsuarioAppDto convertPersonaLogueadaApp(Persona p);
	
	public PersonaDto findOne(Long id);
	
	public Persona findByUsername(String username);

	public Persona findByEmail(String email);
	
	public void guardar(UsuarioDto u);
	
	public List<PersonaDto> findAllPersonasPosibles();
	
	public boolean isSuperAdmin();
	
	public boolean isSuperAdmin(Persona p);
	
	public boolean isDirPrograma();
	
	public boolean isDirPrograma(Persona p);

	public boolean isDocente();
	
	public boolean isDocente(Persona p);
	
	public boolean hasPermissionForPeople(Long idPersona);
	
	public boolean hasPermissionForEduContinua(Long idPersona);
	
	public boolean hasPermissionForAttendance(Long idPersona);
	
	public boolean hasPermissionForAttendance(Persona p);
	
	public PerfilRolUsuarioDto findPermisos(String idAcceso);
	
	public UsuarioDto editarUsuario(String idAcceso);
	
	public boolean updatePermisos(Long idPersona,boolean hasPermisosEduC, boolean hasPermisosPer, boolean hasPermisosAtt,
			List<Long> idsProEduContinua, List<Long> idsProEst, List<Long> idsDeptoDoc, List<Long> idsProGrad,
			List<Long> idsEduAtt, boolean hasPermisosAdminvo, boolean hasPermisosExter, 
			boolean isDirPrograma, boolean isDocente,Long idProgramaDirector);
	
	public DataTablesOutput<PersonaDto> findPossiblePonente(int tipoBusqueda, String value);
	
	
	public PersonaDtoLogueada findPersonaLogueadaDto(Persona p);
	
	public Persona findPersonaById(Long idPersona);
	
	
	public UsuarioDto findMyInfo();

}
