package com.services.products.backend.mapper;

import org.mapstruct.Mapper;

import com.services.products.backend.model.dto.CategoryResponse;
import com.services.products.backend.model.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	CategoryResponse toCategoryReponse(Category category);
	
}
