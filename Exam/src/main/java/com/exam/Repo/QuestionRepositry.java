package com.exam.Repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.Models.Exam.Questions;
import com.exam.Models.Exam.Quiz;

public interface QuestionRepositry extends JpaRepository<Questions, Long> {

	Set<Questions> findByQuiz(Quiz quiz);

}
