package com.mairie.reservation.repositories;

import com.mairie.reservation.models.Reservation;
import com.mairie.reservation.models.Room;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Long> {

    // have all reservations who are treated
    @Query(
            value = "SELECT * FROM reservation re WHERE re.active = 1 OR re.active = -1 and re.status = 1 ",
            nativeQuery = true)
    List<Reservation> findAllReservationStatus();

    // have all new reservations
    @Query(
            value = "SELECT * FROM reservation re WHERE re.active = 0 and re.status = 1 ",
            nativeQuery = true)
    List<Reservation> findAllReservation();

    // when a user validate the reservation .. updating the reservation status then (active = 1)

    @Modifying
    @Query(
            value = "UPDATE reservation SET active = 1 WHERE id = :id ",
            nativeQuery = true)
     void validatedReservation(@Param("id") Long id);

    // when a user rejected the reservation .. updating the reservation status then (active = -1)

    @Modifying
    @Query(
            value = "UPDATE reservation SET active = -1 WHERE id = :id ",
            nativeQuery = true)
    void rejectedReservation(@Param("id") Long id);
}
