package com.example.TingesoApp.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;


@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class EmployeeResponse {
    String rut;
    String name;
    String category;
    String last_name;
    int years_service;
    Date birth;
}
