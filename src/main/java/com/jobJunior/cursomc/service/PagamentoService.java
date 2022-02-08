package com.jobJunior.cursomc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobJunior.cursomc.model.Pagamento;
import com.jobJunior.cursomc.repositories.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;

	public Pagamento save(Pagamento obj) {
		obj.setId(null);
		return pagamentoRepository.save(obj);
	}
}
