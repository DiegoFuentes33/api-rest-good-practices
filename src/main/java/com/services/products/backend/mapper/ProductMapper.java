package com.services.products.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.services.products.backend.model.dto.ProductResponse;
import com.services.products.backend.model.entity.Product;
import static com.services.products.backend.utils.Constants.ACTIVE_STATUS;
import static com.services.products.backend.utils.Constants.INACTIVE_STATUS;


@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {
	
	@Mapping(target = "status", expression = "java(mapStatus(product))")
	ProductResponse toProductResponse(Product product);
	
	default String mapStatus(Product product) {
		return product.getStatus() ? ACTIVE_STATUS : INACTIVE_STATUS;
	}
	
	
	
}
