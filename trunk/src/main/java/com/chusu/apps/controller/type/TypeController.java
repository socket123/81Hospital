package com.chusu.apps.controller.type;

import com.chusu.apps.config.Constants;
import com.chusu.apps.controller.BaseAction;
import com.chusu.apps.model.department.Department;
import com.chusu.apps.model.type.Type;
import com.chusu.apps.service.department.DepartMentService;
import com.chusu.apps.service.type.TypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <Description> <br>
 *  种类管理
 * 
 * @author Administrator<br>
 * @CreateDate 2016年1月12日 <br>
 */
@Controller
@RequestMapping("/admin/type")
public class TypeController extends BaseAction {
    private final Logger logger = Logger.getLogger(TypeController.class);
    @Autowired
    TypeService typeService;





    /**
     * Description: <br>
     * 部门 列表 显示 界面
     *
     * @param model
     * @param currentIndex

     * @return <br>
     */
    @RequestMapping("/list")
    public String selectDepartmentList(ModelMap model, Integer currentIndex,Type type) {
        Type type1 = new Type();
//        Department department = new Department();
//        department.setDepartmentName(departmentName);
        currentIndex = (null == currentIndex)? Constants.page.PAGE_NO:currentIndex;
        model.put("page", typeService.selectPageListByWhere(currentIndex, Constants.page.PAGE_SIZE, type1));
//        model.put("departmentName", type.getDepartmentName());
        return "admin/type/type_list";
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
        Type  type = null;
        if (id != null) {
            type = typeService.selectByPrimaryKey(id);
        }
        model.put("type", type);
        return "admin/type/type_edit";
    }



    /**
     * Description: <br>
     * 前往修改或新增部门 警告 界面
     *
     * @param model
     * @param id
     * @return <br>
     */
    @RequestMapping("/mergepage_Police")
    public String mergepage_Police(ModelMap model, Integer id) {
        Type  type = null;
        if (id != null) {
            type = typeService.selectByPrimaryKey(id);
        }
        model.put("type", type);
        return "admin/type/type_edit_police";
    }
    /**
     * Description: <br>
     * 修改或新增部们 警告
     *
     * @param model
     * @return <br>
     */
    @RequestMapping(value = "/merge_Police", method = RequestMethod.POST)
    @ResponseBody
    public String merge_Police(ModelMap model,  Type type ) {
        logger.info("部门 ");
        return typeService.saveOrUpdate(type);
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
    public String saveOrUpdate(ModelMap model,  Type type ) {
        logger.info("部门 ");
        return typeService.saveOrUpdate(type);
    }

    /**
     * Description: <br>
     * 删除 部门

     * @param id
     * @return <br>
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Integer id) {

        return typeService.delete(id);
    }


}
