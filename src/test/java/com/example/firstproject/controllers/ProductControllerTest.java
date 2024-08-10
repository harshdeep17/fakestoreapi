package com.example.firstproject.controllers;

import com.example.firstproject.config.Configuration;
import com.example.firstproject.exceptions.CategoryNotFoundException;
import com.example.firstproject.exceptions.ProductNotFoundException;
import com.example.firstproject.models.Limit;
import com.example.firstproject.models.Product;
import com.example.firstproject.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    ProductController productController;
    @MockBean
    ProductService mockProductService;
    @MockBean
    private Configuration configuration;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetLimits(){
        when(configuration.getMinimum()).thenReturn(1);
        when(configuration.getMaximum()).thenReturn(1999);
        Limit limit = productController.getLimits();
        assertEquals(1,limit.getMinimum());
        assertEquals(1999,limit.getMaximum());
    }
    @Test
    void testGetProductById_WhenProductNotNull() throws ProductNotFoundException {
        Long productId = 1L;
        Product product=new Product();
        product.setId(productId);
        when(mockProductService.getProductById(productId)).thenReturn(product);
        ResponseEntity<Product> response = productController.getProductById(productId);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(product,response.getBody());

    }
    @Test
    void testGetProductById_WhenProductIsNull() throws ProductNotFoundException {
        //Arrange
        Long productId=1L;
        when(mockProductService.getProductById(productId)).thenReturn(null);

        //Act
        ResponseEntity<Product> response = productController.getProductById(productId);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    void testGetProductById_WhenProductDoesNotExist() throws ProductNotFoundException {
        // Arrange
        Long productId = 1L;
        when(mockProductService.getProductById(productId)).thenThrow(new ProductNotFoundException(productId, "Product with id " + productId + " doesn't exist"));

        // Act & Assert
        ProductNotFoundException thrown = assertThrows(ProductNotFoundException.class,() -> productController.getProductById(productId));
        assertEquals("Product with id " + productId + " doesn't exist",thrown.getMessage());
        verify(mockProductService, times(1)).getProductById(productId);
    }
    @Test
    void testGetAllProduct(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        when(mockProductService.getAllProduct()).thenReturn(productList);
        ResponseEntity<List<Product>> response = productController.getAllProduct();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(productList,response.getBody());
    }
    @Test
    void testCreateProduct() throws CategoryNotFoundException {
        Product product=new Product();
        when(mockProductService.createProduct(product)).thenReturn(product);
        ResponseEntity<Product> response = productController.createProduct(product);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(product,response.getBody());
    }

    @Test
    void testReplaceProductById_ShouldReturnModifiedProduct(){
        Long productId = 1L;
        Product product = new Product();
        when(mockProductService.replaceProductById(productId,product)).thenReturn(product);
        ResponseEntity<Product> response = productController.replaceProductById(productId,product);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(product,response.getBody());
    }
    @Test
    void testReplaceProductById_WhenModifiedProductIsNull() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(mockProductService.replaceProductById(productId,product)).thenReturn(null);

        // Act
        ResponseEntity<Product> response = productController.replaceProductById(productId, product);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    void testPartialUpdateProductById_ShouldReturnPartialUpdatedProduct(){
        Long productId = 1L;
        Product product = new Product();
        when(mockProductService.partialUpdateProductById(productId,product)).thenReturn(product);
        ResponseEntity<Product> response = productController.partialUpdateProductById(productId,product);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(product,response.getBody());
    }
    @Test
    void testPartialUpdateProductById_WhenPartialUpdatedProductIsNull() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(mockProductService.partialUpdateProductById(productId, product)).thenReturn(null);

        // Act
        ResponseEntity<Product> response = productController.partialUpdateProductById(productId, product);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    void testDeleteProductById_ShouldReturnDeletedProduct(){
        Long productId = 1L;
        Product product = new Product();
        when(mockProductService.deleteProductById(productId)).thenReturn(product);
        ResponseEntity<Product> response = productController.deleteProductById(productId);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(product,response.getBody());
    }
    @Test
    void testDeleteProductById_WhenDeletedProductIsNull() {
        // Arrange
        Long productId = 1L;
        when(mockProductService.deleteProductById(productId)).thenReturn(null);

        // Act
        ResponseEntity<Product> response = productController.deleteProductById(productId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertNull(response.getBody());
    }
}
//3A
//Arrange
//Act
//Assert
