package com.jobJunior.cursomc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobJunior.cursomc.controllers.utils.URL;
import com.jobJunior.cursomc.dto.ProdutoDTO;
import com.jobJunior.cursomc.model.Produto;
import com.jobJunior.cursomc.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> finById(@PathVariable Integer id) {
		Produto produto = produtoService.findById(id);
		return ResponseEntity.ok().body(produto);
	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findAllPage(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
		String nomeDecoded = URL.decodParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, direction, orderBy);
		Page<ProdutoDTO> listDtos = list.map(obj -> new ProdutoDTO(obj));

		return ResponseEntity.ok().body(listDtos);
	}

}
