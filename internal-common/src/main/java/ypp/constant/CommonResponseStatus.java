package ypp.constant;


import lombok.Getter;

public enum CommonResponseStatus {
    VERFICARION_CODE_ERROR(1099,"验证码不正确"),
    TOKEN_ERROR(1199,"token错误"),
    SUCCESS(1,"success"),
    USER_NOT_EXIST(1200,"当前用户不存在"),
    PRICE_RULE_EMPTY(1300,"计价规则不存在"),
    FAIL(0,"fail");

    @Getter
    private int code;
    @Getter
    private String value,
    CommonResponseStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
