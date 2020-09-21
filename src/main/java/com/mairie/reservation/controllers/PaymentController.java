package com.mairie.reservation.controllers;

import com.mairie.reservation.models.Employee;
import com.mairie.reservation.models.Payment;
import com.mairie.reservation.models.Reservation;
import com.mairie.reservation.models.Room;
import com.mairie.reservation.services.PaymentService;
import com.mairie.reservation.services.ReservationService;
import com.mairie.reservation.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/api/user")
public class PaymentController {

    private PaymentService paymentService;
    private RoomService roomService;
    private ReservationService reservationService;

    @Autowired
    public PaymentController(PaymentService paymentService, RoomService roomService, ReservationService reservationService) {
        this.paymentService = paymentService;
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @GetMapping("/addPayment")
    public String addNewPaymentPage(Model model) {
        List<Room> reservationList = roomService.allReservedRoom();
        List<Reservation> reservationList1 = reservationService.allReservation();
        model.addAttribute("payment",new Payment());
        model.addAttribute("reservationList",reservationList);
        model.addAttribute("reservationList1",reservationList1);
        return "payments/add-payment";
    }
}
