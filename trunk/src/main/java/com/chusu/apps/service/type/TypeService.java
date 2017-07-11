package com.chusu.apps.service.type;

import com.chusu.apps.config.Constants;
import com.chusu.apps.dao.department.DepartmentMapper;
import com.chusu.apps.dao.type.TypeMapper;
import com.chusu.apps.model.department.Department;
import com.chusu.apps.model.order.Orders;
import com.chusu.apps.model.type.Type;
import com.chusu.apps.service.BaseService;
import com.chusu.platform.util.Pager;
import com.chusu.platform.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeService extends BaseService<Type> {

    private final Logger logger = Logger.getLogger(TypeService.class);


    @Autowired
    TypeMapper  typeMapper;












    /**
     * Description: <br>
     * 订单列表查询
     *
     * @param pageNo
     * @return <br>
     */
    public Pager<Type> selectTypetList(Integer pageNo, Department department,String name,Boolean falge) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(falge) {
            map.put("id", department.getId());
            if(StringUtil.isEmpty(name)){

            }else {
                map.put("name", "%"+name+"%");
            }

        }
        int total = typeMapper.sselectTypetListCount(map);
        Pager<Type> pages = new Pager<Type>(total, pageNo);
       if(falge){
           map.put("offset", pages.getOffset());
           map.put("size", pages.getLimit());
       }
        List<Type> typeList  =  typeMapper.selectTypetList(map);
        pages.setList(typeList);
        return pages;
    }


    /**
     * 修改或保存
     */
    public String saveOrUpdate(Type  type ) {
        int count = 0;
        if (type == null) {
            count = -1;
        } else if (null == type.getId()) {
            type.setCreateTime(new Date());
            logger.info("种类 新增");
            count = typeMapper.insertSelective(type);
        } else {
            logger.info("种类 修改");
            count = typeMapper.updateByPrimaryKeySelective(type);
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
            count = typeMapper.deleteByPrimaryKey(id);
        }
        return count + "";
    }
}
