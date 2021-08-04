package top.chaser.framework.common.base.exception;

import lombok.Getter;

@Getter
public class SystemErrorType implements ErrorType {
    public static final SystemErrorType SUCCESS = new SystemErrorType("0000", "success");
    public static final SystemErrorType NOT_EXIST_ERROR = new SystemErrorType("1001", "does not exist");
    public static final SystemErrorType DATA_REPEAT_ERROR = new SystemErrorType("1002", "data duplication");
    public static final SystemErrorType DUPLICATE_PRIMARY_KEY = new SystemErrorType("1003", "primary key duplication");
    public static final SystemErrorType THIRD_INTF_ERROR = new SystemErrorType("1004", "third party interface error");
    public static final SystemErrorType INTF_ERROR = new SystemErrorType("1005", "call interface error");
    public static final SystemErrorType SIGN_ERROR = new SystemErrorType("2002", "signature error");
    public static final SystemErrorType FORBIDDEN_ERROR = new SystemErrorType("2003", "access denied");
    public static final SystemErrorType AUTH_ERROR = new SystemErrorType("2004", "authentication error");

    public static SystemErrorType SYSTEM_ERROR = new SystemErrorType("9000", "system error");

    protected String code;
    protected String message;

    protected SystemErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public ErrorType setMessage(String message) {
        return new SystemErrorType(this.code,message);
    }
}
