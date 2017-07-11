package com.chusu.apps.controller.department;

import com.chusu.apps.config.Constants;
import com.chusu.apps.controller.BaseAction;
import com.chusu.apps.dao.department.DepartmentMapper;
import com.chusu.apps.model.department.Department;
import com.chusu.apps.model.sys.SysAdmin;
import com.chusu.apps.model.sys.SysRole;
import com.chusu.apps.service.department.DepartMentService;
import com.chusu.apps.service.sys.AdminService;
import com.chusu.apps.service.sys.SysRoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <Description> <br>
 *  部门管理
 * 
 * @author Administrator<br>
 * @CreateDate 2016年1月12日 <br>
 */
@Controller
@RequestMapping("/admin/department")
public class DepartmentController extends BaseAction {
    private final Logger logger = Logger.getLogger(DepartmentController.class);
    @Autowired
    DepartMentService  departMentService;





    /**
     * Description: <br>
     * 部门 列表 显示 界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/list")
    public String selectDepartmentList(ModelMap model, Integer currentIndex,Department department) {
        Department departments = new Department();
//        Department department = new Department();
//        department.setDepartmentName(departmentName);
        currentIndex = (null == currentIndex)? Constants.page.PAGE_NO:currentIndex;
        model.put("page", departMentService.selectPageListByWhere(currentIndex, Constants.page.PAGE_SIZE, departments));
        model.put("departmentName", department.getDepartmentName());
        return "admin/department/department_list";
    }

    /**
     * Description: <br>
     * 前往修改或新增部门界面
     *
     * @param model
     * @param id
     * @return <br>
     */
    @RequestMapping("/mergepage")
    public String add(ModelMap model, Integer id) {
        Department department = null;
        if (id != null) {
            department = departMentService.selectByPrimaryKey(id);
        }
        model.put("department", department);
        return "admin/department/department_edit";
    }

    /**
     * Description: <br>
     * 修改或新增部们
     *
     * @param model
     * @return <br>
     */
    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrUpdate(ModelMap model,  Department department ) {
        logger.info("部门 ");
        return departMentService.saveOrUpdate(department);
    }

    /**
     * Description: <br>
     * 删除 部门
     *
     * @param model
     * @param id
     * @return <br>
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Integer id) {

        return departMentService.delete(id);
    }


}
