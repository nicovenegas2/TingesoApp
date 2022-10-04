package com.example.TingesoApp.Controllers;


import com.example.TingesoApp.Entities.EmployeeEntity;
import com.example.TingesoApp.Entities.EmployeeResponse;
import com.example.TingesoApp.Entities.SalarySheet;
import com.example.TingesoApp.Services.DateService;
import com.example.TingesoApp.Services.EmployeeService;
import com.example.TingesoApp.Services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    SalaryService salaryService;
    @Autowired
    DateService dateService;

    @GetMapping("/employees")
    public String employees(Model model){
        List<EmployeeResponse> employees = employeeService.getAllEmployeesInfo();
        model.addAttribute("employees",employees);
        return "employees";
    }

    @GetMapping("/salaries")
    public String salaries(){
        return "salary";
    }

    @PostMapping("/salary_Month")
    public String processSalary(@RequestParam("s_date")String dateS, RedirectAttributes redi){
        List<SalarySheet> salaries;
        Date date = Date.valueOf(dateS + "-01");
        salaries = salaryService.processSalary(date);
        redi.addFlashAttribute("MonthSearch", dateS);
        redi.addFlashAttribute("salaries", salaries);

        return "redirect:/salaries";
    }
}
