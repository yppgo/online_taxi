package ypp.constant;


import lombok.Getter;

public enum CommonResponseStatus {
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
