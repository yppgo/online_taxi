package com.ypp.service_passenger_user.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PassengerUser {
    private long id;

    private LocalDateTime gmt_create;

    private LocalDateTime gmt_modified;

    private String passengerPhone;

    private String passengerName;

    private byte passengerGender;

    private  byte state;
}
