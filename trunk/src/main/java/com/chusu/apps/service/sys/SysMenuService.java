package com.chusu.apps.service.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chusu.apps.dao.sys.SysMenuMapper;
import com.chusu.apps.model.sys.SysMenu;
import com.chusu.apps.service.BaseService;
import com.chusu.platform.util.GsonUtils;
import com.chusu.platform.util.StringUtil;

@Service
public class SysMenuService extends BaseService<SysMenu>{

    @Autowired
    SysMenuMapper menuDao;

    /**
     * Description: <br>
     * 查询封装菜单树
     * 
     * @return <br>
     */
    public String selectTreeJson(Integer offset, Integer size) {
        List<SysMenu> menuList = super.selectAll();
        return getTreeJson(menuList);
    }
    /**
     * Description: <br>
     * 根据级别查询
     * 
     * @param level
     * @return <br>
     */
    public List<SysMenu> selectSysMenuList(Integer level) {
        // 默认为1级菜单
        SysMenu record = new SysMenu();
        record.setMenuLevel(level);
        return menuDao.select(record);
    }

    /**
     * 修改或保存
     */
    public String saveOrUpdate(SysMenu record) {
        int count = 0;
        if (record.getId() == null) {
            record.setCreateTime(new Date());
            count = menuDao.insertSelective(record);
        } else {
            count = menuDao.updateByPrimaryKeySelective(record);
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
            List<SysMenu> menuList = menuDao.selectSysMenuByPid(id);
            if (!StringUtil.listEmpty(menuList)) {
                count = -2;
            } else {
                count = menuDao.deleteByPrimaryKey(id);
            }
        }
        return count + "";
    }

    public String getTreeJson(List<SysMenu> menuList) {
        String json = "";
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (!StringUtil.listEmpty(menuList)) {
            for (SysMenu menu : menuList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", menu.getId());
                map.put("pId", menu.getPid());
                map.put("name", menu.getMenuName());
                list.add(map);
            }
            json = GsonUtils.toJson(list);
        }
        return json;
    }
}
