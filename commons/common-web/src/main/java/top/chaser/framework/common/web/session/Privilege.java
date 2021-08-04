package top.chaser.framework.common.web.session;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 权限
 *
 * @program:
 * @description:
 * @author: yangzb
 * @date 2021/5/14 11:02 上午
 **/
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, visible = true, property = "type", defaultImpl = Menu.class)
@JsonSubTypes({@JsonSubTypes.Type(value = Menu.class, name = "MENU"),
        @JsonSubTypes.Type(value = Function.class, name = "FUNC"),
        @JsonSubTypes.Type(value = ApiResource.class, name = "API")})
@Accessors(chain = true)
@EqualsAndHashCode
public class Privilege implements Serializable {
    private static final long serialVersionUID = 2179037393108205286L;
    /**
     * 菜单/功能项/接口标识
     */
    private String id;
    /**
     * 菜单/功能项/接口编码
     */
    private String code;
    /**
     * 菜单/功能项/接口名称
     */
    private String name;
    /**
     * 权限类型 菜单/功能项/接口
     */
    private Type type;

    private String systemCode;

    public enum Type {
        /**
         * 菜单
         */
        MENU,
        /**
         * 功能点
         */
        FUNC,
        /**
         * 接口
         */
        API;
    }
}
