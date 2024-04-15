package com.services.products.backend.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponse {
	
	private Long id;
	private String name;
}
