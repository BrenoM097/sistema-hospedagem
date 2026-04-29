package com.br.sistemahospedagem.mappers;

import com.br.sistemahospedagem.domain.pojos.Booking;
import com.br.sistemahospedagem.domain.pojos.Room;
import com.br.sistemahospedagem.domain.pojos.User;
import com.br.sistemahospedagem.infra.schemas.booking.BookingModel;
import com.br.sistemahospedagem.infra.schemas.room.RoomSchema;
import com.br.sistemahospedagem.infra.schemas.user.UserModel;
import org.springframework.stereotype.Component;

@Component
public class PojoEntityMapper {
    public Room RoomSwapper(RoomSchema data) {
        Room room = new Room();
        room.setId(data.getId());
        room.setAdults(data.getAdults());
        room.setFloor(data.getFloor());
        room.setDailyValue(data.getDailyValue());
        room.setBedType(data.getBedType());
        room.setHaveArConditioner(data.isHaveArConditioner());

        return room;
    }

    public RoomSchema RoomSwapper(Room data) {
        RoomSchema room = new RoomSchema();
        room.setId(data.getId());
        room.setAdults(data.getAdults());
        room.setFloor(data.getFloor());
        room.setDailyValue(data.getDailyValue());
        room.setBedType(data.getBedType());
        room.setHaveArConditioner(data.isHaveArConditioner());

        return room;
    }

    public User UserSwapper(UserModel data) {
        User user = new User();
        user.setId(data.getId());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setCpf(data.getCpf());
        user.setPhone(data.getPhone());
        user.setAddress(data.getAddress());
        user.setRole(data.getRole());
        return user;
    }

    public UserModel UserSwapper(User data) {
        UserModel user = new UserModel();
        user.setId(data.getId());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setCpf(data.getCpf());
        user.setPhone(data.getPhone());
        user.setAddress(data.getAddress());
        user.setRole(data.getRole());
        return user;
    }

    public BookingModel BookingSwapper(Booking data) {
        BookingModel booking = new BookingModel();
        booking.setId(data.getId());
        booking.setRoom(this.RoomSwapper(data.getRoom()));
        booking.setCarType(data.getCarType());
        booking.setCheckOutTime(data.getCheckOutTime());
        booking.setCheckIn(data.getCheckIn());
        booking.setParkingLot(data.isParkingLot());
        booking.setStatusEmail(data.getEmailStatus());
        booking.setTotalValue(data.getTotalValue());
        booking.setUser(this.UserSwapper(data.getUser()));
        booking.setCheckOutTime(data.getCheckOutTime());

        return booking;
    }

    public Booking BookingSwapper(BookingModel data) {
        Booking booking = new Booking();
        booking.setId(data.getId());
        booking.setRoom(this.RoomSwapper(data.getRoom()));
        booking.setCarType(data.getCarType());
        booking.setCheckOutTime(data.getCheckOutTime());
        booking.setCheckIn(data.getCheckIn());
        booking.setParkingLot(data.isParkingLot());
        booking.setEmailStatus(data.getStatusEmail());
        booking.setTotalValue(data.getTotalValue());
        booking.setUser(this.UserSwapper(data.getUser()));
        booking.setCheckOutTime(data.getCheckOutTime());

        return booking;
    }
}
