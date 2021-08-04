package top.chaser.framework.common.web.session;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SessionUtil {
    public static User getCurrentUser() {
        User user = null;
        try {
            ClassUtil.loadClass("org.springframework.security.core.context.SecurityContextHolder");
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof User) {
                user = (User) principal;
            } else if (principal instanceof String) {
                user = new User().setUserCode(Convert.toStr(principal)).setNickname(Convert.toStr(principal));
            }
        } catch (Throwable e) {
            log.debug("获取用户登录信息失败", e);
        }
        return user;
    }
}
