package com.mairie.reservation.controllers;

import com.mairie.reservation.models.Employee;
import com.mairie.reservation.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Controller
@RequestMapping("api/admin")
public class EmployeeController {

    private final static Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());

    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/addEmployee")
    public String addNewEmployeePage(Model model) {
        model.addAttribute("employee",new Employee());
        return "employees/add-employee";
    }

    @GetMapping("/employeeList")
    public String showEmployeeListPage(Model model) {
        List<Employee> employeeList = employeeService.allEmployee();
        model.addAttribute("employeeList",employeeList);
        return "employees/employee-list";
    }
    
    @PostMapping(value="/saveEmployee")
    public String addEmployee(@ModelAttribute("employee") Employee employee, BindingResult result) {
        if(result.hasErrors()) {
            LOGGER.severe("Error when saving employee "+result.getModel());
        }else {
            employeeService.saveEmployee(employee);
        }
        return "redirect:/api/admin/employeeList";
    }

    @GetMapping("/editEmployee/{id}")
    public ModelAndView showEmployeeEditPage(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("employees/edit-employee");
        Optional<Employee> employee = employeeService.getOne(id);
        modelAndView.addObject("employee",employee);
        return modelAndView;
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(name = "id") Long id){
        employeeService.deleteEmployee(id);
        return "redirect:/api/admin/employeeList";

    }


}
