package com.example.demo.entity.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping({"/api","/admin"})
public class CategoryController {
@Autowired
CategoryService cs;

@GetMapping("/allcategories")
@PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_USER')")
public List<Category>getAllCategory() {
	return cs.getAllCategory();
	
}


@PreAuthorize("hasRole('ROLE_ADMIN')  ")
@PostMapping("/addcategory")
public  void addCategory(@RequestBody Category category) {
	cs.addCategory(category);
} 
	@GetMapping("/categoryid")
		public Category getCategoryById(@RequestParam Long id ) {
		return cs.getCategoryById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')  ")
@PutMapping("/updatecategory/{id}")
	public void updateCategory(@PathVariable("id") Long id,@RequestBody Category c) {
		 cs.updateCategory( id,c);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')  ")
@DeleteMapping("/deletecategory")
public void deleteCategory(@RequestParam Long id) {
	
	cs.deleteCategory(id);
}
}
