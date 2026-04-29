package com.br.sistemahospedagem.infra.gateways;

import com.br.sistemahospedagem.domain.gateways.BookingGateway;
import com.br.sistemahospedagem.domain.pojos.Booking;
import com.br.sistemahospedagem.infra.schemas.booking.BookingModel;
import com.br.sistemahospedagem.mappers.PojoEntityMapper;
import com.br.sistemahospedagem.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookRepositoryGateway implements BookingGateway {
    private final BookingRepository bookingRepository;
    private final PojoEntityMapper mapper;

    public BookRepositoryGateway(BookingRepository bookingRepository,  PojoEntityMapper mapper) {
        this.bookingRepository = bookingRepository;
        this.mapper = mapper;
    }

    @Override
    public Booking save(Booking booking) {
        BookingModel bookingModel = mapper.BookingSwapper(booking);
        bookingModel = bookingRepository.save(bookingModel);

        booking.setId(bookingModel.getId());
        return booking;
    }

    @Override
    public Booking findLatestBookingByRoomId(Integer roomId) {
        BookingModel bookingModel = bookingRepository.findLatestBookingByRoomId(roomId);
        Booking booking = new Booking(bookingModel.getCheckIn(), bookingModel.getCheckOut(), mapper.UserSwapper(bookingModel.getUser()), bookingModel.isParkingLot(), bookingModel.getCarType(), bookingModel.getCheckOutTime());
        booking.setId(bookingModel.getId());
        return booking;
    }

    @Override
    public Optional<Booking> findById(Long id) {
        Optional<BookingModel> bookingModel = bookingRepository.findBookingById(id);
        return bookingModel.map(mapper::BookingSwapper);
    }
}
