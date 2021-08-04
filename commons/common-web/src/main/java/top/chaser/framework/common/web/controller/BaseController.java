package top.chaser.framework.common.web.controller;

import top.chaser.framework.common.web.session.SessionUtil;
import top.chaser.framework.common.web.session.User;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-03-22 09:10
 **/
public class BaseController {
    public User getCurrentUser() {
        return SessionUtil.getCurrentUser();
    }
}
