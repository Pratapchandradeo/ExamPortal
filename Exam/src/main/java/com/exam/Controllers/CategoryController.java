package com.exam.Controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Exception.CategoryIdNotFoundException;
import com.exam.Models.Exam.Category;
import com.exam.Repo.CategoryRepositry;
import com.exam.ServisesImp.categoryServiceImp;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private categoryServiceImp categoryServiceImp;
	
	@Autowired
	private CategoryRepositry categoryRepositry;
	
	
	
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category)
	{
		
		Category category1 = this.categoryServiceImp.addCategory(category);
		
		return ResponseEntity.ok(category1);
	}
	
	
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable("categoryId") Long categoryId)
	{
		return this.categoryServiceImp.getCategory(categoryId);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> getCategorys()
	{
		return ResponseEntity.ok(this.categoryServiceImp.getCatagories());
	}
	
	
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) throws CategoryIdNotFoundException
	{
		
		
		return this.categoryServiceImp.updateCategory(category);
	}
	
	
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId)
	{
		this.categoryServiceImp.deleteCategory(categoryId);
	}
	

}
