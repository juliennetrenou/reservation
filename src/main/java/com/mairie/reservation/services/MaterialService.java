package com.mairie.reservation.services;

import com.mairie.reservation.models.Employee;
import com.mairie.reservation.models.Material;
import com.mairie.reservation.models.Room;
import com.mairie.reservation.repositories.MaterialRepository;
import com.mairie.reservation.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Material> allMaterials(){
        return (List<Material>) materialRepository.findAll();
    }

    public Material saveMaterial(Material material, Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()){
            material.setRooms(room.get());
        }
        return materialRepository.save(material);
    }

}
