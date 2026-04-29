package com.br.sistemahospedagem.domain.gateways;

import com.br.sistemahospedagem.domain.pojos.Booking;

public interface EmailGateway {
    boolean sendEmail(Booking booking);
}
