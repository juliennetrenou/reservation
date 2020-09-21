package com.mairie.reservation.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor

public class MaterialRequest {
    @NotNull
    private String reference;
    @NotNull
    private String name;

    @NotNull
    private Long rooms;
}
