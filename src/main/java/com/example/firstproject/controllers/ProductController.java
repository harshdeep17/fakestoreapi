package com.example.firstproject.controllers;

import com.example.firstproject.config.Configuration;
import com.example.firstproject.exceptions.CategoryNotFoundException;
import com.example.firstproject.exceptions.ProductNotFoundException;
import com.example.firstproject.models.Limit;
import com.example.firstproject.models.Product;
import com.example.firstproject.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private Configuration configuration;
    public ProductController(@Qualifier("selfProductService") ProductService productService, Configuration configuration){
        this.productService=productService;
        this.configuration=configuration;
    }

    @GetMapping("/limits")
    public Limit getLimits(){
//        return new Limit(1,9999);
        return new Limit(configuration.getMinimum(), configuration.getMaximum());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
//        System.out.println("response");
        Product product=productService.getProductById(id);
        ResponseEntity<Product> response;
        if(product==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        response=new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> productList = productService.getAllProduct();
        ResponseEntity<List<Product>> response = new ResponseEntity<>(productList,HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws CategoryNotFoundException {
//        Product addedProduct = productService.addProduct(product);
//        ResponseEntity response;
//        if(addedProduct==null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        response=new ResponseEntity<>(addedProduct, HttpStatus.OK);
//        return response;
        Product newProduct = productService.createProduct(product);
        ResponseEntity response;
        response=new ResponseEntity<>(newProduct, HttpStatus.OK);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProductById(@PathVariable("id") Long id,@RequestBody Product product){
        System.out.println("response...."+id+" "+product);
        Product modifiedProduct = productService.replaceProductById(id,product);
        ResponseEntity response;
        if(modifiedProduct==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        response=new ResponseEntity<>(modifiedProduct, HttpStatus.OK);
        return response;
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Product> partialUpdateProductById(@PathVariable("id") Long id,@RequestBody Product product){
        Product partialUpdatedProduct = productService.partialUpdateProductById(id,product);
        System.out.println("response");
        ResponseEntity response;
        if(partialUpdatedProduct==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        response=new ResponseEntity<>(partialUpdatedProduct, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("id") Long id){
        Product deletedProduct = productService.deleteProductById(id);
        System.out.println("response");
        ResponseEntity response;
        if(deletedProduct==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        response=new ResponseEntity<>(deletedProduct, HttpStatus.OK);
        return response;
    }
    //create
    //update - patch (partial update)
    //delete
    //replace - put
}
