package top.chaser.framework.common.web.exception;

import lombok.Getter;
import lombok.experimental.Accessors;
import top.chaser.framework.common.base.exception.SystemErrorType;

/**
 * @program:
 * @description:
 * @create: 2019-08-26 17:14
 **/
@Getter
@Accessors(chain = true)
public class WebErrorType extends SystemErrorType {
    public static final WebErrorType PARAM_ERROR = new WebErrorType("2001", "invalid request, request parameters are incorrect");
    public static final WebErrorType UPLOAD_FILE_SIZE_LIMIT = new WebErrorType("2005", "file size exceeds limit");
    public static final WebErrorType REQUEST_BODY_READ_ERROR = new WebErrorType("2006", "read body error");
    public static final WebErrorType MEDIA_TYPE_NOT_SUPPORTED = new WebErrorType("2006", "Content type not supportedr");

    protected WebErrorType(String code, String message) {
        super(code,message);
    }
}
