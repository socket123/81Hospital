package com.chusu.apps.service.department;

import com.chusu.apps.config.Constants;
import com.chusu.apps.dao.department.DepartmentMapper;
import com.chusu.apps.dao.sys.SysAdminMapper;
import com.chusu.apps.dao.sys.SysRoleMenuMapper;
import com.chusu.apps.dao.type.TypeMapper;
import com.chusu.apps.model.department.Department;
import com.chusu.apps.model.order.Orders;
import com.chusu.apps.model.sys.SysAdmin;
import com.chusu.apps.model.sys.SysRole;
import com.chusu.apps.model.type.Type;
import com.chusu.apps.service.BaseService;
import com.chusu.apps.service.type.TypeService;
import com.chusu.platform.util.MD5Utils;
import com.chusu.platform.util.Pager;
import com.chusu.platform.util.StringUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartMentService extends BaseService<Department> {

    private final Logger logger = Logger.getLogger(DepartMentService.class);

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    TypeMapper typeMapper;
//    /**
//     * Description: <br>
//     * 各科室 各种类 的数据统计  根据部门查询 可是物资 统计
//     *
//     * @param pageNo
//     * @return <br>
//     */
//    public PageInfo<Department> selectTypeistAll(Integer pageNo, Department department) {
//
//        PageInfo<Department>  pages = selectPageListByWhere(pageNo, Constants.page.PAGE_SIZE, department);
//
//        pages.setList( selectTypetList(pages.getList()));
//
//
//        return pages;
//    }
//
//
//    /**
//     *   获取 部门 各种类 数据 统计
//     * @param departmentLists
//     * @return
//     */
//    public  List<Department> selectTypetList(List<Department> departmentLists ){
//        Map<String, Object> map = new HashMap<String, Object>();
//        List<Department> departmentList = new ArrayList<Department>();
//        if(null != departmentLists && departmentLists.size() > 0){
//            for (Department department : departmentLists){
//                Department department1 = new Department();
//                List<Type> typeList = new ArrayList<Type>();
//                map.put("id",department.getId());
//                typeList =  typeMapper.selectTypetList(map);
//                if(null != typeList && typeList.size() > 0){
//                    department.setTypeList(typeList);
//                }
//                departmentList.add(department);
//            }
//        }
//        return  departmentList;
//    }

    /**
     * 修改或保存
     */
    public String saveOrUpdate(Department department ) {
        int count = 0;
        if (department == null) {
            count = -1;
        } else if (null == department.getId()) {
            department.setCreateTime(new Date());
            logger.info("部门 新增");
            count = departmentMapper.insertSelective(department);
        } else {
            logger.info("部门 修改");
            count = departmentMapper.updateByPrimaryKeySelective(department);
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
            count = departmentMapper.deleteByPrimaryKey(id);
        }
        return count + "";
    }
}
