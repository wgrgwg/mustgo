package com.mustgo.domain;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Restaurant {
    @Id @GeneratedValue
    @Column(name="restaurant_id")
    private Long id;

    private String name;

    private Category category; 
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Post> postList= new ArrayList<>();
}
