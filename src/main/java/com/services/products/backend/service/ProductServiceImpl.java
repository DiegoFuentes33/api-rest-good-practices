package com.services.products.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.services.products.backend.exceptions.CategoryNotFoundException;
import com.services.products.backend.exceptions.ProductNotFoundException;
import com.services.products.backend.mapper.ProductMapper;
import com.services.products.backend.model.dto.CreateProductRequest;
import com.services.products.backend.model.dto.ProductResponse;
import com.services.products.backend.model.entity.Product;
import com.services.products.backend.repository.CategoryRepository;
import com.services.products.backend.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	@Override
	public ProductResponse findById(Long id) {
		return productRepository.findById(id)
				.map(productMapper::toProductResponse)
				.orElseThrow(ProductNotFoundException::new);
	}

	@Override
	public List<ProductResponse> findAll() {
		return productRepository.findAll()
				.stream()
				.map(productMapper::toProductResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductResponse> findAllByCategoryId(Long categoryId) {
		return categoryRepository.findById(categoryId)
				.map(productRepository::findAllByCategory)
				.map(products -> products.stream()
						.map(productMapper::toProductResponse)
						.collect(Collectors.toList()))
				.orElseThrow(CategoryNotFoundException::new);
		
	}

	@Override
	public ProductResponse save(CreateProductRequest request) {
		return categoryRepository.findById(request.getCategoryId())
				.map(category -> {
					Product product = new Product();
					product.setName(request.getName());
					product.setDescription(request.getDescription());
					product.setPrice(request.getPrice());
					product.setCategory(category);
					product.setStatus(Boolean.TRUE);
					return productRepository.save(product);
				})
				.map(productMapper::toProductResponse)
				.orElseThrow(CategoryNotFoundException::new);
	}

	@Override
	public ProductResponse update(Long id, CreateProductRequest request) {
		return productRepository.findById(id)
				.map(product -> categoryRepository
						.findById(request.getCategoryId())
						.map(category -> {
							product.setName(request.getName());
							product.setDescription(request.getDescription());
							product.setPrice(request.getPrice());
							product.setCategory(category);
							 return productRepository.save(product);
						})
						.orElseThrow(CategoryNotFoundException::new))
				.map(productMapper::toProductResponse)
				.orElseThrow(ProductNotFoundException::new);
	}

	@Override
	public void deleteById(Long id) {
		if(productRepository.findById(id).isEmpty()) {
			throw new ProductNotFoundException();
		}
		productRepository.deleteById(id);
	}

}
