package com.example.day28.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "name shouldn't be empty")
    @Column(columnDefinition = "varchar(20) not null")
    //@Size(min = 3)
    private String name;
    @Column(columnDefinition = "int not null")
    private int price;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<Order> order;
}

