package com.example.TingesoApp.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeEntity {

    @Id
    @Column(unique = true, name = "id_employees")
    String idEmployees;

    @Column(name = "name_employee")
    String name;
    @Column(name="last_name")
    String last_name;
    @Column(name="birth")
    Date birth;
    @Column(name="entry_date")
    Date entry_date;



    Long id_category;

}
