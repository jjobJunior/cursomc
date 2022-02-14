package com.jobJunior.cursomc.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobJunior.cursomc.model.Cliente;
import com.jobJunior.cursomc.repositories.ClienteRepository;
import com.jobJunior.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	private Random rand = new Random();

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Autowired
	private EmailService emailService;

	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);

		if (cliente == null) {
			throw new ObjectNotFoundException("O Email não foi encontrado!");
		}
		String newPass = newPassword();
		cliente.setSenha(bCrypt.encode(newPass));
		clienteRepository.save(cliente);

		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randonChar();
		}

		return new String(vet);
	}

	private char randonChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // sera gerado um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) {// sera gerado uma letra maiuscula
			return (char) (rand.nextInt(10) + 65);
		} else { // sera gerado uma letra minuscula
			return (char) (rand.nextInt(10) + 97);
		}
	}

}
