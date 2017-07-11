package com.chusu.apps.dao.sys;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.chusu.apps.model.sys.SysAdmin;

public interface SysAdminMapper extends Mapper<SysAdmin> {
	/**
     * Description: <br>
     * 分页查询
     * 
     * @param map {userName,account,roleId}
     * @return <br>
     */
    List<SysAdmin> selectAdminList(Map<String, Object> map);

    /**
     * Description: <br>
     * 统计
     * 
     * @param admin
     * @return <br>
     */
    int selectAdminCount(Map<String, Object> map);

    /**
     * Description: <br>
     * 根据权限查询菜单列表
     * 
     * @author Administrator<br>
     * @param roleId
     * @return <br>
     */
    List<Map<String, Object>> selectRoleMenu(Map<String, Object> map);

    /**
     * Description: <br>
     * 查询所有菜单列表
     * 
     * @author Administrator<br>
     * @param pid
     * @return <br>
     */
    List<Map<String, Object>> selectMenu(Integer pid);
}