package com.mairie.reservation.controllers;

import com.mairie.reservation.models.Employee;
import com.mairie.reservation.models.Material;
import com.mairie.reservation.models.Room;
import com.mairie.reservation.requests.MaterialRequest;
import com.mairie.reservation.requests.RoomRequest;
import com.mairie.reservation.services.MaterialService;
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
public class MaterialController {

    private final static Logger LOGGER = Logger.getLogger(RoomController.class.getName());

    @Autowired
    private RoomService roomService;

    @Autowired
    private MaterialService materialService;

    @GetMapping("/addMaterial")
    public String addNewMaterialPage(Model model) {
        List<Room> roomList = roomService.allRoom();
        model.addAttribute("material", new Material());
        model.addAttribute("roomList", roomList);
        return "materials/add-material";
    }

    @GetMapping("/materialList")
    public String showMaterialListPage(Model model) {
        List<Material> materialList = materialService.allMaterials();
        model.addAttribute("materialList",materialList);
        return "materials/material-list";
    }

    @PostMapping("/saveMaterial")
    public String addMaterial(@ModelAttribute("material") @Valid MaterialRequest materialRequest, BindingResult result) {
        if (result.hasErrors()) {
            LOGGER.severe("Error when saving material "+result.getModel());
        } else {
            Material material = new Material();
            material.setName(materialRequest.getName());
            material.setReference(materialRequest.getReference());
            materialService.saveMaterial(material, materialRequest.getRooms());
        }
        return "redirect:/api/admin/materialList";
    }
}
