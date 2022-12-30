package com.exam.Servises;

import java.util.Set;

import com.exam.Models.Exam.Questions;
import com.exam.Models.Exam.Quiz;

public interface QuestionService {

	public Questions addQuestion(Questions questions);
	
	public Questions updateQuestions(Questions questions);
	
	public Set<Questions> getQuestions();
	
	public Questions getQuestion(Long QuestionId);
	
	public void deleteQuestion(Long QuestionId);
	
	public Set<Questions> getQuestionsOfQuiz(Quiz quiz);
	
	public Questions getData(Long qid);
	
}
