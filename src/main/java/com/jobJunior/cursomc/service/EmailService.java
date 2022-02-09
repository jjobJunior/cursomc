package com.jobJunior.cursomc.service;

import org.springframework.mail.SimpleMailMessage;

import com.jobJunior.cursomc.model.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	 
	 void sendEmail(SimpleMailMessage sMessage);
}
