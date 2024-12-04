package ypp.constant;


import lombok.Getter;

public enum CommonResponseStatus {
    VERFICARION_CODE_ERROR(1099,"验证码不正确"),
    TOKEN_ERROR(1199,"token错误"),
    SUCCESS(1,"success"),
    USER_NOT_EXIST(1200,"当前用户不存在"),
    PRICE_RULE_EMPTY(1300,"计价规则不存在"),
    MAP_DISTRIT_ERROR(1300,"地图请求错误"),

    DRIVER_CAR_BIND_NOT_EXISIT(1500,"司机和车辆绑定关系不存在"),
    DRIVER_NOT_EXIST(1501,"司机不存在"),
    DRIVER_CAR_BIND_EXISTS(1502,"司机和车辆关系已绑定，请勿重复绑定"),
    DRIVER_BIND_EXISTS(1503,"司机已被绑定，请勿重复绑定"),
    CAR_BIND_EXISTS(1504,"车辆已被绑定，请勿重复绑定"),
    FAIL(0,"fail");

    @Getter
    private int code;
    @Getter
    private String value;
    CommonResponseStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
