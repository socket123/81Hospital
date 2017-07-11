package com.chusu.apps.dao.sys;

import java.util.List;

import com.chusu.apps.model.sys.SysMenu;

import tk.mybatis.mapper.common.Mapper;

public interface SysMenuMapper extends Mapper<SysMenu> {
	 /**
     * Description: <br>
     * 根据上级id查询
     * 
     * @param pId
     * @return <br>
     */
    List<SysMenu> selectSysMenuByPid(Integer pId);
}