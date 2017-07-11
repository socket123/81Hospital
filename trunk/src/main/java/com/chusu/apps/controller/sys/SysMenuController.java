package com.chusu.apps.controller.sys;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hdfs.web.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chusu.apps.config.Constants;
import com.chusu.apps.controller.BaseAction;
import com.chusu.apps.model.sys.SysMenu;
import com.chusu.apps.service.sys.SysMenuService;
import com.chusu.platform.util.GsonUtils;

/**
 * <Description> <br>
 * 权限处理控制器
 * 
 * @author Administrator<br>
 * @CreateDate 2016年1月12日 <br>
 */
@Controller
@RequestMapping("/admin/sysmenu")
public class SysMenuController extends BaseAction {

    @Autowired
    SysMenuService menuService;

    /**
     * Description: <br>
     * 角色列表
     * 
     * @param model
     * @param currentIndex
     * @param recordName
     * @return <br>
     */
    @RequestMapping("/list")
    public String selectRoleList(ModelMap model) {
        model.put("treeJson", menuService.selectTreeJson(0, 10000));
        return "admin/sys/sysmenu_list";
    }

    /**
     * Description: <br>
     * 角色列表
     * 
     * @param model
     * @param currentIndex
     * @param recordName
     * @return <br>
     */
    @RequestMapping("/pidlist")
    public String selectRoleList(ModelMap model, Integer pid) {
        pid = (null == pid) ? 0 : pid;
        SysMenu menu = menuService.selectByPrimaryKey(pid);
        
        SysMenu record = new SysMenu();
        record.setPid(menu.getPid());
        record.setMenuLevel(1);
        model.put("page", menuService.selectPageListByWhere(Constants.page.PAGE_NO, Constants.page.PAGE_SIZE, record));
        return "admin/sys/sysmenu_list";
    }

    /**
     * Description: <br>
     * 角色列表
     * 
     * @param model
     * @param currentIndex
     * @param recordName
     * @return <br>
     */
    @RequestMapping(value = "/levellist",produces=PRODUCES)
    @ResponseBody
    public String selectRoleList(Integer level) {
        List<SysMenu> menuList = menuService.selectSysMenuList(level);
        String result = GsonUtils.toJson(menuList);
        return result;
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
        SysMenu record = null;
        if (id != null) {
            record = menuService.selectByPrimaryKey(id);
        }
        List<SysMenu> memuList = new ArrayList<SysMenu>();
        if (null != record) {
            int level = record.getMenuLevel() - 1;
            memuList = menuService.selectSysMenuList((level <= 0) ? 1 : level);
        }
        model.put("record", record);
        model.put("memuList", memuList);
        return "admin/sys/sysmenu_edit";
    }

    /**
     * Description: <br>
     * 修改或新增
     * 
     * @param model
     * @return <br>
     */
    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrUpdate(ModelMap model, SysMenu record) {
        return menuService.saveOrUpdate(record);
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
        return menuService.delete(id);
    }
}
