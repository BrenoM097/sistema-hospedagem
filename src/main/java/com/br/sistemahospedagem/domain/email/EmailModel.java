package com.br.sistemahospedagem.domain.email;

import java.time.LocalDateTime;
import com.br.sistemahospedagem.config.StatusEmail;
import lombok.Data;

@Data
public class EmailModel {
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

    // Construtor padrão
    public EmailModel() {
        this.emailFrom = "melobreno89@gmail.com"; // Removido do construtor
        this.sendDateEmail = LocalDateTime.now(); // Definido no construtor
    }
}
