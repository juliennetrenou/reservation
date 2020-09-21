package com.mairie.reservation.repositories;

import com.mairie.reservation.models.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room,Long> {
    @Query(
            value = "SELECT * FROM room r WHERE r.status = 1 ",
            nativeQuery = true)
    List<Room> findAllFreeRoom();

    @Query(value = "UPDATE room SET status = 0 WHERE id = :id",
            nativeQuery = true)
    Boolean updateRoomReservation(@Param("id") Long id);

    @Query(
            value = "SELECT * FROM room r WHERE r.status = 0 ",
            nativeQuery = true)
    List<Room> findAllReservedRoom();
}
