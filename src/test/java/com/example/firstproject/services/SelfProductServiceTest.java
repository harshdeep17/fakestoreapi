package com.example.firstproject.services;

import com.example.firstproject.exceptions.CategoryNotFoundException;
import com.example.firstproject.exceptions.ProductNotFoundException;
import com.example.firstproject.models.Category;
import com.example.firstproject.models.Product;
import com.example.firstproject.repositories.CategoryRepository;
import com.example.firstproject.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SelfProductServiceTest {
    @Autowired
    ProductService selfProductService;
    @MockBean
    ProductRepository mockProductRepository;
    @MockBean
    CategoryRepository mockCategoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById_WhenProductExists() throws ProductNotFoundException {
        //Arrange
        Long productId=1L;
        Product product=new Product();
        when(mockProductRepository.findById(productId)).thenReturn(Optional.of(product));

        //Act
        Product actualProduct = selfProductService.getProductById(productId);

        //Assert
        assertEquals(product,actualProduct);
        verify(mockProductRepository,times(1)).findById(productId);
    }
    @Test
    void testGetProductById_WhenProductNotExists() {
        //Arrange
        Long productId=1L;
        when(mockProductRepository.findById(productId)).thenReturn(Optional.empty());

        //Assert && Act
//        assertThatThrownBy(() -> selfProductService.getProductById(productId))
//                .isInstanceOf(ProductNotFoundException.class)
//                .hasMessageContaining("Product with id " + productId + " doesn't exists");
        ProductNotFoundException thrown=assertThrows(ProductNotFoundException.class,()->selfProductService.getProductById(productId));
        // Additional assertions on the exception
        assertEquals("Product with id " + productId + " doesn't exists",thrown.getMessage());
        verify(mockProductRepository,times(1)).findById(productId);
    }
    @Test
    void testGetAllProduct() {
        //Arrange
        List<Product> expectedProductList = List.of(new Product(),new Product());
        when(mockProductRepository.findAll()).thenReturn(expectedProductList);
        //Act
        List<Product> actualProductList=selfProductService.getAllProduct();
        //Assert
        assertEquals(expectedProductList,actualProductList);
        verify(mockProductRepository, times(1)).findAll();
    }
    @Test
    void testCreateProduct_WhenCategoryExists() throws CategoryNotFoundException {
        //Arrange
        Long categoryId=1L;
        Category category = new Category();
        category.setId(categoryId);
        Product product=new Product();
        product.setCategory(category);
        when(mockCategoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(mockProductRepository.save(any(Product.class))).thenReturn(product);

        // Act
        Product savedProduct = selfProductService.createProduct(product);

        // Assert
        assertEquals(product, savedProduct);
        verify(mockCategoryRepository, times(1)).findById(category.getId());
        verify(mockProductRepository, times(1)).save(product);
    }
    @Test
    void testCreateProduct_WhenCategoryNotExists() {
        //Arrange
        Long categoryId=1L;
        Category category = new Category();
        category.setId(categoryId);
        Product product=new Product();
        product.setCategory(category);
        when(mockCategoryRepository.findById(category.getId())).thenReturn(Optional.empty());

        //Assert && Act
        CategoryNotFoundException thrown=assertThrows(CategoryNotFoundException.class,()->selfProductService.createProduct(product));
        // Additional assertions on the exception
        assertEquals("Category with id "+categoryId+" does not exists.",thrown.getMessage());
        verify(mockCategoryRepository, times(1)).findById(category.getId());
        verify(mockProductRepository, never()).save(product);
    }

    @Test
    void testReplaceProductById() {
    }

    @Test
    void testPartialUpdateProductById() {
    }

    @Test
    void testDeleteProductById() {
    }
}