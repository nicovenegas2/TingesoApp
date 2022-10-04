package com.example.TingesoApp.Services;


import com.example.TingesoApp.Entities.DateEntity;
import com.example.TingesoApp.Entities.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

@Service
public class DataProcessService {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DateService dateService;
    private String folder = "files//";
    private String name = "Data.txt";
    private Scanner scanner;


    public boolean processData(){
        String line;
        String[] dataArray;
        DateEntity date;
        try{
            File file = new File(folder+name);
            scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                line = scanner.nextLine();
                dataArray = line.split(";");
                date = new DateEntity();
                date.setDate(Date.valueOf(this.changeFormatDate(dataArray[0])));
                date.setEntry_time(Time.valueOf(this.changeFormatTime(dataArray[1])));
                date.setId_employee(dataArray[2]);
                date.setJustification(0);
                date.setExtra_hours(0);
                dateService.save(date);
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public String changeFormatDate(String date){
        String[] array = date.split("/");
        return array[2]+"-"+array[1]+"-"+array[0];
    }

    public String changeFormatTime(String time){ return time+":00";}
}
