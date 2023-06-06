package com.example.security.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String msg;

    @ManyToOne
    @JoinColumn(columnDefinition = "userId",referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;
}
