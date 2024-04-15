package com.services.products.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.services.products.backend.exceptions.CategoryNotFoundException;
import com.services.products.backend.exceptions.ProductNotFoundException;
import com.services.products.backend.model.dto.ErrorResponse;

import static com.services.products.backend.utils.ErrorCatalog.PRODUCT_NOT_FOUND;
import static com.services.products.backend.utils.ErrorCatalog.CATEGORY_NOT_FOUND;
import static com.services.products.backend.utils.ErrorCatalog.INVALID_PRODUCT;
import static com.services.products.backend.utils.ErrorCatalog.GENERIC_ERROR;




import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ProductNotFoundException.class)
	public ErrorResponse handleProductNotFoundException() {
		return ErrorResponse.builder()
				.code(PRODUCT_NOT_FOUND.getCode())
				.status(HttpStatus.NOT_FOUND)
				.message(PRODUCT_NOT_FOUND.getMessage())
				.timeStamp(LocalDateTime.now())
				.build();
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CategoryNotFoundException.class)
	public ErrorResponse handleCategoryNotFoundException() {
		return ErrorResponse.builder()
				.code(CATEGORY_NOT_FOUND.getCode())
				.status(HttpStatus.NOT_FOUND)
				.message(CATEGORY_NOT_FOUND.getMessage())
				.timeStamp(LocalDateTime.now())
				.build();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		
		BindingResult result = exception.getBindingResult();
		
		return ErrorResponse.builder()
				.code(INVALID_PRODUCT.getCode())
				.status(HttpStatus.BAD_REQUEST)
				.message(INVALID_PRODUCT.getMessage())
				.detailMessages(result.getFieldErrors()
						.stream()
						.map(fieldError -> fieldError.getDefaultMessage())
						.collect(Collectors.toList()))
				.timeStamp(LocalDateTime.now())
				.build();
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleInternalServerError(Exception exception) {
		return ErrorResponse.builder()
				.code(GENERIC_ERROR.getCode())
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.message(GENERIC_ERROR.getMessage())
				.detailMessages(Collections.singletonList(exception.getMessage()))
				.timeStamp(LocalDateTime.now())
				.build();
	}
	
}
