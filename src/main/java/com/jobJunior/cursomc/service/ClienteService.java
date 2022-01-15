package com.jobJunior.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobJunior.cursomc.modelo.Cliente;
import com.jobJunior.cursomc.repositories.ClienteRepository;
import com.jobJunior.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("O Objeto não foi encontrado! Id: "
		+ id + ", Tipo: " + Cliente.class.getName()));
	}
}
