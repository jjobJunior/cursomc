package com.jobJunior.cursomc.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.jobJunior.cursomc.model.Cliente;
import com.jobJunior.cursomc.model.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);

	void sendEmail(SimpleMailMessage sMessage);

	void sendOrderConfirmationHtmlEmail(Pedido obj);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
	
}
