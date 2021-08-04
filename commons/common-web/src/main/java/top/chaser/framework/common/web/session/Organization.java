package top.chaser.framework.common.web.session;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 组织机构
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
public class Organization {
    /**
     * 机构标识
     */
    private String id;
    /**
     * 机构编码
     */
    private String code;
    /**
     * 机构名称
     */
    private String name;

    /**
     * 上级机构标识
     */
    private String parentId;

    /**
     * 机构层级
     */
    private Integer level;

}
