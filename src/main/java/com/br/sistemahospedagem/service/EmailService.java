package com.br.sistemahospedagem.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.br.sistemahospedagem.config.StatusEmail;
import com.br.sistemahospedagem.domain.email.EmailModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmail(EmailModel emailModel) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(emailModel.getEmailFrom());
            helper.setTo(emailModel.getEmailTo());
            helper.setSubject(emailModel.getSubject());
            helper.setText(emailModel.getText(), true);

            emailSender.send(message);
            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MessagingException | MailException e) {
            System.out.println(e);
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }
    }

    public String processEmailTemplate(String name, LocalDate localDate, LocalDate localDate2) {
        Context context = new Context();
        context.setVariable("firstName", name);
        context.setVariable("checkIn", localDate);
        context.setVariable("checkOut", localDate2);
        
        return templateEngine.process("email_template", context);
    }
}
