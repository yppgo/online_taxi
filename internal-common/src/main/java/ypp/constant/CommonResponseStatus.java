package ypp.constant;


import lombok.Getter;

public enum CommonResponseStatus {
    VERFICARION_CODE_ERROR(1099,"验证码不正确"),
    SUCCESS(1,"success"),
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
