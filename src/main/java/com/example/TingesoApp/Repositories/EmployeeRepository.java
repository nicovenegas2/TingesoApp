package com.example.TingesoApp.Repositories;


import com.example.TingesoApp.Entities.EmployeeEntity;
import com.example.TingesoApp.Entities.EmployeeResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, String> {

}
