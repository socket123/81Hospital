package com.chusu.apps.service.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hdfs.web.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chusu.apps.dao.sys.SysRoleMapper;
import com.chusu.apps.dao.sys.SysRoleMenuMapper;
import com.chusu.apps.model.sys.SysMenu;
import com.chusu.apps.model.sys.SysRole;
import com.chusu.apps.model.sys.SysRoleMenu;
import com.chusu.apps.service.BaseService;
import com.chusu.platform.util.DateUtil;
import com.chusu.platform.util.GsonUtils;
import com.chusu.platform.util.Pager;
import com.chusu.platform.util.StringUtil;
import com.github.pagehelper.PageInfo;

@Service
public class SysRoleService extends BaseService<SysRole>{

    private final Logger logger = Logger.getLogger(SysMenuService.class);

    @Autowired
    SysRoleMapper roleDao;

    @Autowired
    SysRoleMenuMapper roleMenuDao;

    @Autowired
    SysMenuService menuService;


    /**
     * 修改或保存
     */
    public String saveOrUpdate(SysRole role) {
        int count = 0;
        if (role == null) {
            count = -1;
        } else if (null == role.getId()) {
        	role.setCreateTime(new Date());
            count = roleDao.insertSelective(role);
        } else {
            count = roleDao.updateByPrimaryKeySelective(role);
        }
        return count + "";
    }

    /**
     * 根据id删除
     */
    public String delete(Integer id) {
        int count = 0;
        if (id == null) {
            count = -1;
        } else {
            count = roleDao.deleteByPrimaryKey(id);
        }
        return count + "";
    }

    /**
     * Description: <br>
     * 查询出所有已分配菜单，封装zTree勾选
     * 
     * @param roleName
     * @param pageNo
     * @return <br>
     */
    public String selectCheckMenu(Integer roleId) {
        String json = "";
        logger.info("根据角色id获得菜单,roleId=" + roleId);
        if (roleId != null) {
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("offset", 0);
            queryMap.put("size", 10000);
            List<SysMenu> menuList = menuService.selectAll();

            List<Integer> rmList = roleMenuDao.selectByRoleId(roleId);

            if (StringUtil.listEmpty(rmList)) {
                logger.info("没有菜单，不进行check封装");
                json = menuService.getTreeJson(menuList);
            } else {
                logger.info("开始进行check封装");
                // 封装treeList
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                for (SysMenu menu : menuList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", menu.getId());
                    map.put("pId", menu.getPid());
                    map.put("name", menu.getMenuName());
                    if (rmList.contains(menu.getId())) {
                        map.put("checked", "true");
                    }
                    list.add(map);
                }
                json = GsonUtils.toJson(list);
            }
        }

        return json;
    }

    /**
     * Description: <br>
     * 保存菜单角色关联关系
     * 
     * @param roleId
     * @param menuId
     * @return <br>
     */
    public int saveRoleMenu(Integer roleId, Integer menuId) {
        SysRoleMenu record = new SysRoleMenu();
        record.setMenuId(menuId);
        record.setRoleId(roleId);
        return roleMenuDao.insertSelective(record);
    }

    /**
     * Description: <br>
     * 根据角色删除
     * 
     * @param roleId
     * @return <br>
     */
    public String delRoleMenu(Integer roleId) {
        int count = 0;
        if (roleId == null) {
            count = -1;
        } else {
        	SysRoleMenu menu = new SysRoleMenu();
        	menu.setRoleId(roleId);
            count = roleMenuDao.delete(menu);
        }
        return count + "";
    }
}
