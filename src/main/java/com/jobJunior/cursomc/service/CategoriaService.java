package com.jobJunior.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobJunior.cursomc.modelo.Categoria;
import com.jobJunior.cursomc.repositories.CategoriaRepository;
import com.jobJunior.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("O Objeto não foi encontrado! Id: "
		+ id + ", Tipo: " + Categoria.class.getName()));
	}

}
