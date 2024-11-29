package ypp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PassengerUser {
    private long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String passengerPhone;

    private String passengerName;

    private byte passengerGender;

    private  byte state;

    private  String profilePhoto;
}
