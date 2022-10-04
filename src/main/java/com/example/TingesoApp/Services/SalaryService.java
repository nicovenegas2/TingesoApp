package com.example.TingesoApp.Services;

import com.example.TingesoApp.Entities.EmployeeEntity;
import com.example.TingesoApp.Entities.SalarySheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalaryService {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    DateService dateService;


    public List<SalarySheet> processSalary(Date date){
        List<SalarySheet> salarySheets = new ArrayList<>();
        SalarySheet salarySheet = new SalarySheet();
        float salaryTotal, salaryMonth, salaryHour, bonus=0, discount=0;
        try{
            List<EmployeeEntity> employeeEntities = employeeService.getEmployees();
            HashMap<Long, Float> salaryPerHour = categoryService.getSalaryPerHours();
            HashMap<Long, Float> salaryPerMonth = categoryService.getSalaryPerMonth();
            HashMap<Long, String> categoryName = categoryService.getCategoryNames();
            for (EmployeeEntity employee:employeeEntities){
                salaryHour = salaryPerHour.get(employee.getId_category());
                salaryMonth = salaryPerMonth.get(employee.getId_category());
                salarySheet.setRut(employee.getIdEmployees());
                salarySheet.setName(employee.getName() + " " +employee.getLast_name());
                salarySheet.setCategory(categoryName.get(employee.getId_category()));
                salarySheet.setYears_service(employeeService.years_service(employee.getIdEmployees()));
                salarySheet.setDiscount_percent(dateService.calculatePercentDiscount(date, employee.getIdEmployees()));
                salarySheet.setAbsences(dateService.countAbsencesPerMonth(date, employee.getIdEmployees()));
                salarySheet.setSalary_extra_hours(dateService.countExtraHours(date, employee.getIdEmployees()) * salaryPerHour.get(employee.getId_category()));
                bonus = (salaryMonth * (salarySheet.getBonus_percent()/100));
                discount = (salaryMonth * (salarySheet.getDiscount_percent()/100));
                salaryTotal = salaryMonth + bonus - discount + salarySheet.getSalary_extra_hours();
                salarySheet.setSalary(salaryTotal);
                salarySheets.add(salarySheet);
                salarySheet = new SalarySheet();
            }
        }catch (Error e){
        }
        return salarySheets;
    }

}
