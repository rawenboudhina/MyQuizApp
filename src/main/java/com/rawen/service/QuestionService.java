package com.rawen.service;

import com.rawen.models.Category;
import com.rawen.models.Question;
import com.rawen.repositories.CategoryRepository;
import com.rawen.repositories.QuestionRepsitory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepsitory questionRepository;
    private final CategoryRepository categoryRepository;  // Injection du CategoryRepository

    public QuestionService(QuestionRepsitory questionRepository, CategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Question> getQuestionsByCategory(Long categoryId) {
        return questionRepository.findByCategoryId(categoryId);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question question) {
        Optional<Question> existingQuestion = questionRepository.findById(id);
        if (existingQuestion.isPresent()) {
            Question questionToUpdate = existingQuestion.get();
            questionToUpdate.setText(question.getText());
            questionToUpdate.setOptions(question.getOptions());
            questionToUpdate.setCorrectAnswer(question.getCorrectAnswer());

            // Vérification et mise à jour de la catégorie
            if (question.getCategory() != null && question.getCategory().getId() != null) {
                Category category = categoryRepository.findById(question.getCategory().getId())
                        .orElseThrow(() -> new RuntimeException("Category not found"));
                questionToUpdate.setCategory(category);
            }

            return questionRepository.save(questionToUpdate);
        }
        return null;
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
