package com.expedientesclinicos.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired(required = false)
    @Nullable
    private JavaMailSender mailSender;

    public void send(String to, String subject, String body) {
        if (mailSender == null) {
            System.out.println("[EmailService] Mail no configurado. Destinatario: " + to + ", Asunto: " + subject + ", Cuerpo: " + body);
            return;
        }
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(body);
            mailSender.send(msg);
        } catch (MailException ex) {
            System.out.println("[EmailService] Falló envío de correo: " + ex.getMessage());
        }
    }
}
