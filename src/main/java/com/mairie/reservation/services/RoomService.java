package com.mairie.reservation.services;

import com.mairie.reservation.models.Employee;
import com.mairie.reservation.models.Room;
import com.mairie.reservation.repositories.EmployeeRepository;
import com.mairie.reservation.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Room> allRoom(){
        return (List<Room>) roomRepository.findAll();
        //return roomRepository.findAllFreeRoom();
    }

    public List<Room> allFreeRoom(){
        return roomRepository.findAllFreeRoom();
    }

    public List<Room> allReservedRoom() {
        return roomRepository.findAllReservedRoom();
    }

    public Room saveRoom(Room room, Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            room.setEmployees(employee.get());
        }
        return roomRepository.save(room);
    }

    public Boolean updateRoom(Long id) {
        return roomRepository.updateRoomReservation(id);
    }

}
