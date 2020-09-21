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
public class Room {
    @Id
    @GeneratedValue
    Long id;
    private String name;
    private String address;
    private Float minCapacity;
    private Float maxCapacity;
    private Float surface;
    private Float price;
    private Integer status = 1;// room libre
    private Integer active = 1;// room is active

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employees;

    @OneToMany(targetEntity = Material.class, mappedBy = "rooms",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Material> materials;

    @OneToMany(targetEntity = Reservation.class, mappedBy = "rooms",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Reservation> reservation;
}
