package top.chaser.framework.common.base.exception;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * 业务异常，需要调用方处理的 返回http状态码200
 * @program:
 * @description: 业务异常
 * @author:
 * @create: 2019-03-08 16:59
 **/
@Getter
@Accessors(chain = true)
public class BusiException extends SystemException{
    public BusiException(@NonNull ErrorType errorType) {
        super(errorType);
    }

    public BusiException(@NonNull ErrorType errorType, String message) {
        super(errorType, message);
    }

    public BusiException(@NonNull ErrorType errorType, Throwable cause) {
        super(errorType, cause);
    }

    public BusiException(@NonNull ErrorType errorType, String message , Throwable cause) {
        super(errorType, message, cause);
    }
}
