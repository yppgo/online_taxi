package ypp.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class OrderDTO {
      private Long passengerId;

      private String passengerPhone;
      //出发地点
      private String address;
      //出发时间
      @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
      private LocalDateTime departTime;
      //订单创建时间
      @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
      private LocalDateTime orderTime;
      //出发地点
      private String departure;
      //出发地经度
      private String depLongitude;
      //出发地纬度
      private String depLatitude;
      //目的地
      private String destination;
      //目的地经度
      private String desLongitude;
      //目的地纬度
      private String desLatitude;
      //坐标加密标识
      private Integer encrypt;
      //运价类型编码
      private String fareType;
}
