package com.jobJunior.cursomc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobJunior.cursomc.model.Estado;
import com.jobJunior.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {

	
	@Autowired EstadoRepository estadoRepository;
	
	public List<Estado> findAll(){
		return estadoRepository.findAllByOrderByNome();
	}
}
