package com.br.sistemahospedagem.infra.gateways;

import com.br.sistemahospedagem.domain.gateways.RoomGateway;
import com.br.sistemahospedagem.domain.pojos.Room;
import com.br.sistemahospedagem.exceptions.NotFoundException;
import com.br.sistemahospedagem.infra.schemas.room.RoomSchema;
import com.br.sistemahospedagem.mappers.PojoEntityMapper;
import com.br.sistemahospedagem.repositories.RoomRepository;
import org.springframework.stereotype.Component;

@Component
public class RoomRepositoryGateway implements RoomGateway {
    private final RoomRepository repository;
    private final PojoEntityMapper mapper;

    public RoomRepositoryGateway(RoomRepository repository, PojoEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Room findRoomById(Integer id) {
        RoomSchema entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Room Not Found"));
        return mapper.RoomSwapper(entity);
    }
}
