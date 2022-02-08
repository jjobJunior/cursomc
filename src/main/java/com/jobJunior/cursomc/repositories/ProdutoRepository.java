package com.jobJunior.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jobJunior.cursomc.model.Categoria;
import com.jobJunior.cursomc.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

//	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
//	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);

	@Transactional(readOnly=true)
	Page<Produto> findDistinctByNomeContainingIgnoreCaseAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
