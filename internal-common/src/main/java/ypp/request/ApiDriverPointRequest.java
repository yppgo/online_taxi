package ypp.request;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ApiDriverPointRequest {
    private Long carId;

    private PointDTO[] points;
}
