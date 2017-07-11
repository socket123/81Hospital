package com.chusu.apps.controller.sys;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chusu.apps.config.Constants;
import com.chusu.apps.config.ErrorCode;
import com.chusu.apps.controller.BaseAction;
import com.chusu.apps.model.sys.SysAdmin;
import com.chusu.apps.service.sys.AdminService;
import com.chusu.platform.util.MD5Utils;
import com.chusu.platform.util.StringUtil;

@Controller
@RequestMapping("/back")
public class LoginController extends BaseAction {

    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    public String login() {
        return "/admin/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        request.getSession().setAttribute(Constants.key.BACKEND_USER_SESSION_KEY, null);
        return "/admin/login";
    }

    @RequestMapping(value = "/loginajax")
    @ResponseBody
    public String login(String username, String password) {
        String code = ErrorCode.WARN[0];
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return code;
        }
        SysAdmin admin = adminService.selectAdminByName(username);
        password = MD5Utils.encode(password);
        if (null != admin && password.equals(admin.getPassword())) {
            if (!StringUtil.isEmpty(admin.getRelationId())) {
                String[] ids = admin.getRelationId().split(",");
                admin.setRelationIds(Arrays.asList(ids));
            }
            request.getSession().setAttribute(Constants.key.BACKEND_USER_SESSION_KEY, admin);
            code = ErrorCode.SUCCESS[0];
        }
        return code;
    }
}
