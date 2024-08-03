package com.example.firstproject.representingInheritance.mappedSuperClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="msc_mentor")
public class Mentor extends User{
    private double mentorRating;
}
