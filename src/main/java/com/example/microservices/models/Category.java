package com.example.microservices.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String title; //desc is reserved keyword in mysql we can't use
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true) // (fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "category") // (fetch = FetchType.EAGER)
//    @JsonBackReference
    private List<Product> products;
}
