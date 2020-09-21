package com.mairie.reservation.controllers;

import com.mairie.reservation.models.Employee;
import com.mairie.reservation.models.Room;
import com.mairie.reservation.requests.RoomRequest;
import com.mairie.reservation.services.EmployeeService;
import com.mairie.reservation.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/admin")
public class RoomController {

    private final static Logger LOGGER = Logger.getLogger(RoomController.class.getName());

    @Autowired
    private RoomService roomService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/addRoom")
    public String addNewRoomPage(Model model) {
        List<Employee> employeeList = employeeService.allEmployee();
        model.addAttribute("room", new Room());
        model.addAttribute("employeeList", employeeList);
        return "rooms/add-room";
    }

    @GetMapping("/roomList")
    public String showRoomListPage(Model model) {
        List<Room> roomList = roomService.allRoom();
        model.addAttribute("roomList",roomList);
        return "rooms/room-list";
    }

    @PostMapping("/saveRoom")
    public String addRoom(@ModelAttribute("room") @Valid RoomRequest roomRequest, BindingResult result) {
        if (result.hasErrors()) {
            LOGGER.severe("Error when saving room "+result.getModel());
        } else {
            Room room = new Room();
            LOGGER.info("name"+roomRequest.getName());
            room.setName(roomRequest.getName());
            room.setAddress(roomRequest.getAddress());
            room.setMinCapacity(roomRequest.getMinCapacity());
            room.setMaxCapacity(roomRequest.getMaxCapacity());
            room.setPrice(roomRequest.getPrice());
            room.setSurface(roomRequest.getSurface());
            roomService.saveRoom(room, roomRequest.getEmployees());
        }
        return "redirect:/api/admin/roomList";
    }

}
