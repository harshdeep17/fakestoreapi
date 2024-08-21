package com.example.microservices.services;

import com.example.microservices.exceptions.CategoryNotFoundException;
import com.example.microservices.exceptions.ProductNotFoundException;
import com.example.microservices.models.Category;
import com.example.microservices.models.Product;
import com.example.microservices.repositories.CategoryRepository;
import com.example.microservices.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(!optionalProduct.isPresent()){
            throw new ProductNotFoundException(id,"Product with id " + id + " doesn't exists");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) throws CategoryNotFoundException {
        Category category=product.getCategory();
        if (category != null) {
            if (category.getId() == null) {
                // Attempt to find an existing category by title
                Optional<Category> existingCategory = categoryRepository.findByTitle(category.getTitle());
                if (existingCategory.isPresent()) {
                    // Use existing category if found
                    product.setCategory(existingCategory.get());
                } else {
                    // Save the new category
                    category = categoryRepository.save(category);
                    product.setCategory(category);
                }
            } else {
                // Check if category with given ID exists
                Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
                if (!optionalCategory.isPresent()) {
                    throw new CategoryNotFoundException(category.getId(), "Category with id " + category.getId() + " does not exist.");
                }
                product.setCategory(optionalCategory.get());
            }
        }
        return productRepository.save(product);
    }

    @Override
    public Product replaceProductById(Long id, Product product) {
        return null;
    }

    @Override
    public Product partialUpdateProductById(Long id, Product product) {
        return null;
    }

    @Override
    public Product deleteProductById(Long id) {
        return null;
    }
}
