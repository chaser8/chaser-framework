package top.chaser.framework.common.base.exception;


/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-17 09:09
 **/
public class AuthenticationException extends SystemException {
    public AuthenticationException() {
        super(SystemErrorType.AUTH_ERROR);
    }

    public AuthenticationException(String message) {
        super(SystemErrorType.AUTH_ERROR, message);
    }

    public AuthenticationException(Throwable cause) {
        super(SystemErrorType.AUTH_ERROR, cause);
    }

    public AuthenticationException(String message , Throwable cause) {
        super(SystemErrorType.AUTH_ERROR, message, cause);
    }
}
