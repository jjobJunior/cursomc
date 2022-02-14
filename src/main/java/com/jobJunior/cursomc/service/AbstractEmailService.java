package com.jobJunior.cursomc.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.jobJunior.cursomc.model.Cliente;
import com.jobJunior.cursomc.model.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

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

	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);

	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
		MimeMessage mimeMessage = prepareMimeMessageFromPedido(obj);
		sendHtmlEmail(mimeMessage);
		}
		catch (MessagingException e) {
			sendOrderConfirmationHtmlEmail(obj);
		}
}

	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sMailMessage = prepareNewPasswordEmail(cliente, newPass);
		sendEmail(sMailMessage);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sMessage = new SimpleMailMessage();
		sMessage.setTo(cliente.getEmail());
		sMessage.setFrom(sender);
		sMessage.setSubject("Solicitação de nova senha!");
		sMessage.setSentDate(new Date(System.currentTimeMillis()));
		sMessage.setText("Nova senha:" + newPass);
		return sMessage;
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		messageHelper.setTo(obj.getCliente().getEmail());
		messageHelper.setFrom(sender);
		messageHelper.setSubject("Pedido confirmado! Codigo: " + obj.getId());
		messageHelper.setSentDate(new Date(System.currentTimeMillis()));
		messageHelper.setText(htmlFromTemplatePedido(obj), true);

		return mimeMessage;
	}

}
