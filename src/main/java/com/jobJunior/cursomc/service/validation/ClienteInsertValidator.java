package com.jobJunior.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobJunior.cursomc.controllers.exceptions.FieldMessage;
import com.jobJunior.cursomc.dto.ClienteNewDTO;
import com.jobJunior.cursomc.model.Cliente;
import com.jobJunior.cursomc.model.enuns.TipoCliente;
import com.jobJunior.cursomc.repositories.ClienteRepository;
import com.jobJunior.cursomc.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getcpf_ou_cnpj())) {
			list.add(new FieldMessage("cpf_ou_cnpj", "CPF inválido!"));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getcpf_ou_cnpj())) {
			list.add(new FieldMessage("cpf_ou_cnpj", "CNPJ inválido!"));
		}

		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email","O email ja esta cadastrado no sistema!"));			
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
