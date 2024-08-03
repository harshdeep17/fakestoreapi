package com.example.firstproject.exceptionHandlers;

import com.example.firstproject.dtos.CategoryNotFoundExceptionDto;
import com.example.firstproject.dtos.ExceptionDto;
import com.example.firstproject.dtos.ProductNotFoundExceptionDto;
import com.example.firstproject.exceptions.CategoryNotFoundException;
import com.example.firstproject.exceptions.ProductNotFoundException;
import com.example.firstproject.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(){
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setTimestamp(LocalDateTime.now());
        exceptionDto.setError("internal server error");
        exceptionDto.setMessage("can not be resolved");
        ResponseEntity<ExceptionDto> responseEntity=new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
        return responseEntity;
    }
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBoundsException(){
        ResponseEntity<Void> responseEntity=new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return responseEntity;
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ProductNotFoundExceptionDto productNotFoundExceptionDto=new ProductNotFoundExceptionDto();
        productNotFoundExceptionDto.setTimestamp(LocalDateTime.now());
        productNotFoundExceptionDto.setError("Not found");
        productNotFoundExceptionDto.setMessage(productNotFoundException.getMessage());
//        productNotFoundExceptionDto.setMessage("Product with id "+productNotFoundException.getId()+" not found");
        ResponseEntity<ProductNotFoundExceptionDto> responseEntity=new ResponseEntity<>(productNotFoundExceptionDto,HttpStatus.NOT_FOUND);
        return responseEntity;
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<CategoryNotFoundExceptionDto> handleCategoryNotFoundException(CategoryNotFoundException categoryNotFoundException){
        CategoryNotFoundExceptionDto categoryNotFoundExceptionDto=new CategoryNotFoundExceptionDto();
        categoryNotFoundExceptionDto.setTimestamp(LocalDateTime.now());
        categoryNotFoundExceptionDto.setError("Not found");
        categoryNotFoundExceptionDto.setMessage(categoryNotFoundException.getMessage());
//        categoryNotFoundExceptionDto.setMessage("Category with id "+categoryNotFoundException.getId()+" not found");
        ResponseEntity<CategoryNotFoundExceptionDto> responseEntity=new ResponseEntity<>(categoryNotFoundExceptionDto,HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
