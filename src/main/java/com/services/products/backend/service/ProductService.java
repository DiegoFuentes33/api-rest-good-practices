package com.services.products.backend.service;

import java.util.List;

import com.services.products.backend.model.dto.CreateProductRequest;
import com.services.products.backend.model.dto.ProductResponse;

public interface ProductService {
	
	ProductResponse findById(Long id);
	List<ProductResponse> findAll();
	List<ProductResponse> findAllByCategoryId(Long categoryId);
	ProductResponse save(CreateProductRequest request);
	ProductResponse update(Long id, CreateProductRequest request);
	void deleteById(Long id);
	
}
