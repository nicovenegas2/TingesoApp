package com.example.TingesoApp.Services;

import com.example.TingesoApp.Entities.CategoryEntity;
import com.example.TingesoApp.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ArrayList<CategoryEntity> getCategories(){
        return (ArrayList<CategoryEntity>) categoryRepository.findAll();
    }

    public boolean save(CategoryEntity category){
        try {
            categoryRepository.save(category);
            return true;
        } catch (Error e){
            return false;
        }
    }

    public CategoryEntity getById(Long id){
        CategoryEntity category = new CategoryEntity();
        try{
            category = categoryRepository.findById(id).get();
        }catch(Error e){
            System.out.println(e);
        }
        return category;
    }

    public HashMap<Long, Float> getSalaryPerHours(){
        HashMap<Long, Float> salaries = new HashMap<>();
        try{
            List<CategoryEntity> categories = (List<CategoryEntity>) categoryRepository.findAll();
            for (CategoryEntity category:categories){
                salaries.put(category.getId(),category.getSalaryPerHour());
            }
        }catch (Error e){
            return null;
        }
        return salaries;
    }

    public HashMap<Long, Float> getSalaryPerMonth(){
        HashMap<Long, Float> salaries = new HashMap<>();
        try{
            List<CategoryEntity> categories = (List<CategoryEntity>) categoryRepository.findAll();
            for (CategoryEntity category:categories){
                salaries.put(category.getId(),category.getSalaryPerMonth());
            }
        }catch (Error e){
            return null;
        }
        return salaries;
    }

    public HashMap<Long, String> getCategoryNames(){
        HashMap<Long, String> salaries = new HashMap<>();
        try{
            List<CategoryEntity> categories = (List<CategoryEntity>) categoryRepository.findAll();
            for (CategoryEntity category:categories){
                salaries.put(category.getId(),category.getName());
            }
        }catch (Error e){
            return null;
        }
        return salaries;
    }
}
