package com.br.sistemahospedagem.domain.gateways;

import com.br.sistemahospedagem.domain.pojos.Room;

public interface RoomGateway {
    Room findRoomById(Integer id);
}
