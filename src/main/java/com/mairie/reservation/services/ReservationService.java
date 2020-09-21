package com.mairie.reservation.services;


import com.mairie.reservation.models.Employee;
import com.mairie.reservation.models.Reservation;
import com.mairie.reservation.models.Room;
import com.mairie.reservation.repositories.ReservationRepository;
import com.mairie.reservation.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> allReservationStatus(){
        return (List<Reservation>) reservationRepository.findAllReservationStatus();
    }


    public List<Reservation> allReservation(){
        //return (List<Reservation>) reservationRepository.findAll();
        return (List<Reservation>) reservationRepository.findAllReservation();
    }

    public Reservation saveReservation(Reservation reservation, Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()){
            reservation.setRooms(room.get());
        }
        return reservationRepository.save(reservation);
    }

    public void validatedReservation(Long id) {
        reservationRepository.validatedReservation(id);
    }

    public void rejectedReservation(Long id) {
         reservationRepository.rejectedReservation(id);
    }


    public Optional<Reservation> getOne(Long id) {
        return reservationRepository.findById(id);
    }

}
