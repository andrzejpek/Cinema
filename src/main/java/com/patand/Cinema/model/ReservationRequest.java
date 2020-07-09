package com.patand.Cinema.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {


    private String movieShowId;
    private String ticketId;
    private String UserId;

}
