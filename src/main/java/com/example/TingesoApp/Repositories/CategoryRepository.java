package com.example.TingesoApp.Repositories;

import com.example.TingesoApp.Entities.CategoryEntity;
import com.example.TingesoApp.Entities.DateEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {


}
