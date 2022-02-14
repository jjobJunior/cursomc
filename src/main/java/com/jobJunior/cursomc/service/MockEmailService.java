package com.jobJunior.cursomc.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage sMessage) {
		LOG.info("Simulando envio de email...");
		LOG.info(sMessage.toString());
		LOG.info("Email enviado com sucesso!");

	}

	@Override
	public void sendHtmlEmail(MimeMessage mimeMessage) {
		LOG.info("Simulando envio de email HTML...");
		LOG.info(mimeMessage.toString());
		LOG.info("Email enviado com sucesso!");
	}

}
