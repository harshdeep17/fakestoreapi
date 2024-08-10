package com.example.firstproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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
    @JsonManagedReference
    private Category category;
    private int quantity;


}
//@JoinColumn: Defines the foreign key column category_id in the Product table.
