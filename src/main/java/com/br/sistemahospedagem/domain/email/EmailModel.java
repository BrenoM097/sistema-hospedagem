package com.br.sistemahospedagem.domain.email;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EmailModel {
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

    public EmailModel() {
        this.emailFrom = "melobreno89@gmail.com"; 
        this.sendDateEmail = LocalDateTime.now(); 
    }
}
