package com.example.TingesoApp.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class SalarySheet {
    String rut;
    String name;
    int years_service;
    String category;
    float salary;
    float salary_extra_hours;
    int absences;
    float discount_percent;
    float bonus_percent;

}
