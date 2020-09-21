package com.mairie.reservation.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ReservationRequest {

    @NotNull
    private String startDate;

    @NotNull
    private String startHour;

    @NotNull
    private String endDate;

    @NotNull
    private String endHour;

    @NotNull
    private String manifestationName;

    @NotNull
    private Integer nb;

    @NotNull
    private String userType;

    @NotNull
    private String userName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private Long rooms;
}
