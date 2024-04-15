package com.services.products.backend.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

//En los DTO se hacen las validaciones.
@Getter
@Builder
public class CreateProductRequest {
	
	@NotEmpty(message = "The field name cannot be empty or null.")
	private String name;
	
	private String description;
	
	@NotNull(message = "The field price cannot be null.")
	private BigDecimal price;
	
	@NotNull(message = "The field category cannot be null")
	private Long categoryId;

}
