package com.services.products.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.products.backend.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
