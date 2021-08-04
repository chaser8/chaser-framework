package top.chaser.framework.common.web.session;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 菜单
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
public class Menu extends Privilege {
    public Menu() {
        setType(Type.MENU);
    }

    /**
     * 菜单排序
     */
    private Integer index;
    /**
     * 上级菜单标识
     */
    private String parentId;
    /**
     * 菜单层级
     */
    private Integer level;
    /**
     * 区域标识
     */
    private String regionId;

    /**
     * 地址
     */
    private String uri;
}
