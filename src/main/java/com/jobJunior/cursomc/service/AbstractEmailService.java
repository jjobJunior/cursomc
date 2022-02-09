package com.jobJunior.cursomc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.jobJunior.cursomc.model.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sMailMessage = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sMailMessage);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sMessage = new SimpleMailMessage();
		sMessage.setTo(obj.getCliente().getEmail());
		sMessage.setFrom(sender);
		sMessage.setSubject("Pedido confirmado! Codigo: " + obj.getId());
		sMessage.setSentDate(new Date(System.currentTimeMillis()));
		sMessage.setText(obj.toString());
		return sMessage;
	}

}
