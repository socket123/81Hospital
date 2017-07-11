package com.chusu.apps.controller.business;

import com.chusu.apps.config.Constants;
import com.chusu.apps.controller.BaseAction;
import com.chusu.apps.model.business.User;
import com.chusu.apps.service.business.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LXX on 2017-06-17.
 */

@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseAction {
    @Autowired
    UserService userService;

    /**
     * Description: <br>
     * 用户列表
     *
     * @param model
     * @return <br>
     */
    @RequestMapping("/list")
    public String selectList(ModelMap model, User user) {
        model.put("page", userService.selectPageListByWhere(Constants.page.PAGE_NO, Constants.page.PAGE_SIZE, user));
        model.put("user", user);
        return "admin/business/user/user_list";
    }

    /**
     * Description: <br>
     * 前往修改或新增角色界面
     *
     * @param model
     * @param id
     * @return <br>
     */
    @RequestMapping("/mergepage")
    public String add(ModelMap model, Integer id) {
        User record = null;
        record.setCreateTime(new Date());
        if (id != null) {
            record = userService.selectByPrimaryKey(id);
        }
        model.put("record", record);
        return "admin/business/user/user_edit";
    }

    /**
     * Description: <br>
     * 修改
     *
     * @param model
     * @return <br>
     */
    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrUpdate(ModelMap model, User record) {
        int i = userService.updateSelective(record);
        return String.valueOf(i);
    }

    /**
     * Description: <br>
     * 删除
     *
     * @param id
     * @return <br>
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Integer id) {
        int i = userService.deleteById(id);
        return String.valueOf(i);
    }

}
