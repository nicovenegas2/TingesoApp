package com.example.TingesoApp.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name = "dates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false,name = "id_dates")
    Long id;

    Date date;
    Time entry_time;
    @Column(name = "justification")
    int justification;
    int extra_hours;
    String id_employee;
}
