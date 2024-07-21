package com.example.firstproject.controllers;

import com.example.firstproject.exceptions.ProductNotFoundException;
import com.example.firstproject.models.Product;
import com.example.firstproject.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        System.out.println("response");
        Product product=productService.getProductById(id);
        ResponseEntity<Product> response;
        if(product==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        response=new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }
    @GetMapping()
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        System.out.println("response...."+product);
        Product addedProduct = productService.addProduct(product);
        ResponseEntity response;
        if(addedProduct==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        response=new ResponseEntity<>(addedProduct, HttpStatus.OK);
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
