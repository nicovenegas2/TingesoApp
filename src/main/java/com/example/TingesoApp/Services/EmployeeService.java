package com.example.TingesoApp.Services;


import com.example.TingesoApp.Entities.CategoryEntity;
import com.example.TingesoApp.Entities.EmployeeEntity;
import com.example.TingesoApp.Entities.EmployeeResponse;
import com.example.TingesoApp.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CategoryService categoryService;

    public EmployeeService(){

    }
    public ArrayList<EmployeeEntity> getEmployees(){
        return (ArrayList) employeeRepository.findAll();
    }
    public int countEmployees(){return (int) employeeRepository.count();}
    public List<EmployeeResponse> getAllEmployeesInfo(){
        List<EmployeeResponse> employees = new ArrayList<>();
        EmployeeResponse employeeResponse = new EmployeeResponse();
        CategoryEntity category;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        try{
            ArrayList<EmployeeEntity> employeesList = (ArrayList) employeeRepository.findAll();
            for(EmployeeEntity em: employeesList){
                category = categoryService.getById(em.getId_category());
                cal.setTime(em.getEntry_date());
                employeeResponse.setName(em.getName_employee());
                employeeResponse.setBirth(em.getBirth());
                employeeResponse.setYears_service(year - cal.get(Calendar.YEAR));
                employeeResponse.setRut(em.getIdEmployees());
                employeeResponse.setLast_name(em.getLast_name());
                employeeResponse.setCategory(category.getName());
                employees.add(employeeResponse);
                employeeResponse = new EmployeeResponse();
            }
        }catch (Error e){
            System.out.println("error" + e);
        }
        return employees;
    }

    public int years_service(String idEmployee){
        int years = -1;
        int now;
        try{
            EmployeeEntity employee = employeeRepository.findById(idEmployee).get();
            CategoryEntity category;
            Calendar cal = Calendar.getInstance();
            now = cal.get(Calendar.YEAR);
            cal.setTime(employee.getEntry_date());
            years = now -cal.get(Calendar.YEAR);
        }catch(Error e){}
        return years;
    }

    public float bonus_service(int years){
        float bonus = 0;
        if(5 <= years && years < 10) bonus = 5;
        if(10 <= years && years < 15) bonus = 8;
        if(15 <= years && years < 20) bonus = 11;
        if(20 <= years && years < 25) bonus = 14;
        if(25 <= years) bonus = 17;
        return bonus;
    }

}
