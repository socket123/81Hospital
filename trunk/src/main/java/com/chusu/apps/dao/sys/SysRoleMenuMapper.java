package com.chusu.apps.dao.sys;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.chusu.apps.model.sys.SysRoleMenu;

public interface SysRoleMenuMapper extends Mapper<SysRoleMenu> {
	
	List<Integer> selectByRoleId(Integer roleId);
}