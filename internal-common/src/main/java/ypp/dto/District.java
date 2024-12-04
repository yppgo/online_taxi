package ypp.dto;

import lombok.Data;

@Data
public class District {
    private String addressCode;

    private String addressName;

    private String parentAddressCode;

    private Integer level;
}
