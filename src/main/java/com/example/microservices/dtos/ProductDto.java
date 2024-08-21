package com.example.microservices.dtos;

import com.example.microservices.models.Category;
import com.example.microservices.models.Product;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private Long categoryId;  // Only include category ID and prevent circular dependency issues
    private String categoryTitle;
    private int quantity;
    public static ProductDto from(Product product){
        if(product==null)return null;
        ProductDto productDto=new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        productDto.setQuantity(product.getQuantity());
        Category category=product.getCategory();
        if(category!=null){

        if(category.getId()!=null){
            productDto.setCategoryId(category.getId());
        }
        if(category.getTitle()!=null){
            productDto.setCategoryTitle(category.getTitle());
        }
        }
        return productDto;
    }
}
