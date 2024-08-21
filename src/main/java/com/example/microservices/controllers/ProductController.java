package com.example.microservices.controllers;

import com.example.microservices.commons.AuthCommon;
import com.example.microservices.configs.Configuration;
import com.example.microservices.dtos.ProductDto;
import com.example.microservices.dtos.UserDto;
import com.example.microservices.exceptions.CategoryNotFoundException;
import com.example.microservices.exceptions.ProductNotFoundException;
import com.example.microservices.models.Limit;
import com.example.microservices.models.Product;
import com.example.microservices.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final Configuration configuration;
    private final AuthCommon authCommon;
    public ProductController(@Qualifier("selfProductService") ProductService productService,
                             Configuration configuration, AuthCommon authCommon){
        this.productService = productService;
        this.configuration = configuration;
        this.authCommon = authCommon;
    }
    @GetMapping("/token-info")
    public String getTokenInfo(@AuthenticationPrincipal Jwt jwt) {
        // Access scopes and roles from the JWT
        String scopes = jwt.getClaimAsStringList("scope") != null ? jwt.getClaimAsStringList("scope").toString() : "No scopes found";
        String roles = jwt.getClaimAsStringList("roles") != null ? jwt.getClaimAsStringList("roles").toString() : "No roles found";

        // Return or log the information
        return String.format("Scopes: %s, Roles: %s", scopes, roles);
    }
    @GetMapping("/limits")
    public Limit getLimits(){
//        return new Limit(1,9999);
        return new Limit(configuration.getMinimum(), configuration.getMaximum());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
//        System.out.println("response");
        ResponseEntity<ProductDto> response;
//        UserDto userDto = authCommon.validateToken(authToken);
//        if(userDto==null){
//            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
//        }
        //we can also do role based authorization
        Product product=productService.getProductById(id);
        if(product==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        response=new ResponseEntity<>(ProductDto.from(product),HttpStatus.OK);
        return response;
    }
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        List<Product> productList = productService.getAllProduct();
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product:productList){
            productDtoList.add(ProductDto.from(product));
        }
        ResponseEntity<List<ProductDto>> response = new ResponseEntity<>(productDtoList,HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws CategoryNotFoundException {
//        Product addedProduct = productService.addProduct(product);
//        ResponseEntity response;
//        if(addedProduct==null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        response=new ResponseEntity<>(addedProduct, HttpStatus.OK);
//        return response;
        Product product = Product.from(productDto);
        Product newProduct = productService.createProduct(product);
        ProductDto newProductDto = ProductDto.from(newProduct);
        ResponseEntity<ProductDto> response;
        response=new ResponseEntity<>(newProductDto, HttpStatus.OK);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProductById(@PathVariable("id") Long id,@RequestBody Product product){
        System.out.println("response...."+id+" "+product);
        Product modifiedProduct = productService.replaceProductById(id,product);
        ResponseEntity<Product> response;
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
        ResponseEntity<Product> response;
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
        ResponseEntity<Product> response;
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
