package com.example.TingesoApp.Controllers;

import com.example.TingesoApp.Services.DataProcessService;
import com.example.TingesoApp.Services.EmployeeService;
import com.example.TingesoApp.Services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DataProcessService dataProcessService;

    @GetMapping("/")
    public String home(Model model){
        int countEmployees = employeeService.countEmployees();
        model.addAttribute("totalEmployees", countEmployees);
        return "index";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("tiempos")MultipartFile file){
        System.out.println("upload");
        uploadService.save(file);
        System.out.println("processData");
        dataProcessService.processData();
        return "redirect:/";
    }

}
