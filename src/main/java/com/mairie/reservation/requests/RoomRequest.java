package com.mairie.reservation.requests;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor

public class RoomRequest {

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private Float minCapacity;

    @NotNull
    private Float maxCapacity;

    @NotNull
    private Float surface;

    @NotNull
    private Float price;

    @NotNull
    private Long employees;
}
