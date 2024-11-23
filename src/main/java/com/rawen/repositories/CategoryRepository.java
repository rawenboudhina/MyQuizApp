package com.rawen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rawen.models.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

}