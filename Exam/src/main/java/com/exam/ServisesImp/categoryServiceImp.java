package com.exam.ServisesImp;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Models.Exam.Category;
import com.exam.Repo.CategoryRepositry;
import com.exam.Servises.CategoryService;

@Service
public class categoryServiceImp implements CategoryService {

	
	@Autowired
	private CategoryRepositry categoryRepositry;
	
	
	

	
	@Override
	public Category addCategory(Category category) {
		return this.categoryRepositry.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
	
		return this.categoryRepositry.save(category);
	}

	@Override
	public Set<Category> getCatagories() {
		
		return new LinkedHashSet<>(this.categoryRepositry.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) {
		
		return this.categoryRepositry.findById(categoryId).get();
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category = new Category();
		category.setCid(categoryId);
		
		this.categoryRepositry.delete(category);
		
	}

}
