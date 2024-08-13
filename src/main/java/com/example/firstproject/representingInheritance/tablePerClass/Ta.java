package com.example.firstproject.representingInheritance.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tpc_ta")
public class Ta extends User {
    private int numberOfSessions;
    private double avgRating;
}
