package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;
import com.ufps.cedcufps.modelos.InformeSnies;

public interface IInformeSniesService {

	public List<InformeSnies> findAll();
	
	public void save(InformeSnies i);
	
	public Optional<InformeSnies> findOne(Long id);
}
