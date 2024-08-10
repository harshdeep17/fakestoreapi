package com.example.firstproject.services;

import com.example.firstproject.exceptions.CategoryNotFoundException;
import com.example.firstproject.exceptions.ProductNotFoundException;
import com.example.firstproject.models.Category;
import com.example.firstproject.models.Product;
import com.example.firstproject.repositories.CategoryRepository;
import com.example.firstproject.repositories.ProductRepository;
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
        if(category.getId()==null){
//            category=categoryRepository.save(category);
            product.setCategory(category);
        }else{
            //check for invalid id
            Long id=category.getId();
            Optional<Category> optionalCategory = categoryRepository.findById(id);
            if(!optionalCategory.isPresent()){
                throw new CategoryNotFoundException(id,"Category with id "+id+" does not exists.");
            }
            product.setCategory(optionalCategory.get());
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
