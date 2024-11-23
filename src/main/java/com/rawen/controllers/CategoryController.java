package com.rawen.controllers;



import org.springframework.web.bind.annotation.*;

import com.rawen.models.Category;
import com.rawen.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/add")
    public Category createCategory(@RequestBody Category category) {
        System.out.println("Category received: " + category.getName());
        return categoryService.createCategory(category);
    }
 // **Modification d'une catégorie**
    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    // **Suppression d'une catégorie**
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "Category deleted successfully!";
    }
    @GetMapping("/test")
    public String test() {
        return "Test successful!";
    }
    

}
