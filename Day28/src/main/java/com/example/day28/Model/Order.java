package com.example.day28.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MyOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null")
    private int quantity;

    @Column(columnDefinition = "int not null")
    private int totalPrice;

    @NotNull(message = "date shouldn't be empty")
    @Column(columnDefinition = "date")
    private Date dateReceived;

    @NotNull(message = "status shouldn't be empty")
    @Pattern(regexp = "^(new|inProgress|completed)$")
    private String status;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "productId",referencedColumnName = "id")
    @JsonIgnore
    private Product product;
}
