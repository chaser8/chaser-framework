package top.chaser.framework.common.web.session;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 功能点
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
public class Function extends Privilege {
    public Function(){
        setType(Type.FUNC);
    }
    /**
     * 所属菜单标识
     */
    private String menuId;
}
