package com.mairie.reservation.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue
    Long id;
    private String name;
    private String firstName;
    private String address;
    private String phoneNumber;
    private String login;
    private String password;
    private String profile;
    private Integer deleteBy = 1;//

    @OneToMany(targetEntity = Room.class, mappedBy = "employees",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //private List<Product> products;
    private Set<Room> rooms;
}
