package com.rawen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        try {
            Question addedQuestion = questionService.addQuestion(question);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedQuestion);  // Retourne la question ajoutée avec un code 201
        } catch (RuntimeException e) {
            // Si une erreur se produit (ex. catégorie inexistante), retourner un code 400 avec le message d'erreur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
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
