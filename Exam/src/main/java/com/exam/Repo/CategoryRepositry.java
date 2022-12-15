package com.exam.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.Models.Exam.Category;

public interface CategoryRepositry extends JpaRepository<Category, Long> {

}
