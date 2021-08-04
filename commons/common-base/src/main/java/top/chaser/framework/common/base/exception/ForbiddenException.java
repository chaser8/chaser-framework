package top.chaser.framework.common.base.exception;


/**
 *
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-17 09:29
 **/
public class ForbiddenException extends SystemException {
    public ForbiddenException() {
        super(SystemErrorType.FORBIDDEN_ERROR);
    }

    public ForbiddenException(String message) {
        super(SystemErrorType.FORBIDDEN_ERROR, message);
    }

    public ForbiddenException(Throwable cause) {
        super(SystemErrorType.FORBIDDEN_ERROR, cause);
    }

    public ForbiddenException( String message , Throwable cause) {
        super(SystemErrorType.FORBIDDEN_ERROR, message, cause);
    }
}
