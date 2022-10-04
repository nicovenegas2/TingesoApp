package com.example.TingesoApp.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id_categories")
    Long id;

    @Column(name = "name_category")
    String name;

    @Column(name = "salary_per_hour")
    float salaryPerHour;

    @Column(name = "salary_per_month")
    float salaryPerMonth;
}
