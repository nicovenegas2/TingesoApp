package com.example.TingesoApp.Controllers;


import com.example.TingesoApp.Services.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
@RequestMapping
public class DateController {
    @Autowired
    DateService dateService;

    @GetMapping("/hours_index")
    public String hours(){
        return "horas_justificativos";
    }

    @PostMapping("/upload_justify")
    public String upload_justify(@RequestParam("j_rut") String rut, @RequestParam("j_date")Date date){
    System.out.println(rut + date.toString());
    dateService.addJustify(rut, date);

    return "redirect:/hours_index";
    }

    @PostMapping("/upload_hours")
    public String upload_hours(@RequestParam("h_rut") String rut, @RequestParam("h_date")Date date, @RequestParam("h_hours") int hours){
        System.out.println(rut + date.toString());
        dateService.addExtraHours(rut, date, hours);

        return "redirect:/hours_index";
    }



}
