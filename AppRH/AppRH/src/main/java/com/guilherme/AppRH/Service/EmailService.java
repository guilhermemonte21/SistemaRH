package com.guilherme.AppRH.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
    public class EmailService {

        private final JavaMailSender mailSender;

        public EmailService(JavaMailSender mailSender) {
            this.mailSender = mailSender;
        }

        @Async
        public void enviarEmailSimples(String para, String assunto, String texto) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(para);
            message.setSubject(assunto);
            message.setText(texto);
            message.setFrom("gmonte003@gmail.com");

            mailSender.send(message);
        }}

