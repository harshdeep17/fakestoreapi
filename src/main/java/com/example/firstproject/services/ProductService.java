package com.example.firstproject.services;

import com.example.firstproject.exceptions.CategoryNotFoundException;
import com.example.firstproject.exceptions.ProductNotFoundException;
import com.example.firstproject.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProduct();
    Product createProduct(Product product) throws CategoryNotFoundException;
    Product replaceProductById(Long id,Product product);
    Product partialUpdateProductById(Long id,Product product);
    Product deleteProductById(Long id);
}
