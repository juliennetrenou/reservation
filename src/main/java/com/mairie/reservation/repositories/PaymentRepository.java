package com.mairie.reservation.repositories;

import com.mairie.reservation.models.Payment;
import com.mairie.reservation.models.Reservation;
import com.mairie.reservation.models.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    @Query(
            value = "SELECT ro.name as name FROM room ro, reservation r WHERE r.room_id = ro.id  ",
            nativeQuery = true)
    Collection<Reservation> findAllReservation();
}
