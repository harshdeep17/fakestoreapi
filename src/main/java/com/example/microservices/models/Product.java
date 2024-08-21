package com.example.microservices.models;


import com.example.microservices.dtos.ProductDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private double price;
    private String image;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
//    @JsonIgnore
//    @JsonManagedReference
    private Category category;
    private int quantity;
    public static Product from(ProductDto productDto){
        if(productDto==null)return null;
        Product product=new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        product.setQuantity(productDto.getQuantity());
        Category category=new Category();
        if(productDto.getCategoryId()!=null){
            category.setId(productDto.getCategoryId());
        }
        if(productDto.getCategoryTitle()!=null){
            category.setTitle(productDto.getCategoryTitle());
        }
        product.setCategory(category);
        return product;
    }
}
//@JoinColumn: Defines the foreign key column category_id in the Product table.
