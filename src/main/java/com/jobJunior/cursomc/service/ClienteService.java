package com.jobJunior.cursomc.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobJunior.cursomc.dto.ClienteDTO;
import com.jobJunior.cursomc.dto.ClienteNewDTO;
import com.jobJunior.cursomc.model.Cidade;
import com.jobJunior.cursomc.model.Cliente;
import com.jobJunior.cursomc.model.Endereco;
import com.jobJunior.cursomc.model.enuns.TipoCliente;
import com.jobJunior.cursomc.repositories.ClienteRepository;
import com.jobJunior.cursomc.repositories.EnderecoRepository;
import com.jobJunior.cursomc.service.exceptions.DataIntegratyViolationException;
import com.jobJunior.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pEncoder;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"O Objeto não foi encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}

	public void delete(Integer id) {
		Cliente cliente = findById(id);
		if (cliente.getPedidos().size() != 0) {
			throw new DataIntegratyViolationException("O cliente não pode ser excluido pois esta associado a pedidos!");
		}
		clienteRepository.deleteById(id);
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(@Valid ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null,null);
	}

	public Cliente fromDTO(@Valid ClienteNewDTO objDto) {
		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getcpf_ou_cnpj(), TipoCliente.toEnum(objDto.getTipo()),pEncoder.encode(objDto.getSenha()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cliente, cid);
		cliente.getEnderecos().add(end);
		cliente.getTelefoneSet().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cliente.getTelefoneSet().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cliente.getTelefoneSet().add(objDto.getTelefone3());
		}
		return cliente;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}



}
