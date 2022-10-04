package com.example.TingesoApp.Repositories;

import com.example.TingesoApp.Entities.DateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

@Repository
public interface DateRepository extends JpaRepository<DateEntity , Long> {

    @Query(value = "SELECT * FROM dates WHERE date = ? AND id_employee = ?", nativeQuery = true)
    List<DateEntity> findDateByDateAndEmployee(Date date, String rut);

    @Query(value = "SELECT * FROM dates WHERE MONTH(?) = MONTH(date) AND YEAR(date) = YEAR(?)",nativeQuery = true)
    List<DateEntity> findAllByDate(Date date);

    @Query(value = "SELECT * FROM dates WHERE MONTH(?2) = MONTH(date) AND YEAR(date) = YEAR(?2) AND id_employee = ?1 AND justification=0 " +
            "AND TIMEDIFF(entry_time,\"09:00:00\") > 0 AND MINUTE(TIMEDIFF(entry_time,\"09:00:00\")) > 10  AND MINUTE(TIMEDIFF(entry_time,\"09:00:00\")) <= 25", nativeQuery = true)
    public List<DateEntity> getAbsencesLightEmployeeByMonth(String rut, Date date);

    @Query(value = "SELECT * FROM dates WHERE MONTH(?2) = MONTH(date) AND YEAR(date) = YEAR(?2) AND id_employee = ?1 AND justification=0 " +
            "AND TIMEDIFF(entry_time,\"09:00:00\") > 0 AND MINUTE(TIMEDIFF(entry_time,\"09:00:00\")) >25  AND MINUTE(TIMEDIFF(entry_time,\"09:00:00\")) <= 45", nativeQuery = true)
    public List<DateEntity> getAbsencesMiddleEmployeeByMonth(String rut, Date date);

    @Query(value = "SELECT * FROM dates WHERE MONTH(?2) = MONTH(date) AND YEAR(date) = YEAR(?2) AND id_employee = ?1 AND justification=0 " +
            "AND HOUR(entry_time) > 9", nativeQuery = true)
    public List<DateEntity> getAbsencesHighEmployeeByMonth(String rut, Date date);

    @Query(value = "SELECT * FROM dates WHERE MONTH(?2) = MONTH(date) AND YEAR(date) = YEAR(?2) AND id_employee = ?1 AND extra_hours>0 ", nativeQuery = true)
    public List<DateEntity> getExtraHoursByDate(String rut, Date date);
}
