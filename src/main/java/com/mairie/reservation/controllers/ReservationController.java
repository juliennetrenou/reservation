package com.mairie.reservation.controllers;

import com.mairie.reservation.models.Employee;
import com.mairie.reservation.models.Material;
import com.mairie.reservation.models.Reservation;
import com.mairie.reservation.models.Room;
import com.mairie.reservation.requests.ReservationRequest;
import com.mairie.reservation.requests.RoomRequest;
import com.mairie.reservation.services.MaterialService;
import com.mairie.reservation.services.ReservationService;
import com.mairie.reservation.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/user")
public class ReservationController {

    private final static Logger LOGGER = Logger.getLogger(ReservationController.class.getName());

    private RoomService roomService;
    private ReservationService reservationService;
    private MaterialService materialService;

    @Autowired
    public ReservationController(RoomService roomService, ReservationService reservationService, MaterialService materialService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.materialService = materialService;
    }


    @PostMapping("/saveReservation")
    public String addReservation(@ModelAttribute("reservation") @Valid ReservationRequest reservationRequest, BindingResult result) {
        if (result.hasErrors()) {
            LOGGER.severe("Error when saving reservation "+result.getModel());
        } else {
            Reservation reservation = new Reservation();
            reservation.setStartDate(reservationRequest.getStartDate());
            reservation.setEndDate(reservationRequest.getEndDate());
            reservation.setStartHour(reservationRequest.getStartHour());
            reservation.setEndHour(reservationRequest.getEndHour());
            reservation.setEmail(reservationRequest.getEmail());
            reservation.setUserType(reservationRequest.getUserType());
            reservation.setUserName(reservationRequest.getUserName());
            reservation.setNb(reservationRequest.getNb());
            reservation.setManifestationName(reservationRequest.getManifestationName());

/*
            if(roomService.updateRoom(reservationRequest.getRooms())) {
                LOGGER.info("saving reservation successfully "+result.getModel());

            } else {
                LOGGER.severe("Error when saving reservation "+result.getModel());

            }
*/

            reservationService.saveReservation(reservation,reservationRequest.getRooms());

        }
        return "redirect:/api/portal";
    }

    @GetMapping("/reservationList")
    public String showReservationListPage(Model model) {
        List<Reservation> reservationList = reservationService.allReservation();
        model.addAttribute("reservationList",reservationList);
        return "agent/all-new-reservation";
    }

    @GetMapping("/treatyReservationList")
    public String showTreatyReservationListPage(Model model) {
        List<Reservation> reservationList = reservationService.allReservationStatus();
        model.addAttribute("reservationList", reservationList);
        return "agent/all-reservation-treaty";
    }


    @GetMapping("/validatedReservation/{id}")
    public String showReservationValidated(@PathVariable(name = "id") Long id){
        reservationService.validatedReservation(id);
        return "agent/all-reservation-treaty";
    }

    @GetMapping("/rejectedReservation/{id}")
    public String showReservationRejected(@PathVariable(name = "id") Long id){
        reservationService.rejectedReservation(id);
        return "agent/all-reservation-treaty";
    }


}
