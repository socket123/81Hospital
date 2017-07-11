package com.chusu.apps.service.sys;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chusu.apps.dao.sys.SysAdminMapper;
import com.chusu.apps.model.sys.SysAdmin;
import com.chusu.platform.util.MD5Utils;
import com.chusu.platform.util.Pager;
import com.chusu.platform.util.StringUtil;

@Service
public class AdminService {

    private final Logger logger = Logger.getLogger(AdminService.class);

    @Autowired
    SysAdminMapper adminDao;

    public static void main(String[] args) {
		System.out.println(MD5Utils.encode("admin"));
	}
    /**
     * Description: <br>
     * 根据用户名称查询
     * 
     * @author Administrator<br>
     * @param username
     * @return <br>
     */
    public SysAdmin selectAdminByName(String account) {
        logger.debug("查询管理员，参数：account=" + account);
        SysAdmin admin = new SysAdmin();
        admin.setAccount(account);
        return adminDao.selectOne(admin);
    }

    /**
     * Description: <br>
     * 根据角色id查询菜单
     * 
     * @author Administrator<br>
     * @param roleId
     * @return <br>
     */
    public List<Map<String, Object>> selectRoleMenu(Integer roleId, Integer pId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleId);
        map.put("pId", pId);
        List<Map<String, Object>> list = null;
        if (roleId == -1) {
            // 超级管理员,查询所有列表
            list = adminDao.selectMenu(pId);
        } else {
            list = adminDao.selectRoleMenu(map);
        }
        return list;
    }

    /**
     * Description: <br>
     * 查询二级和三级菜单
     * 
     * @author Administrator<br>
     * @param roleId
     * @param pId
     * @return <br>
     */
    public List<Map<String, Object>> selectSecondMenu(Integer roleId, Integer pId) {
        List<Map<String, Object>> menuList = selectRoleMenu(roleId, pId);
        if (!StringUtil.listEmpty(menuList)) {
            for (Map<String, Object> map : menuList) {
                map.put("childList", selectRoleMenu(roleId, StringUtil.toInt(map.get("id"))));
            }
        }
        return menuList;
    }

    /**
     * Description: <br>
     * 角色列表查询
     * 
     * @param roleName
     * @param pageNo
     * @return <br>
     */
    public Pager<SysAdmin> selectAdminList(Integer pageNo, SysAdmin admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", admin.getUsername());
        map.put("account", admin.getAccount());
        map.put("roleId", admin.getRoleId());
        int total = adminDao.selectAdminCount(map);
        Pager<SysAdmin> pages = new Pager<SysAdmin>(total, pageNo);
        map.put("offset", pages.getOffset());
        map.put("size", pages.getLimit());
        pages.setList(adminDao.selectAdminList(map));
        return pages;
    }

    public SysAdmin selectByPrimaryKey(Integer id) {
        SysAdmin record = null;
        if (null != id) {
            record = adminDao.selectByPrimaryKey(id);
        }
        return record;
    }

    /**
     * 修改或保存角色
     */
    public String saveOrUpdate(SysAdmin record) {
        int count = 0;
        if (record == null) {
            count = -1;
        } else if (null == record.getId()) {
            record.setCreateTime(new Date());
            if (StringUtils.isEmpty(record.getPassword())) {
                // 设置默认密码
                record.setPassword(MD5Utils.encode("ntgame888"));
            }
            count = adminDao.insertSelective(record);
        } else {
            count = adminDao.updateByPrimaryKeySelective(record);
        }
        return count + "";
    }

    /**
     * 删除角色
     */
    public String delete(Integer id) {
        int count = 0;
        if (id == null) {
            count = -1;
        } else {
            count = adminDao.deleteByPrimaryKey(id);
        }
        return count + "";
    }
}
