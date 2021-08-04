package top.chaser.framework.common.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import top.chaser.framework.common.base.exception.ErrorType;
import top.chaser.framework.common.base.exception.SystemErrorType;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: fishing-time-framework
 * @description:
 * @author:
 * @create: 2019-10-18 17:24
 **/
public class R<T> implements Serializable {
    private static final long serialVersionUID = 5657829410109424730L;

    /**
     * 消息id
     */
    protected String code;
    /**
     * 消息内容
     */
    protected String message;
    /**
     * 时间戳：Date 类型
     */
    @JsonFormat(pattern="yyyyMMddHHmmssSSS")
    protected Date time;

    @Setter
    @Getter
    @Accessors(chain = true)
    private String transactionId;

    /**
     * 返回数据
     */
    protected T data;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public T getData() {
        return data;
    }

    public R() {
        this.time = new Date();
    }

    protected R(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.time = new Date();
        this.data = data;
    }

    public static <T> R<T> success(T data){
        return new R(SystemErrorType.SUCCESS.getCode(), SystemErrorType.SUCCESS.getMessage(),data);
    }

    public static <T> R<T> success(){
        return new R(SystemErrorType.SUCCESS.getCode(), SystemErrorType.SUCCESS.getMessage(),null);
    }

    public static <T> R<T> fail(ErrorType errorType, T data){
        return new R(errorType.getCode(), errorType.getMessage(),data);
    }

    public static <T> R<T> fail(ErrorType errorType, String message, T data){
        return new R(errorType.getCode(),message,data);
    }

    public static <T> R<T> fail(ErrorType errorType, String message){
        return new R(errorType.getCode(),message,null);
    }
    public static <T> R<T> fail(ErrorType errorType){
        return new R(errorType.getCode(), errorType.getMessage(),null);
    }

    public static <T> R<T> fail(String message){
        return new R(SystemErrorType.SYSTEM_ERROR.getCode(),message,null);
    }
    public static <T> R<T> fail(){
        return new R(SystemErrorType.SYSTEM_ERROR.getCode(), SystemErrorType.SYSTEM_ERROR.getMessage(),null);
    }
}
