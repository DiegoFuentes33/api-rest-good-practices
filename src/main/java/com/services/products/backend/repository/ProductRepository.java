package com.services.products.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.products.backend.model.entity.Category;
import com.services.products.backend.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findAllByCategory(Category category);

}
