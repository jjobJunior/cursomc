package com.jobJunior.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jobJunior.cursomc.model.Categoria;
import com.jobJunior.cursomc.model.Produto;
import com.jobJunior.cursomc.repositories.CategoriaRepository;
import com.jobJunior.cursomc.repositories.ProdutoRepository;
import com.jobJunior.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository pedidoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto findById(Integer id) {
		Optional<Produto> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"O Objeto não foi encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String direction,
			String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return pedidoRepository.findDistinctByNomeContainingIgnoreCaseAndCategoriasIn(nome, categorias, pageRequest);
	}
}
