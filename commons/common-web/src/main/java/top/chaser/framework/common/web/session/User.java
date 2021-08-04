package top.chaser.framework.common.web.session;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class User implements Serializable {
    private static final long serialVersionUID = 2179037393108205286L;
    /**
     * 用户标识
     */
    private String userId;
    /**
     * 员工标识
     */
    private String staffId;
    /**
     * 用户帐号
     */
    private String userCode;

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 手机号码
     */
    private String userTel;
    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户归属机构
     */
    private Organization org;

    /**
     * 用户的角色
     */
    private Set<Role> roles;

    /**
     * 用户拥有的权限
     */
    private Set<Privilege> privileges;

    /**
     * 密码连续错误次数
     */
    private int passwordErrorTimes;
    /**
     * 用户状态
     */
    private Status status;

    public enum Status {
        /**
         * 正常状态
         */
        NORMAL,
        /**
         * 未知状态
         */
        UNKNOWN,
        /**
         * 锁定状态
         */
        LOCKED;
    }
}
