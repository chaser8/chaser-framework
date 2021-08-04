package top.chaser.framework.common.base.exception;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 业务异常，需要调用方处理的 返回http状态码200
 *
 * @program:
 * @description: 业务异常
 * @author:
 * @create: 2019-03-08 16:59
 **/
@Getter
@Accessors(chain = true)
public class CallApiException extends SystemException {
    protected Object response;

    public CallApiException() {
        super(SystemErrorType.INTF_ERROR);
    }

    public CallApiException(String message) {
        super(SystemErrorType.INTF_ERROR, message);
    }

    public CallApiException(Throwable cause) {
        super(SystemErrorType.INTF_ERROR, cause);
    }

    public CallApiException(String message , Throwable cause) {
        super(SystemErrorType.INTF_ERROR, message, cause);
    }
}
