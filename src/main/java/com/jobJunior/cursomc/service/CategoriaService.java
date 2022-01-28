package com.jobJunior.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jobJunior.cursomc.modelo.Categoria;
import com.jobJunior.cursomc.repositories.CategoriaRepository;
import com.jobJunior.cursomc.service.exceptions.DataIntegratyViolationException;
import com.jobJunior.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"O Objeto não foi encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return categoriaRepository.save(obj);
	}

	public void delete(Integer id) {
		Categoria categoria = findById(id);
		if (categoria.getProdutos().size() != 0) {
			throw new DataIntegratyViolationException("A categoria não pode ser excluida pois esta associada a produtos!");
		}
		categoriaRepository.deleteById(id);
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				return categoriaRepository.findAll(pageRequest);
	}


}
