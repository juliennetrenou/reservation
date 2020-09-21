package com.mairie.reservation.controllers;


import com.mairie.reservation.models.Material;
import com.mairie.reservation.models.Reservation;
import com.mairie.reservation.models.Room;
import com.mairie.reservation.services.MaterialService;
import com.mairie.reservation.services.ReservationService;
import com.mairie.reservation.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class PortalController {

    private RoomService roomService;
    private ReservationService reservationService;
    private MaterialService materialService;

    @Autowired
    public PortalController(RoomService roomService, ReservationService reservationService, MaterialService materialService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.materialService = materialService;
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "index/login";
    }

    @GetMapping("/portal")
    public String showPortal(Model model) {
        List<Room> roomList = roomService.allRoom();
        List<Material> materialList = materialService.allMaterials();
        model.addAttribute("materialList",materialList);
        model.addAttribute("roomList",roomList);
        return "reservations/portal";
    }

    @GetMapping("/addReservation")
    public String showNewReservationPage(Model model) {
        List<Room> roomList = roomService.allFreeRoom();
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("roomList",roomList);
        return "reservations/add-reservation";
    }

}
