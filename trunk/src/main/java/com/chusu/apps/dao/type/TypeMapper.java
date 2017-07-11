package com.chusu.apps.dao.type;

import com.chusu.apps.model.department.Department;
import com.chusu.apps.model.order.Orders;
import com.chusu.apps.model.type.Type;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TypeMapper extends Mapper<Type> {

    /**
     * Description: <br>
     * 科室 各种类 的数据统计  根据部门查询 可是物资 统计 分页查询
     *
     * @param map {userName,account,roleId}
     * @return <br>
     */
    List<Type> selectTypetList(Map<String, Object> map);

    /**
     * Description: <br>
     * 统计
     *
     * @param admin
     * @return <br>
     */
    int sselectTypetListCount(Map<String, Object> map);


}