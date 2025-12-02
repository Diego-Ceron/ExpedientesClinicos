package com.expedientesclinicos.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    @Autowired(required = false)
    @Nullable
    private JavaMailSender mailSender;

    @Value("${spring.mail.from:}")
    private String mailFrom;

    @Value("${email.provider:}")
    private String emailProvider;

    @Value("${sendgrid.api.key:}")
    private String sendgridApiKey;

    @Value("${sendgrid.from.email:}")
    private String sendgridFromEmail;

    @Value("${sendgrid.from.name:Expedientes Clinicos}")
    private String sendgridFromName;

    public void send(String to, String subject, String body) {
        // SMTP si está disponible
        if (mailSender != null) {
            try {
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(to);
                if (mailFrom != null && !mailFrom.isBlank()) {
                    msg.setFrom(mailFrom);
                }
                msg.setSubject(subject);
                msg.setText(body);
                mailSender.send(msg);
                return;
            } catch (MailException ex) {
                System.out.println("[EmailService] Falló envío SMTP: " + ex.getMessage());
            }
        }

        // Fallback: leer de env si properties no están
        if (emailProvider == null || emailProvider.isBlank()) {
            emailProvider = System.getenv().getOrDefault("EMAIL_PROVIDER", "");
        }
        if (sendgridApiKey == null || sendgridApiKey.isBlank()) {
            String envKey = System.getenv("SENDGRID_API_KEY");
            if (envKey != null) sendgridApiKey = envKey;
        }
        if (sendgridFromEmail == null || sendgridFromEmail.isBlank()) {
            String envFrom = System.getenv("SENDGRID_FROM_EMAIL");
            if (envFrom != null) sendgridFromEmail = envFrom;
        }
        if (sendgridFromName == null || sendgridFromName.isBlank()) {
            String envFromName = System.getenv("SENDGRID_FROM_NAME");
            if (envFromName != null) sendgridFromName = envFromName;
        }

        // Proveedor HTTP SendGrid
        if ("sendgrid".equalsIgnoreCase(emailProvider) && sendgridApiKey != null && !sendgridApiKey.isBlank()) {
            try {
                RestTemplate rt = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(sendgridApiKey);

                Map<String, Object> req = new HashMap<>();
                Map<String, String> from = new HashMap<>();
                from.put("email", sendgridFromEmail != null && !sendgridFromEmail.isBlank() ? sendgridFromEmail : "no-reply@expedientes.local");
                from.put("name", sendgridFromName);
                req.put("from", from);
                req.put("personalizations", List.of(Map.of("to", List.of(Map.of("email", to)), "subject", subject)));
                req.put("content", List.of(Map.of("type", "text/plain", "value", body)));

                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(req, headers);
                ResponseEntity<String> resp = rt.postForEntity("https://api.sendgrid.com/v3/mail/send", entity, String.class);
                System.out.println("[EmailService] Envío SendGrid status: " + resp.getStatusCode() + ", to=" + to + ", from=" + from.get("email"));
                return;
            } catch (Exception e) {
                System.out.println("[EmailService] Falló envío SendGrid: " + e.getMessage());
            }
        }

        System.out.println("[EmailService] Mail no configurado o falló proveedor. Destinatario: " + to + ", Asunto: " + subject + ", provider=" + emailProvider + ", SENDGRID_API_KEY set=" + (sendgridApiKey != null && !sendgridApiKey.isBlank()) + ", SENDGRID_FROM_EMAIL=" + (sendgridFromEmail == null ? "" : sendgridFromEmail));
    }
}
