package top.chaser.framework.common.web.session;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * API服务
 *
 * @program:
 * @description:
 * @author: yangzb
 * @date 2021/5/14 11:02 上午
 **/
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class ApiResource extends Privilege {
    public ApiResource() {
        setType(Type.API);
    }

    /**
     * 服务方法
     */
    private String method;

    /**
     * 地址
     */
    private String uri;
}
