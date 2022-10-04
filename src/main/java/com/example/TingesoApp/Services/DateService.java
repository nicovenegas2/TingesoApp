package com.example.TingesoApp.Services;

import com.example.TingesoApp.Entities.DateEntity;
import com.example.TingesoApp.Repositories.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DateService {
    @Autowired
    DateRepository dateRepository;

    public boolean save(DateEntity date){
        List<DateEntity> check;
        try {
            check = dateRepository.findDateByDateAndEmployee(date.getDate(), date.getId_employee());
            if(check.isEmpty()){
                dateRepository.save(date);
            }
        } catch (Error e){
            return false;
        }
        return true;
    }

    public boolean addJustify(String rut, Date date){
        try {
            List<DateEntity> dateEntities;
            DateEntity dateEntity;
            dateEntities = dateRepository.findDateByDateAndEmployee(date,rut);
            if (!dateEntities.isEmpty()) {
                dateEntity = dateEntities.get(0);
                dateEntity.setJustification(1);
                System.out.println(dateEntity.toString());
                dateRepository.save(dateEntity);
            }else return false;
        }catch (Error e){
            return false;
        }
        return true;
    }

    public boolean addExtraHours(String rut, Date date, int hours){
        try {
            List<DateEntity> dateEntities;
            DateEntity dateEntity;
            dateEntities = dateRepository.findDateByDateAndEmployee(date,rut);
            if (!dateEntities.isEmpty()) {
                dateEntity = dateEntities.get(0);
                dateEntity.setExtra_hours(hours);
                System.out.println(dateEntity.toString());
                dateRepository.save(dateEntity);
            }else return false;
        }catch (Error e){
            return false;
        }
        return true;
    }

    public List<DateEntity> getAllByDate(Date date){return (List<DateEntity>) dateRepository.findAllByDate(date);}

    public float calculatePercentDiscount(Date date, String rut){
        float healthCare = 8;
        float previsional = 10;
        float discount = healthCare + previsional;
        float discountPerAbsence = 15;
        List<DateEntity> dateEntities = dateRepository.getAbsencesLightEmployeeByMonth(rut,date);
        discount += discountPerAbsence * dateEntities.size();
        dateEntities = dateRepository.getAbsencesMiddleEmployeeByMonth(rut,date);
        discount += discountPerAbsence * dateEntities.size();
        dateEntities = dateRepository.getAbsencesHighEmployeeByMonth(rut,date);
        discount += discountPerAbsence * dateEntities.size();
        return discount;
    }

    public int countAbsencesPerMonth(Date date, String rut){
        int discount = 0;
        List<DateEntity> dateEntities = dateRepository.getAbsencesLightEmployeeByMonth(rut,date);
        discount += dateEntities.size();
        dateEntities = dateRepository.getAbsencesMiddleEmployeeByMonth(rut,date);
        discount += dateEntities.size();
        dateEntities = dateRepository.getAbsencesHighEmployeeByMonth(rut,date);
        discount += dateEntities.size();
        return discount;
    }

    public int countExtraHours(Date date, String rut){
        int hours = 0;
        List<DateEntity> dates = dateRepository.getExtraHoursByDate(rut, date);
        for(DateEntity dateEntity:dates){
            hours += dateEntity.getExtra_hours();
        }
        return hours;
    }
}
