package com.mairie.reservation.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    Long id;
    private String startDate;
    private String startHour;
    private String endDate;
    private String endHour;
    private String manifestationName;
    private Integer nb;
    private String userType;
    private String userName;
    private String email;
    private Integer status = 1;//
    private Integer active = 0;// validated(1)  or rejected(-1)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room rooms;

    @OneToMany(targetEntity = Payment.class, mappedBy = "reservations",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //private List<Product> products;
    private Set<Payment> payments;
}
