package ypp.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ypp.constant.CommonResponseStatus;

@Data
@Accessors(chain = true)//生成链式setter方法
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;
    //创建一个无参数方法
    public static <T> ResponseResult success(){
        return new ResponseResult().setCode(CommonResponseStatus.SUCCESS.getCode()).setMessage(CommonResponseStatus.SUCCESS.getValue());
    }
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonResponseStatus.SUCCESS.getCode()).setMessage(CommonResponseStatus.SUCCESS.getValue()).setData(data);
    }
    public static <T> ResponseResult fail(T data){
        return new ResponseResult().setData(data);
    }
    public static ResponseResult fail(int code,String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }
}
