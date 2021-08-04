package top.chaser.framework.common.base.exception;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 系统级异常，返回http状态码 500
 *
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-30 11:28
 **/
@Getter
@Setter
public class SystemException extends RuntimeException {
    protected ErrorType errorType = null;

    public SystemException(@NonNull ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public SystemException(@NonNull ErrorType errorType, String message) {
        super(StrUtil.isNotBlank(message) ? message : errorType.getMessage());
        this.errorType = errorType.setMessage(message);
    }

    public SystemException(@NonNull ErrorType errorType, Throwable cause) {
        super(errorType.getMessage(), cause);
        this.errorType = errorType;
    }

    public SystemException(@NonNull ErrorType errorType, String message, Throwable cause) {
        super(StrUtil.isNotBlank(message) ? message : errorType.getMessage(), cause);
        this.errorType = errorType.setMessage(message);
    }
}
