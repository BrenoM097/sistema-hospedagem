package com.br.sistemahospedagem.exceptions;

public class RoomNotFoundException extends RuntimeException {
    
    public RoomNotFoundException(String msg) {
        super(msg);
    }
}
