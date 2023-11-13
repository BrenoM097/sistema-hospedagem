package com.br.sistemahospedagem.domain.booking;

public enum CheckOutTime {
    MEIODIA(12),
    TARDE(18),
    NOITE(22);
    
    private int value;

    private CheckOutTime(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
