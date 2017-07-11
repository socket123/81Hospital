package com.chusu.apps.dao.order;

import com.chusu.apps.model.order.Orders;
import com.chusu.apps.model.sys.SysAdmin;
import com.chusu.apps.service.order.OrderService;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrdersMapper extends Mapper<Orders> {



    /**
     * Description: <br>
     * 分页查询
     *
     * @param map {userName,account,roleId}
     * @return <br>
     */
    List<Orders> selectOrderList(Map<String, Object> map);

    /**
     * Description: <br>
     * 统计
     *
     * @param admin
     * @return <br>
     */
    int selectOrderCount(Map<String, Object> map);


    /**
     * Description: <br>
     * 医院物资 分页查询
     *
     * @param map {userName,account,roleId}
     * @return <br>
     */
    List<Orders> selectOrderListAll(Map<String, Object> map);


    /**
     *   查询  各科室 各种类 最新时间 的数据统计
     * @param map
     * @return
     */
    List<Orders> selectOrderListTimes(Map<String, Object> map);

    /**
     * Description: <br>
     * 医院物资 统计
     *
     * @param admin
     * @return <br>
     */
    int selectOrderListAllCount(Map<String, Object> map);

}