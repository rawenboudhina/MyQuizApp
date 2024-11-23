package com.rawen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rawen.models.Category;
import com.rawen.models.Question;
import com.rawen.repositories.CategoryRepository;
import com.rawen.repositories.QuestionRepsitory;
import com.rawen.service.QuestionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepsitory questionRepsitory; // Injecter le repository avec @Autowired

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category/{categoryId}")
    public List<Question> getQuestionsByCategory(@PathVariable Long categoryId) {
        return questionService.getQuestionsByCategory(categoryId);
    }

    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PutMapping("/update/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        Optional<Question> existingQuestion = questionRepsitory.findById(id);
        if (existingQuestion.isPresent()) {
            Question questionToUpdate = existingQuestion.get();

            questionToUpdate.setText(question.getText());
            questionToUpdate.setOptions(question.getOptions());
            questionToUpdate.setCorrectAnswer(question.getCorrectAnswer());

            if (question.getCategory() != null && question.getCategory().getId() != null) {
                Category category = categoryRepository.findById(question.getCategory().getId())
                        .orElseThrow(() -> new RuntimeException("Category not found"));
                questionToUpdate.setCategory(category);
            }

            return questionRepsitory.save(questionToUpdate);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return "Question deleted successfully!";
    }
}
