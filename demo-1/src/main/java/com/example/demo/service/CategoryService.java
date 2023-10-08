package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

@Service

public class CategoryService {
@Autowired 
CategoryRepository cr;
public List<Category> getAllCategory(){
	return cr.findAll();
}
public void addCategory(Category category) {
	cr.save(category);
}
public  Category getCategoryById(Long id) {
	return cr.findById(id).get();
}
public void updateCategory(Long id, Category category) {
   category.setId(id);
cr.save(category);

}
public void deleteCategory(Long id) {
	cr.deleteById(id);
}
}