package com.chusu.apps.controller.sys;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chusu.apps.config.Constants;
import com.chusu.apps.config.RespDto;
import com.chusu.apps.controller.BaseAction;
import com.chusu.apps.model.sys.SysAdmin;
import com.chusu.apps.model.sys.SysRole;
import com.chusu.apps.service.sys.AdminService;
import com.chusu.apps.service.sys.SysRoleService;

/**
 * <Description> <br>
 * 权限处理控制器
 * 
 * @author Administrator<br>
 * @CreateDate 2016年1月12日 <br>
 */
@Controller
@RequestMapping("/admin")
public class RoleController extends BaseAction {

    @Autowired
    AdminService adminService;

    @Autowired
    SysRoleService roleService;

    /**
     * Description: <br>
     * 页面首页
     * 
     * @param model
     * @return <br>
     */
    @RequestMapping("/index")
    public String index(ModelMap model) {
    	SysAdmin admin = (SysAdmin) session.getAttribute(Constants.key.BACKEND_USER_SESSION_KEY);
        model.put("oneMenu", adminService.selectRoleMenu(admin.getRoleId(), 0));
        model.put("admin", admin);
        return "/admin/index";
    }

    /**
     * Description: <br>
     * 左侧菜单
     * 
     * @param model
     * @param pid
     * @return <br>
     */
    @RequestMapping("/getmenu")
    public String getleftMenu(ModelMap model, Integer pid) {
        SysAdmin admin = (SysAdmin) request.getSession().getAttribute(Constants.key.BACKEND_USER_SESSION_KEY);
        model.put("secondMenu", adminService.selectSecondMenu(admin.getRoleId(), pid));
        return "admin/left_menu";
    }

    /**
     * Description: <br>
     * 角色列表
     * 
     * @param model
     * @param currentIndex
     * @param roleName
     * @return <br>
     */
    @RequestMapping("/role/list")
    public String selectRoleList(ModelMap model, Integer currentIndex, String roleName) {
    	SysRole role = new SysRole();
    	role.setRoleName(roleName);
    	currentIndex = (null == currentIndex)? Constants.page.PAGE_NO:currentIndex;
        model.put("page", roleService.selectPageListByWhere(currentIndex, Constants.page.PAGE_SIZE, role));
        model.put("roleName", roleName);
        return "admin/sys/role_list";
    }

    /**
     * Description: <br>
     * 前往修改或新增角色界面
     * 
     * @param model
     * @param id
     * @return <br>
     */
    @RequestMapping("/role/mergepage")
    public String add(ModelMap model, Integer id) {
        SysRole role = null;
        if (id != null) {
            role = roleService.selectByPrimaryKey(id);
        }
        model.put("role", role);
        return "admin/sys/role_edit";
    }

    /**
     * Description: <br>
     * 修改或新增
     * 
     * @param model
     * @return <br>
     */
    @RequestMapping(value = "/role/merge", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrUpdate(ModelMap model, SysRole role) {
        return roleService.saveOrUpdate(role);
    }

    /**
     * Description: <br>
     * 删除
     * 
     * @param model
     * @param id
     * @return <br>
     */
    @RequestMapping(value = "/role/del", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Integer id) {
    	roleService.delRoleMenu(id);
        return roleService.delete(id);
    }

    /**
     * Description: <br>
     * 跳转至调整菜单界面
     * 
     * @return <br>
     */
    @RequestMapping(value = "role/setMenu")
    public String toEditPage(ModelMap model, Integer id) {
        model.put("treeJson", roleService.selectCheckMenu(id));
        model.put("roleId", id);
        return "admin/sys/rolemenu_tree";
    }

    /**
     * Description: <br>
     * 保存角色菜单
     * 
     * @return <br>
     */
    @RequestMapping(value = "role/saveRoleMenu")
    @ResponseBody
    public String saveRoleMenu(ModelMap model, Integer roleId, String menuIds) {
        int count = 0;
        if (!StringUtils.isEmpty(menuIds) && null != roleId) {
            try {
                String[] menuId = menuIds.split(",");
                roleService.delRoleMenu(roleId);
                for (int i = 0; i < menuId.length; i++) {
                    roleService.saveRoleMenu(roleId, Integer.parseInt(menuId[i]));
                    count++;
                }
            } catch (Exception e) {
                roleService.delRoleMenu(roleId);
                count = -1;
            }
        }
        return count + "";
    }
}
