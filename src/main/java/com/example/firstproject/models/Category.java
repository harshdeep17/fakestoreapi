package com.example.firstproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String title; //desc is reserved keyword in mysql we can't use
    @OneToMany(mappedBy = "category") // (fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Product> products;
}
